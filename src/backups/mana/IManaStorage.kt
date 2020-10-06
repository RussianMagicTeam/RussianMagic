package ru.rikgela.russianmagic.mana

interface IManaStorage {
    fun addMana(mana: Int) : Unit
    fun receiveMana(mana: Int) : Unit
    fun getManaStored(): Int
//    fun getMaxManaStored(): Int
    fun canExtract(): Boolean
    fun canReceive(): Boolean
}
