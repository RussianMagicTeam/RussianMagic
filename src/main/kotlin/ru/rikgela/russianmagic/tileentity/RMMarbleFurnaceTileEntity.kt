package ru.rikgela.russianmagic.tileentity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslationTextComponent
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.container.RMMarbleFurnaceContainer
import ru.rikgela.russianmagic.init.RMTileEntityTypes
import ru.rikgela.russianmagic.mana.IManaReceiver

class RMMarbleFurnaceTileEntity(tileEntityTypeIn: TileEntityType<*> = RMTileEntityTypes.RM_MARBLE_FURNACE.get()) : AbstractRMFurnaceTileEntity(tileEntityTypeIn), ITickableTileEntity, INamedContainerProvider, IManaReceiver {

    override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
        return RMMarbleFurnaceContainer(windowID, playerInv, this)
    }

    val name: ITextComponent
        get() = customName ?: defaultName

    private val defaultName: ITextComponent
        get() = TranslationTextComponent("container.$MOD_ID.rm_marble_furnace")

    override fun getDisplayName(): ITextComponent {
        return name
    }
}