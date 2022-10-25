//package ru.rikgela.russianmagic.objects.items
//
//import com.google.common.collect.ImmutableMap
//import net.minecraft.sounds.SoundEvents
//import net.minecraft.sounds.SoundSource
//import net.minecraft.world.InteractionResult
//import net.minecraft.world.entity.player.Player
//import net.minecraft.world.item.AxeItem
//import net.minecraft.world.item.ItemStack
//import net.minecraft.world.item.Tier
//import net.minecraft.world.item.context.UseOnContext
//import net.minecraft.world.level.block.Block
//import net.minecraftforge.common.ToolActions
//import ru.rikgela.russianmagic.init.RMBlocks.EBONY_LOG
//import ru.rikgela.russianmagic.init.RMBlocks.EBONY_WOOD
//import ru.rikgela.russianmagic.init.RMBlocks.STRIPPED_EBONY_LOG
//import ru.rikgela.russianmagic.init.RMBlocks.STRIPPED_EBONY_WOOD
//import java.util.*
//import java.util.function.Consumer
//
//class RMAxeItem(tier: Tier,
//                attackDamageIn: Float,
//                attackSpeedIn: Float,
//                builder: Properties
//) : AxeItem(tier, attackDamageIn, attackSpeedIn, builder) {
//    private val blockStrippedMap: Map<Block, Block> = ImmutableMap.Builder<Block, Block>().put(EBONY_LOG.get(), STRIPPED_EBONY_LOG.get()).put(EBONY_WOOD.get(), STRIPPED_EBONY_WOOD.get()).build()
//
//    override fun useOn(context: UseOnContext): InteractionResult {
//        val level = context.level
//        val blockPos = context.clickedPos
//        val blockState = level.getBlockState(blockPos)
//        val block = blockStrippedMap[blockState.block]
//        val itemstack: ItemStack = context.itemInHand
//        return if (block != null) {
//            val player = context.player
//            level.playSound(player, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f)
//            level.setBlock(
//                blockPos,
//                Optional.ofNullable(blockState.getToolModifiedState(context, ToolActions.AXE_SCRAPE, false)).get(),
//                11
//            )
//            if (player != null) {
//                itemstack.hurtAndBreak<Player>(1, player,
//                    Consumer { p_150686_: Player ->
//                        p_150686_.broadcastBreakEvent(
//                            context.hand
//                        )
//                    })
//            }
//            InteractionResult.sidedSuccess(level.isClientSide)
//        } else {
//            super.useOn(context)
//        }
//    }
//
//}