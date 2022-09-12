import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraftforge.fml.network.PacketDistributor
import org.apache.logging.log4j.core.jmx.Server
import ru.rikgela.russianmagic.common.RMNetworkChannel
import ru.rikgela.russianmagic.objects.player.MagicHealth
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana
import ru.rikgela.russianmagic.objects.player.reborn.IReborn
import ru.rikgela.russianmagic.objects.player.reborn.RebornNetwork
import kotlin.math.min

open class Reborn: IReborn {

    // Properties
    override var rebornPrepare = 0.0F
    override var isInReborn = false
    private var wasInReborn = false
    private var afkTick = 0
    override var rebornProgress: Float = 0.0F
    override var rebornStage = 1

    // To save
    override fun toByteArray(): ByteArray {
        val rebornPrepare = java.lang.Float.floatToIntBits(this.rebornPrepare)
        val rebornProgress = java.lang.Float.floatToIntBits(this.rebornProgress)
        val ret = byteArrayOf(
            ((rebornPrepare ushr 24) and 0xFFFF).toByte(),
            ((rebornPrepare ushr 16) and 0xFFFF).toByte(),
            ((rebornPrepare ushr 8) and 0xFFFF).toByte(),
            (rebornPrepare and 0xFFFF).toByte(),
            if (isInReborn) 1 else 0,
            if (wasInReborn) 1 else 0,
            ((afkTick ushr 24) and 0xFFFF).toByte(),
            ((afkTick ushr 16) and 0xFFFF).toByte(),
            ((afkTick ushr 8) and 0xFFFF).toByte(),
            (afkTick and 0xFFFF).toByte(),
            ((rebornProgress ushr 24) and 0xFFFF).toByte(),
            ((rebornProgress ushr 16) and 0xFFFF).toByte(),
            ((rebornProgress ushr 8) and 0xFFFF).toByte(),
            (rebornProgress and 0xFFFF).toByte(),
            ((rebornStage ushr 24) and 0xFFFF).toByte(),
            ((rebornStage ushr 16) and 0xFFFF).toByte(),
            ((rebornStage ushr 8) and 0xFFFF).toByte(),
            (rebornStage and 0xFFFF).toByte(),
        )
        return ret
    }

    // To load
    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        rebornPrepare = java.lang.Float.intBitsToFloat(buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        )
        isInReborn = buff[i++].toInt() == 1
        wasInReborn = buff[i++].toInt() == 1
        afkTick = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        rebornProgress = java.lang.Float.intBitsToFloat(buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        )
        rebornStage = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        return i
    }

    // Initializators
    companion object {
        fun withParams(rebornPrepare: Float, isInReborn: Boolean, rebornProgress: Float, rebornStage: Int): Reborn {
            val ret = Reborn()
            ret.rebornPrepare = rebornPrepare
            ret.isInReborn = isInReborn
            ret.rebornProgress = rebornProgress
            ret.rebornStage = rebornStage
            return ret
        }
        fun fromPlayer(player: PlayerEntity): Reborn {
            if (REBORN_CAP != null) {
                return player.getCapability(REBORN_CAP!!).orElse(Reborn()) as Reborn
            }
            return Reborn()
        }
    }

    override fun copy(playerProperties: IReborn) {
        this.rebornPrepare = playerProperties.rebornPrepare
        this.isInReborn = playerProperties.isInReborn
        this.rebornProgress = playerProperties.rebornProgress
        this.rebornStage = playerProperties.rebornStage
    }

    // Important

    override fun sendToPlayer(player: ServerPlayerEntity) {
        RMNetworkChannel.send(PacketDistributor.PLAYER.with { player }, RebornNetwork(this.toByteArray()))
    }

    override fun playerTick(playerIn: ServerPlayerEntity) {
        if(isInReborn){
            if(wasInReborn){
                rebornProgress += 1F
                if (rebornProgress == rebornStage * 1000F) {
                    rebornStage += 1
                    rebornProgress = 0F
                    rebornPrepare = 0F
                    wasInReborn = false
                    isInReborn = false
                }
            }
            else{
                // TO DO
                wasInReborn = true
                PlayerMana.fromPlayer(playerIn).blockMana()
            }
        }
        else{
            if (wasInReborn){
                rebornProgress = 0F
                rebornPrepare -= 5
                wasInReborn = false
                MagicHealth.fromPlayer(playerIn).harmMagicHealth(rebornProgress.toInt())
                PlayerMana.fromPlayer(playerIn).allowMana()
            }
            afkTick += 1
            if(afkTick == 20000){
                rebornPrepare += 0.1F
                afkTick = 0
            }
        }
    }

    // Methods
    fun addPrepare(points: Float){
        rebornPrepare = min(100F, points + rebornPrepare)
    }

    private fun startReborn(){
        isInReborn = true
    }

    private fun stopReborn(){
        isInReborn = false
    }


    private fun stageUp(player: ServerPlayerEntity){
        if (rebornPrepare >= 1F) {
            rebornStage += 1
            rebornPrepare = 0F
        }
    }
}

