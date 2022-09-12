package ru.rikgela.russianmagic.objects.player.mana

import net.minecraft.nbt.ByteArrayNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability


class PlayerManaStorage : Capability.IStorage<IPlayerMana> {
    override fun writeNBT(capability: Capability<IPlayerMana>, instance: IPlayerMana, side: Direction?): INBT {
        return ByteArrayNBT(instance.toByteArray())
    }

    override fun readNBT(capability: Capability<IPlayerMana>, instance: IPlayerMana, side: Direction?, nbt: INBT) {
        if (nbt is ByteArrayNBT) {
            instance.loadFromByteArray(nbt.byteArray)
        }
    }
}


