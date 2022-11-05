package ru.rikgela.russianmagic.objects.screens

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory
import ru.rikgela.russianmagic.objects.menutypes.AbstractRMFurnaceMenuType

class RMFurnaceScreen(
    private val screenContainerMarble: AbstractRMFurnaceMenuType,
    inv: Inventory,
    titleIn: Component,
    private val TEXTURE: ResourceLocation
) : AbstractContainerScreen<AbstractRMFurnaceMenuType>(screenContainerMarble, inv, titleIn){

    override fun renderBg(pPoseStack: PoseStack, pPartialTick: Float, pMouseX: Int, pMouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x = (width - imageWidth) / 2
        val y = (height - imageHeight) / 2
        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight)
        renderProgressArrow(pPoseStack, x, y)
//        energyInfoArea.draw(pPoseStack)
//        renderer.render(pPoseStack, x + 55, y + 15, menu.getFluidStack())
    }


    private fun renderProgressArrow(pPoseStack: PoseStack, x: Int, y: Int) {
        if (menu.isCrafting) {
            blit(pPoseStack, x + 105, y + 33, 176, 0, 8, menu.scaledProgress)
        }
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