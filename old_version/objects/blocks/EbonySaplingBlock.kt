package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.BushBlock
import net.minecraft.block.IGrowable
import net.minecraft.block.trees.Tree
import net.minecraft.state.IntegerProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.event.ForgeEventFactory
import java.util.*
import java.util.function.Supplier

class EbonySaplingBlock(private val tree: Supplier<Tree>, properties: Properties) : BushBlock(properties), IGrowable {
    override fun getShape(state: BlockState, worldIn: IBlockReader, pos: BlockPos, context: ISelectionContext): VoxelShape {
        return SHAPE
    }

    override fun tick(state: BlockState, worldIn: ServerWorld, pos: BlockPos, rand: Random) {
        super.tick(state, worldIn, pos, rand)
        if (!worldIn.isAreaLoaded(pos, 1)) {
            return
        }
        if (worldIn.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(worldIn, pos, state, rand)
        }
    }

    private fun grow(serverWorld: ServerWorld, pos: BlockPos, state: BlockState, rand: Random) {
        if (state.get(STAGE) == 0) {
            serverWorld.setBlockState(pos, state.cycle(STAGE), 4)
        } else {
            if (!ForgeEventFactory.saplingGrowTree(serverWorld, rand, pos)) return
            tree.get().place(serverWorld, serverWorld.chunkProvider.chunkGenerator, pos, state, rand)
        }
    }

    override fun grow(serverWorld: ServerWorld, rand: Random, pos: BlockPos, state: BlockState) {
        this.grow(serverWorld, pos, state, rand)
    }

    override fun canGrow(worldIn: IBlockReader, pos: BlockPos, state: BlockState, isClient: Boolean): Boolean {
        return true
    }

    override fun canUseBonemeal(worldIn: World, rand: Random, pos: BlockPos, state: BlockState): Boolean {
        return worldIn.rand.nextFloat() < 0.45
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        builder.add(STAGE)
    }

    companion object {
        val STAGE: IntegerProperty = BlockStateProperties.STAGE_0_1
        private val SHAPE = Block.makeCuboidShape(2.0, 0.0, 2.0, 14.0, 12.0, 14.0)
    }

}