package ru.rikgela.russianmagic.objects.player.mana

import PLAYER_MANA_CAP
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide

class PlayerManaEventHandler {
    @SubscribeEvent
    fun onPlayerLogsIn(event: PlayerEvent.PlayerLoggedInEvent) {
        val player: PlayerEntity = event.player
        if (PLAYER_MANA_CAP != null) {
            val playerMana: IPlayerMana = player.getCapability(PLAYER_MANA_CAP!!, null).orElse(PlayerMana()) as IPlayerMana
            if (player is ServerPlayerEntity)
                playerMana.sendToPlayer(player)
        } else {
            player.sendMessage(StringTextComponent("Player_mana not registered!"))
        }
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
            val playerMana = PlayerMana.fromPlayer(event.player)
            playerMana.playerTick(event.player as ServerPlayerEntity)
            playerMana.sendToPlayer(event.player as ServerPlayerEntity)
        }
    }
}
