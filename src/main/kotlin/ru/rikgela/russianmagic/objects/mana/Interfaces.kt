package ru.rikgela.russianmagic.objects.mana

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos

interface IManaBase {
    val currentMana: Int
    val baseMaxMana: Int
}

interface IMana : IManaBase {
    fun toByteArray(): ByteArray

    //returned last needed byte.
    fun loadFromByteArray(buff: ByteArray): Int
    fun consume(points: Int): Boolean

    //Returns how much mana was transferred
    fun give(points: Int, rate: Float): Int
    fun fill(points: Int)
    fun copy(mana: IMana)
}

interface IPlayerMana : IMana, IManaTaker {
    //val manaPerTick: Float
    var lvlExp: Float
    var lvl: Int
    fun playerTick(playerIn: ServerPlayerEntity)
    fun consume(points: Int, player: ServerPlayerEntity): Boolean
    fun sendToPlayer(player: ServerPlayerEntity)
}

interface IManaSpreader : IManaBase {
    val maxSpread: Int
    fun spread(points: Int, rate: Float): Int
}

interface IManaReceiver : IManaBase {
    val maxTransfer: Int
    fun transfer(points: Int)
}

interface IManaTaker {
    val isConnectedToManaSpreader: Boolean
    val spreaderWorldPos: String
    fun connectToManaSpreader(manaSpreader: BlockPos, manaConsumer: BlockPos, server: MinecraftServer, worldId: Int)
    fun disconnectToManaSpreader()
}


