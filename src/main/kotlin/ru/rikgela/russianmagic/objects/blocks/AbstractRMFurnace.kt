package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.FurnaceBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.network.NetworkHooks
import ru.rikgela.russianmagic.objects.blockentities.AbstractRMFurnaceBlockEntity


abstract class AbstractRMFurnace(properties: Properties) : BaseEntityBlock(properties) {


//    override fun hasTileEntity(state: BlockState): Boolean {
//        return true
//    }
//    val FACING: DirectionProperty? = BlockStateProperties.HORIZONTAL_FACING

    fun openContainer(level: Level, blockPos: BlockPos?, player: Player) {
        val blockentity = level.getBlockEntity(blockPos)
        if (blockentity is FurnaceBlockEntity) {
            player.openMenu(blockentity as MenuProvider?)
            player.awardStat(Stats.INTERACT_WITH_FURNACE)
        }
    }

    @OnlyIn(Dist.CLIENT)
    override fun animateTick(blockState: BlockState, level: Level, blockPos: BlockPos, randomSource: RandomSource) {
        if (blockState.getValue(LIT)) {
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
            val directionAxis = direction.axis
            val d3 = 0.52
            val d4 = randomSource.nextDouble() * 0.6 - 0.3
            val d5 = if (directionAxis === Direction.Axis.X) direction.stepX.toDouble() * 0.52 else d4
            val d6 = randomSource.nextDouble() * 6.0 / 16.0
            val d7 = if (directionAxis === Direction.Axis.Z) direction.stepZ.toDouble() * 0.52 else d4
            level.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
            level.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0)
        }
    }

//    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
//        super.fillStateContainer(builder)
//        builder.add(FACING, LIT)
//    }
//
    @Deprecated("Deprecated in Java")
    override fun mirror(state: BlockState, mirrorIn: Mirror): BlockState {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)))
    }

    @Deprecated("Deprecated in Java")
    override fun rotate(state: BlockState, rot: Rotation): BlockState {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)))
    }

//
//    override fun getLightValue(state: BlockState): Int {
//        return if (state.get(LIT)) super.lightValue else 0
//    }
//
    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return defaultBlockState().setValue(FACING, context.horizontalDirection.opposite)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(FACING, LIT)
    }

    @Deprecated("Deprecated in Java")
    override fun getRenderShape(p_49232_: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    @Deprecated("Deprecated in Java")
    override fun onRemove(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pNewState: BlockState,
        pIsMoving: Boolean
    ) {
        if (pState.block !== pNewState.block) {
            val blockEntity = pLevel.getBlockEntity(pPos)
            if (blockEntity is AbstractRMFurnaceBlockEntity) {
                blockEntity.drops()
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving)
    }

    @Deprecated("Deprecated in Java")
    override fun use(
        blockState: BlockState,
        level: Level,
        blockPos: BlockPos,
        player: Player,
        interactionHand: InteractionHand,
        blockHitResult: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide()) {
            if (Minecraft.getInstance().options.keyShift.isDown)
                if (player.getItemInHand(interactionHand).isEmpty){}
//                    RMCCMessage.transferManaToTileEntity(pos.x, pos.y, pos.z)
                else
                    super.use(blockState, level, blockPos, player, interactionHand, blockHitResult)
            else {
                val entity = level.getBlockEntity(blockPos)
                if (entity is AbstractRMFurnaceBlockEntity) {
                    NetworkHooks.openScreen(player as ServerPlayer?, entity as AbstractRMFurnaceBlockEntity?, blockPos)
                    player.awardStat(Stats.INTERACT_WITH_FURNACE)
                } else {
                    throw IllegalStateException("Our Container provider is missing!")
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide())
    }

    //
//    override fun hasComparatorInputOverride(state: BlockState): Boolean {
//        return true
//    }
//
//    override fun getComparatorInputOverride(blockState: BlockState, worldIn: World, pos: BlockPos): Int {
//        return Container.calcRedstone(worldIn.getTileEntity(pos))
//    }
//
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
    override fun setPlacedBy(
    level: Level,
    blockPos: BlockPos,
    blockState: BlockState,
    livingEntity: LivingEntity?,
    itemStack: ItemStack
) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack)
        if (itemStack.hasCustomHoverName()) {
            val tile = level.getBlockEntity(blockPos)
            if (tile is AbstractRMFurnaceBlockEntity) {
                tile.customName = itemStack.hoverName
            }
        }
    }

    companion object {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val LIT: BooleanProperty = BlockStateProperties.LIT
    }

    init {
        this.defaultBlockState().setValue(LIT, false).setValue(FACING, Direction.NORTH)
    }

//    init {
//        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
//    }
}