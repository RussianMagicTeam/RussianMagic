package ru.rikgela.russianmagic.objects.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.StringTextComponent
import net.minecraft.world.World
import ru.rikgela.russianmagic.mana.Mana.Companion.fromPlayer
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity
import ru.rikgela.russianmagic.util.helpers.KeyboardHelper

class FireballScroll(properties: Properties) : Item(properties) {
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        if (KeyboardHelper.isHoldingShift) {
            tooltip.add(StringTextComponent("This is a scroll of" + "\u00A7e" + " regeneration" + "\u00A77"))
        } else {
            tooltip.add(StringTextComponent("Hold " + "\u00A7e" + "Shift" + "\u00A77" + " for more information"))
        }
        super.addInformation(stack, worldIn, tooltip, flagIn)
    }

    override fun hasEffect(stack: ItemStack): Boolean {
        return true
    }

    override fun onItemRightClick(worldIn: World, playerIn: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        if(playerIn is ServerPlayerEntity) {
            val mana = fromPlayer(playerIn)
            if (mana.consume(100, playerIn)) {
                val looking: Vec3d = playerIn.lookVec
                val projectileEntity = ProjectileEntity(worldIn, playerIn as LivingEntity, looking.x, looking.y, looking.z)
                worldIn.addEntity(projectileEntity)
                val message = String.format("Hello there, you have §7%d§r mana left.", mana.currentMana)
                playerIn.sendMessage(StringTextComponent(message))
            } else {
                playerIn.sendMessage(StringTextComponent("You don't have enough mana!"))
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn)
    }
}