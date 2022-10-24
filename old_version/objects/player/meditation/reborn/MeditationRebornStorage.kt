package ru.rikgela.russianmagic.objects.player.meditation.reborn
import net.minecraft.nbt.ByteArrayNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability

class MeditationRebornStorage : Capability.IStorage<IMeditationReborn> {
    override fun writeNBT(capability: Capability<IMeditationReborn>, instance: IMeditationReborn, side: Direction?): INBT {
        return ByteArrayNBT(instance.toByteArray())
    }

    override fun readNBT(capability: Capability<IMeditationReborn>, instance: IMeditationReborn, side: Direction?, nbt: INBT) {
        if (nbt is ByteArrayNBT) {
            instance.loadFromByteArray(nbt.byteArray)
        }
    }
}