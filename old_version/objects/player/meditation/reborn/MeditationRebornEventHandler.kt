package ru.rikgela.russianmagic.objects.player.meditation.reborn
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide

class MeditationRebornEventHandler {
    @SubscribeEvent
    fun onPlayerLogsIn(event: PlayerEvent.PlayerLoggedInEvent) {
        val player: PlayerEntity = event.player
        if (MEDITATION_REBORN_CAP != null) {
            val meditationReborn: IMeditationReborn = player.getCapability(
                MEDITATION_REBORN_CAP!!, null
            ).orElse(MeditationReborn()) as IMeditationReborn
            if (player is ServerPlayerEntity)
                meditationReborn.sendToPlayer(player)
        } else {
            player.sendMessage(StringTextComponent("MeditationReborn not registered!"))
        }
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
            val meditationReborn = MeditationReborn.fromPlayer(event.player)
            meditationReborn.playerTick(event.player as ServerPlayerEntity)
            meditationReborn.sendToPlayer(event.player as ServerPlayerEntity)
        }
    }
}