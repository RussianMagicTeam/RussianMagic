import net.minecraft.client.Minecraft
import net.minecraft.network.PacketBuffer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

class MeditationRebornNetwork(
    private val meditationRebornBytes: ByteArray
) {
    companion object {
        fun fromPacketBuffer(pb: PacketBuffer): MeditationRebornNetwork {
            return MeditationRebornNetwork(pb.readByteArray())
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeByteArray(meditationRebornBytes)
    }

    @OnlyIn(Dist.CLIENT)
    fun handle(ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()?.enqueueWork {
            MEDITATION_REBORN_CAP?.let {
                Minecraft.getInstance().player?.getCapability(it)?.orElse(MeditationReborn())?.loadFromByteArray(meditationRebornBytes)
            }
        }
        ctx.get()?.packetHandled = true
    }
}