package ru.rikgela.russianmagic.mana

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.nbt.ByteArrayNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.fml.network.PacketDistributor
import ru.rikgela.russianmagic.common.RMNetworkChannel
import java.lang.Float.floatToIntBits
import java.lang.Float.intBitsToFloat
import java.lang.Integer.max
import kotlin.math.min

open class Mana : IMana {
    companion object {
        fun withParams(startManaCount: Int, maxManaCount: Int): Mana {
            val ret = Mana()
            ret.currentMana = startManaCount
            ret.maxMana = maxManaCount
            return ret
        }
    }

    override fun copy(mana: IMana) {
        this.currentMana = mana.currentMana
    }

    override fun toByteArray(): ByteArray {
        return byteArrayOf(
                ((currentMana ushr 24) and 0xFFFF).toByte(),
                ((currentMana ushr 16) and 0xFFFF).toByte(),
                ((currentMana ushr 8) and 0xFFFF).toByte(),
                (currentMana and 0xFFFF).toByte(),
                ((maxMana ushr 24) and 0xFFFF).toByte(),
                ((maxMana ushr 16) and 0xFFFF).toByte(),
                ((maxMana ushr 8) and 0xFFFF).toByte(),
                (maxMana and 0xFFFF).toByte()
        )
    }

    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        currentMana = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)

        maxMana = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        return i
    }

    override var currentMana = 250
    override var maxMana = 1000

    override fun consume(points: Int): Boolean {
        if (currentMana >= points) {
            currentMana -= points
            return true
        }
        return false
    }

    override fun fill(points: Int): Int {
        val count = min(maxMana - currentMana, points)
        currentMana += count
        return points - count
    }
}

class PlayerMana : Mana(), IPlayerMana {
    companion object {
        fun withParams(startManaCount: Int, maxManaCount: Int, manaPerTick: Float): PlayerMana {
            val ret = PlayerMana()
            ret.currentMana = startManaCount
            ret.maxMana = maxManaCount
            ret.manaPerTick = manaPerTick
            return ret
        }

        fun fromPlayer(player: PlayerEntity): PlayerMana {
            if (MANA_CAP != null) {
                return player.getCapability(MANA_CAP!!).orElse(PlayerMana()) as PlayerMana
            }
            return PlayerMana()
        }
    }

    override var manaPerTick = 1F
    private var ticks = 0

    override fun sendToPlayer(player: ServerPlayerEntity) {
        RMNetworkChannel.send(PacketDistributor.PLAYER.with { player }, ManaMessage(this))
    }

    override fun consume(points: Int, player: ServerPlayerEntity): Boolean {
        return if (consume(points)) {
            sendToPlayer(player)
            true
        } else {
            false
        }
    }

    override fun tick() {
        if (ticks % 20 == 0) {
            fill(max((20 * manaPerTick).toInt(), 1))
        }
        if (ticks % 100 == 0) {
            manaPerTick = if (manaPerTick <= 10000) (manaPerTick * 1.1).toFloat() else 10000.0F
        }
        ticks++
    }

    override fun toByteArray(): ByteArray {
        val manaPerTick = floatToIntBits(this.manaPerTick)
        var ret = super.toByteArray()
        ret += ((manaPerTick ushr 24) and 0xFFFF).toByte()
        ret += ((manaPerTick ushr 16) and 0xFFFF).toByte()
        ret += ((manaPerTick ushr 8) and 0xFFFF).toByte()
        ret += (manaPerTick and 0xFFFF).toByte()
        return ret
    }

    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = super.loadFromByteArray(buff)
        val manaPerTickBits = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        manaPerTick = intBitsToFloat(manaPerTickBits)
        return i
    }
}

class ManaStorage : IStorage<IMana> {
    override fun writeNBT(capability: Capability<IMana>, instance: IMana, side: Direction?): INBT {
        return ByteArrayNBT(instance.toByteArray())
    }

    override fun readNBT(capability: Capability<IMana>, instance: IMana, side: Direction?, nbt: INBT) {
        if (nbt is ByteArrayNBT) {
            instance.loadFromByteArray(nbt.byteArray)
        }
    }
}
