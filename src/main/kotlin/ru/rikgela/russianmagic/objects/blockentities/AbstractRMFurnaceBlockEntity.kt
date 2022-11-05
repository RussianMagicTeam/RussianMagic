package ru.rikgela.russianmagic.objects.blockentities

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.util.Mth.ceil
import net.minecraft.world.MenuProvider
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.common.util.NonNullSupplier
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemStackHandler
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.blocks.AbstractRMFurnace
import ru.rikgela.russianmagic.objects.blocks.RMMekanism
import ru.rikgela.russianmagic.objects.mana.IMana
import ru.rikgela.russianmagic.objects.mana.IManaReceiver
import ru.rikgela.russianmagic.objects.mana.IManaTaker
import ru.rikgela.russianmagic.objects.mana.Mana
import ru.rikgela.russianmagic.objects.mana.transfer.ManaReceiver
import ru.rikgela.russianmagic.objects.mana.transfer.ManaTaker
import java.util.Collections.emptySet
import java.util.stream.Collectors


abstract class AbstractRMFurnaceBlockEntity(
    BlockEntityTypeIn: BlockEntityType<*>,
    blockPos: BlockPos,
    blockState: BlockState,
    private val rmMekanism: RMMekanism
) : BlockEntity(BlockEntityTypeIn, blockPos, blockState), IManaReceiver, IManaTaker, MenuProvider {

    var customName: MutableComponent? = null
    var currentSmeltTime = 0
    private val mana: IMana = Mana.withParams(rmMekanism.tier * 100, rmMekanism.tier * 1000)
    private val manaReceiver: IManaReceiver = ManaReceiver(mana)
    private val manaTaker: ManaTaker = ManaTaker()
    val maxSmeltTime = 100 / rmMekanism.tier
    abstract val upSlots: IntArray
    abstract val downSlots: IntArray
    abstract val horizontalSlots: IntArray
//    val inventory: RMItemHandler = RMItemHandler(2 + rmMekanism.supportSlots)

    val name: MutableComponent
        get() = customName ?: defaultName

    override fun getRate(manaConsumer: BlockPos, sensitivity: Float): Float = manaTaker.getRate(manaConsumer, sensitivity)

    private val defaultName: MutableComponent
        get() = Component.literal("container.$MOD_ID.${rmMekanism.name}")

    override val isConnectedToManaSpreader: Boolean
        get() = manaTaker.isConnectedToManaSpreader

    override fun disconnectToManaSpreader() {
        manaTaker.disconnectToManaSpreader()
    }

    override val spreaderWorldPos: String
        get() = manaTaker.spreaderWorldPos

    //IManaReceiver implementation
    override val currentMana: Int
        get() = manaReceiver.currentMana
    override val baseMaxMana: Int
        get() = manaReceiver.baseMaxMana
    override val maxTransfer: Int
        get() = manaReceiver.maxTransfer

    override fun getMana(
        points: Int,
        levelManaSpreader: Level,
        manaConsumer: BlockPos,
        sensitivity: Float
    ): Int{
        return manaTaker.getMana(points, levelManaSpreader, manaConsumer, sensitivity)
    }

    override fun transfer(points: Int) {
        return manaReceiver.transfer(points)
    }

    override fun getDisplayName(): MutableComponent {
        return name
    }

    private val inventory = ItemStackHandler(3)
    private val optional = LazyOptional.of(NonNullSupplier<IItemHandlerModifiable> { this.inventory})
    override fun invalidateCaps() {
        this.optional.invalidate()
    }

    override fun load(nbt: CompoundTag) {
        super.load(nbt)
        this.inventory.deserializeNBT(nbt.getCompound("Inventory"))
        if (nbt.contains("CustomName")) {
            customName = Component.literal(nbt.getString("CustomName"))
        }
        mana.deserializeNBT(nbt.getCompound("Mana"))
        manaTaker.deserializeNBT(nbt.getCompound("ManaTaker"))
        currentSmeltTime = nbt.getInt("CurrentSmeltTime")
    }

    override fun saveAdditional(nbt: CompoundTag) {
        nbt.put("Inventory", this.inventory.serializeNBT())
        nbt.put("Mana", mana.serializeNBT())
        nbt.put("ManaTaker", manaTaker.serializeNBT())
        if (customName != null) {
            nbt.putString("CustomName", customName.toString())
        }
        nbt.putInt("CurrentSmeltTime", currentSmeltTime)
        super.saveAdditional(nbt)
    }

    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap === ForgeCapabilities.ITEM_HANDLER) optional.cast() else super.getCapability(cap, side)
    }

    private fun update() {
        setChanged()
//        markDirty()
        if (level != null && level?.isClientSide != true) {
            level!!.sendBlockUpdated(blockPos, this.blockState, this.blockState, 0)
        }
    }

    private fun dropProgress() {
        currentSmeltTime = 0
        level!!.setBlockAndUpdate(blockPos, this.blockState.setValue(AbstractRMFurnace.LIT, false))
        update()
    }

    private fun canBurn(recipe: Recipe<*>): Boolean {
        return mana.currentMana >= 10 &&
                recipe.type in rmMekanism.recipe_types &&
                (inventory.getStackInSlot(1).count == 0
                        || (inventory.getStackInSlot(1) == recipe.resultItem
                        && inventory.getStackInSlot(1).count + recipe.resultItem.count <= inventory.getStackInSlot(1).maxStackSize))
    }

    fun tick(level: Level, blockPos: BlockPos, blockState: BlockState, blockEntity: AbstractRMFurnaceBlockEntity) {
        if (!level.isClientSide) {
            if (mana.baseMaxMana != mana.currentMana) {
                val diffMana = mana.baseMaxMana - mana.currentMana
                val manaToRequest = diffMana / manaTaker.getRate(blockPos, 1F)
                if (diffMana > 0){
                    this.mana.fill(manaTaker.getMana(ceil(manaToRequest), level, blockPos, 1F))
                }
                else{
                    val points = Integer.max(((currentMana - mana.baseMaxMana) * (3F/20F)).toInt(), 1)
                    this.mana.consume(points)
                }
            }
            val recipe = getRecipe(inventory.getStackInSlot(0)) ?: return dropProgress()
            if (canBurn(recipe)) {
                level.setBlockAndUpdate(blockPos, this.blockState.setValue(AbstractRMFurnace.LIT, true))
                if (currentSmeltTime < maxSmeltTime) {
                    currentSmeltTime++
                    update()
                } else {
                    if (mana.consume(10)) {
                        currentSmeltTime = 0
                        val output = recipe.resultItem
                        inventory.insertItem(1, output.copy(), false)
//                        inventory.decrStackSize(0, 1)
                        update()
                    } else {
                        dropProgress()
                    }
                }
            } else {
                dropProgress()
            }
        }
    }

    private fun getRecipe(stack: ItemStack?): Recipe<*>? {
        if (stack == null) {
            return null
        }
        for(recipe_type in rmMekanism.recipe_types)
        {
            val recipes = findRecipesByType(recipe_type, level)
            for (recipe in recipes) {
                if (level?.recipeManager?.recipes?.contains(recipe)!!){
                    return recipe
                }
            }
        }
        return null
    }

    protected var data: ContainerData = object : ContainerData {
        override fun get(index: Int): Int {
            return when (index) {
                0 -> currentSmeltTime
                1 -> maxSmeltTime
                else -> 0
            }
        }

        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> currentSmeltTime = value
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }

    //
