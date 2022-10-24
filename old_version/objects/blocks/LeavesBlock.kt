package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.item.BlockItemUseContext
import net.minecraft.particles.ParticleTypes
import net.minecraft.state.BooleanProperty
import net.minecraft.state.IntegerProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tags.BlockTags
import net.minecraft.util.Direction
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.IWorld
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.common.IShearable
import java.util.*

class RMLeavesBlock(properties: Properties) : Block(properties), IShearable {
    /**
     * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
     * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */
    override fun ticksRandomly(state: BlockState): Boolean {
        return state.get(DISTANCE) == 7 && !state.get(PERSISTENT)
    }

    /**
     * Performs a random tick on a block.
     */
    override fun randomTick(state: BlockState, worldIn: ServerWorld, pos: BlockPos, random: Random) {
        if (!state.get(PERSISTENT) && state.get(DISTANCE) == 7) {
            spawnDrops(state, worldIn, pos)
            worldIn.removeBlock(pos, false)
        }
    }

    override fun tick(state: BlockState, worldIn: ServerWorld, pos: BlockPos, rand: Random) {
        worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3)
    }

    override fun getOpacity(state: BlockState, worldIn: IBlockReader, pos: BlockPos): Int {
        return 1
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    override fun updatePostPlacement(stateIn: BlockState, facing: Direction, facingState: BlockState, worldIn: IWorld, currentPos: BlockPos, facingPos: BlockPos): BlockState {
        val i = getDistance(facingState) + 1
        if (i != 1 || stateIn.get(DISTANCE) != i) {
            worldIn.pendingBlockTicks.scheduleTick(currentPos, this, 1)
        }
        return stateIn
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
     * this method is unrelated to [randomTick] and [.needsRandomTick], and will always be called regardless
     * of whether the block can receive random update ticks
     */
    @OnlyIn(Dist.CLIENT)
    override fun animateTick(stateIn: BlockState, worldIn: World, pos: BlockPos, rand: Random) {
        if (worldIn.isRainingAt(pos.up())) {
            if (rand.nextInt(15) == 1) {
                val blockPos = pos.down()
                val blockState = worldIn.getBlockState(blockPos)
                if (!blockState.isSolid || !blockState.isSolidSide(worldIn, blockPos, Direction.UP)) {
                    val d0 = (pos.x.toFloat() + rand.nextFloat()).toDouble()
                    val d1 = pos.y.toDouble() - 0.05
                    val d2 = (pos.z.toFloat() + rand.nextFloat()).toDouble()
                    worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0, 0.0, 0.0)
                }
            }
        }
    }

    override fun causesSuffocation(state: BlockState, worldIn: IBlockReader, pos: BlockPos): Boolean {
        return false
    }

    override fun canEntitySpawn(state: BlockState, worldIn: IBlockReader, pos: BlockPos, type: EntityType<*>): Boolean {
        return type === EntityType.OCELOT || type === EntityType.PARROT
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        builder.add(DISTANCE, PERSISTENT)
    }

    override fun getStateForPlacement(context: BlockItemUseContext): BlockState {
        return updateDistance(defaultState.with(PERSISTENT, java.lang.Boolean.valueOf(true)), context.world, context.pos)
    }

    companion object {
        val DISTANCE: IntegerProperty = BlockStateProperties.DISTANCE_1_7
        val PERSISTENT: BooleanProperty = BlockStateProperties.PERSISTENT
        private fun updateDistance(state: BlockState, worldIn: IWorld, pos: BlockPos): BlockState {
            var i = 7
            BlockPos.PooledMutable.retain().use { blockPosPooledMutable ->
                for (direction in Direction.values()) {
                    blockPosPooledMutable.setPos(pos).move(direction)
                    i = i.coerceAtMost(getDistance(worldIn.getBlockState(blockPosPooledMutable)) + 1)
                    if (i == 1) {
                        break
                    }
                }
            }
            return state.with(DISTANCE, Integer.valueOf(i))
        }

        private fun getDistance(neighbor: BlockState): Int {
            return if (BlockTags.Wrapper(ResourceLocation("forge", "logs")).contains(neighbor.block)) {
                0
            } else {
                if (neighbor.block is RMLeavesBlock) neighbor.get(DISTANCE) else 7
            }
        }
    }

    init {
        defaultState = stateContainer.baseState.with(DISTANCE, Integer.valueOf(7)).with(PERSISTENT, java.lang.Boolean.valueOf(false))
    }
}