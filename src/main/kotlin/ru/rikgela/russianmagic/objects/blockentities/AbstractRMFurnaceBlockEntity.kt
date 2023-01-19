package ru.rikgela.russianmagic.objects.blockentities

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.util.Mth.ceil
import net.minecraft.world.Containers
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler
import net.minecraftforge.items.wrapper.RangedWrapper
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.network.RMMessages
import ru.rikgela.russianmagic.network.packet.ItemStackSyncS2CPacket
import ru.rikgela.russianmagic.network.packet.ManaSyncS2CPacket
import ru.rikgela.russianmagic.objects.blocks.AbstractRMFurnace
import ru.rikgela.russianmagic.objects.blocks.AbstractRMFurnace.Companion.LIT
import ru.rikgela.russianmagic.objects.blocks.RMMekanism
import ru.rikgela.russianmagic.objects.mana.IManaReceiver
import ru.rikgela.russianmagic.objects.mana.IManaTaker
import ru.rikgela.russianmagic.objects.mana.Mana
import ru.rikgela.russianmagic.objects.mana.transfer.ManaReceiver
import ru.rikgela.russianmagic.objects.mana.transfer.ManaTaker


abstract class AbstractRMFurnaceBlockEntity(
    BlockEntityTypeIn: BlockEntityType<*>,
    blockPos: BlockPos,
    blockState: BlockState,
    private val rmMekanism: RMMekanism
) : BlockEntity(BlockEntityTypeIn, blockPos, blockState), IManaReceiver, IManaTaker, MenuProvider, IItemHandler {

    var customName: Component? = null
    var progress = 0
    protected val mana: Mana = object : Mana(rmMekanism.tier * 100, rmMekanism.tier * 1000) {
        override fun onContentsChanged(){
            setChanged()
            if (!level!!.isClientSide()) {
                RMMessages.sendToClients(ManaSyncS2CPacket(this, worldPosition))
            }
        }
    }
    private val manaReceiver: IManaReceiver = ManaReceiver(mana)
    private val manaTaker: ManaTaker = ManaTaker()
    val maxSmeltTime = 100 / rmMekanism.tier
    override val rate: Float
        get() = manaTaker.getRate(this.blockPos, 0.1F * this.rmMekanism.tier)

    val name: Component
        get() = customName ?: defaultName

    private val defaultName: MutableComponent
        get() = Component.translatable("container.$MOD_ID.${rmMekanism.name}")

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

    override fun getDisplayName(): Component {
        return name
    }

    protected val inventory: ItemStackHandler = object : ItemStackHandler(
        rmMekanism.inputSlots + rmMekanism.outputSlots + rmMekanism.supportSlots
    ) {
        override fun onContentsChanged(slot: Int) {
            setChanged()
            if (!level!!.isClientSide()) {
                RMMessages.sendToClients(ItemStackSyncS2CPacket(this, worldPosition))
            }
        }
    }

    override fun getSlots(): Int {
        return this.inventory.slots
    }

    override fun getStackInSlot(slot: Int): ItemStack {
        return this.inventory.getStackInSlot(slot)
    }

    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
        return this.inventory.insertItem(slot, stack, simulate)
    }

    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
        return this.extractItem(slot, amount, simulate)
    }

    override fun getSlotLimit(slot: Int): Int {
        return this.inventory.getSlotLimit(slot)
    }

    override fun isItemValid(slot: Int, stack: ItemStack): Boolean {
        return this.inventory.isItemValid(slot, stack)
    }
    private val inputSlots = RangedWrapper(
        inventory,
        0,
        rmMekanism.inputSlots
    )
    private val outputSlots = RangedWrapper(
        inventory,
        rmMekanism.inputSlots,
        rmMekanism.inputSlots + rmMekanism.outputSlots
    )
    private val supportSlots = RangedWrapper(
        inventory,
        rmMekanism.inputSlots + rmMekanism.outputSlots,
        rmMekanism.inputSlots + rmMekanism.outputSlots + rmMekanism.supportSlots
    )

    private var inventorySlotsProvider = LazyOptional.of { this.inventory }
    private var inputSlotsProvider = LazyOptional.of { this.inputSlots }
    private var outputSlotsProvider = LazyOptional.of { this.outputSlots }
    private var supportSlotsProvider = LazyOptional.of { this.supportSlots }
    override fun invalidateCaps() {
        super.invalidateCaps()
        inventorySlotsProvider.invalidate()
        inputSlotsProvider.invalidate()
        outputSlotsProvider.invalidate()
        supportSlotsProvider.invalidate()
    }

    override fun onLoad() {
        super.onLoad()
//        lazyItemHandler = LazyOptional.of<Any> { inventory }
        inventorySlotsProvider = LazyOptional.of { this.inventory }
        inputSlotsProvider = LazyOptional.of { this.inputSlots }
        outputSlotsProvider = LazyOptional.of { this.outputSlots }
        supportSlotsProvider = LazyOptional.of { this.supportSlots }
//        lazyEnergyHandler = LazyOptional.of<Any> { ENERGY_STORAGE }
//        lazyFluidHandler = LazyOptional.of<Any> { FLUID_TANK }
    }


    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (!this.isRemoved && cap === ForgeCapabilities.ITEM_HANDLER){
            when (side) {
                null -> inventorySlotsProvider.cast()
                Direction.UP -> inputSlotsProvider.cast()
                Direction.DOWN -> outputSlotsProvider.cast()
                else -> supportSlotsProvider.cast()
            }
        } else super.getCapability(cap, side)
    }

    protected open fun markDirty() {
        if (level != null && level!!.hasChunkAt(blockPos)) {
            level!!.sendBlockUpdated(blockPos, blockState, blockState, 2)
            level!!.getChunkAt(blockPos).isUnsaved = true
        }
    }

    override fun setChanged() {
        if (level != null) {
            markDirty()
            val state = blockState
            if (state.hasAnalogOutputSignal()) level!!.updateNeighbourForOutputSignal(worldPosition, state.block)
        }
    }

    fun update() {
        setChanged()
        if (level != null && level?.isClientSide != true) {
            level!!.sendBlockUpdated(blockPos, this.blockState, this.blockState, 2)
        }
    }

    private fun dropProgress() {
        progress = 0
        level!!.setBlockAndUpdate(blockPos, this.blockState.setValue(AbstractRMFurnace.LIT, false))
        update()
    }

    private fun canBurn(recipe: Recipe<*>): Boolean {
        return mana.currentMana >= 10 &&
                recipe.type in rmMekanism.recipe_types &&
                (inventory.getStackInSlot(1).count == 0
                        || (inventory.getStackInSlot(1).item == (recipe.resultItem.item)
                        && inventory.getStackInSlot(1).count + recipe.resultItem.count <= inventory.getStackInSlot(1).maxStackSize))
    }

    open fun drops() {
        val simpleContainer = SimpleContainer(inventory.slots)
        for (i in 0 until inventory.slots) {
            simpleContainer.setItem(i, inventory.getStackInSlot(i))
        }
        Containers.dropContents(level!!, worldPosition, simpleContainer)
    }

    companion object{
        fun tick(level: Level, blockPos: BlockPos, blockState: BlockState, blockEntity: AbstractRMFurnaceBlockEntity) {
            if (!level.isClientSide) {
                if (blockEntity.mana.baseMaxMana != blockEntity.mana.currentMana) {
                    val diffMana = blockEntity.mana.baseMaxMana - blockEntity.mana.currentMana
                    val manaToRequest = diffMana / blockEntity.manaTaker.getRate(blockPos, 1F)
                    if (manaToRequest > 0){
                        blockEntity.mana.fill(blockEntity.manaTaker.getMana(ceil(manaToRequest), level, blockPos, 1F))
                    }
                    else{
                        val points = Integer.max(((blockEntity.currentMana - blockEntity.mana.baseMaxMana) * (3F/20F)).toInt(), 1)
                        blockEntity.mana.consume(points)
                    }
                }
                val recipe = blockEntity.getRecipe(blockEntity.inventory.getStackInSlot(0)) ?: return blockEntity.dropProgress()
                if (blockEntity.canBurn(recipe)) {
                    level.setBlockAndUpdate(blockPos, blockState.setValue(LIT, true))
                    if (blockEntity.progress < blockEntity.maxSmeltTime) {
                        blockEntity.progress++
                        blockEntity.update()
                    } else {
                        if (blockEntity.mana.consume(10)) {
                            blockEntity.progress = 0
                            val output = recipe.resultItem
                            blockEntity.inventory.insertItem(1, output.copy(), false)
                            blockEntity.inventory.extractItem(0, 1, false)
                            blockEntity.update()
                        } else {
                            blockEntity.dropProgress()
                        }
                    }
                } else {
                    blockEntity.dropProgress()
                }
            }
        }
    }


    private inline fun <reified T> typeCast(any: Any?): T = any as T
    private fun getRecipe(stack: ItemStack?): Recipe<*>? {
        if (stack == ItemStack.EMPTY) {
            return null
        }
        for(recipe_type in rmMekanism.recipe_types)
        {
            val simpleContainer = SimpleContainer(stack)
            val recipe = level!!.recipeManager.getRecipeFor(typeCast(recipe_type), simpleContainer, level!!)
            if (!recipe.isEmpty)
                return recipe.get()
        }
        return null
    }

    protected var data: ContainerData = object : ContainerData {
        override fun get(index: Int): Int {
            return when (index) {
                0 -> progress
                1 -> maxSmeltTime
                else -> 0
            }
        }

        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> progress = value
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }

    fun setItemStackHandler(itemStackHandler: ItemStackHandler) {
        for (i in 0 until itemStackHandler.slots) {
            inventory.setStackInSlot(i, itemStackHandler.getStackInSlot(i))
        }
    }

    fun setManaHandler(mana: Mana) {
        this.mana.copy(mana)
    }
    
    fun getRenderStack(): ItemStack {
        val stack: ItemStack = if (!inventory.getStackInSlot(2).isEmpty()) {
            inventory.getStackInSlot(2)
        } else {
            inventory.getStackInSlot(1)
        }
        return stack
    }

    fun stillValid(player: Player): Boolean {
        return if (level!!.getBlockEntity(worldPosition) !== this) {
            false
        } else {
            player.distanceToSqr(
                worldPosition.x.toDouble() + 0.5,
                worldPosition.y.toDouble() + 0.5,
                worldPosition.z.toDouble() + 0.5
            ) <= 64.0
        }
    }

    override fun connectToManaSpreader(
        manaSpreaderPos: BlockPos,
        levelManaSpreader: Level
    ) {
        manaTaker.connectToManaSpreader(manaSpreaderPos, levelManaSpreader)
    }

    override fun load(nbt: CompoundTag) {
        super.load(nbt)
        this.progress = nbt.getInt("Progress")
        this.inventory.deserializeNBT(nbt.getCompound("Inventory"))
        this.mana.deserializeNBT(nbt.getCompound("Mana"))

//            RMMessages.sendToClients(ItemStackSyncS2CPacket(this, worldPosition))
    }

    override fun saveAdditional(nbt: CompoundTag) {
        nbt.putInt("Progress", this.progress)
        nbt.put("Inventory", inventory.serializeNBT())
        nbt.put("Mana", mana.serializeNBT())
        super.saveAdditional(nbt)
    }
}
