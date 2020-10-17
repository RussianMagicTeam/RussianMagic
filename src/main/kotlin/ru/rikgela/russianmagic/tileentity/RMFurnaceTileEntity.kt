package ru.rikgela.russianmagic.tileentity

import net.minecraft.client.Minecraft
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.crafting.IRecipeType
import net.minecraft.item.crafting.Ingredient
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SUpdateTileEntityPacket
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.Direction
import net.minecraft.util.NonNullList
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslationTextComponent
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.Constants
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.wrapper.RecipeWrapper
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.container.RMFurnaceContainer
import ru.rikgela.russianmagic.init.RMTileEntityTypes
import ru.rikgela.russianmagic.init.RecipeSerializerInit
import ru.rikgela.russianmagic.objects.blocks.RMFurnaceBlock
import ru.rikgela.russianmagic.recipes.RMRecipe
import ru.rikgela.russianmagic.util.RMItemHandler
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

class RMFurnaceTileEntity @JvmOverloads constructor(tileEntityTypeIn: TileEntityType<*>? = RMTileEntityTypes.RM_FURNACE.get()) : TileEntity(tileEntityTypeIn), ITickableTileEntity, INamedContainerProvider {
    var customName: ITextComponent? = null
    @JvmField
    var currentSmeltTime = 0

    @JvmField
    val maxSmeltTime = 100
    val inventory: RMItemHandler = RMItemHandler(2)
    override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container? {
        return RMFurnaceContainer(windowID, playerInv, this)
    }

    override fun tick() {
        var dirty = false
        if (world != null && !world!!.isRemote) {
            if (world!!.isBlockPowered(getPos())) {
                if (getRecipe(inventory.getStackInSlot(0)) != null) {
                    if (currentSmeltTime != maxSmeltTime) {
                        world!!.setBlockState(getPos(),
                                this.blockState.with(RMFurnaceBlock.LIT, true))
                        currentSmeltTime++
                        dirty = true
                    } else {
                        world!!.setBlockState(getPos(),
                                this.blockState.with(RMFurnaceBlock.LIT, false))
                        currentSmeltTime = 0
                        val output = getRecipe(inventory.getStackInSlot(0))!!.recipeOutput
                        inventory.insertItem(1, output.copy(), false)
                        inventory.decrStackSize(0, 1)
                        dirty = true
                    }
                }
            }
        }
        if (dirty) {
            markDirty()
            world!!.notifyBlockUpdate(getPos(), this.blockState, this.blockState,
                    Constants.BlockFlags.BLOCK_UPDATE)
        }
    }

    val name: ITextComponent?
        get() = if (customName != null) customName else defaultName

    private val defaultName: ITextComponent
        private get() = TranslationTextComponent("container.$MOD_ID.rm_furnace")

    override fun getDisplayName(): ITextComponent {
        return name!!
    }

    override fun read(compound: CompoundNBT) {
        super.read(compound)
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"))
        }
        val inv = NonNullList.withSize(inventory.slots, ItemStack.EMPTY)
        ItemStackHelper.loadAllItems(compound, inv)
        inventory.setNonNullList(inv)
        currentSmeltTime = compound.getInt("CurrentSmeltTime")
    }

    override fun write(compound: CompoundNBT): CompoundNBT {
        super.write(compound)
        if (customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(customName))
        }
        ItemStackHelper.saveAllItems(compound, inventory.toNonNullList())
        compound.putInt("CurrentSmeltTime", currentSmeltTime)
        return compound
    }

    private fun getRecipe(stack: ItemStack?): RMRecipe? {
        if (stack == null) {
            return null
        }
        val recipes = findRecipesByType(RecipeSerializerInit.RM_TYPE, world)
        for (iRecipe in recipes) {
            val recipe = iRecipe as RMRecipe
            if (recipe.matches(RecipeWrapper(inventory), this.world!!)) {
                return recipe
            }
        }
        return null
    }

    fun getInventory(): IItemHandlerModifiable {
        return inventory
    }

    override fun getUpdatePacket(): SUpdateTileEntityPacket? {
        val nbt = CompoundNBT()
        write(nbt)
        return SUpdateTileEntityPacket(pos, 0, nbt)
    }

    override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) {
        read(pkt.nbtCompound)
    }

    override fun getUpdateTag(): CompoundNBT {
        val nbt = CompoundNBT()
        write(nbt)
        return nbt
    }

    override fun handleUpdateTag(nbt: CompoundNBT) {
        read(nbt)
    }

    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of { inventory as IItemHandler })
    }

    companion object {
        fun findRecipesByType(typeIn: IRecipeType<*>, world: World?): Set<IRecipe<*>> {
            return if (world != null) world.recipeManager.recipes.stream()
                    .filter { recipe: IRecipe<*> -> recipe.type === typeIn }.collect(Collectors.toSet()) else emptySet()
        }

        @OnlyIn(Dist.CLIENT)
        fun findRecipesByType(typeIn: IRecipeType<*>): Set<IRecipe<*>> {
            val world = Minecraft.getInstance().world
            return if (world != null) world.recipeManager.recipes.stream()
                    .filter { recipe: IRecipe<*> -> recipe.type === typeIn }.collect(Collectors.toSet()) else emptySet()
        }

        fun getAllRecipeInputs(typeIn: IRecipeType<*>, worldIn: World): Set<ItemStack> {
            val inputs: MutableSet<ItemStack> = HashSet()
            val recipes = findRecipesByType(typeIn, worldIn)
            for (recipe in recipes) {
                val ingredients = recipe.ingredients
                ingredients.forEach(Consumer { ingredient: Ingredient ->
                    for (stack in ingredient.matchingStacks) {
                        inputs.add(stack)
                    }
                })
            }
            return inputs
        }
    }
    init {
        //inventory = RMItemHandler(2)
    }
}