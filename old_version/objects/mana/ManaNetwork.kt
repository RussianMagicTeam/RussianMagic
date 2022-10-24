package ru.rikgela.russianmagic.objects.mana

import net.minecraft.client.Minecraft
import net.minecraft.network.PacketBuffer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier


class ManaMessage(
    private val manaBytes: ByteArray
) {
    companion object {
        fun fromPacketBuffer(pb: PacketBuffer): ManaMessage {
            return ManaMessage(pb.readByteArray())
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeByteArray(manaBytes)
    }

    @OnlyIn(Dist.CLIENT)
    fun handle(ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()?.enqueueWork {
            MANA_CAP?.let {
                Minecraft.getInstance().player?.getCapability(it)?.orElse(Mana())?.loadFromByteArray(manaBytes)
            }
        }
        ctx.get()?.packetHandled = true
    }
}