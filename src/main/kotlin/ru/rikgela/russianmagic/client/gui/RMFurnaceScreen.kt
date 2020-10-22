package ru.rikgela.russianmagic.client.gui

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.container.RMFurnaceContainer

class RMFurnaceScreen(private val screenContainer: RMFurnaceContainer, inv: PlayerInventory, titleIn: ITextComponent) : ContainerScreen<RMFurnaceContainer?>(screenContainer, inv, titleIn) {
    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f)
        minecraft!!.getTextureManager().bindTexture(TEXTURE)
        this.blit(guiLeft, guiTop, 0, 0, xSize, ySize)
        this.blit(guiLeft + 79, guiTop + 35, 176, 0, container!!.smeltProgressionScaled, 16)
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY)
        font.drawString(title.formattedText, 8.0f, 8.0f, 0x404040)
        val curMana = screenContainer.tileEntity.manaReceiver.currentMana
        val maxMana = screenContainer.tileEntity.manaReceiver.maxMana
        font.drawString("curMana: $curMana, maxMana: $maxMana", 8.0f, 16.0f, 0x404040)
        font.drawString(playerInventory.displayName.formattedText, 8.0f, 69.0f, 0x404040)
    }

    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.renderBackground()
        super.render(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    companion object {
        private val TEXTURE = ResourceLocation(MOD_ID,
                "textures/gui/rm_furnace_screen.png")
    }

    init {
        guiLeft = 0
        guiTop = 0
        xSize = 176
        ySize = 166
    }
}