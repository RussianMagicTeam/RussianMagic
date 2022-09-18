package ru.rikgela.russianmagic.objects.player

import MAGIC_HEALTH_CAP
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraftforge.fml.network.PacketDistributor
import ru.rikgela.russianmagic.common.RMNetworkChannel
import ru.rikgela.russianmagic.objects.player.magichealth.MagicHealthNetwork
import java.lang.Integer.max
import java.lang.Integer.min

open class MagicHealth: IMagicHealth {

    // Properties
    override var curMagicHealth = 1000
    override var maxMagicHealth = 1000

    // To save
    override fun toByteArray(): ByteArray {
        return byteArrayOf(
            ((curMagicHealth ushr 24) and 0xFFFF).toByte(),
            ((curMagicHealth ushr 16) and 0xFFFF).toByte(),
            ((curMagicHealth ushr 8) and 0xFFFF).toByte(),
            (curMagicHealth and 0xFFFF).toByte(),
            ((maxMagicHealth ushr 24) and 0xFFFF).toByte(),
            ((maxMagicHealth ushr 16) and 0xFFFF).toByte(),
            ((maxMagicHealth ushr 8) and 0xFFFF).toByte(),
            (maxMagicHealth and 0xFFFF).toByte(),
        )
    }

    // To load
    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        curMagicHealth = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        maxMagicHealth = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        return i
    }

    // Initializators
    companion object {
        fun withParams(startCurMagicHealth: Int, startMaxMagicHealth: Int): MagicHealth {
            val ret = MagicHealth()
            ret.curMagicHealth = startCurMagicHealth
            ret.maxMagicHealth = startMaxMagicHealth
            return ret
        }
        fun fromPlayer(player: PlayerEntity): MagicHealth {
            if (MAGIC_HEALTH_CAP != null) {
                return player.getCapability(MAGIC_HEALTH_CAP!!).orElse(MagicHealth()) as MagicHealth
            }
            return MagicHealth()
        }
    }

    override fun copy(playerProperties: IMagicHealth) {
        this.curMagicHealth = playerProperties.curMagicHealth
        this.maxMagicHealth = playerProperties.maxMagicHealth
    }

    // Important

    override fun sendToPlayer(player: ServerPlayerEntity) {
        RMNetworkChannel.send(PacketDistributor.PLAYER.with { player }, MagicHealthNetwork(this.toByteArray()))
    }

    // Methods
    override fun harmMagicHealth(points: Int){
        curMagicHealth = max(curMagicHealth-points, 0)
    }

    override fun healMagicHealth(points: Int){
        curMagicHealth = min(maxMagicHealth, points + curMagicHealth)
    }
}
