package ru.rikgela.russianmagic.mana

import net.minecraft.client.Minecraft
import net.minecraft.network.PacketBuffer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier


class ManaMessage(
        private val mana: Mana
) {
    companion object {
        fun fromPacketBuffer(pb: PacketBuffer): ManaMessage {
            val ret = Mana()
            ret.loadFromByteArray(pb.readByteArray())
            return ManaMessage(ret)
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeByteArray(mana.toByteArray())
    }

    @OnlyIn(Dist.CLIENT)
    fun handle(ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()?.enqueueWork {
            MANA_CAP?.let { Minecraft.getInstance().player?.getCapability(it)?.orElse(Mana())?.copy(mana) }
        }
        ctx.get()?.packetHandled = true
    }
}

class ManaReceiverMessage(
        private val manaReceiver: ManaReceiver<Mana>
) {
    companion object {
        fun fromPacketBuffer(pb: PacketBuffer): ManaReceiverMessage {
            val ret = ManaReceiver(Mana())
            ret.loadFromByteArray(pb.readByteArray())
            return ManaReceiverMessage(ret)
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeByteArray(manaReceiver.toByteArray())
    }

    @OnlyIn(Dist.CLIENT)
    fun handle(ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()?.enqueueWork {
            MANA_RECEIVER_CAP?.let {
                Minecraft.getInstance().player?.getCapability(it)?.orElse(ManaReceiver(Mana()))?.copy(manaReceiver)
            }
        }
        ctx.get()?.packetHandled = true
    }
}
