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
import java.lang.Float.floatToIntBits
import java.lang.Float.intBitsToFloat
import java.lang.Integer.max

open class Mana : IMana {
    companion object {
        fun withParams(startManaCount: Int, maxManaCount: Int): Mana {
            val ret = Mana()
            ret.currentMana = startManaCount
            ret.baseMaxMana = maxManaCount
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
                ((baseMaxMana ushr 24) and 0xFFFF).toByte(),
                ((baseMaxMana ushr 16) and 0xFFFF).toByte(),
                ((baseMaxMana ushr 8) and 0xFFFF).toByte(),
                (baseMaxMana and 0xFFFF).toByte()
        )
    }

    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        currentMana = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)

        baseMaxMana = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        return i
    }

    override var currentMana = 250
    override var baseMaxMana = 1000

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
            ret.baseMaxMana = maxManaCount
            ret.lvl = 0
            ret.lvlExp = 0F
            return ret
        }

        fun fromPlayer(player: PlayerEntity): PlayerMana {
            if (MANA_CAP != null) {
                return player.getCapability(MANA_CAP!!).orElse(PlayerMana()) as PlayerMana
            }
            return PlayerMana()
        }
    }

    private var lvl = 0
    private var lvlExp = 0F
    var maxMana = baseMaxMana + baseMaxMana * lvl + (baseMaxMana + baseMaxMana * lvl) * delta()
    var isInReborn = false

    private fun delta(): Float {
        if (lvlExp < 0.17F)
            return lvlExp * 2F
        if (lvlExp < 0.33F)
            return 0.33F - (lvlExp - 0.17F) * 4F
        return (lvlExp - 0.5F) * 2
    }

    override fun sendToPlayer(player: ServerPlayerEntity) {
        RMNetworkChannel.send(PacketDistributor.PLAYER.with { player }, ManaMessage(this))
    }

    override fun consume(points: Int, player: ServerPlayerEntity): Boolean {
        return if (consume(points)) {
            if (isInReborn) {
                lvlExp += points / 1000F
                maxMana = baseMaxMana + baseMaxMana * lvl + (baseMaxMana + baseMaxMana * lvl) * delta()
            } else {
                if (points > maxMana / 10) {
                    player.attackEntityFrom(DamageSource.MAGIC, (100F * (points - maxMana * 0.1F) / maxMana))
                }
                if (player.isAlive) {
                    if (lvlExp < 0.33F) {
                        isInReborn = false
                        lvlExp += points / 1000F
                        maxMana = baseMaxMana + baseMaxMana * lvl + (baseMaxMana + baseMaxMana * lvl) * delta()
                    }
                }
                if (lvlExp >= 1F) {
                    lvlExp -= 1F
                    maxMana = baseMaxMana + baseMaxMana * lvl + (baseMaxMana + baseMaxMana * lvl) * delta()
                }

            }
            sendToPlayer(player)
            true
        } else {
            false
        }
    }

    fun overload(points: Int, player: ServerPlayerEntity) {
        if (lvlExp >= 0.33F) {
            isInReborn = true
        }
        consume(points, player)
    }

    private var ticks = 0
    private val koef = 3F / 20F

    override fun playerTick(playerIn: ServerPlayerEntity) {
        if (ticks > 20) {
            ticks = 0
            val prevIsInReborn = isInReborn
            if (currentMana <= maxMana)
                fill(max((maxMana.toInt() - currentMana) / 100, 1))
            else {
                overload(max(((currentMana - maxMana) * koef).toInt(), 1), playerIn)
            }
            if (prevIsInReborn > isInReborn) {
                maxMana = baseMaxMana + baseMaxMana * lvl + (baseMaxMana + baseMaxMana * lvl) * (0.33F - (lvlExp - 0.17F) * 4F)
            }
        }
        ticks++
    }

    override fun toByteArray(): ByteArray {
        val lvl_exp = floatToIntBits(this.lvlExp)
        var ret = super.toByteArray()
        ret += ((lvl_exp ushr 24) and 0xFFFF).toByte()
        ret += ((lvl_exp ushr 16) and 0xFFFF).toByte()
        ret += ((lvl_exp ushr 8) and 0xFFFF).toByte()
        ret += (lvl_exp and 0xFFFF).toByte()
        ret += ((lvl ushr 24) and 0xFFFF).toByte()
        ret += ((lvl ushr 16) and 0xFFFF).toByte()
        ret += ((lvl ushr 8) and 0xFFFF).toByte()
        ret += (lvl and 0xFFFF).toByte()
        return ret
    }

    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = super.loadFromByteArray(buff)
        val lvlExpBits = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        lvl = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        lvlExp = intBitsToFloat(lvlExpBits)
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
