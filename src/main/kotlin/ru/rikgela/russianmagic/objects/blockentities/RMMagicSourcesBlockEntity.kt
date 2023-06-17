package ru.rikgela.russianmagic.objects.blockentities

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState
import ru.rikgela.russianmagic.init.RMBlockEntityTypes

object RMMagicSourcesBlockEntity {
    class RMBasicMagicSourceBlockEntity(blockPos: BlockPos,
                                       blockState: BlockState
    )
        : AbstractRMMagicSourceBlockEntity(
        RMBlockEntityTypes.RM_BASIC_MAGIC_SOURCE.get(),
        blockPos,
        blockState,
        1
        )
}