package ru.rikgela.russianmagic.mana

import net.minecraft.nbt.INBT
import net.minecraft.nbt.IntNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional

@CapabilityInject(IMana::class)
var MANA_RECEIVER_CAP: Capability<IManaReceiver>? = null

class ManaReceiverProvider(private val instance: IManaReceiver?) : ICapabilitySerializable<INBT> {

    @Override
    fun hasCapability(capability: Capability<Any>, facing: Direction): Boolean {
        return capability == MANA_RECEIVER_CAP
    }

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap == MANA_RECEIVER_CAP) LazyOptional.of { instance as T } else LazyOptional.empty()
    }

    override fun deserializeNBT(nbt: INBT?) {
        MANA_RECEIVER_CAP?.storage?.readNBT(MANA_RECEIVER_CAP, this.instance, null, nbt)
    }

    override fun serializeNBT(): INBT {
        return MANA_RECEIVER_CAP?.storage?.writeNBT(MANA_RECEIVER_CAP, this.instance, null) ?: IntNBT.valueOf(0)
    }
}