package ru.rikgela.russianmagic.tileentity

import net.minecraft.tileentity.TileEntityType
import ru.rikgela.russianmagic.init.RMTileEntityTypes

object RMMagicSourcesTileEntity {
    class RMBasicMagicSourceTileEntity(tileEntityTypeIn: TileEntityType<*> =
                                               RMTileEntityTypes.RM_BASIC_MAGIC_SOURCE.get())
        : AbstractRMMagicSourceTileEntity(tileEntityTypeIn)
}