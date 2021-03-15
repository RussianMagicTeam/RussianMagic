package ru.rikgela.russianmagic.mana

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide
import ru.rikgela.russianmagic.MOD_ID

class ManaCapabilityHandler {
    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<Entity>) {
        if (event.`object` is PlayerEntity) {
            event.addCapability(MANA_CAP, ManaProvider(PlayerMana()))
        } else {
            if (event.`object` is IManaReceiver) {
                event.addCapability(MANA_CAP, ManaProvider(Mana()))
                if (event.`object` is IManaReceiver) {
                    event.addCapability(MANA_RECEIVER_CAP,
                            ManaReceiverProvider(ManaReceiver(Mana())))
                }
            }
            if (event.`object` is IManaSpreader) {
                event.addCapability(MANA_CAP, ManaProvider(Mana()))
            }
        }
    }

    companion object {
        val MANA_CAP = ResourceLocation(MOD_ID, "mana")
        val MANA_RECEIVER_CAP = ResourceLocation(MOD_ID, "manareceiver")
    }
}

class ManaEventHandler {
    @SubscribeEvent
    fun onPlayerLogsIn(event: PlayerEvent.PlayerLoggedInEvent) {
        val player: PlayerEntity = event.player
        if (MANA_CAP != null) {
            val mana: IPlayerMana = player.getCapability(MANA_CAP!!, null).orElse(PlayerMana()) as IPlayerMana
            if (player is ServerPlayerEntity)
                mana.sendToPlayer(player)
        } else {
            player.sendMessage(StringTextComponent("Mana not registered!"))
        }
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
            val mana = PlayerMana.fromPlayer(event.player)
            mana.tick()
            mana.sendToPlayer(event.player as ServerPlayerEntity)
        }
    }
}