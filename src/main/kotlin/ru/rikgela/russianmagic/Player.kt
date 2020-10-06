/*package ru.rikgela.russianmagic

import net.minecraft.entity.Entity
import net.minecraft.nbt.INBT
import net.minecraft.nbt.IntNBT
import net.minecraft.util.Direction
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.energy.EnergyStorage
import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent


interface IManaHandler {
    var mana: Int

    fun addMana(mana: Int)
    fun removeMana(mana: Int)
}

@CapabilityInject(IManaHandler::class)
val CAPABILITY_MANA: Capability<IManaHandler>? = null

class DefaultManaHandler : IManaHandler {
    override var mana = 0

    override fun addMana(mana: Int) {
        this.mana += mana
    }

    override fun removeMana(mana: Int) {
        this.mana -= mana
        if (this.mana < 0) this.mana = 0
    }
}

*/