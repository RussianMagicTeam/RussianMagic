package ru.rikgela.russianmagic.util.helpers

import net.minecraft.client.Minecraft
import net.minecraft.client.util.InputMappings
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import org.lwjgl.glfw.GLFW

object KeyboardHelper {
    private val WINDOW = Minecraft.getInstance().mainWindow.handle

    @get:OnlyIn(Dist.CLIENT)
    val isHoldingShift: Boolean
        get() = InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT)

    @get:OnlyIn(Dist.CLIENT)
    val isHoldingCtrl: Boolean
        get() = InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL)
}