package ru.rikgela.russianmagic.mana

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.INBT
import net.minecraft.nbt.IntNBT
import net.minecraft.util.Direction
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.MOD_ID


interface IMana {
    fun consume(points: Int)
    fun fill(points: Int)
    fun set(points: Int)
    val mana: Int
}

class Mana : IMana {
    override var mana = 250
        private set

    override fun consume(points: Int) {
        mana -= points
        if (mana < 0) mana = 0
    }

    override fun fill(points: Int) {
        mana += points
    }

    override fun set(points: Int) {
        mana = points
    }

}

class ManaStorage : IStorage<IMana> {
    override fun writeNBT(capability: Capability<IMana>, instance: IMana, side: Direction?): INBT {
        return IntNBT.valueOf(instance.mana)
    }

    override fun readNBT(capability: Capability<IMana>, instance: IMana, side: Direction?, nbt: INBT) {
        instance.set((nbt as IntNBT).int)
    }
}

@CapabilityInject(IMana::class)
var MANA_CAP: Capability<IMana>? = null

class ManaProvider : ICapabilitySerializable<INBT> {
    private val instance: IMana? = MANA_CAP?.defaultInstance

    @Override
    fun hasCapability(capability: Capability<Any>, facing: Direction): Boolean {
        return capability == MANA_CAP
    }

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap == MANA_CAP) LazyOptional.of { instance as T } else LazyOptional.empty()
    }

    override fun deserializeNBT(nbt: INBT?) {
        MANA_CAP?.storage?.readNBT(MANA_CAP, this.instance, null, nbt)
    }

    override fun serializeNBT(): INBT {
        return MANA_CAP?.storage?.writeNBT(MANA_CAP, this.instance, null) ?: IntNBT.valueOf(0)
    }
}

class ManaCapabilityHandler {
    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<Entity>) {
        event.addCapability(MANA_CAP, ManaProvider())
    }

    companion object {
        val MANA_CAP = ResourceLocation(MOD_ID, "mana")
    }
}

class EventHandler {
    @SubscribeEvent
    fun onPlayerLogsIn(event: PlayerLoggedInEvent) {
        val player: PlayerEntity = event.player
        if (MANA_CAP != null) {
            val mana: IMana = player.getCapability(MANA_CAP!!, null).orElse(Mana())
            val message = String.format("Hello there, you have §7%d§r mana left.", mana.mana)
            player.sendMessage(StringTextComponent(message))
        } else {
            player.sendMessage(StringTextComponent("Mana not registered!"))
        }
    }

    @SubscribeEvent
    fun onPlayerSleep(event: PlayerSleepInBedEvent) {
        val player: PlayerEntity = event.player
//        if (player.worldObj.isRemote) return
        if (MANA_CAP != null) {
            val mana: IMana = player.getCapability(MANA_CAP!!, null).orElse(Mana())
            mana.fill(50)
            val message = String.format("You refreshed yourself in the bed. You received 50 mana, you have §7%d§r mana left.", mana.mana)
            player.sendMessage(StringTextComponent(message))
        } else {
            player.sendMessage(StringTextComponent("Mana not registered!"))
        }
    }
}