package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.BlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.IBlockReader
import ru.rikgela.russianmagic.init.RMTileEntityTypes

class RMEbonyFurnaceBlock(properties: Properties) : AbstractRMFurnace(properties) {

    override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
        return RMTileEntityTypes.RM_EBONY_FURNACE.get().create()!!
    }
}