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
import ru.rikgela.russianmagic.container.RMMarbleFurnaceContainer
import ru.rikgela.russianmagic.init.RMTileEntityTypes
import ru.rikgela.russianmagic.mana.IMana
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.mana.Mana
import ru.rikgela.russianmagic.mana.ManaReceiver
import ru.rikgela.russianmagic.objects.blocks.AbstractRMFurnace
import ru.rikgela.russianmagic.objects.blocks.AbstractRMFurnace.Companion.LIT
import ru.rikgela.russianmagic.objects.blocks.RMMarbleFurnaceBlock
import ru.rikgela.russianmagic.util.RMItemHandler
import java.util.stream.Collectors

class RMMarbleFurnaceTileEntity(tileEntityTypeIn: TileEntityType<*> = RMTileEntityTypes.RM_MARBLE_FURNACE.get()) : AbstractRMFurnaceTileEntity(tileEntityTypeIn), ITickableTileEntity, INamedContainerProvider, IManaReceiver {

    override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
        return RMMarbleFurnaceContainer(windowID, playerInv, this)
    }

    private fun dropProgress() {
        currentSmeltTime = 0
        world!!.setBlockState(getPos(), this.blockState.with(AbstractRMFurnace.LIT, false))
        update()
    }

    val name: ITextComponent
        get() = customName ?: defaultName

    private val defaultName: ITextComponent
        get() = TranslationTextComponent("container.$MOD_ID.rm_marble_furnace")

    override fun getDisplayName(): ITextComponent {
        return name
    }
}