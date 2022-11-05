package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import ru.rikgela.russianmagic.init.RMBlockEntityTypes


object RMFurnacesBlock {
    class RMDiamondFurnaceBlock(properties: Properties
    )
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_DIAMOND_FURNACE.get().create(blockPos, blockState)!!
        }
    }

    class RMIsolatedDiamondFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_ISOLATED_DIAMOND_FURNACE.get().create(blockPos, blockState)!!
        }
    }

    class RMEbonyFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_EBONY_FURNACE.get().create(blockPos, blockState)!!
        }
    }

    class RMMarbleFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_MARBLE_FURNACE.get().create(blockPos, blockState)!!
        }
    }

    class RMWhiteJadeFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_WHITE_JADE_FURNACE.get().create(blockPos, blockState)!!
        }
    }

    class RMRhinestoneFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_RHINESTONE_FURNACE.get().create(blockPos, blockState)!!
        }
    }

    class RMAquamarineFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_AQUAMARINE_FURNACE.get().create(blockPos, blockState)!!
        }
    }
}