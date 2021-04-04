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
        var color = "337CFF".toInt(16)
        if (mana.currentMana > mana.maxMana) {
            val colorRed = ("33".toInt(16) - (("33".toInt(16) - "FF".toInt(16))).toFloat() * min((mana.currentMana - mana.maxMana).toFloat() / (mana.maxMana.toFloat() * 2F), 1F)).toInt()
            val colorGreen = ("7C".toInt(16) - (("7C".toInt(16) - "00".toInt(16))).toFloat() * min((mana.currentMana - mana.maxMana).toFloat() / (mana.maxMana.toFloat() * 2F), 1F)).toInt()
            val colorBlue = ("FF".toInt(16) - (("FF".toInt(16) - "00".toInt(16))).toFloat() * min((mana.currentMana - mana.maxMana).toFloat() / (mana.maxMana.toFloat() * 2F), 1F)).toInt()
            color = colorRed * "10000".toInt(16) + colorGreen * "100".toInt(16) + colorBlue
        }
        //(("337CFF".toInt(16) - ("337CFF".toInt(16) - "FF0000".toInt(16))).toFloat() * max((mana.currentMana - mana.maxMana).toFloat() / (mana.maxMana.toFloat() * 2F), 0F)).toInt()
        fill(x, y, width + x, y - height, -0x1000000 or "C9CAB9".toInt(16))
        fillGradient(x, y, (width * min(mana.currentMana.toFloat() / mana.maxMana.toFloat(), 1F)).toInt() + x, y - height, -0x1000000 or color, Color(-0x1000000 or "1145A1".toInt(16)).darker().rgb)
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