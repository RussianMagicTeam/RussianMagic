package ru.rikgela.russianmagic.objects.mana

open class Mana : IMana {

    // Properties
    override var currentMana = 250
    override var baseMaxMana = 1000

    // To save
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

    // To load
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

    // Initializators
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

    // Important

    // Methods
    override fun consume(points: Int): Boolean {
        if (currentMana >= points) {
            currentMana -= points
            return true
        }
        return false
    }

    override fun give(points: Int, rate: Float): Int {
        var after_rate_to_send = (points * rate).toInt()
        if (after_rate_to_send == 0) return 0

        val after_rate_consume = (after_rate_to_send / rate).toInt()
        if (currentMana >= after_rate_consume) {
            currentMana -= after_rate_consume
            return if (rate > 1F) points else if (rate < 0F) 0 else after_rate_to_send
        } else {
            after_rate_to_send = (currentMana * rate).toInt()
            if (after_rate_to_send == 0) return 0
            val tmp: Int = if (rate > 1) currentMana else if (rate < 0) 0 else after_rate_to_send
            currentMana = 0
            return tmp
        }
    }

    override fun fill(points: Int) {
        currentMana += points
    }
}

