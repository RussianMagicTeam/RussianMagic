package ru.rikgela.russianmagic.objects.player.mana

import net.minecraft.client.Minecraft
import net.minecraft.network.PacketBuffer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

class PlayerManaNetwork(private val playerManaBytes: ByteArray) {
    companion object {
        fun fromPacketBuffer(pb: PacketBuffer): PlayerManaNetwork {
            return PlayerManaNetwork(pb.readByteArray())
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeByteArray(playerManaBytes)
    }

    @OnlyIn(Dist.CLIENT)
    fun handle(ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()?.enqueueWork {
            PLAYER_MANA_CAP?.let {
                Minecraft.getInstance().player?.getCapability(it)?.orElse(PlayerMana())?.loadFromByteArray(playerManaBytes)
            }
        }
        ctx.get()?.packetHandled = true
    }
}