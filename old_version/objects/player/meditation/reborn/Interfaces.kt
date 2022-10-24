package ru.rikgela.russianmagic.objects.player.meditation.reborn

import net.minecraft.entity.player.ServerPlayerEntity

interface IBaseMeditationReborn {
    val inMeditation: Boolean
    val meditationProgress: Float
    var sinkingSpeed: Float
}

interface IMeditationReborn : IBaseMeditationReborn {
    fun playerTick(playerIn: ServerPlayerEntity)
    fun toByteArray(): ByteArray
    fun startMeditation(playerIn: ServerPlayerEntity)
    fun stopMeditation(playerIn: ServerPlayerEntity)
    fun sendToPlayer(player: ServerPlayerEntity)
    //returned last needed byte.
    fun loadFromByteArray(buff: ByteArray): Int
    fun copy(playerProperties: IMeditationReborn)
}
