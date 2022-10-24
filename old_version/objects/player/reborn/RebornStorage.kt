package ru.rikgela.russianmagic.objects.player.reborn

import net.minecraft.nbt.ByteArrayNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability

class RebornStorage : Capability.IStorage<IReborn> {
    override fun writeNBT(capability: Capability<IReborn>, instance: IReborn, side: Direction?): INBT {
        return ByteArrayNBT(instance.toByteArray())
    }

    override fun readNBT(capability: Capability<IReborn>, instance: IReborn, side: Direction?, nbt: INBT) {
        if (nbt is ByteArrayNBT) {
            instance.loadFromByteArray(nbt.byteArray)
        }
    }
}