package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.util.math.shapes.IBooleanFunction
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.math.shapes.VoxelShapes
import net.minecraft.util.text.StringTextComponent
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.common.RMCCMessage
import ru.rikgela.russianmagic.tileentity.AbstractRMMagicSourceTileEntity
import ru.rikgela.russianmagic.util.helpers.KeyboardHelper
import java.util.*

abstract class AbstractRMMagicSourceBlock(properties: Properties) : Block(properties) {

    //protected val SHAPE = makeCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0)

    //override fun getShape(state: BlockState?, worldIn: IBlockReader?, pos: BlockPos?, context: ISelectionContext?): VoxelShape? {
    //    return this.SHAPE
    //}
    private var tileEntity: TileEntity? = null

    override fun hasTileEntity(state: BlockState): Boolean {
        return true
    }

    override fun getCollisionShape(state: BlockState, worldIn: IBlockReader, pos: BlockPos, context: ISelectionContext): VoxelShape {
        return VoxelShapes.empty()
    }

    override fun onEntityCollision(state: BlockState, worldIn: World, pos: BlockPos, entityIn: Entity) {
        if (!worldIn.isRemote && !entityIn.isPassenger && !entityIn.isBeingRidden && entityIn.isNonBoss && VoxelShapes.compare(VoxelShapes.create(entityIn.boundingBox.offset((-pos.x).toDouble(), (-pos.y).toDouble(), (-pos.z).toDouble())), state.getShape(worldIn, pos), IBooleanFunction.AND)) {
            RMCCMessage.transferManaFromTileEntity(pos.x, pos.y, pos.z)
            //entityIn.changeDimension(if (worldIn.dimension.type === DimensionType.THE_END) DimensionType.OVERWORLD else DimensionType.THE_END)
        }
    }
    //override fun mirror(state: BlockState, mirrorIn: Mirror): BlockState {
    //return state.rotate(mirrorIn.toRotation(state.get(FACING)))
    //}

    //override fun rotate(state: BlockState, rot: Rotation): BlockState {
    //return state.with(FACING, rot.rotate(state.get(FACING)))
    //}

    override fun getLightValue(state: BlockState): Int {
        return super.lightValue
    }

    //override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
    //return defaultState.with(FACING, context.placementHorizontalFacing.opposite)
    //}

    override fun hasComparatorInputOverride(state: BlockState): Boolean {
        return true
    }

//    override fun getComparatorInputOverride(blockState: BlockState, worldIn: World, pos: BlockPos): Int {
//        return Container.calcRedstone(worldIn.getTileEntity(pos))
//    }

    @OnlyIn(Dist.CLIENT)
    override fun animateTick(stateIn: BlockState, worldIn: World, pos: BlockPos, rand: Random) {
        val d0 = pos.x.toDouble() + 0.5
        val d1 = pos.y.toDouble()
        val d2 = pos.z.toDouble() + 0.5
        if (rand.nextDouble() < 0.1) {
            worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f,
                    false)
        }
//        val direction = stateIn.get(FACING)
//        val directionAxis = direction.axis
//        val d4 = rand.nextDouble() * 0.6 - 0.3
//        val d5 = if (directionAxis === Direction.Axis.X) direction.xOffset.toDouble() * 0.52 else d4
//        val d6 = rand.nextDouble() * 6.0 / 16.0
//        val d7 = if (directionAxis === Direction.Axis.Z) direction.zOffset.toDouble() * 0.52 else d4
//        worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
//        worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
    }

    override fun onBlockActivated(state: BlockState, worldIn: World, pos: BlockPos, player: PlayerEntity,
                                  handIn: Hand, hit: BlockRayTraceResult): ActionResultType {
        if (worldIn.isRemote) {
            if (KeyboardHelper.isHoldingShift) {
                RMCCMessage.transferManaFromTileEntity(pos.x, pos.y, pos.z)
            }
        } else {
            val tile = ((worldIn as ServerWorld).getTileEntity(pos) as AbstractRMMagicSourceTileEntity)
            player.sendMessage(
                    StringTextComponent(
                            String.format("Your magic source have §7%d§r mana left.", tile.currentMana)
                    )
            )
        }

        return ActionResultType.SUCCESS
    }

    override fun onReplaced(state: BlockState, worldIn: World, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
        val tile = worldIn.getTileEntity(pos)
        if (state.hasTileEntity() && state.block !== newState.block) {
            worldIn.removeTileEntity(pos)
        }
    }

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack)
        if (stack.hasDisplayName()) {
            val tile = worldIn.getTileEntity(pos)
            if (tile is AbstractRMMagicSourceTileEntity) {
                tile.customName = stack.displayName
            }
        }
    }

    //companion object {
    //val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
    //}

    //init {
    //defaultState = stateContainer.baseState.with(FACING, Direction.NORTH)
    //}
}