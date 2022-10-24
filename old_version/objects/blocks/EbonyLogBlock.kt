package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.LogBlock
import net.minecraft.block.material.MaterialColor
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.EnumProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.Direction
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader

class EbonyLogBlock(private val verticalColor: MaterialColor, properties: Properties) : LogBlock(verticalColor, properties) {
    override fun rotate(state: BlockState, rot: Rotation): BlockState {
        return when (rot) {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> when (state.get(AXIS) as Direction.Axis) {
                Direction.Axis.X -> state.with(AXIS, Direction.Axis.Z)
                Direction.Axis.Z -> state.with(AXIS, Direction.Axis.X)
                else -> state
            }
            else -> state
        }
    }

    override fun getMaterialColor(state: BlockState, worldIn: IBlockReader, pos: BlockPos): MaterialColor {
        return if (state.get(AXIS) === Direction.Axis.Y) this.verticalColor else materialColor
    }

    public override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        builder.add(AXIS)
    }

    override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
        return defaultState.with(AXIS, context.face.axis)
    }

    companion object {
        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.AXIS
    }

    init {
        defaultState = defaultState.with(AXIS, Direction.Axis.Y)
    }
}