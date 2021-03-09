package ru.rikgela.russianmagic.client.particle

import net.minecraft.client.particle.IAnimatedSprite
import net.minecraft.client.particle.IParticleFactory
import net.minecraft.client.particle.Particle
import net.minecraft.particles.BasicParticleType
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

object RMParticles {
    @OnlyIn(Dist.CLIENT)
    class ManaParticle private constructor(worldIn: World,
                                           xCoordIn: Double, yCoordIn: Double, zCoordIn: Double,
                                           xSpeedIn: Double, ySpeedIn: Double, zSpeedIn: Double)
        : AbstractManaParticle(worldIn,
            xCoordIn, yCoordIn, zCoordIn,
            xSpeedIn, ySpeedIn, zSpeedIn) {
        @OnlyIn(Dist.CLIENT)
        class Factory(private val spriteSet: IAnimatedSprite) : IParticleFactory<BasicParticleType?> {
            override fun makeParticle(typeIn: BasicParticleType?, worldIn: World, x: Double, y: Double, z: Double, xSpeed: Double, ySpeed: Double, zSpeed: Double): Particle? {
                val manaparticle = ManaParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed)
                manaparticle.selectSpriteRandomly(spriteSet)
                return manaparticle
            }
        }

        init {
            red = 255.0f
            green = 81.0f
            blue = 0.0f
            setColor(red / 255.0f, green / 255.0f, blue / 255.0f)
            setSize(0.02f, 0.02f)
        }
    }
}
