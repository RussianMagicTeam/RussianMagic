package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.util.RandomSource
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.AbstractFurnaceBlock
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.FurnaceBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.init.RMBlockEntityTypes
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity.RMDiamondFurnaceBlockEntity


abstract class AbstractRMFurnace(properties: Properties) : BaseEntityBlock(properties) {


//    override fun hasTileEntity(state: BlockState): Boolean {
//        return true
//    }

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        blockState: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return createTickerHelper(
            blockEntityType,
            RMBlockEntityTypes.RM_ISOLATED_DIAMOND_FURNACE.get()
        ) {
                level: Level,
                blockPos: BlockPos,
                blockState: BlockState,
                rmIsolatedDiamondFurnaceBlockEntity: RMFurnacesBlockEntity.RMIsolatedDiamondFurnaceBlockEntity
            -> RMDiamondFurnaceBlockEntity::tick
        }

        fun openContainer(level: Level, blockPos: BlockPos?, player: Player) {
            val blockentity = level.getBlockEntity(blockPos)
            if (blockentity is FurnaceBlockEntity) {
                player.openMenu(blockentity as MenuProvider?)
                player.awardStat(Stats.INTERACT_WITH_FURNACE)
            }
        }

        @OnlyIn(Dist.CLIENT)
        fun animateTick(blockState: BlockState, level: Level, blockPos: BlockPos, randomSource: RandomSource) {
            if (blockState.getValue(AbstractFurnaceBlock.LIT)) {
                val d0 = blockPos.x.toDouble() + 0.5
                val d1 = blockPos.y.toDouble()
                val d2 = blockPos.z.toDouble() + 0.5
                if (randomSource.nextDouble() < 0.1) {
                    level.playLocalSound(
                        d0,
                        d1,
                        d2,
                        SoundEvents.FURNACE_FIRE_CRACKLE,
                        SoundSource.BLOCKS,
                        1.0f,
                        1.0f,
                        false
                    )
                }
                val direction = blockState.getValue(AbstractFurnaceBlock.FACING)
                val `direction$axis` = direction.axis
                val d3 = 0.52
                val d4 = randomSource.nextDouble() * 0.6 - 0.3
                val d5 = if (`direction$axis` === Direction.Axis.X) direction.stepX.toDouble() * 0.52 else d4
                val d6 = randomSource.nextDouble() * 6.0 / 16.0
                val d7 = if (`direction$axis` === Direction.Axis.Z) direction.stepZ.toDouble() * 0.52 else d4
                level.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
                level.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
            }
        }
//        createTickerHelper(
//            blockEntityType,
//            RMBlockEntityTypes.RM_ISOLATED_DIAMOND_FURNACE.get(),
//            RMDiamondFurnaceBlockEntity::tick
//        )
    }

//    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
//        super.fillStateContainer(builder)
//        builder.add(FACING, LIT)
//    }
//
//    override fun mirror(state: BlockState, mirrorIn: Mirror): BlockState {
//        return state.rotate(mirrorIn.toRotation(state.get(FACING)))
//    }

//    override fun rotate(state: BlockState, rot: Rotation): BlockState {
//        return state.with(FACING, rot.rotate(state.get(FACING)))
//    }
//
//    override fun getLightValue(state: BlockState): Int {
//        return if (state.get(LIT)) super.lightValue else 0
//    }
//
//    override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
//        return defaultState.with(FACING, context.placementHorizontalFacing.opposite)
//    }
//
//    override fun hasComparatorInputOverride(state: BlockState): Boolean {
//        return true
//    }
//
//    override fun getComparatorInputOverride(blockState: BlockState, worldIn: World, pos: BlockPos): Int {
//        return Container.calcRedstone(worldIn.getTileEntity(pos))
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    override fun animateTick(stateIn: BlockState, worldIn: World, pos: BlockPos, rand: Random) {
//        if (stateIn.get(LIT)) {
//            val d0 = pos.x.toDouble() + 0.5
//            val d1 = pos.y.toDouble()
//            val d2 = pos.z.toDouble() + 0.5
//            if (rand.nextDouble() < 0.1) {
//                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f,
//                        false)
//            }
//            val direction = stateIn.get(FACING)
//            val directionAxis = direction.axis
//            val d4 = rand.nextDouble() * 0.6 - 0.3
//            val d5 = if (directionAxis === Direction.Axis.X) direction.xOffset.toDouble() * 0.52 else d4
//            val d6 = rand.nextDouble() * 6.0 / 16.0
//            val d7 = if (directionAxis === Direction.Axis.Z) direction.zOffset.toDouble() * 0.52 else d4
//            worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
//            worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
//        }
//    }
//
//    override fun onBlockActivated(state: BlockState, worldIn: World, pos: BlockPos, player: PlayerEntity,
//                                  handIn: Hand, hit: BlockRayTraceResult): ActionResultType {
//        if (worldIn.isRemote) {
//            if (KeyboardHelper.isHoldingShift)
//                if (player.heldItemMainhand.isEmpty)
//                    RMCCMessage.transferManaToTileEntity(pos.x, pos.y, pos.z)
//                else
//                    super.onBlockActivated(state, worldIn, pos, player, handIn, hit)
//            else
//                RMCCMessage.openTileEntityGui(pos.x, pos.y, pos.z)
//        }
//        return ActionResultType.SUCCESS
//    }
//
//    override fun onReplaced(state: BlockState, worldIn: World, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
//        val tile = worldIn.getTileEntity(pos)
//        if (tile is AbstractRMFurnaceTileEntity && state.block !== newState.block) {
//            (tile.inventory).toNonNullList().forEach(Consumer { item: ItemStack ->
//                val itemEntity = ItemEntity(worldIn, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), item)
//                worldIn.addEntity(itemEntity)
//            })
//        }
//        if (state.hasTileEntity() && state.block !== newState.block) {
//            worldIn.removeTileEntity(pos)
//        }
//    }
//
//    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
//        super.onBlockPlacedBy(worldIn, pos, state, placer, stack)
//        if (stack.hasDisplayName()) {
//            val tile = worldIn.getTileEntity(pos)
//            if (tile is AbstractRMFurnaceTileEntity) {
//                tile.customName = stack.displayName
//            }
//        }
//    }
//
    companion object {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val LIT: BooleanProperty = BooleanProperty.create("lit")
    }
//
//    init {
//        defaultState = stateContainer.baseState.with(FACING, Direction.NORTH).with(LIT, false)
//    }
}