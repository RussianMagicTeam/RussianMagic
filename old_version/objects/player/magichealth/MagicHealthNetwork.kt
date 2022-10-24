package ru.rikgela.russianmagic.objects.player.magichealth

import net.minecraft.client.Minecraft
import net.minecraft.network.PacketBuffer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

class MagicHealthNetwork(
    private val magicHealthBytes: ByteArray
) {
    companion object {
        fun fromPacketBuffer(pb: PacketBuffer): MagicHealthNetwork {
            return MagicHealthNetwork(pb.readByteArray())
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeByteArray(magicHealthBytes)
    }

    @OnlyIn(Dist.CLIENT)
    fun handle(ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()?.enqueueWork {
            MAGIC_HEALTH_CAP?.let {
                Minecraft.getInstance().player?.getCapability(it)?.orElse(MagicHealth())?.loadFromByteArray(magicHealthBytes)
            }
        }
        ctx.get()?.packetHandled = true
    }
}