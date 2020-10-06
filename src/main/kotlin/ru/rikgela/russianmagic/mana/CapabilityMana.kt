package ru.rikgela.russianmagic.mana

import net.minecraft.nbt.INBT
import net.minecraft.nbt.IntNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager

object CapabilityMana {
    @CapabilityInject(IManaStorage::class)
    var MANA: Capability<IManaStorage>? = null
    fun register() {
        CapabilityManager.INSTANCE.register(IManaStorage::class.java, object : IStorage<IManaStorage> {
            override fun writeNBT(capability: Capability<IManaStorage>, instance: IManaStorage, side: Direction): INBT? {
                return IntNBT.valueOf(instance.getManaStored())
            }

            override fun readNBT(capability: Capability<IManaStorage>, instance: IManaStorage, side: Direction, nbt: INBT) {
                require(instance is ManaStorage) { "Can not deserialize to an instance that isn't the default implementation" }
                instance.mana = (nbt as IntNBT).int
            }
        }) { ManaStorage(1000) }
    }
}
