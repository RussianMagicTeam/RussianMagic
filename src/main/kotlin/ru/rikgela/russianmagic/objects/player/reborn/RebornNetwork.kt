package ru.rikgela.russianmagic.objects.player.reborn

import net.minecraft.client.Minecraft
import net.minecraft.network.PacketBuffer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

class RebornNetwork(
    private val rebornBytes: ByteArray
) {
    companion object {
        fun fromPacketBuffer(pb: PacketBuffer): RebornNetwork {
            return RebornNetwork(pb.readByteArray())
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeByteArray(rebornBytes)
    }

    @OnlyIn(Dist.CLIENT)
    fun handle(ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()?.enqueueWork {
            REBORN_CAP?.let {
                Minecraft.getInstance().player?.getCapability(it)?.orElse(Reborn())?.loadFromByteArray(rebornBytes)
            }
        }
        ctx.get()?.packetHandled = true
    }
}