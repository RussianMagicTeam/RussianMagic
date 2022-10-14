package ru.rikgela.russianmagic.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.AbstractGui
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.objects.player.magichealth.MagicHealth
import java.awt.Color

@OnlyIn(Dist.CLIENT)
class MagicHealthHUD(
    private val height: Int = 10,
    private val width: Int = 100,
    private val xPos: Int = 1,
    private val yPos: Int = 90,
) : AbstractGui() {

    fun drawHUD() {
        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100
        val magicHealth = MagicHealth.fromPlayer(minecraft.player as PlayerEntity)
        val color = 0x33FF4D
        fill(x, y, width + x, y - height, -0x1000000 or 0xC9CAB9)
        fillGradient(x, y,
            width * kotlin.math.min(magicHealth.curMagicHealth / magicHealth.maxMagicHealth, 1) + x,
            y - height,
            -0x1000000 or color,
            Color(-0x1000000 or 0x1D5729).darker().rgb)
    }

    fun printHud() {
        val x = minecraft.mainWindow.scaledWidth * xPos / 100
        val y = minecraft.mainWindow.scaledHeight * yPos / 100
        val magicHealth = MagicHealth.fromPlayer(minecraft.player as PlayerEntity)
        Minecraft.getInstance().fontRenderer.drawString(
            ((magicHealth.curMagicHealth / magicHealth.maxMagicHealth) * 100).toString() + "%",
            x.toFloat() + 3,
            y.toFloat() - 8,
            TextFormatting.WHITE.color!!
        )

    }

    companion object {
        private val minecraft = Minecraft.getInstance()
    }
}