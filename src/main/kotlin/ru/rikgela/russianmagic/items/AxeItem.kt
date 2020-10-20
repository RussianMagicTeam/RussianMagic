package ru.rikgela.russianmagic.items

import com.google.common.collect.ImmutableMap
import net.minecraft.block.Block
import net.minecraft.block.RotatedPillarBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.AxeItem
import net.minecraft.item.IItemTier
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvents
import ru.rikgela.russianmagic.blocks.Blocks.EBONY_LOG
import ru.rikgela.russianmagic.blocks.Blocks.EBONY_WOOD
import ru.rikgela.russianmagic.blocks.Blocks.STRIPPED_EBONY_LOG
import ru.rikgela.russianmagic.blocks.Blocks.STRIPPED_EBONY_WOOD

class RMAxeItem(tier: IItemTier,
                attackDamageIn: Float,
                attackSpeedIn: Float,
                builder: Properties
) : AxeItem(tier, attackDamageIn, attackSpeedIn, builder) {
    private val blockStrippedMap: Map<Block, Block> = ImmutableMap.Builder<Block, Block>().put(EBONY_LOG.get(), STRIPPED_EBONY_LOG.get()).put(EBONY_WOOD.get(), STRIPPED_EBONY_WOOD.get()).build()

    override fun onItemUse(context: ItemUseContext): ActionResultType {
        val world = context.world
        val blockPos = context.pos
        val blockState = world.getBlockState(blockPos)
        val block = blockStrippedMap[blockState.block]
        return if (block != null) {
            val playerEntity = context.player
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f)
            if (!world.isRemote) {
                world.setBlockState(blockPos, block.defaultState.with(RotatedPillarBlock.AXIS, blockState.get(RotatedPillarBlock.AXIS)), 11)
                if (playerEntity != null) {
                    context.item.damageItem(1, playerEntity, { p_220040_1_: PlayerEntity -> p_220040_1_.sendBreakAnimation(context.hand) })
                }
            }
            ActionResultType.SUCCESS
        } else {
            super.onItemUse(context)
        }
    }
}