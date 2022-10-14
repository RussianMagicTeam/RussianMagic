package ru.rikgela.russianmagic.init

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.client.particle.ColoredParticleType

//@OnlyIn(Dist.CLIENT)
object RMParticles {
    val PARTICLES = DeferredRegister(ForgeRegistries.PARTICLE_TYPES, MOD_ID)

    @JvmField
    val MANA_PARTICLE = PARTICLES.register("rm_mana_particle") { ColoredParticleType(true) }
}