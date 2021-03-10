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

