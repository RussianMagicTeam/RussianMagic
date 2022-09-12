package ru.rikgela.russianmagic.objects.player.mana

import PlayerManaProvider
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.MOD_ID

class PlayerManaCapabilityHandler {
    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<Entity>) {
        if (event.`object` is PlayerEntity) {
            event.addCapability(PLAYER_MANA_CAP, PlayerManaProvider(PlayerMana()))
        }
    }

    companion object {
        val PLAYER_MANA_CAP = ResourceLocation(MOD_ID, "player_mana")
    }
}