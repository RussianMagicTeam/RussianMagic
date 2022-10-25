package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraftforge.common.ToolAction
import net.minecraftforge.common.property.Properties
import ru.rikgela.russianmagic.init.RMBlocks
import ru.rikgela.russianmagic.objects.items.RMAxeItem

class EbonyRotatedPillarBlock(properties: Properties) : RotatedPillarBlock(
    properties
) {

    override fun isFlammable(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Boolean {
        return true
    }

    override fun getFlammability(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Int {
        return 5
    }

    override fun getFireSpreadSpeed(
        state: BlockState?,
        level: BlockGetter?,
        pos: BlockPos?,
        direction: Direction?
    ): Int {
        return 5
    }

    override fun getToolModifiedState(
        state: BlockState?,
        context: UseOnContext?,
        toolAction: ToolAction?,
        simulate: Boolean
    ): BlockState? {
        if(context?.itemInHand?.item is RMAxeItem){
            if(state?.`is`(RMBlocks.EBONY_LOG.get())!!){
                return RMBlocks.STRIPPED_EBONY_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS))
            }
            if(state.`is`(RMBlocks.EBONY_WOOD.get())){
                return RMBlocks.STRIPPED_EBONY_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS))
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate)
    }

    companion object {
        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.AXIS
    }

}