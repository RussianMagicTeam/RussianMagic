package ru.rikgela.russianmagic.objects.player.magichealth

import net.minecraft.nbt.INBT
import net.minecraft.nbt.IntNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional

@CapabilityInject(IMagicHealth::class)
var MAGIC_HEALTH_CAP: Capability<IMagicHealth>? = null

class MagicHealthProvider(private val instance: IMagicHealth?) : ICapabilitySerializable<INBT> {

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap == MAGIC_HEALTH_CAP) LazyOptional.of { instance as T } else LazyOptional.empty()
    }

    override fun deserializeNBT(nbt: INBT?) {
        MAGIC_HEALTH_CAP?.storage?.readNBT(MAGIC_HEALTH_CAP, this.instance, null, nbt)
    }

    override fun serializeNBT(): INBT {
        return MAGIC_HEALTH_CAP?.storage?.writeNBT(MAGIC_HEALTH_CAP, this.instance, null) ?: IntNBT.valueOf(0)
    }
}
