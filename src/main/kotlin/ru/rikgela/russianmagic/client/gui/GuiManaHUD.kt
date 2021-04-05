package ru.rikgela.russianmagic.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.AbstractGui
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.objects.mana.PlayerMana
import java.awt.Color
import java.lang.Float.min

@OnlyIn(Dist.CLIENT)
class GuiManaHUD(
        private val height: Int = 10,
        private val width: Int = 100,
        private val xPos: Int = 1,
        private val yPos: Int = 95,
) : AbstractGui() {

    fun drawHUD() {
        val mana = PlayerMana.fromPlayer(minecraft.player as PlayerEntity)

        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100
        var color = 0x337CFF
        if (mana.currentMana > mana.maxMana) {
            val lambda = min((mana.currentMana - mana.maxMana).toFloat() / (mana.maxMana.toFloat() * 2F), 1F)
            val colorRed = (0x33 - ((0x33 - 0xFF)).toFloat() * lambda).toInt()
            val colorGreen = (0x7C - ((0x7C - 0x00)).toFloat() * lambda).toInt()
            val colorBlue = (0xFF - ((0xFF - 0x00)).toFloat() * lambda).toInt()
            color = colorRed * 0x10000 + colorGreen * 0x100 + colorBlue
        }
        fill(x, y, width + x, y - height, -0x1000000 or 0xC9CAB9)
        fillGradient(x, y, (width * min(mana.currentMana.toFloat() / mana.maxMana.toFloat(), 1F)).toInt() + x, y - height, -0x1000000 or color, Color(-0x1000000 or 0x1145A1).darker().rgb)
    }

    fun printHud() {
        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100
        val mana = PlayerMana.fromPlayer(minecraft.player as PlayerEntity)
        Minecraft.getInstance().fontRenderer.drawString(mana.currentMana.toString(), x.toFloat() + 3, y.toFloat() - 8, TextFormatting.WHITE.color!!)
    }

    companion object {
        private val minecraft = Minecraft.getInstance()
    }
}