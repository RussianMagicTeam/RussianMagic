package ru.rikgela.russianmagic.objects.mana

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.nbt.ByteArrayNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.DamageSource
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.fml.network.PacketDistributor
import ru.rikgela.russianmagic.common.RMNetworkChannel
import java.lang.Integer.max

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

    override fun give(points: Int): Int {
        if (currentMana >= points) {
            currentMana -= points
            return points
        }
        val tmp: Int = currentMana
        currentMana = 0
        return tmp
    }

    override fun fill(points: Int) {
        //val count = min(maxMana - currentMana, points)
        currentMana += points
    }
}

class PlayerMana : Mana(), IPlayerMana {
    companion object {
        fun withParams(startManaCount: Int, maxManaCount: Int): PlayerMana {
            val ret = PlayerMana()
            ret.currentMana = startManaCount
            ret.maxMana = maxManaCount
            return ret
        }

        fun fromPlayer(player: PlayerEntity): PlayerMana {
            if (MANA_CAP != null) {
                return player.getCapability(MANA_CAP!!).orElse(PlayerMana()) as PlayerMana
            }
            return PlayerMana()
        }
    }

    //override var manaPerTick = 1F
    var averageConsume = 100

    override fun sendToPlayer(player: ServerPlayerEntity) {
        RMNetworkChannel.send(PacketDistributor.PLAYER.with { player }, ManaMessage(this))
    }

    override fun consume(points: Int, player: ServerPlayerEntity): Boolean {
        return if (consume(points)) {
            if (points > maxMana / 10)
                player.attackEntityFrom(DamageSource.MAGIC, (100F * (points - maxMana / 10F) / maxMana))
            sendToPlayer(player)
            true
        } else {
            false
        }
    }

    private var ticks = 0

    override fun playerTick(playerIn: ServerPlayerEntity) {
        if (ticks % 20 == 0) {
            if (currentMana <= maxMana)
                fill(max((maxMana - currentMana) / 100, 1))
            else
                consume(max(((currentMana - maxMana) * (3F / 20F)).toInt(), 1), playerIn)
        }
        ticks++
    }

    override fun toByteArray(): ByteArray {
        //val manaPerTick = floatToIntBits(this.manaPerTick)
        var ret = super.toByteArray()
        /*ret += ((manaPerTick ushr 24) and 0xFFFF).toByte()
        ret += ((manaPerTick ushr 16) and 0xFFFF).toByte()
        ret += ((manaPerTick ushr 8) and 0xFFFF).toByte()
        ret += (manaPerTick and 0xFFFF).toByte()
        */
        return ret
    }

    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = super.loadFromByteArray(buff)
        /*val manaPerTickBits = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        manaPerTick = intBitsToFloat(manaPerTickBits)
        */
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
