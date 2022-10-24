package ru.rikgela.russianmagic.objects.player.mana

import net.minecraft.nbt.INBT
import net.minecraft.nbt.IntNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional

@CapabilityInject(IPlayerMana::class)
var PLAYER_MANA_CAP: Capability<IPlayerMana>? = null

class PlayerManaProvider(private val instance: IPlayerMana?) : ICapabilitySerializable<INBT> {

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap == PLAYER_MANA_CAP) LazyOptional.of { instance as T } else LazyOptional.empty()
    }

    override fun deserializeNBT(nbt: INBT?) {
        PLAYER_MANA_CAP?.storage?.readNBT(PLAYER_MANA_CAP, this.instance, null, nbt)
    }

    override fun serializeNBT(): INBT {
        return PLAYER_MANA_CAP?.storage?.writeNBT(PLAYER_MANA_CAP, this.instance, null) ?: IntNBT.valueOf(0)
    }
}
