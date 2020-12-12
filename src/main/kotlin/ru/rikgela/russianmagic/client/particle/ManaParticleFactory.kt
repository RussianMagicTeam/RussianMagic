package ru.rikgela.russianmagic.client.particle

import ManaParticle
import net.minecraft.client.particle.IParticleFactory
import net.minecraft.client.particle.Particle
import net.minecraft.world.World
import javax.annotation.Nullable


class ManaParticleFactory : IParticleFactory<ManaParticleData> {
    @Nullable
    override fun makeParticle(typeIn: ManaParticleData, worldIn: World, x: Double, y: Double, z: Double, xSpeed: Double, ySpeed: Double, zSpeed: Double): Particle? {
        return ManaParticle(worldIn, x, y, z)
    }
}