//    override fun getUpdatePacket(): SUpdateTileEntityPacket? {
//        val nbt = CompoundNBT()
//        write(nbt)
//        return SUpdateTileEntityPacket(pos, 0, nbt)
//    }
//
//    override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) {
//        read(pkt.nbtCompound)
//    }
//
//    override fun getUpdateTag(): CompoundNBT {
//        val nbt = CompoundNBT()
//        write(nbt)
//        return nbt
//    }
//
//    override fun handleUpdateTag(nbt: CompoundNBT) {
//        read(nbt)
//    }
//
    companion object {
        fun findRecipesByType(typeIn: RecipeType<*>, level: Level?): Set<Recipe<*>> {
            return if (level != null) level.recipeManager.recipes.stream()
                    .filter { recipe: Recipe<*> -> recipe.type == typeIn }
                    .collect(Collectors.toSet()) else emptySet()
        }
    }
//
//    override fun getSlotsForFace(side: Direction): IntArray? {
//        return if (side == Direction.DOWN) {
//            downSlots
//        } else {
//            if (side == Direction.UP) upSlots else horizontalSlots
//        }
//    }
//
//    override fun getStackInSlot(index: Int): ItemStack {
//        return this.inventory.get(index)
//    }
//
//    override fun decrStackSize(index: Int, count: Int): ItemStack? {
//        return ItemStackHelper.getAndSplit(this.inventory.getList(), index, count)
//    }
//
//    override fun getSizeInventory(): Int {
//        return inventory.size
//    }
//
//    override fun isEmpty(): Boolean {
//        return this.inventory.isEmpty
//    }
//
//    override fun canInsertItem(index: Int, itemStackIn: ItemStack, direction: Direction?): Boolean {
//        return isItemValidForSlot(index, itemStackIn)
//    }
//
//    override fun isUsableByPlayer(player: PlayerEntity): Boolean {
//        return if (world!!.getTileEntity(pos) !== this) {
//            false
//        } else {
//            player.getDistanceSq(pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5) <= 64.0
//        }
//    }
//
//    override fun canExtractItem(index: Int, stack: ItemStack, direction: Direction): Boolean {
//        if (direction == Direction.DOWN && index == 1) {
//            return true
//        }
//        return false
//    }
//
//    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
//        this.inventory.set(index, stack)
//        if (stack.count > this.inventoryStackLimit) {
//            stack.count = this.inventoryStackLimit
//        }
//    }
//
//    override fun removeStackFromSlot(index: Int): ItemStack {
//        return inventory.getAndRemove(this.inventory.getList(), index)
//    }
//
//    override fun clear() {
//        return this.inventory.clear()
//    }
//
//    override fun isItemValidForSlot(index: Int, stack: ItemStack?): Boolean {
//        return index in upSlots || index in horizontalSlots
//    }
//
//    override fun transfer(points: Int) {
//        manaReceiver.transfer(points)
//        update()
//    }
//
    override fun connectToManaSpreader(
        manaSpreaderPos: BlockPos,
        levelManaSpreader: Level
    ) {
        manaTaker.connectToManaSpreader(manaSpreaderPos, levelManaSpreader)
    }

}
