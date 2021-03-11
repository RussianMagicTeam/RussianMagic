package ru.rikgela.russianmagic.init

import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.client.particle.ColoredParticleType

object RMParticles {
    val PARTICLES = DeferredRegister(ForgeRegistries.PARTICLE_TYPES, MOD_ID)

    @JvmField
    val MANA_PARTICLE = PARTICLES.register("rm_mana_particle") { ColoredParticleType(true) }
}