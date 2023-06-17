package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import java.util.*

abstract class AbstractRMMagicSourceBlock(properties: Properties) : BaseEntityBlock(properties) {

//    private var shape = makeCuboidShape(7.0, 7.0, 7.0, 9.0, 9.0, 9.0)
//    private var model = BlockRenderType.MODEL


//    override fun getShape(state: BlockState?, worldIn: IBlockReader?, pos: BlockPos, context: ISelectionContext?): VoxelShape? {
//        if (worldIn is EmptyBlockReader) return this.shape
//        val shift: Float = min((worldIn?.getTileEntity(pos) as AbstractRMMagicSourceTileEntity).currentMana.toFloat() / (worldIn.getTileEntity(pos) as AbstractRMMagicSourceTileEntity).baseMaxMana.toFloat(), 1F)
//        this.shape = makeCuboidShape(7.0 - 6.0 * shift,
//                7.0 - 6.0 * shift,
//                7.0 - 6.0 * shift,
//                9.0 + 6.0 * shift,
//                9.0 + 6.0 * shift,
//                9.0 + 6.0 * shift)
//        return this.shape
//    }

    @Deprecated("Deprecated in Java")
    override fun getRenderShape(p_49232_: BlockState): RenderShape {
        return RenderShape.MODEL
    }

//    override fun getCollisionShape(state: BlockState, worldIn: IBlockReader, pos: BlockPos, context: ISelectionContext): VoxelShape {
//        return VoxelShapes.empty()
//    }

//    override fun onEntityCollision(state: BlockState, worldIn: World, pos: BlockPos, entityIn: Entity) {
//        if (
//            !worldIn.isRemote && entityIn is ServerPlayerEntity
//            && VoxelShapes.compare(
//                VoxelShapes.create(
//                    entityIn.boundingBox.offset((-pos.x).toDouble(), (-pos.y).toDouble(), (-pos.z).toDouble())
//                ), state.getShape(worldIn, pos), IBooleanFunction.AND)
//        ) {
//            val tile = worldIn.getTileEntity(BlockPos(pos.x, pos.y, pos.z)) as AbstractRMMagicSourceTileEntity
//            val playerMana: IPlayerMana = PlayerMana.fromPlayer(entityIn)
//            playerMana.fill(
//                tile.spread(
//                    Integer.min(tile.maxSpread, tile.currentMana),
//                    playerMana.rate
//                )
//            )
//        }
//    }

//    override fun hasComparatorInputOverride(state: BlockState): Boolean {
//        return true
//    }

    @OnlyIn(Dist.CLIENT)
    override fun animateTick(blockState: BlockState, level: Level, blockPos: BlockPos, randomSource: RandomSource) {
        val d0 = blockPos.x.toDouble() + 0.5
        val d1 = blockPos.y.toDouble()
        val d2 = blockPos.z.toDouble() + 0.5
        if (randomSource.nextDouble() < 0.1) {
            level.playLocalSound(
                d0, d1, d2,
                SoundEvents.FURNACE_FIRE_CRACKLE,
                SoundSource.BLOCKS,
                1.0f, 1.0f, false
            )
        }
    }

    override fun use(
        blockState: BlockState,
        level: Level,
        blockPos: BlockPos,
        player: Player,
        interactionHand: InteractionHand,
        blockHitResult: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide()) {
            if (player.getItemInHand(interactionHand).isEmpty) {
                if (player.isShiftKeyDown) {
//                    RMCCMessage.transferManaFromTileEntity(blockPos.x, blockPos.y, blockPos.z)
                }
//                else {
//                    val block = level.getBlockEntity(blockPos) as AbstractRMMagicSourceBlockEntity
//                    player.sendMessage(
//                            StringTextComponent(
//                                    String.format("Your magic source have §7%d§r mana left.", tile.currentMana)
//                            )
//                    )
//                }
            }
            else{
                super.use(blockState, level, blockPos, player, interactionHand, blockHitResult)
            }
        }
        return InteractionResult.SUCCESS
    }

//    override fun onReplaced(state: BlockState, worldIn: World, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
//        if (state.hasTileEntity() && state.block !== newState.block) {
//            worldIn.removeTileEntity(pos)
//        }
//    }


    override fun setPlacedBy(
    level: Level,
    blockPos: BlockPos,
    blockState: BlockState,
    livingEntity: LivingEntity?,
    itemStack: ItemStack
    ) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack)
//        if (!itemStack.hasCustomHoverName()) {
//            val tile = level.getBlockEntity(blockPos)
//            if (tile is AbstractRMMagicSourceBlockEntity) {
//                tile.playerStringUuid = (livingEntity as Player).stringUUID
//                if (livingEntity is ServerPlayer)
////                    PlayerMana.fromPlayer(livingEntity)
////                        .connectToManaSpreader(blockPos, livingEntity.position(), level.server!!, level.worldType.id)
//            }
//        }
    }
}