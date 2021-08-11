package ru.rikgela.russianmagic.objects.mana

import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos
import java.lang.Float.floatToIntBits
import java.lang.Float.intBitsToFloat
import kotlin.math.sqrt

class ManaReceiver<T : IMana>(private val mana: T) : IManaReceiver {
    override val currentMana: Int
        get() = mana.currentMana

    override val baseMaxMana: Int
        get() = mana.baseMaxMana

    override val maxTransfer: Int
        get() = baseMaxMana - currentMana

    override fun transfer(points: Int) {
        mana.fill((points))
    }
}

class ManaSpreader<T : IMana>(private val mana: T) : IManaSpreader {
    override val currentMana: Int
        get() = mana.currentMana

    override val baseMaxMana: Int
        get() = mana.baseMaxMana

    override val maxSpread: Int
        get() = currentMana

    override fun spread(points: Int, rate: Float): Int {
        return mana.give(points, rate)
    }
}

class ManaTaker : IManaTaker {
    var spreaderPos: BlockPos? = null
    var rate: Float = 1F
    var worldId: Int = 0
    override val isConnectedToManaSpreader: Boolean
        get() = spreaderPos != null
    override val spreaderWorldPos: String
        get() = if (spreaderPos != null) worldId.toString() + ", " + spreaderPos!!.x.toString() + ", " + spreaderPos!!.y.toString() + ", " + spreaderPos!!.z.toString() else "NULL"

    override fun connectToManaSpreader(
        manaSpreader: BlockPos,
        manaConsumer: BlockPos,
        server: MinecraftServer,
        worldId: Int
    ) {
        server.forgeGetWorldMap().forEach { dim, world ->
            if (dim.id == worldId) {
                val te = world.getTileEntity(manaSpreader)
                if (te is IManaSpreader) {
                    spreaderPos = manaSpreader
                    this.worldId = worldId
                    val distance = sqrt(
                        te.getDistanceSq(
                            manaConsumer.x.toDouble(),
                            manaConsumer.y.toDouble(),
                            manaConsumer.z.toDouble()
                        ).toFloat()
                    )
                    rate = if (distance > 99) 1F / distance else (1F - distance * 0.01).toFloat()
                }
            }
        }
    }

    override fun disconnectToManaSpreader() {
        spreaderPos = null
        rate = 1F
    }

    fun getMana(points: Int, server: MinecraftServer): Int {
        if (!isConnectedToManaSpreader) {
            return 0
        }
        var ret: Int? = null
        server.forgeGetWorldMap().forEach { dim, world ->
            if (dim.id == worldId) {
                val te = world.getTileEntity(spreaderPos!!)
                if (te is IManaSpreader) {
                    ret = te.spread(points, rate)
                } else {
                    //Todo action if cannot get tileEntity
                }
            }
        }
        return ret ?: 0
    }


    fun toByteArray(): ByteArray {
        val x = spreaderPos?.x ?: -1
        val y = spreaderPos?.y ?: -1
        val z = spreaderPos?.z ?: -1
        val rate = floatToIntBits(this.rate)
        return byteArrayOf(
            ((worldId ushr 24) and 0xFFFF).toByte(),
            ((worldId ushr 16) and 0xFFFF).toByte(),
            ((worldId ushr 8) and 0xFFFF).toByte(),
            (worldId and 0xFFFF).toByte(),
            ((x ushr 24) and 0xFFFF).toByte(),
            ((x ushr 16) and 0xFFFF).toByte(),
            ((x ushr 8) and 0xFFFF).toByte(),
            (x and 0xFFFF).toByte(),
            ((y ushr 24) and 0xFFFF).toByte(),
            ((y ushr 16) and 0xFFFF).toByte(),
            ((y ushr 8) and 0xFFFF).toByte(),
            (y and 0xFFFF).toByte(),
            ((z ushr 24) and 0xFFFF).toByte(),
            ((z ushr 16) and 0xFFFF).toByte(),
            ((z ushr 8) and 0xFFFF).toByte(),
            (z and 0xFFFF).toByte(),
            ((rate ushr 24) and 0xFFFF).toByte(),
            ((rate ushr 16) and 0xFFFF).toByte(),
            ((rate ushr 8) and 0xFFFF).toByte(),
            (rate and 0xFFFF).toByte()
        )
    }

    fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        worldId = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        spreaderPos = BlockPos(
            buff[i++].toInt() shl 24 or
                    (buff[i++].toInt() and 0xFF shl 16) or
                    (buff[i++].toInt() and 0xFF shl 8) or
                    (buff[i++].toInt() and 0xFF),
            buff[i++].toInt() shl 24 or
                    (buff[i++].toInt() and 0xFF shl 16) or
                    (buff[i++].toInt() and 0xFF shl 8) or
                    (buff[i++].toInt() and 0xFF),
            buff[i++].toInt() shl 24 or
                    (buff[i++].toInt() and 0xFF shl 16) or
                    (buff[i++].toInt() and 0xFF shl 8) or
                    (buff[i++].toInt() and 0xFF)
        )
        val rate = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        this.rate = intBitsToFloat(rate)
        if (spreaderPos?.y ?: -1 == -1) {
            spreaderPos = null
        }
        return i
    }
}

