package ru.rikgela.russianmagic.objects.player.meditation.reborn

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.MOD_ID

class MeditationRebornCapabilityHandler {
    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<Entity>) {
        if (event.`object` is PlayerEntity) {
            event.addCapability(MEDITATION_REBORN_CAP, MeditationRebornProvider(MeditationReborn()))
        }
    }

    companion object {
        val MEDITATION_REBORN_CAP = ResourceLocation(MOD_ID, "meditation_reborn")
    }
}