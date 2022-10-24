package ru.rikgela.russianmagic.objects.player.reborn

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.fml.network.PacketDistributor
import ru.rikgela.russianmagic.common.RMNetworkChannel
import ru.rikgela.russianmagic.objects.player.magichealth.MagicHealth
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana
import ru.rikgela.russianmagic.objects.player.meditation.reborn.MeditationReborn
import kotlin.math.max
import kotlin.math.min

open class Reborn: IReborn {

    // Properties
    override var rebornPrepare = 0.0F
    override var isInReborn = false
    override var rebornProgress: Float = 0.0F
    override var rebornStage = 1

    // To save
    override fun toByteArray(): ByteArray {
        val rebornPrepare = java.lang.Float.floatToIntBits(this.rebornPrepare)
        val rebornProgress = java.lang.Float.floatToIntBits(this.rebornProgress)
        return byteArrayOf(
            ((rebornPrepare ushr 24) and 0xFFFF).toByte(),
            ((rebornPrepare ushr 16) and 0xFFFF).toByte(),
            ((rebornPrepare ushr 8) and 0xFFFF).toByte(),
            (rebornPrepare and 0xFFFF).toByte(),
            if (isInReborn) 1 else 0,
            ((rebornProgress ushr 24) and 0xFFFF).toByte(),
            ((rebornProgress ushr 16) and 0xFFFF).toByte(),
            ((rebornProgress ushr 8) and 0xFFFF).toByte(),
            (rebornProgress and 0xFFFF).toByte(),
            ((rebornStage ushr 24) and 0xFFFF).toByte(),
            ((rebornStage ushr 16) and 0xFFFF).toByte(),
            ((rebornStage ushr 8) and 0xFFFF).toByte(),
            (rebornStage and 0xFFFF).toByte(),
        )
    }

    // To load
    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = 0
        rebornPrepare = java.lang.Float.intBitsToFloat(buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        )
        isInReborn = buff[i++].toInt() == 1
        rebornProgress = java.lang.Float.intBitsToFloat(buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        )
        rebornStage = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        return i
    }

    // Initializers
    companion object {
        fun withParams(rebornPrepare: Float, isInReborn: Boolean, rebornProgress: Float, rebornStage: Int): Reborn {
            val ret = Reborn()
            ret.rebornPrepare = rebornPrepare
            ret.isInReborn = isInReborn
            ret.rebornProgress = rebornProgress
            ret.rebornStage = rebornStage
            return ret
        }
        fun fromPlayer(player: PlayerEntity): Reborn {
            if (REBORN_CAP != null) {
                return player.getCapability(REBORN_CAP!!).orElse(Reborn()) as Reborn
            }
            return Reborn()
        }
    }

    override fun copy(playerProperties: IReborn) {
        this.rebornPrepare = playerProperties.rebornPrepare
        this.isInReborn = playerProperties.isInReborn
        this.rebornProgress = playerProperties.rebornProgress
        this.rebornStage = playerProperties.rebornStage
    }

    // Important

    override fun sendToPlayer(player: ServerPlayerEntity) {
        RMNetworkChannel.send(PacketDistributor.PLAYER.with { player }, RebornNetwork(this.toByteArray()))
    }

    override fun playerTick(playerIn: ServerPlayerEntity) {
        if(this.isInReborn){
            val mana = PlayerMana.fromPlayer(playerIn)
            this.rebornProgress += 1F / this.rebornStage
            if (this.rebornProgress >= 100F) {
                playerIn.sendMessage(StringTextComponent("You have reached a new reborn level!"))
                this.rebornStage += 1
                this.rebornProgress = 0F
                this.rebornPrepare = 0F
                this.isInReborn = false
                mana.maxManaRefresh(this)
                val meditationReborn = MeditationReborn.fromPlayer(playerIn)
                meditationReborn.sinkingSpeedRefresh(this)
            }
        }
        else{
            this.addPrepare(0.00001F)
        }
    }

    // Methods
    fun addPrepare(points: Float){
        rebornPrepare = min(100F, points + rebornPrepare)
    }

    fun startReborn(playerIn: ServerPlayerEntity){
        if (this.rebornPrepare == 100F){
            this.isInReborn = true
            PlayerMana.fromPlayer(playerIn).blockMana()
            playerIn.sendMessage(StringTextComponent("ru.rikgela.russianmagic.objects.player.reborn.Reborn is started!"))
            sendToPlayer(playerIn)
        }
        else{
            MagicHealth.fromPlayer(playerIn).harmMagicHealth(100 - this.rebornPrepare.toInt())
            playerIn.sendMessage(StringTextComponent("You are not prepared to reborn!"))
        }
    }

    fun stopReborn(playerIn: ServerPlayerEntity){
        this.rebornProgress = 0F
        this.rebornPrepare = max(this.rebornPrepare - 5F, 0F)
        this.isInReborn = false
        MagicHealth.fromPlayer(playerIn).harmMagicHealth(this.rebornProgress.toInt())
        PlayerMana.fromPlayer(playerIn).allowMana()
        playerIn.sendMessage(StringTextComponent("ru.rikgela.russianmagic.objects.player.reborn.Reborn is stopped!"))
        sendToPlayer(playerIn)
    }
}
