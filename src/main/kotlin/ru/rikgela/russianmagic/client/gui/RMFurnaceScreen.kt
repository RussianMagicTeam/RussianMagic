package ru.rikgela.russianmagic.client.gui

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import ru.rikgela.russianmagic.objects.container.AbstractRMFurnaceContainer
import java.text.DecimalFormat

class RMFurnaceScreen(
        private val screenContainerMarble: AbstractRMFurnaceContainer,
        inv: PlayerInventory,
        titleIn: ITextComponent,
        private val TEXTURE: ResourceLocation
) : ContainerScreen<AbstractRMFurnaceContainer?>(screenContainerMarble, inv, titleIn) {

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f)
        minecraft!!.getTextureManager().bindTexture(TEXTURE)
        this.blit(guiLeft, guiTop, 0, 0, xSize, ySize)
        this.blit(guiLeft + 79, guiTop + 35, 176, 0, container!!.smeltProgressionScaled, 16)
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY)
        font.drawString(title.formattedText, 8.0f, 8.0f, 0x404040)
        val curMana = screenContainerMarble.tileEntityFurnace.currentMana
        val maxMana = screenContainerMarble.tileEntityFurnace.baseMaxMana
        val Mana = I18n.format("capability.russianmagic.mana") + ": $curMana/$maxMana"
        font.drawString(Mana, 8.0f, 16.0f, 0x404040)
        font.drawString(playerInventory.displayName.formattedText, 8.0f, 69.0f, 0x404040)
        val df = DecimalFormat("00.00")
        val Rate = "Rate: " + df.format(screenContainerMarble.tileEntityFurnace.rate * 100) + "%"
        font.drawString(Rate, 8.0f, 24.0f, 0x404040)
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.renderBackground()
        super.render(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    init {
        guiLeft = 0
        guiTop = 0
        xSize = 176
        ySize = 166
    }
}