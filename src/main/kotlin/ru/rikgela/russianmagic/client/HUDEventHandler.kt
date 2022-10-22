package ru.rikgela.russianmagic.client

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.client.gui.ManaHUD
import ru.rikgela.russianmagic.client.gui.MagicHealthHUD
import ru.rikgela.russianmagic.client.gui.MeditationRebornHUD
import ru.rikgela.russianmagic.client.gui.RebornHUD

@OnlyIn(Dist.CLIENT)
class HUDEventHandler {
    private val manaHUD = ManaHUD()
    private val rebornHUD = RebornHUD()
    private val magicHealthHUD = MagicHealthHUD()
    private val meditationRebornHUD = MeditationRebornHUD()
    @SubscribeEvent
    fun renderEntityHUD(event: RenderGameOverlayEvent.Text) {
        manaHUD.drawHUD()
        manaHUD.printHud()
        rebornHUD.drawHUD()
        rebornHUD.printHud()
        magicHealthHUD.drawHUD()
        magicHealthHUD.printHud()
        meditationRebornHUD.drawHUD()
        meditationRebornHUD.printHud()
    }
}