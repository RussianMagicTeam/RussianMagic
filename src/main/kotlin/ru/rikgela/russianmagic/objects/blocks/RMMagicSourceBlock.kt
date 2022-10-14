package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.BlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.world.IBlockReader
import net.minecraftforge.fml.RegistryObject
import ru.rikgela.russianmagic.objects.tileentity.RMMagicSourcesTileEntity

object RMMagicSourceBlock {
    class RMBasicMagicSource(properties: Properties,
                             private val registryObject
                             : RegistryObject<TileEntityType<RMMagicSourcesTileEntity.RMBasicMagicSourceTileEntity>>)
        : AbstractRMMagicSourceBlock(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }
}