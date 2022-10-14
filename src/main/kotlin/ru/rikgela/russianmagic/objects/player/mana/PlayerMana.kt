package ru.rikgela.russianmagic.objects.player.mana

import PLAYER_MANA_CAP
import Reborn
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.server.MinecraftServer
import net.minecraft.util.DamageSource
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.fml.network.PacketDistributor
import ru.rikgela.russianmagic.common.RMNetworkChannel
import ru.rikgela.russianmagic.objects.mana.IManaTaker
import ru.rikgela.russianmagic.objects.mana.Mana
import ru.rikgela.russianmagic.objects.mana.transfer.ManaTaker
import ru.rikgela.russianmagic.objects.player.MagicHealth

class PlayerMana : Mana(), IPlayerMana, IManaTaker {
    // Properties
    var maxMana: Float = 1000.0F
    private var ticks: Int = 0
    private val koef: Float = 3F / 20F
    private var canCast: Boolean = true
    private val sensitivity: Float = 0.1F
    private var manaTaker: ManaTaker = ManaTaker()

    override val rate: Float
        get() = manaTaker.rate

    override val isConnectedToManaSpreader: Boolean
        get() = manaTaker.isConnectedToManaSpreader

    override val spreaderWorldPos: String
        get() = manaTaker.spreaderWorldPos

    // To save
    override fun toByteArray(): ByteArray {
        val maxMana = java.lang.Float.floatToIntBits(this.maxMana)
        var ret = super.toByteArray()
        ret += ((maxMana ushr 24) and 0xFFFF).toByte()
        ret += ((maxMana ushr 16) and 0xFFFF).toByte()
        ret += ((maxMana ushr 8) and 0xFFFF).toByte()
        ret += (maxMana and 0xFFFF).toByte()
        ret += if(canCast) 0 else 1
        ret += manaTaker.toByteArray()
        return ret
    }

    // To load
    override fun loadFromByteArray(buff: ByteArray): Int {
        var i = super.loadFromByteArray(buff)
        val maxManaBits = buff[i++].toInt() shl 24 or
                (buff[i++].toInt() and 0xFF shl 16) or
                (buff[i++].toInt() and 0xFF shl 8) or
                (buff[i++].toInt() and 0xFF)
        maxMana = java.lang.Float.intBitsToFloat(maxManaBits)
        canCast = buff[i++].toInt() == 1
        i += manaTaker.loadFromByteArray(buff.sliceArray(IntRange(i, buff.size-1)))
        return i
    }

    // Initializators
    companion object {
        fun withParams(startManaCount: Int, maxManaCount: Int): PlayerMana {
            val ret = PlayerMana()
            ret.currentMana = startManaCount
            ret.baseMaxMana = maxManaCount
            return ret
        }
        fun fromPlayer(player: PlayerEntity): PlayerMana {
            if (PLAYER_MANA_CAP != null) {
                return player.getCapability(PLAYER_MANA_CAP!!).orElse(PlayerMana()) as PlayerMana
            }
            return PlayerMana()
        }
    }

    // Important
    override fun sendToPlayer(player: ServerPlayerEntity) {
        RMNetworkChannel.send(PacketDistributor.PLAYER.with { player }, PlayerManaNetwork(this.toByteArray()))
    }

    override fun playerTick(playerIn: ServerPlayerEntity) {
        if (ticks >= 20) {
            ticks = 0
            naturalManaBreath(playerIn)
        }
        ticks++
    }

    fun maxManaRefresh(reborn: Reborn){
        maxMana = baseMaxMana.toFloat() * reborn.rebornStage
    }

    // Methods
    override fun connectToManaSpreader(
        manaSpreader: BlockPos,
        manaConsumer: BlockPos,
        server: MinecraftServer,
        worldId: Int,
        sensitivity: Float
    ) {
        manaTaker.connectToManaSpreader(manaSpreader, server, worldId, this.sensitivity)
    }

    override fun disconnectToManaSpreader() {
        manaTaker.disconnectToManaSpreader()
    }

    fun blockMana(){
        canCast = false
    }

    fun allowMana(){
        canCast = true
    }

    fun meditationStart(meditationDepth: Float){
        this.blockMana()
        manaTaker.rate = sensitivity * (meditationDepth)
    }

    fun meditationStop(){
        this.allowMana()
        manaTaker.rate = sensitivity
    }

    private fun naturalManaBreath(playerIn: ServerPlayerEntity){
        val reborn: Reborn = Reborn.fromPlayer(playerIn)
        if (this.currentMana >= this.maxMana * 10){
            if (!reborn.isInReborn){
                reborn.startReborn(playerIn)
            }
        }
        else{
            if (reborn.isInReborn) {
                reborn.stopReborn(playerIn)
            }
        }
        val meditation = MeditationReborn.fromPlayer(playerIn)
        if (meditation.inMeditation) {
            val curMaxMana = maxMana * 10
            if (currentMana <= curMaxMana) {
                fill(Integer.max((curMaxMana.toInt() - currentMana) / 100, 1))
                fill(manaTaker.getMana(curMaxMana.toInt(), playerIn.getServer()!!, playerIn.position))
            }
            else {
                val points = Integer.max(((currentMana - curMaxMana) * koef).toInt(), 1)
                consume(points)
                sendToPlayer(playerIn)
            }
        }
        else {
            if (currentMana <= maxMana)
                fill(Integer.max((maxMana.toInt() - currentMana) / 100, 1))
            else {
                val points = Integer.max(((currentMana - maxMana) * koef).toInt(), 1)
                consume(points)
                reborn.addPrepare(points * 0.00001F)
                sendToPlayer(playerIn)
            }
        }
    }
    
    override fun artificialConsume(points: Int, player: ServerPlayerEntity): Boolean {
        if(canCast){
            val manaFromMagicSource = manaTaker.getMana((points * 0.9F).toInt(), player.getServer()!!, player.position)
            return if (consume(points - manaFromMagicSource)) {
                if (points > maxMana / 10) {
                    player.attackEntityFrom(DamageSource.MAGIC, (100F * (points - maxMana * 0.1F) / maxMana))
                    MagicHealth.fromPlayer(player).harmMagicHealth((100F * (points - maxMana * 0.1F) / maxMana).toInt())
                }
                Reborn.fromPlayer(player).addPrepare(points / this.maxMana)
                sendToPlayer(player)
                true
            } else {
                player.attackEntityFrom(DamageSource.MAGIC, (100F * (points - maxMana * 0.1F) / maxMana))
                false
            }
        }
        else{
            player.sendMessage(StringTextComponent("Your mana is blocked!"))
            return false
        }
    }
}
