import MAGIC_HEALTH_CAP
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide
import ru.rikgela.russianmagic.objects.player.IMagicHealth
import ru.rikgela.russianmagic.objects.player.MagicHealth
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana

class MagicHealthEventHandler {
    @SubscribeEvent
    fun onPlayerLogsIn(event: PlayerEvent.PlayerLoggedInEvent) {
        val player: PlayerEntity = event.player
        if (MAGIC_HEALTH_CAP != null) {
            val magicHealth: IMagicHealth = player.getCapability(MAGIC_HEALTH_CAP!!, null).orElse(MagicHealth()) as IMagicHealth
            if (player is ServerPlayerEntity)
                magicHealth.sendToPlayer(player)
        } else {
            player.sendMessage(StringTextComponent("MagicHealth not registered!"))
        }
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
            val magicHealth = MagicHealth.fromPlayer(event.player)
            magicHealth.playerTick(event.player as ServerPlayerEntity)
            magicHealth.sendToPlayer(event.player as ServerPlayerEntity)
        }
    }
}