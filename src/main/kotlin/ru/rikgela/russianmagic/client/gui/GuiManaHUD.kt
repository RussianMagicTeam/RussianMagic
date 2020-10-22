package ru.rikgela.russianmagic.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.AbstractGui
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.mana.Mana
import java.awt.Color

@OnlyIn(Dist.CLIENT)
class GuiManaHUD(
        private val height: Int = 10,
        private val width: Int = 100,
        private val xPos: Int = 1,
        private val yPos: Int = 95,
) : AbstractGui() {

    fun drawHUD() {
        val mana = Mana.fromPlayer(minecraft.player as PlayerEntity)

        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100

        fill(x, y, width + x, y - height, -0x1000000 or "C9CAB9".toInt(16))
        fillGradient(x, y, (width * mana.currentMana / mana.maxMana) + x, y - height, -0x1000000 or "337CFF".toInt(16), Color(-0x1000000 or "1145A1".toInt(16)).darker().rgb)

    }

    fun printHud() {
        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100
        val mana = Mana.fromPlayer(minecraft.player as PlayerEntity)
        Minecraft.getInstance().fontRenderer.drawString(mana.currentMana.toString(), x.toFloat() + 3, y.toFloat() - 8, TextFormatting.WHITE.color!!)
    }

    companion object {
        private val minecraft = Minecraft.getInstance()
    }
}