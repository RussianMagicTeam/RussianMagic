package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.item.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.container.Container
import net.minecraft.item.BlockItemUseContext
import net.minecraft.item.ItemStack
import net.minecraft.particles.ParticleTypes
import net.minecraft.state.BooleanProperty
import net.minecraft.state.DirectionProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.RegistryObject
import ru.rikgela.russianmagic.common.RMCCMessage
import ru.rikgela.russianmagic.init.RMTileEntityTypes
import ru.rikgela.russianmagic.tileentity.AbstractRMFurnaceTileEntity
import ru.rikgela.russianmagic.tileentity.RMMarbleFurnaceTileEntity
import ru.rikgela.russianmagic.util.helpers.KeyboardHelper
import java.util.*
import java.util.function.Consumer

abstract class AbstractRMFurnace(properties: Properties) : Block(properties) {

    private var tileEntity: TileEntity? = null

    override fun hasTileEntity(state: BlockState): Boolean {
        return true
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        super.fillStateContainer(builder)
        builder.add(FACING, LIT)
    }

    override fun mirror(state: BlockState, mirrorIn: Mirror): BlockState {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)))
    }

    override fun rotate(state: BlockState, rot: Rotation): BlockState {
        return state.with(FACING, rot.rotate(state.get(FACING)))
    }

    override fun getLightValue(state: BlockState): Int {
        return if (state.get(LIT)) super.lightValue else 0
    }

    override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
        return defaultState.with(FACING, context.placementHorizontalFacing.opposite)
    }

    override fun hasComparatorInputOverride(state: BlockState): Boolean {
        return true
    }

    override fun getComparatorInputOverride(blockState: BlockState, worldIn: World, pos: BlockPos): Int {
        return Container.calcRedstone(worldIn.getTileEntity(pos))
    }

    @OnlyIn(Dist.CLIENT)
    override fun animateTick(stateIn: BlockState, worldIn: World, pos: BlockPos, rand: Random) {
        if (stateIn.get(LIT)) {
            val d0 = pos.x.toDouble() + 0.5
            val d1 = pos.y.toDouble()
            val d2 = pos.z.toDouble() + 0.5
            if (rand.nextDouble() < 0.1) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f,
                        false)
            }
            val direction = stateIn.get(FACING)
            val directionAxis = direction.axis
            val d4 = rand.nextDouble() * 0.6 - 0.3
            val d5 = if (directionAxis === Direction.Axis.X) direction.xOffset.toDouble() * 0.52 else d4
            val d6 = rand.nextDouble() * 6.0 / 16.0
            val d7 = if (directionAxis === Direction.Axis.Z) direction.zOffset.toDouble() * 0.52 else d4
            worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
            worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
        }
    }

    override fun onBlockActivated(state: BlockState, worldIn: World, pos: BlockPos, player: PlayerEntity,
                                  handIn: Hand, hit: BlockRayTraceResult): ActionResultType {
        if (worldIn.isRemote) {
            if (KeyboardHelper.isHoldingShift) {
                RMCCMessage.transferManaToTileEntity(pos.x, pos.y, pos.z)
            } else {
                RMCCMessage.openTileEntityGui(pos.x, pos.y, pos.z)
            }
        }
        return ActionResultType.SUCCESS
    }

    companion object {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val LIT: BooleanProperty = BooleanProperty.create("lit")
    }

    init {
        defaultState = stateContainer.baseState.with(FACING, Direction.NORTH).with(LIT, false)
    }
}