package ru.rikgela.russianmagic.mana

import net.minecraft.nbt.ByteArrayNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
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

    override var source_pos_x = 0
    override var source_pos_y = 0
    override var source_pos_z = 0

    override fun setPositionOfMagicSource(pos_x: Int, pos_y: Int, pos_z: Int) {
        source_pos_x = pos_x
        source_pos_y = pos_y
        source_pos_z = pos_z
    }

    override fun copy(manaReceiver: IManaReceiver) {
        this.source_pos_x = manaReceiver.source_pos_x
        this.source_pos_y = manaReceiver.source_pos_y
        this.source_pos_z = manaReceiver.source_pos_z
    }

    override fun toByteArray(): ByteArray {
        return byteArrayOf(
                ((source_pos_x ushr 24) and 0xFFFF).toByte(),
                ((source_pos_x ushr 16) and 0xFFFF).toByte(),
                ((source_pos_x ushr 8) and 0xFFFF).toByte(),
                (source_pos_x and 0xFFFF).toByte(),
                ((source_pos_y ushr 24) and 0xFFFF).toByte(),
                ((source_pos_y ushr 16) and 0xFFFF).toByte(),
                ((source_pos_y ushr 8) and 0xFFFF).toByte(),
                (source_pos_y and 0xFFFF).toByte(),
                ((source_pos_z ushr 24) and 0xFFFF).toByte(),
                ((source_pos_z ushr 16) and 0xFFFF).toByte(),
                ((source_pos_z ushr 8) and 0xFFFF).toByte(),
                (source_pos_z and 0xFFFF).toByte(),
        )
    }

    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        source_pos_x = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)

        source_pos_y = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)

        source_pos_z = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
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

