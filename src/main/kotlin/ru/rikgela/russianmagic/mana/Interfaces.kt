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
    fun give(points: Int): Int
    fun fill(points: Int): Int
    fun copy(mana: IMana)
}

interface IPlayerMana : IMana {
    val manaPerTick: Float
    fun tick()
    fun consume(points: Int, player: ServerPlayerEntity): Boolean
    fun sendToPlayer(player: ServerPlayerEntity)
}

interface IManaSpreader : IManaBase {
    val maxSpread: Int
    fun spread(points: Int): Int
}

interface IManaReceiver : IManaBase {
    val maxTransfer: Int
    val source_pos_x: Int
    val source_pos_y: Int
    val source_pos_z: Int
    fun setPositionOfMagicSource(pos_x: Int, pos_y: Int, pos_z: Int)
    fun loadFromByteArray(buff: ByteArray): Int
    fun toByteArray(): ByteArray
    fun copy(manaReceiver: IManaReceiver)
    fun transfer(points: Int): Int
}


