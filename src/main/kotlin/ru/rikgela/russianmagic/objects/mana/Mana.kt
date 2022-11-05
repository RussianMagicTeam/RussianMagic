package ru.rikgela.russianmagic.objects.mana

import net.minecraft.nbt.CompoundTag

open class Mana : IMana {

    // Properties
    override var currentMana = 250
    override var baseMaxMana = 1000

    // To save

    override fun serializeNBT(): CompoundTag{
        val nbt = CompoundTag()
        nbt.putInt("currentMana", this.currentMana)
        nbt.putInt("baseMaxMana", this.currentMana)
        return nbt
    }

    // To load


    override fun deserializeNBT(nbt: CompoundTag) {
        this.currentMana = nbt.getInt("currentMana")
        this.baseMaxMana = nbt.getInt("baseMaxMana")
    }

    // Initializers
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
        if (points <= 0){
            return 0
        }
        var afterRateToSend = (points * rate).toInt()
        if (afterRateToSend == 0) return 0

        val afterRateConsume = (afterRateToSend / rate).toInt()
        if (afterRateConsume == 0){
            return 0
        }
        if (currentMana >= afterRateConsume) {
            currentMana -= afterRateConsume
            return if (rate > 1F) points else if (rate < 0F) 0 else afterRateToSend
        } else {
            afterRateToSend = (currentMana * rate).toInt()
            if (afterRateToSend == 0) return 0
            val tmp: Int = if (rate > 1) currentMana else if (rate < 0) 0 else afterRateToSend
            currentMana = 0
            return tmp
        }
    }

    override fun fill(points: Int) {
        currentMana += points
    }
}

