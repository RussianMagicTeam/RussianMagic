package ru.rikgela.russianmagic.objects.player.reborn

import net.minecraft.entity.player.ServerPlayerEntity


interface IRebornBase {
    val rebornPrepare: Float
    val isInReborn: Boolean
    var rebornProgress: Float
    val rebornStage: Int
}

interface IReborn : IRebornBase {
    fun toByteArray(): ByteArray
    fun sendToPlayer(player: ServerPlayerEntity)
    fun playerTick(playerIn: ServerPlayerEntity)
    //returned last needed byte.
    fun loadFromByteArray(buff: ByteArray): Int
    fun copy(playerProperties: IReborn)
}
