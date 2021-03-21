package ru.rikgela.russianmagic.mana

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos

interface IManaBase {
    val currentMana: Int
    val maxMana: Int
}

interface IMana : IManaBase {
    fun toByteArray(): ByteArray

    //returned last needed byte.
    fun loadFromByteArray(buff: ByteArray): Int
    fun consume(points: Int): Boolean

    //Returns how much mana was transferred
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
    fun transfer(points: Int): Int
}

interface IManaTaker {
    val isConnectedToManaSpreader: Boolean
    val spreaderWorldPos: String
    fun connectToManaSpreader(magicSource: BlockPos, server: MinecraftServer, worldId: Int)
    fun disconnectToManaSpreader()
}


