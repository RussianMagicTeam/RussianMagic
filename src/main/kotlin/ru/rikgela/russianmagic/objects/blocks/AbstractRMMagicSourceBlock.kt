package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.ItemStack
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
import net.minecraft.world.EmptyBlockReader
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.common.RMCCMessage
import ru.rikgela.russianmagic.objects.player.mana.IPlayerMana
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana
import ru.rikgela.russianmagic.objects.tileentity.AbstractRMMagicSourceTileEntity
import ru.rikgela.russianmagic.util.helpers.KeyboardHelper
import java.lang.Float.min
import java.util.*

abstract class AbstractRMMagicSourceBlock(properties: Properties) : Block(properties) {

    private var shape = makeCuboidShape(7.0, 7.0, 7.0, 9.0, 9.0, 9.0)
    private var model = BlockRenderType.MODEL


    override fun getShape(state: BlockState?, worldIn: IBlockReader?, pos: BlockPos, context: ISelectionContext?): VoxelShape? {
        if (worldIn is EmptyBlockReader) return this.shape
        val shift: Float = min((worldIn?.getTileEntity(pos) as AbstractRMMagicSourceTileEntity).currentMana.toFloat() / (worldIn.getTileEntity(pos) as AbstractRMMagicSourceTileEntity).baseMaxMana.toFloat(), 1F)
        this.shape = makeCuboidShape(7.0 - 6.0 * shift,
                7.0 - 6.0 * shift,
                7.0 - 6.0 * shift,
                9.0 + 6.0 * shift,
                9.0 + 6.0 * shift,
                9.0 + 6.0 * shift)
        return this.shape
    }

    override fun getRenderType(state: BlockState?): BlockRenderType? {
        return model
    }

    override fun hasTileEntity(state: BlockState): Boolean {
        return true
    }

    override fun getCollisionShape(state: BlockState, worldIn: IBlockReader, pos: BlockPos, context: ISelectionContext): VoxelShape {
        return VoxelShapes.empty()
    }

    override fun onEntityCollision(state: BlockState, worldIn: World, pos: BlockPos, entityIn: Entity) {
        if (
            !worldIn.isRemote && entityIn is ServerPlayerEntity
            && VoxelShapes.compare(
                VoxelShapes.create(
                    entityIn.boundingBox.offset((-pos.x).toDouble(), (-pos.y).toDouble(), (-pos.z).toDouble())
                ), state.getShape(worldIn, pos), IBooleanFunction.AND)
        ) {
            val tile = worldIn.getTileEntity(BlockPos(pos.x, pos.y, pos.z)) as AbstractRMMagicSourceTileEntity
            val playerMana: IPlayerMana = PlayerMana.fromPlayer(entityIn)
            playerMana.fill(
                tile.spread(
                    Integer.min(tile.maxSpread, tile.currentMana),
                    playerMana.rate
                )
            )
        }
    }

    override fun hasComparatorInputOverride(state: BlockState): Boolean {
        return true
    }

    @OnlyIn(Dist.CLIENT)
    override fun animateTick(stateIn: BlockState, worldIn: World, pos: BlockPos, rand: Random) {
        val d0 = pos.x.toDouble() + 0.5
        val d1 = pos.y.toDouble()
        val d2 = pos.z.toDouble() + 0.5
        if (rand.nextDouble() < 0.1) {
            worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f,
                    false)
        }
    }

    override fun onBlockActivated(state: BlockState, worldIn: World, pos: BlockPos, player: PlayerEntity,
                                  handIn: Hand, hit: BlockRayTraceResult): ActionResultType {
        if (worldIn.isRemote) {
            if (player.heldItemMainhand.isEmpty) {
                if (KeyboardHelper.isHoldingShift) {
                    RMCCMessage.transferManaFromTileEntity(pos.x, pos.y, pos.z)
                } else {
                    val tile = worldIn.getTileEntity(pos) as AbstractRMMagicSourceTileEntity
                    player.sendMessage(
                            StringTextComponent(
                                    String.format("Your magic source have §7%d§r mana left.", tile.currentMana)
                            )
                    )
                }
            }
        }
        return ActionResultType.SUCCESS
    }

    override fun onReplaced(state: BlockState, worldIn: World, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
        if (state.hasTileEntity() && state.block !== newState.block) {
            worldIn.removeTileEntity(pos)
        }
    }

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack)
        if (!stack.hasDisplayName()) {
            val tile = worldIn.getTileEntity(pos)
            if (tile is AbstractRMMagicSourceTileEntity) {
                tile.customName = stack.displayName
                tile.playerUuid = (placer as PlayerEntity).displayNameAndUUID
                if (placer is ServerPlayerEntity)
                    PlayerMana.fromPlayer(placer)
                        .connectToManaSpreader(pos, placer.position, worldIn.server!!, worldIn.worldType.id)
            }
        }
    }
}