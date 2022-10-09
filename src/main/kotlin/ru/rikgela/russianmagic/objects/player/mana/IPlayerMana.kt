package ru.rikgela.russianmagic.objects.player.mana

import net.minecraft.entity.player.ServerPlayerEntity
import ru.rikgela.russianmagic.objects.mana.IMana
import ru.rikgela.russianmagic.objects.mana.IManaTaker

interface IPlayerMana : IMana, IManaTaker {
    //val manaPerTick: Float
    fun playerTick(playerIn: ServerPlayerEntity)
    fun artificialConsume(points: Int, player: ServerPlayerEntity): Boolean
    fun sendToPlayer(player: ServerPlayerEntity)
}