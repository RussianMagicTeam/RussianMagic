package ru.rikgela.russianmagic.objects.player.magichealth

import net.minecraft.nbt.ByteArrayNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import ru.rikgela.russianmagic.objects.player.IMagicHealth

class MagicHealthStorage : Capability.IStorage<IMagicHealth> {
    override fun writeNBT(capability: Capability<IMagicHealth>, instance: IMagicHealth, side: Direction?): INBT {
        return ByteArrayNBT(instance.toByteArray())
    }

    override fun readNBT(capability: Capability<IMagicHealth>, instance: IMagicHealth, side: Direction?, nbt: INBT) {
        if (nbt is ByteArrayNBT) {
            instance.loadFromByteArray(nbt.byteArray)
        }
    }
}