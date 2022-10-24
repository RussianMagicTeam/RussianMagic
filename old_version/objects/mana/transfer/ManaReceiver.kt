package ru.rikgela.russianmagic.objects.mana.transfer

import ru.rikgela.russianmagic.objects.mana.IMana
import ru.rikgela.russianmagic.objects.mana.IManaReceiver

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