package ru.rikgela.russianmagic.objects.player

import net.minecraft.entity.player.ServerPlayerEntity

interface IMagicHealthBase {
    val curMagicHealth: Int
    val maxMagicHealth: Int
}

interface IMagicHealth : IMagicHealthBase {
    fun toByteArray(): ByteArray
    fun harmMagicHealth(points: Int)
    fun healMagicHealth(points: Int)
    fun sendToPlayer(player: ServerPlayerEntity)
    //returned last needed byte.
    fun loadFromByteArray(buff: ByteArray): Int
    fun copy(playerProperties: IMagicHealth)
}


