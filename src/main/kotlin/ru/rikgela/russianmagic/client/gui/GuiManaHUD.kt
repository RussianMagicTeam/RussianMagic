package ru.rikgela.russianmagic.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.AbstractGui
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.text.TextFormatting
import ru.rikgela.russianmagic.mana.Mana
import java.awt.Color

class GuiManaHUD : AbstractGui() {
    fun drawHUD() {
        val mana = Mana.fromPlayer(minecraft.player as PlayerEntity)
        val offsetLeft = 10
        var x = 100.0 //Length
        x += offsetLeft
        val y = minecraft.mainWindow.scaledHeight - 5
        val offsetStop = minecraft.mainWindow.scaledHeight - 15
        fill(offsetLeft, y, 100 + offsetLeft, offsetStop, -0x1000000 or "C9CAB9".toInt(16))
        fillGradient(x.toInt(), y, offsetLeft, offsetStop, -0x1000000 or "337CFF".toInt(16), Color(-0x1000000 or "1145A1".toInt(16)).darker().rgb)
        var i = 100
        while (i <= 1000) {
            val marker = 100 * (i / 1000.0 - 0.0) + offsetLeft
            fill(marker.toInt(), y, marker.toInt() + 1, offsetStop, -0x1000000 or "E4F10A".toInt(16))
            i += 100
        }

        Minecraft.getInstance().fontRenderer.drawString(mana.mana.toString(), x.toFloat() - 100, y.toFloat() - 10, TextFormatting.WHITE.color!!)
    }

    companion object {
        private val minecraft = Minecraft.getInstance()
    }
}