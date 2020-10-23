package ru.rikgela.russianmagic.mana


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
}
