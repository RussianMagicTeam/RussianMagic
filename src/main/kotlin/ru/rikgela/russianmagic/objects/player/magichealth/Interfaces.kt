package ru.rikgela.russianmagic.objects.player.magichealth

import net.minecraft.entity.player.ServerPlayerEntity

interface IMagicHealthBase {
    val curMagicHealth: Int
    val maxMagicHealth: Int
}

interface IMagicHealth : IMagicHealthBase {
    fun playerTick(playerIn: ServerPlayerEntity)
    fun toByteArray(): ByteArray
    fun harmMagicHealth(points: Int)
    fun healMagicHealth(points: Int)
    fun sendToPlayer(player: ServerPlayerEntity)
    //returned last needed byte.
    fun loadFromByteArray(buff: ByteArray): Int
    fun copy(playerProperties: IMagicHealth)
}


