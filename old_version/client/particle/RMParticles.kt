package ru.rikgela.russianmagic.client.particle

import net.minecraft.client.particle.IAnimatedSprite
import net.minecraft.client.particle.IParticleFactory
import net.minecraft.client.particle.Particle
import net.minecraft.world.World


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
                val manaParticle = ManaParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed)
                manaParticle.red = typeIn.red
                manaParticle.green = typeIn.green
                manaParticle.blue = typeIn.blue
                manaParticle.updateColor()
                manaParticle.selectSpriteRandomly(spriteSet)
                return manaParticle
            }
        }
    }
}

