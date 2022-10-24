package ru.rikgela.russianmagic.objects.player.magichealth

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.MOD_ID

class MagicHealthCapabilityHandler {
    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<Entity>) {
        if (event.`object` is PlayerEntity) {
            event.addCapability(MAGIC_HEALTH_CAP, MagicHealthProvider(MagicHealth()))
        }
    }

    companion object {
        val MAGIC_HEALTH_CAP = ResourceLocation(MOD_ID, "magic_health")
    }
}