package ru.rikgela.russianmagic.client

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.client.gui.GuiManaHUD

@OnlyIn(Dist.CLIENT)
class HUDEventHandler {
    private val manaHUD = GuiManaHUD()


    @SubscribeEvent
    fun renderEntityHUD(event: RenderGameOverlayEvent.Text) {
        manaHUD.drawHUD()
        manaHUD.printHud()
    }
}