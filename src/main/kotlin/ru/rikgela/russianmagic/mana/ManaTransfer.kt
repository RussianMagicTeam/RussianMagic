package ru.rikgela.russianmagic.mana

import net.minecraft.nbt.ByteArrayNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.capabilities.Capability

class ManaReceiver<T : IMana>(private val mana: T) : IManaReceiver {
    override val currentMana: Int
        get() = mana.currentMana

    override val maxMana: Int
        get() = mana.maxMana

    override val maxTransfer: Int
        get() = maxMana - currentMana

    override fun transfer(points: Int): Int {
        return mana.fill(points)
    }

    override var magicSource: BlockPos = BlockPos(0, 0, 0)


    override fun setPositionOfMagicSource(magicSourcePos: BlockPos) {
        magicSource = magicSourcePos
    }

    override fun copy(manaReceiver: IManaReceiver) {
        this.magicSource = manaReceiver.magicSource
    }

    override fun toByteArray(): ByteArray {
        return byteArrayOf(
                ((magicSource.x ushr 24) and 0xFFFF).toByte(),
                ((magicSource.x ushr 16) and 0xFFFF).toByte(),
                ((magicSource.x ushr 8) and 0xFFFF).toByte(),
                (magicSource.x and 0xFFFF).toByte(),
                ((magicSource.y ushr 24) and 0xFFFF).toByte(),
                ((magicSource.y ushr 16) and 0xFFFF).toByte(),
                ((magicSource.y ushr 8) and 0xFFFF).toByte(),
                (magicSource.y and 0xFFFF).toByte(),
                ((magicSource.z ushr 24) and 0xFFFF).toByte(),
                ((magicSource.z ushr 16) and 0xFFFF).toByte(),
                ((magicSource.z ushr 8) and 0xFFFF).toByte(),
                (magicSource.z and 0xFFFF).toByte(),
        )
    }

    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        magicSource = BlockPos(buff[i++].toInt() shl 24 or
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
        return i
    }
}

class ReceiverStorage : Capability.IStorage<IManaReceiver> {
    override fun writeNBT(capability: Capability<IManaReceiver>, instance: IManaReceiver, side: Direction?): INBT {
        return ByteArrayNBT(instance.toByteArray())
    }

    override fun readNBT(capability: Capability<IManaReceiver>, instance: IManaReceiver, side: Direction?, nbt: INBT) {
        if (nbt is ByteArrayNBT) {
            instance.loadFromByteArray(nbt.byteArray)
        }
    }
}

class ManaSpreader<T : IMana>(private val mana: T) : IManaSpreader {
    override val currentMana: Int
        get() = mana.currentMana

    override val maxMana: Int
        get() = mana.maxMana

    override val maxSpread: Int
        get() = currentMana

    override fun spread(points: Int): Int {
        return mana.give(points)
    }
}

