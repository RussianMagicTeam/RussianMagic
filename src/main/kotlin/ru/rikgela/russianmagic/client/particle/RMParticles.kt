package ru.rikgela.russianmagic.client.particle

import net.minecraft.client.particle.IAnimatedSprite
import net.minecraft.client.particle.IParticleFactory
import net.minecraft.client.particle.Particle
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class ManaParticle private constructor(
    worldIn: World,
    xCoordIn: Double, yCoordIn: Double, zCoordIn: Double,
    xSpeedIn: Double, ySpeedIn: Double, zSpeedIn: Double
) : AbstractManaParticle(
    worldIn,
    xCoordIn, yCoordIn, zCoordIn,
    xSpeedIn, ySpeedIn, zSpeedIn
) {

    init {
        updateColor()
        setSize(0.02f, 0.02f)
    }

    companion object {
        @OnlyIn(Dist.CLIENT)
        class Factory(private val spriteSet: IAnimatedSprite) : IParticleFactory<ColoredParticleType> {
            override fun makeParticle(
                typeIn: ColoredParticleType,
                worldIn: World,
                x: Double,
                y: Double,
                z: Double,
                xSpeed: Double,
                ySpeed: Double,
                zSpeed: Double
            ): Particle {
                val manaparticle = ManaParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed)
                manaparticle.red = typeIn.red
                manaparticle.green = typeIn.green
                manaparticle.blue = typeIn.blue
                manaparticle.updateColor()
                manaparticle.selectSpriteRandomly(spriteSet)
                return manaparticle
            }
        }
    }
}

