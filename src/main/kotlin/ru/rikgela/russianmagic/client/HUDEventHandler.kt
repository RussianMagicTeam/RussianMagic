package ru.rikgela.russianmagic.client

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.client.gui.GuiManaHUD
import ru.rikgela.russianmagic.client.gui.GuiXpHUD

@OnlyIn(Dist.CLIENT)
class HUDEventHandler {
    private val manaHUD = GuiManaHUD()
    private val xpHUD = GuiXpHUD()

    @SubscribeEvent
    fun renderEntityHUD(event: RenderGameOverlayEvent.Text) {
        xpHUD.drawHUD()
        xpHUD.printHud()
        manaHUD.drawHUD()
        manaHUD.printHud()
    }
}