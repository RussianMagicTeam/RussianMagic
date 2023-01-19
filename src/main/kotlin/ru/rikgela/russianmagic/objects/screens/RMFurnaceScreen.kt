package ru.rikgela.russianmagic.objects.screens

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.resources.language.I18n
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory
import ru.rikgela.russianmagic.objects.menutypes.AbstractRMFurnaceMenuType
import java.awt.Color
import java.text.DecimalFormat

class RMFurnaceScreen(
    private val abstractRMFurnaceMenuType: AbstractRMFurnaceMenuType,
    inv: Inventory,
    titleIn: Component,
    private val TEXTURE: ResourceLocation
) : AbstractContainerScreen<AbstractRMFurnaceMenuType>(abstractRMFurnaceMenuType, inv, titleIn){

    override fun renderBg(pPoseStack: PoseStack, pPartialTick: Float, pMouseX: Int, pMouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x = (width - imageWidth) / 2
        val y = (height - imageHeight) / 2
        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight)
        renderProgressArrow(pPoseStack, x, y)
//        this.renderTooltip(pPoseStack, )

//        energyInfoArea.draw(pPoseStack)
//        renderer.render(pPoseStack, x + 55, y + 15, menu.getFluidStack())
    }


    private fun renderProgressArrow(pPoseStack: PoseStack, x: Int, y: Int) {
        if (menu.isCrafting) {
            blit(pPoseStack, guiLeft, guiTop, 0, 0, xSize, ySize)
            blit(pPoseStack, guiLeft + 79, guiTop + 35, 176, 0, menu.scaledProgress, 16)
        }
    }

    override fun render(pPoseStack: PoseStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(pPoseStack)
        super.render(pPoseStack, mouseX, mouseY, delta)
        renderTooltip(pPoseStack, mouseX, mouseY - 20)
        val curMana = abstractRMFurnaceMenuType.blockEntity!!.currentMana
        val maxMana = abstractRMFurnaceMenuType.blockEntity!!.baseMaxMana
        val mana = I18n.get("capability.russianmagic.mana") + ": $curMana/$maxMana"
        font.draw(pPoseStack, mana, guiLeft + 8.0f, guiTop + 16.0f, 0x404040)
        val df = DecimalFormat("00.00")
        val rate = "Rate: " + df.format(abstractRMFurnaceMenuType.blockEntity!!.rate * 100) + "%"
        font.draw(pPoseStack, rate, guiLeft + 8.0f, guiTop + 24.0f, 0x404040)
        renderMana(pPoseStack)
    }

    fun renderMana(pPoseStack: PoseStack){
        val currentMana = this.abstractRMFurnaceMenuType.blockEntity!!.currentMana
        val maxMana = this.abstractRMFurnaceMenuType.blockEntity!!.baseMaxMana
        val x = guiLeft + 6
        val y = guiTop + 22
        val hudWidth = 12
        val hudHeight = 40
        var color = 0x337CFF
        if (currentMana > maxMana) {
            val lambda = java.lang.Float.min((currentMana - maxMana) / (maxMana * 2F), 1F)
            val colorRed = (0x33 - ((0x33 - 0xFF)).toFloat() * lambda).toInt()
            val colorGreen = (0x7C - ((0x7C - 0x00)).toFloat() * lambda).toInt()
            val colorBlue = (0xFF - ((0xFF - 0x00)).toFloat() * lambda).toInt()
            color = colorRed * 0x10000 + colorGreen * 0x100 + colorBlue
        }
        fill(pPoseStack, x, y, x + hudWidth, y + hudHeight, -0x1000000 or 0xE9EAE9)
        fillGradient(
            pPoseStack,
            x + hudWidth,
            y + hudHeight,
            x,
            y + hudHeight - (hudHeight * java.lang.Float.min(currentMana.toFloat() / maxMana, 1F)).toInt(),
            -0x1000000 or color,
            Color(-0x1000000 or 0x1145A1).darker().rgb)
    }

//    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
//        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f)
//        minecraft!!.getTextureManager().bindTexture(TEXTURE)
//        this.blit(guiLeft, guiTop, 0, 0, xSize, ySize)
//        this.blit(guiLeft + 79, guiTop + 35, 176, 0, container!!.smeltProgressionScaled, 16)
//    }
//
//    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
//        super.drawGuiContainerForegroundLayer(mouseX, mouseY)
//        font.drawString(title.formattedText, 8.0f, 8.0f, 0x404040)
//        val curMana = screenContainerMarble.tileEntityFurnace.currentMana
//        val maxMana = screenContainerMarble.tileEntityFurnace.baseMaxMana
//        val mana = I18n.format("capability.russianmagic.mana") + ": $curMana/$maxMana"
//        font.drawString(mana, 8.0f, 16.0f, 0x404040)
//        font.drawString(playerInventory.displayName.formattedText, 8.0f, 69.0f, 0x404040)
//        val df = DecimalFormat("00.00")
//        val rate = "Rate: " + df.format(screenContainerMarble.tileEntityFurnace.rate * 100) + "%"
//        font.drawString(rate, 8.0f, 24.0f, 0x404040)
//    }
//
//    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
//        this.renderBackground()
//        super.render(mouseX, mouseY, partialTicks)
//        renderHoveredToolTip(mouseX, mouseY)
//    }
//
//    init {
//        guiLeft = 0
//        guiTop = 0
//        xSize = 176
//        ySize = 166
//    }
}