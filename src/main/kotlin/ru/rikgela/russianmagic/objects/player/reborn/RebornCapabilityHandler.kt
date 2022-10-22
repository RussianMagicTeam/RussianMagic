package ru.rikgela.russianmagic.objects.player.reborn

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.MOD_ID

class RebornCapabilityHandler {
    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<Entity>) {
        if (event.`object` is PlayerEntity) {
            event.addCapability(REBORN_CAP, RebornProvider(Reborn()))
        }
    }

    companion object {
        val REBORN_CAP = ResourceLocation(MOD_ID, "player_reborn")
    }
}
