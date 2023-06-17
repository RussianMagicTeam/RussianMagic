package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import ru.rikgela.russianmagic.init.RMBlockEntityTypes
import ru.rikgela.russianmagic.objects.blockentities.AbstractRMMagicSourceBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMMagicSourcesBlockEntity
import java.util.*

class RMMagicSourceBlock {
    class RMBasicMagicSource(properties: Properties)
        : AbstractRMMagicSourceBlock(properties) {

        override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
            return RMMagicSourcesBlockEntity.RMBasicMagicSourceBlockEntity(blockPos, blockState)
        }
        override fun <T : BlockEntity?> getTicker(
            level: Level,
            blockState: BlockState,
            blockEntityType: BlockEntityType<T>
        ): BlockEntityTicker<T>? {
            return createTickerHelper(
                blockEntityType,
                RMBlockEntityTypes.RM_BASIC_MAGIC_SOURCE.get()
            ) { level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmBasicMagicSourceBlockEntity: RMMagicSourcesBlockEntity.RMBasicMagicSourceBlockEntity
                ->
                AbstractRMMagicSourceBlockEntity.tick(level, blockPos, blockState, rmBasicMagicSourceBlockEntity)
            }
        }
    }
}