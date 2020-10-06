package ru.rikgela.russianmagic.mana


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
public class ManaStorage : IManaStorage {
    var mana = 0
    var maxManaStored = 0
    var maxReceive = 0
    var maxExtract = 0

    constructor(capacity: Int) {
        this.maxManaStored = capacity
        this.maxReceive = capacity
        this.maxExtract = capacity
        this.mana = 0
    }

    constructor(capacity: Int, maxTransfer: Int) {
        this.maxManaStored = capacity
        this.maxReceive = maxTransfer
        this.maxExtract = maxTransfer
        this.mana = 0
    }

    constructor(capacity: Int, maxReceive: Int, maxExtract: Int) {
        this.maxManaStored = capacity
        this.maxReceive = maxReceive
        this.maxExtract = maxExtract
        this.mana = 0
    }

    fun ManaStorage(capacity: Int, maxReceive: Int, maxExtract: Int, mana: Int) {
        this.maxManaStored = capacity
        this.maxReceive = maxReceive
        this.maxExtract = maxExtract
        this.mana = Math.max(0, Math.min(capacity, mana))
    }

    override fun addMana(mana: Int) {
        this.mana += mana
    }

    override fun receiveMana(mana: Int) {
        this.mana -= mana
        if (this.mana < 0) this.mana = 0
    }

    override fun getManaStored(): Int {
        return mana
    }

    /*fun getMaxManaStored(): Int {
        return maxManaStored
    }*/

    override fun canExtract(): Boolean {
        return maxExtract > 0
    }

    override fun canReceive(): Boolean {
        return maxReceive > 0
    }

    init {
        this.mana = Math.max(0, Math.min(maxManaStored, mana))
    }
}