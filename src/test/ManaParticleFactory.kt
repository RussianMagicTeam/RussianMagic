/*package ru.rikgela.russianmagic.client.particle

import ManaParticle
import ManaParticleData
import net.minecraft.client.particle.ManaParticle
import net.minecraft.client.particle.IAnimatedSprite
import net.minecraft.client.particle.IParticleFactory
import net.minecraft.client.particle.Particle
import net.minecraft.world.World


/**
 * Created by TGG on 25/03/2020.
 *
 * On the client side:
 * When the client wants to spawn a Particle, it gives the FlameParticleData to this factory method
 * The factory selects an appropriate
 *
 */
class ManaParticleFactory : IParticleFactory<ManaParticleData> {
    //IParticleFactory
    override fun makeParticle(manaParticleData: ManaParticleData, world: World, xPos: Double, yPos: Double, zPos: Double, xVelocity: Double, yVelocity: Double, zVelocity: Double): Particle? {
        val newParticle = ManaParticle(world, xPos, yPos, zPos, xVelocity, yVelocity, zVelocity,
                manaParticleData.getTint(), manaParticleData.getDiameter(),
                sprites)
        newParticle.selectSpriteRandomly(sprites) // choose a random sprite from the available list (in this case there is only one)
        return newParticle
    }

    private val sprites // contains a list of textures; choose one using either
            : IAnimatedSprite

    // newParticle.selectSpriteRandomly(sprites); or newParticle.selectSpriteWithAge(sprites);
    // this method is needed for proper registration of your Factory:
    // The ParticleManager.register method creates a Sprite and passes it to your factory for subsequent use when rendering, then
    //   populates it with the textures from your textures/particle/xxx.json
    constructor(sprite: IAnimatedSprite) {
        sprites = sprite
    }

    // This is private to prevent you accidentally registering the Factory using the default constructor.
    // ParticleManager has two register methods, and if you use the wrong one the game will enter an infinite loop
    private constructor() {
        throw UnsupportedOperationException("Use the FlameParticleFactory(IAnimatedSprite sprite) constructor")
    }
}*/