package ru.rikgela.russianmagic.objects.mana

import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.common.util.LazyOptional


class ManaProvider : ICapabilityProvider, INBTSerializable<CompoundTag> {
    private var mana: Mana? = null
    private val optional: LazyOptional<Mana> = LazyOptional.of { createMana() }

    private fun createMana(): Mana {
        if (this.mana == null) {
            this.mana = Mana()
        }
        return this.mana!!
    }

    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap === ManaProvider.MANA_CAP) {
            optional.cast()
        } else LazyOptional.empty()
    }

    override fun serializeNBT(): CompoundTag {
        val nbt = CompoundTag()
        nbt.put("Mana", createMana().serializeNBT())
        return nbt
    }

    override fun deserializeNBT(nbt: CompoundTag) {
        createMana().deserializeNBT(nbt)
    }

    companion object {
        var MANA_CAP: Capability<Mana> =
            CapabilityManager.get(object : CapabilityToken<Mana>() {})
    }
}




//
//@CapabilityInject(IMana::class)
//var MANA_CAP: Capability<IMana>? = null
//
//class ManaProvider(private val instance: IMana?) : ICapabilitySerializable<INBT> {
//
//    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
//        return if (cap == MANA_CAP) LazyOptional.of { instance as T } else LazyOptional.empty()
//    }
//
//    override fun deserializeNBT(nbt: INBT?) {
//        MANA_CAP?.storage?.readNBT(MANA_CAP, this.instance, null, nbt)
//    }
//
//    override fun serializeNBT(): INBT {
//        return MANA_CAP?.storage?.writeNBT(MANA_CAP, this.instance, null) ?: IntNBT.valueOf(0)
//    }
//}
