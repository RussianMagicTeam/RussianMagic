package ru.rikgela.russianmagic.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.AbstractGui
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.objects.player.reborn.Reborn
import java.awt.Color
import java.lang.Float.min

@OnlyIn(Dist.CLIENT)
class RebornHUD(
    private val height: Int = 10,
    private val width: Int = 100,
    private val xPos: Int = 1,
    private val yPos: Int = 92,
) : AbstractGui() {

    fun drawHUD() {
        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100
        val reborn = Reborn.fromPlayer(minecraft.player as PlayerEntity)
        var color = 0x00CC00
        if (reborn.isInReborn) {
            val lambda = min((reborn.rebornProgress) / (100F), 1F)
            val colorRed = (0x00 - ((0x00 - 0x00)).toFloat() * lambda).toInt()
            val colorGreen = (0xCC - ((0xCC - 0x99)).toFloat() * lambda).toInt()
            val colorBlue = (0x00 - ((0x00 - 0x99)).toFloat() * lambda).toInt()
            color = colorRed * 0x10000 + colorGreen * 0x100 + colorBlue
        }
        fill(x, y, width + x, y - height, -0x1000000 or 0xC9CAB9)
        fillGradient(x, y,
            (width * min(reborn.rebornPrepare / 100F, 1F)).toInt() + x,
            y - height,
            -0x1000000 or color,
            Color(-0x1000000 or 0x1D5729).darker().rgb)
    }

    fun printHud() {
        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100
        val reborn = Reborn.fromPlayer(minecraft.player as PlayerEntity)
        Minecraft.getInstance().fontRenderer.drawString(
            reborn.rebornStage.toString() + ": " + (reborn.rebornPrepare).toInt().toString() + "%", x.toFloat() + 3, y.toFloat() - 8, TextFormatting.WHITE.color!!)
    }

    companion object {
        private val minecraft = Minecraft.getInstance()
    }
}