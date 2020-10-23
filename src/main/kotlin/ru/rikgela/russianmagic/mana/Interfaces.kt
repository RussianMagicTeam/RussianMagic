package ru.rikgela.russianmagic.mana

import net.minecraft.entity.player.ServerPlayerEntity

interface IManaBase {
    val currentMana: Int
    val maxMana: Int
}

interface IMana : IManaBase {
    fun toByteArray(): ByteArray

    //returned last needed byte.
    fun loadFromByteArray(buff: ByteArray): Int
    fun consume(points: Int): Boolean
    fun fill(points: Int): Int
    fun copy(mana: IMana)
}

interface IPlayerMana : IMana {
    val manaPerTick: Float
    fun tick()
    fun consume(points: Int, player: ServerPlayerEntity): Boolean
    fun sendToPlayer(player: ServerPlayerEntity)
}

interface IManaSpreader : IManaBase

interface IManaReceiver : IManaBase {
    val maxTransfer: Int
    fun transfer(points: Int): Int
}