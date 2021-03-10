package ru.rikgela.russianmagic.tileentity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntityType
import ru.rikgela.russianmagic.init.RMTileEntityTypes
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.util.RMMekanism

object RMMagicSourcesTileEntity {
    class RMBasicMagicSourceTileEntity(rmMekanism: RMMekanism,
                                       tileEntityTypeIn: TileEntityType<*> =
                                               RMTileEntityTypes.RM_DIAMOND_FURNACE.get())
        : AbstractRMMagicSourceTileEntity(tileEntityTypeIn, rmMekanism),
            ITickableTileEntity, IManaReceiver {
        fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity) {
            //return //RMFurnacesContainer.RMOneSupportOneToOneFurnaceContainer(windowID, playerInv, this)
        }
    }
}