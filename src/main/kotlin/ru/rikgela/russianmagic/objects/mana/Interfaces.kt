package ru.rikgela.russianmagic.objects.mana

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
    val rate: Float
    fun connectToManaSpreader(
        manaSpreader: BlockPos,
        manaConsumer: BlockPos,
        server: MinecraftServer,
        worldId: Int,
        sensitivity: Float = 1F
    )
    fun disconnectToManaSpreader()
}
