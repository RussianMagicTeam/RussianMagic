package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import ru.rikgela.russianmagic.init.RMBlockEntityTypes
import ru.rikgela.russianmagic.objects.blockentities.AbstractRMFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity


object RMFurnacesBlock {
    class RMDiamondFurnaceBlock(properties: Properties
    )
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
//            return RMBlockEntityTypes.RM_DIAMOND_FURNACE.get().create(blockPos, blockState)!!
            return RMFurnacesBlockEntity.RMDiamondFurnaceBlockEntity(blockPos, blockState)
        }

        override fun <T : BlockEntity?> getTicker(
            level: Level,
            blockState: BlockState,
            blockEntityType: BlockEntityType<T>
        ): BlockEntityTicker<T>? {
            return createTickerHelper(
                blockEntityType,
                RMBlockEntityTypes.RM_DIAMOND_FURNACE.get()
            ) { level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmDiamondFurnaceBlockEntity: RMFurnacesBlockEntity.RMDiamondFurnaceBlockEntity
                ->
                AbstractRMFurnaceBlockEntity.tick(level, blockPos, blockState, rmDiamondFurnaceBlockEntity)
            }
        }
    }

    class RMIsolatedDiamondFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_ISOLATED_DIAMOND_FURNACE.get().create(blockPos, blockState)!!
        }

        override fun <T : BlockEntity?> getTicker(
            level: Level,
            blockState: BlockState,
            blockEntityType: BlockEntityType<T>
        ): BlockEntityTicker<T>? {
            return createTickerHelper(
                blockEntityType,
                RMBlockEntityTypes.RM_ISOLATED_DIAMOND_FURNACE.get()
            ) { level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmIsolatedDiamondFurnaceBlockEntity: RMFurnacesBlockEntity.RMIsolatedDiamondFurnaceBlockEntity
                ->
                AbstractRMFurnaceBlockEntity.tick(level, blockPos, blockState, rmIsolatedDiamondFurnaceBlockEntity)
            }
        }
    }

    class RMEbonyFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_EBONY_FURNACE.get().create(blockPos, blockState)!!
        }
        override fun <T : BlockEntity?> getTicker(
            level: Level,
            blockState: BlockState,
            blockEntityType: BlockEntityType<T>
        ): BlockEntityTicker<T>? {
            return createTickerHelper(
                blockEntityType,
                RMBlockEntityTypes.RM_EBONY_FURNACE.get()
            ) { level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmEbonyFurnaceBlockEntity: RMFurnacesBlockEntity.RMEbonyFurnaceBlockEntity
                ->
                AbstractRMFurnaceBlockEntity.tick(level, blockPos, blockState, rmEbonyFurnaceBlockEntity)
            }
        }
    }

    class RMMarbleFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_MARBLE_FURNACE.get().create(blockPos, blockState)!!
        }

        override fun <T : BlockEntity?> getTicker(
            level: Level,
            blockState: BlockState,
            blockEntityType: BlockEntityType<T>
        ): BlockEntityTicker<T>? {
            return createTickerHelper(
                blockEntityType,
                RMBlockEntityTypes.RM_MARBLE_FURNACE.get()
            ) { level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmMarbleFurnaceBlockEntity: RMFurnacesBlockEntity.RMMarbleFurnaceBlockEntity
                ->
                AbstractRMFurnaceBlockEntity.tick(level, blockPos, blockState, rmMarbleFurnaceBlockEntity)
            }
        }
    }

    class RMWhiteJadeFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_WHITE_JADE_FURNACE.get().create(blockPos, blockState)!!
        }

        override fun <T : BlockEntity?> getTicker(
            level: Level,
            blockState: BlockState,
            blockEntityType: BlockEntityType<T>
        ): BlockEntityTicker<T>? {
            return createTickerHelper(
                blockEntityType,
                RMBlockEntityTypes.RM_WHITE_JADE_FURNACE.get()
            ) { level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmWhiteJadeFurnaceBlockEntity: RMFurnacesBlockEntity.RMWhiteJadeFurnaceBlockEntity
                ->
                AbstractRMFurnaceBlockEntity.tick(level, blockPos, blockState, rmWhiteJadeFurnaceBlockEntity)
            }
        }
    }

    class RMRhinestoneFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_RHINESTONE_FURNACE.get().create(blockPos, blockState)!!
        }

        override fun <T : BlockEntity?> getTicker(
            level: Level,
            blockState: BlockState,
            blockEntityType: BlockEntityType<T>
        ): BlockEntityTicker<T>? {
            return createTickerHelper(
                blockEntityType,
                RMBlockEntityTypes.RM_RHINESTONE_FURNACE.get()
            ) { level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmRhinestoneFurnaceBlockEntity: RMFurnacesBlockEntity.RMRhinestoneFurnaceBlockEntity
                ->
                AbstractRMFurnaceBlockEntity.tick(level, blockPos, blockState, rmRhinestoneFurnaceBlockEntity)
            }
        }
    }

    class RMAquamarineFurnaceBlock(properties: Properties)
        : AbstractRMFurnace(properties) {
        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMBlockEntityTypes.RM_AQUAMARINE_FURNACE.get().create(blockPos, blockState)!!
        }

        override fun <T : BlockEntity?> getTicker(
            level: Level,
            blockState: BlockState,
            blockEntityType: BlockEntityType<T>
        ): BlockEntityTicker<T>? {
            return createTickerHelper(
                blockEntityType,
                RMBlockEntityTypes.RM_AQUAMARINE_FURNACE.get()
            ) { level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmAquamarineFurnaceBlockEntity: RMFurnacesBlockEntity.RMAquamarineFurnaceBlockEntity
                ->
                AbstractRMFurnaceBlockEntity.tick(level, blockPos, blockState, rmAquamarineFurnaceBlockEntity)
            }
        }
    }
}