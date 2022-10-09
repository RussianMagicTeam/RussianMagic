package ru.rikgela.russianmagic.objects.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.StringTextComponent
import net.minecraft.world.World
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana
import ru.rikgela.russianmagic.util.helpers.KeyboardHelper

class MeditationRebornScroll (properties: Properties) : Item(properties) {
    override fun addInformation(
        stack: ItemStack,
        worldIn: World?,
        tooltip: MutableList<ITextComponent>,
        flagIn: ITooltipFlag
    ) {
        if (KeyboardHelper.isHoldingShift) {
            tooltip.add(StringTextComponent("This is a scroll of" + "\u00A7e" + " meditation reborn" + "\u00A77"))
        } else {
            tooltip.add(StringTextComponent("Hold " + "\u00A7e" + "Shift" + "\u00A77" + " for more information"))
        }
        super.addInformation(stack, worldIn, tooltip, flagIn)
    }

    override fun hasEffect(stack: ItemStack): Boolean {
        return true
    }

    override fun onItemRightClick(worldIn: World, playerIn: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        if (playerIn is ServerPlayerEntity) {
            val meditationReborn = MeditationReborn.fromPlayer(playerIn)
            if (meditationReborn.inMeditation) {
                meditationReborn.stopMeditation(playerIn)
            } else {
                meditationReborn.startMeditation(playerIn)
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn)
    }
}