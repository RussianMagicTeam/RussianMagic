package ru.rikgela.russianmagic.objects.player.reborn

import REBORN_CAP
import Reborn
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide

class RebornEventHandler {
    @SubscribeEvent
    fun onPlayerLogsIn(event: PlayerEvent.PlayerLoggedInEvent) {
        val player: PlayerEntity = event.player
        if (REBORN_CAP != null) {
            val playerReborn: IReborn = player.getCapability(REBORN_CAP!!, null).orElse(Reborn()) as IReborn
            if (player is ServerPlayerEntity)
                playerReborn.sendToPlayer(player)
        } else {
            player.sendMessage(StringTextComponent("Player Reborn not registered!"))
        }
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
            val playerReborn = Reborn.fromPlayer(event.player)
            playerReborn.playerTick(event.player as ServerPlayerEntity)
            playerReborn.sendToPlayer(event.player as ServerPlayerEntity)
        }
    }
}
