package ru.rikgela.russianmagic.objects.mana.transfer

import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos
import ru.rikgela.russianmagic.objects.mana.IManaSpreader
import ru.rikgela.russianmagic.objects.mana.IManaTaker
import java.lang.Float.floatToIntBits
import java.lang.Float.intBitsToFloat
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class ManaTaker : IManaTaker {

    // Properties
    var spreaderPos: BlockPos? = null
    override var rate: Float = 0F
        set(value){
            field = max(min(value, 1F), 0F)
        }
    var worldId: Int = 0
    val baseDistance = 100F
    var trueDistance = 0F
    override val isConnectedToManaSpreader: Boolean
        get() = spreaderPos != null
    override val spreaderWorldPos: String
        get() = if (spreaderPos != null) worldId.toString() + ", " + spreaderPos!!.x.toString() + ", " + spreaderPos!!.y.toString() + ", " + spreaderPos!!.z.toString() else "NULL"

    // To save
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

    // To load
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
        if ((spreaderPos?.y ?: -1) == -1) {
            spreaderPos = null
        }
        this.trueDistance = this.baseDistance * this.rate
        return i
    }

    // Important

    // Methods

    override fun connectToManaSpreader(
        manaSpreader: BlockPos,
        manaConsumer: BlockPos,
        server: MinecraftServer,
        worldId: Int,
        sensitivity: Float
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
                    this.rate = sensitivity * if (distance > 99) 1F / distance else (1F - distance * 0.01).toFloat()
                    this.trueDistance = this.baseDistance * this.rate
                }
            }
        }
    }

    fun connectToManaSpreader(
        manaSpreader: BlockPos,
        server: MinecraftServer,
        worldId: Int,
        rate: Float
    ) {
        server.forgeGetWorldMap().forEach { dim, world ->
            if (dim.id == worldId) {
                val te = world.getTileEntity(manaSpreader)
                if (te is IManaSpreader) {
                    spreaderPos = manaSpreader
                    this.worldId = worldId
                    this.rate = rate
                    this.trueDistance = this.baseDistance * this.rate
                }
            }
        }
    }

    override fun disconnectToManaSpreader() {
        this.spreaderPos = null
        this.rate = 0F
        this.trueDistance = 0F
    }


    fun getMana(points: Int, server: MinecraftServer, manaConsumer: BlockPos = BlockPos(-1, -1, -1)): Int {
        if (!isConnectedToManaSpreader) {
            return 0
        }
        var ret: Int? = null
        server.forgeGetWorldMap().forEach { dim, world ->
            if (dim.id == worldId) {
                val te = world.getTileEntity(spreaderPos!!)
                if (te is IManaSpreader) {
                    if (manaConsumer != BlockPos(-1, -1, -1)) {
                        val distance = sqrt(
                            te.getDistanceSq(
                                manaConsumer.x.toDouble(),
                                manaConsumer.y.toDouble(),
                                manaConsumer.z.toDouble()
                            ).toFloat()
                        )
                        val rate =
                            if (distance >= trueDistance) (1F / distance) * this.rate
                            else (1F - distance / trueDistance) * this.rate
                        ret = te.spread(points, rate)
                    }
                } else {
                    //Todo action if cannot get tileEntity
                }
            }
        }
        return ret ?: 0
    }
}

