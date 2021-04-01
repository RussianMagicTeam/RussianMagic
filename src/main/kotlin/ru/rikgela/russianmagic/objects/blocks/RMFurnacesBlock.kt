package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.BlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.world.IBlockReader
import net.minecraftforge.fml.RegistryObject
import ru.rikgela.russianmagic.objects.tileentity.RMFurnacesTileEntity

object RMFurnacesBlock {
    class RMDiamondFurnaceBlock(properties: Properties,
                                val registryObject
                                : RegistryObject<TileEntityType<RMFurnacesTileEntity.RMDiamondFurnaceTileEntity>>)
        : AbstractRMFurnace(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }

    class RMIsolatedDiamondFurnaceBlock(properties: Properties,
                                        val registryObject
                                        : RegistryObject<TileEntityType<RMFurnacesTileEntity.RMIsolatedDiamondFurnaceTileEntity>>)
        : AbstractRMFurnace(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }

    class RMEbonyFurnaceBlock(properties: Properties,
                              val registryObject
                              : RegistryObject<TileEntityType<RMFurnacesTileEntity.RMEbonyFurnaceTileEntity>>)
        : AbstractRMFurnace(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }

    class RMMarbleFurnaceBlock(properties: Properties,
                               val registryObject
                               : RegistryObject<TileEntityType<RMFurnacesTileEntity.RMMarbleFurnaceTileEntity>>)
        : AbstractRMFurnace(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }

    class RMWhiteJadeFurnaceBlock(properties: Properties,
                                  val registryObject
                                  : RegistryObject<TileEntityType<RMFurnacesTileEntity.RMWhiteJadeFurnaceTileEntity>>)
        : AbstractRMFurnace(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }

    class RMRhinestoneFurnaceBlock(properties: Properties,
                                   val registryObject
                                   : RegistryObject<TileEntityType<RMFurnacesTileEntity.RMRhinestoneFurnaceTileEntity>>)
        : AbstractRMFurnace(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }

    class RMAquamarineFurnaceBlock(properties: Properties,
                                   val registryObject
                                   : RegistryObject<TileEntityType<RMFurnacesTileEntity.RMAquamarineFurnaceTileEntity>>)
        : AbstractRMFurnace(properties) {
        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
            return registryObject.get().create()!!
        }
    }
}