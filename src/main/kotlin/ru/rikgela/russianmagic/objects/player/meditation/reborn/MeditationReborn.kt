
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraftforge.fml.network.PacketDistributor
import ru.rikgela.russianmagic.common.RMNetworkChannel
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana
import kotlin.math.max
import kotlin.math.min

open class MeditationReborn: IMeditationReborn {

    // Properties
    override var inMeditation = false
    override var meditationProgress = 0F
    override var sinkingSpeed = 0.001F
    private val baseDepthMeditation = 0.9F

    // To save
    override fun toByteArray(): ByteArray {
        val meditationProgress = java.lang.Float.floatToIntBits(this.meditationProgress)
        val sinkingSpeed = java.lang.Float.floatToIntBits(this.sinkingSpeed)
        return byteArrayOf(
            if (inMeditation) 1 else 0,
            ((meditationProgress ushr 24) and 0xFFFF).toByte(),
            ((meditationProgress ushr 16) and 0xFFFF).toByte(),
            ((meditationProgress ushr 8) and 0xFFFF).toByte(),
            (meditationProgress and 0xFFFF).toByte(),
            ((sinkingSpeed ushr 24) and 0xFFFF).toByte(),
            ((sinkingSpeed ushr 16) and 0xFFFF).toByte(),
            ((sinkingSpeed ushr 8) and 0xFFFF).toByte(),
            (sinkingSpeed and 0xFFFF).toByte(),
        )
    }

    // To load
    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        inMeditation = buff[i++].toInt() == 1
        meditationProgress = java.lang.Float.intBitsToFloat(
            buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        )
        sinkingSpeed = java.lang.Float.intBitsToFloat(
            buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        )
        return i
    }

    // Initializators
    companion object {
        fun withParams(inMeditation: Boolean, meditationProgress: Float, sinkingSpeed: Float): MeditationReborn {
            val ret = MeditationReborn()
            ret.inMeditation = inMeditation
            ret.meditationProgress = meditationProgress
            ret.sinkingSpeed = sinkingSpeed
            return ret
        }
        fun fromPlayer(player: PlayerEntity): MeditationReborn {
            if (MEDITATION_REBORN_CAP != null) {
                return player.getCapability(MEDITATION_REBORN_CAP!!).orElse(MeditationReborn()) as MeditationReborn
            }
            return MeditationReborn()
        }
    }

    override fun copy(playerProperties: IMeditationReborn) {
        this.inMeditation = playerProperties.inMeditation
        this.meditationProgress = playerProperties.meditationProgress
        this.sinkingSpeed = playerProperties.sinkingSpeed
    }

    // Important

    override fun sendToPlayer(player: ServerPlayerEntity) {
        RMNetworkChannel.send(PacketDistributor.PLAYER.with { player }, MeditationRebornNetwork(this.toByteArray()))
    }

    override fun playerTick(playerIn: ServerPlayerEntity) {
        val playerMana = PlayerMana.fromPlayer(playerIn)
        if (this.inMeditation) {
            if (this.meditationProgress < 1F) {
                this.meditationProgress = min(this.meditationProgress + this.sinkingSpeed, 1F)
                playerMana.meditationStart(this.baseDepthMeditation * this.meditationProgress)
            }
        }
        else{
            if (this.meditationProgress > 0F) {
                this.meditationProgress = max(this.meditationProgress - this.sinkingSpeed, 0F)
                playerMana.meditationStart(this.baseDepthMeditation * this.meditationProgress)
            }
            else{
                playerMana.meditationStop()
            }
        }
    }

    // Methods
    override fun startMeditation(playerIn: ServerPlayerEntity) {
        this.inMeditation = true
        sendToPlayer(playerIn)
    }

    override fun stopMeditation(playerIn: ServerPlayerEntity) {
        this.inMeditation = false
        sendToPlayer(playerIn)
    }

    fun sinkingSpeedRefresh(reborn: Reborn){
        this.sinkingSpeed = (this.sinkingSpeed / (reborn.rebornStage - 1)) * reborn.rebornStage
    }
}

