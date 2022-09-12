package ru.rikgela.russianmagic.objects.mana.transfer

import ru.rikgela.russianmagic.objects.mana.IMana
import ru.rikgela.russianmagic.objects.mana.IManaSpreader

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