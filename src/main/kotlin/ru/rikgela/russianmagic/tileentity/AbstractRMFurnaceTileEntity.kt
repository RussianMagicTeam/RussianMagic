package ru.rikgela.russianmagic.tileentity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipe
import net.minecraft.item.crafting.IRecipe
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
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.Constants
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.wrapper.RecipeWrapper
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.container.AbstractRMFurnaceContainer
import ru.rikgela.russianmagic.container.RMMarbleFurnaceContainer
import ru.rikgela.russianmagic.init.RMTileEntityTypes
import ru.rikgela.russianmagic.mana.IMana
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.mana.Mana
import ru.rikgela.russianmagic.mana.ManaReceiver
import ru.rikgela.russianmagic.objects.blocks.AbstractRMFurnace
import ru.rikgela.russianmagic.util.RMItemHandler
import ru.rikgela.russianmagic.util.RMMekanism
import java.util.stream.Collectors

abstract class AbstractRMFurnaceTileEntity(tileEntityTypeIn: TileEntityType<*>, val rmMekanism: RMMekanism) : TileEntity(tileEntityTypeIn), ITickableTileEntity, INamedContainerProvider, IManaReceiver {
    var customName: ITextComponent? = null
    var currentSmeltTime = 0

    private val mana: IMana = Mana.withParams(rmMekanism.tier*100, rmMekanism.tier*1000)
    private val manaReceiver: IManaReceiver = ManaReceiver(mana)
    val maxSmeltTime = 100/rmMekanism.tier
    val inventory: RMItemHandler = RMItemHandler(2)

    val name: ITextComponent
        get() = customName ?: defaultName

    private val defaultName: ITextComponent
        get() = TranslationTextComponent("container.$MOD_ID.${rmMekanism.name}")

    override fun getDisplayName(): ITextComponent {
        return name
    }

    fun update() {
        markDirty()
        if (world != null && world?.isRemote != true) {
            world!!.notifyBlockUpdate(getPos(), this.blockState, this.blockState,
                    Constants.BlockFlags.BLOCK_UPDATE)
        }
    }

    private fun dropProgress() {
        currentSmeltTime = 0
        world!!.setBlockState(getPos(), this.blockState.with(AbstractRMFurnace.LIT, false))
        update()
    }

    fun canBurn(recipe: FurnaceRecipe): Boolean {
        return mana.currentMana >= 10 &&
                (inventory.getStackInSlot(1).count == 0
                        || (inventory.getStackInSlot(1).item == recipe.recipeOutput.item
                        && inventory.getStackInSlot(1).count + recipe.recipeOutput.count <= inventory.getStackInSlot(1).maxStackSize))
    }

    override fun tick() {
        if (world?.isRemote == false) {
            val recipe = getRecipe(inventory.getStackInSlot(0)) ?: return dropProgress()
            if (canBurn(recipe)) {
                world!!.setBlockState(getPos(), this.blockState.with(AbstractRMFurnace.LIT, true))
                if (currentSmeltTime < maxSmeltTime) {
                    currentSmeltTime++
                    update()
                } else {
                    if (mana.consume(10)) {
                        currentSmeltTime = 0
                        val output = recipe.recipeOutput
                        inventory.insertItem(1, output.copy(), false)
                        inventory.decrStackSize(0, 1)
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


    override fun read(compound: CompoundNBT) {
        super.read(compound)
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"))
        }
        mana.loadFromByteArray(compound.getByteArray("Mana"))
        val inv = NonNullList.withSize(inventory.slots, ItemStack.EMPTY)
        ItemStackHelper.loadAllItems(compound, inv)
        inventory.setNonNullList(inv)
        currentSmeltTime = compound.getInt("CurrentSmeltTime")
    }

    override fun write(compound: CompoundNBT): CompoundNBT {
        super.write(compound)
        compound.putByteArray("Mana", mana.toByteArray())
        if (customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(customName!!))
        }
        ItemStackHelper.saveAllItems(compound, inventory.toNonNullList())
        compound.putInt("CurrentSmeltTime", currentSmeltTime)
        return compound
    }

    private fun getRecipe(stack: ItemStack?): FurnaceRecipe? {
        if (stack == null) {
            return null
        }
        for(recipe_shape: String in rmMekanism.recipe_shape)
        {
            val recipes = findRecipesByType(recipe_shape, world)
            for (iRecipe in recipes) {
                val recipe = iRecipe as FurnaceRecipe
                if (recipe.matches(RecipeWrapper(inventory), this.world!!)) {
                    return recipe
                }
            }
        }
        return null
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
        fun findRecipesByType(typeIn: String, world: World?): Set<IRecipe<*>> {
            return if (world != null) world.recipeManager.recipes.stream()
                    .filter { recipe: IRecipe<*> -> recipe.type.toString() == typeIn }
                    .collect(Collectors.toSet()) else emptySet()
        }
    }

    //IManaReceiver implementation
    override val currentMana: Int
        get() = manaReceiver.currentMana
    override val maxMana: Int
        get() = manaReceiver.maxMana
    override val maxTransfer: Int
        get() = manaReceiver.maxTransfer

    override fun transfer(points: Int): Int {
        val ret = manaReceiver.transfer(points)
        if (ret != points) update()
        return ret
    }
}