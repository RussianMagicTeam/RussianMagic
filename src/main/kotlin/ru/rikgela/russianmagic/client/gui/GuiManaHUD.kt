package ru.rikgela.russianmagic.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.AbstractGui
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.text.TextFormatting
import ru.rikgela.russianmagic.mana.Mana
import java.awt.Color

class GuiManaHUD(
        val height: Int = 10,
        val width: Int = 100,
        val xPos: Int = 1,
        val yPos: Int = 95,
) : AbstractGui() {

    fun drawHUD() {
        val mana = Mana.fromPlayer(minecraft.player as PlayerEntity)

        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100

        fill(x, y, width + x, y - height, -0x1000000 or "C9CAB9".toInt(16))
        fillGradient(x, y, (width * mana.mana / mana.maxMana) + x, y - height, -0x1000000 or "337CFF".toInt(16), Color(-0x1000000 or "1145A1".toInt(16)).darker().rgb)

    }

    fun printHud() {
        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100
        val mana = Mana.fromPlayer(minecraft.player as PlayerEntity)
        Minecraft.getInstance().fontRenderer.drawString(mana.mana.toString(), x.toFloat() + 3, y.toFloat() - 8, TextFormatting.WHITE.color!!)
    }

    companion object {
        private val minecraft = Minecraft.getInstance()
    }
}