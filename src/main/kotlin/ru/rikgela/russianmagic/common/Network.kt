package ru.rikgela.russianmagic.common

import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.network.NetworkEvent
import net.minecraftforge.fml.network.NetworkRegistry
import ru.rikgela.russianmagic.MOD_ID
import java.util.function.Supplier


private const val PROTOCOL_VERSION = "1"
val RMNetworkChannel = NetworkRegistry.newSimpleChannel(
        ResourceLocation(MOD_ID, "main"),
        { PROTOCOL_VERSION },
        { anObject: String? -> PROTOCOL_VERSION.equals(anObject) }
) { anObject: String? -> PROTOCOL_VERSION.equals(anObject) }

class RMNetworkMessage(
        val msg: String
) {
    companion object {
        fun fromString(msg: String): RMNetworkMessage {
            return RMNetworkMessage(msg)
        }

        fun fromPacketBuffer(pb: PacketBuffer): RMNetworkMessage {
            return fromString(pb.readByteArray().decodeToString())
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeString(msg)
    }

    fun handle(ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()?.enqueueWork {
            println(msg)
        }
        ctx.get()?.packetHandled = true
    }
}


