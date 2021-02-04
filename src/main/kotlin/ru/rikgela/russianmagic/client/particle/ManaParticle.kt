package ru.rikgela.russianmagic.client.particle

import net.minecraft.client.particle.*
import net.minecraft.particles.BasicParticleType
import net.minecraft.tags.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn


@OnlyIn(Dist.CLIENT)
class ManaParticle private constructor(worldIn: World, xCoordIn: Double, yCoordIn: Double, zCoordIn: Double, xSpeedIn: Double, ySpeedIn: Double, zSpeedIn: Double) : SpriteTexturedParticle(worldIn, xCoordIn, yCoordIn, zCoordIn) {
    override fun tick() {
        prevPosX = posX
        prevPosY = posY
        prevPosZ = posZ
        if (maxAge-- <= 0) {
            setExpired()
        } else {
            motionY += 0.002
            move(motionX, motionY, motionZ)
            motionX *= 0.85f.toDouble()
            motionY *= 0.85f.toDouble()
            motionZ *= 0.85f.toDouble()
            if (!world.getFluidState(BlockPos(posX, posY, posZ)).isTagged(FluidTags.WATER)) {
                setExpired()
            }
        }
    }

    override fun getRenderType(): IParticleRenderType {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE
    }

    /*@OnlyIn(Dist.CLIENT)
    class Factory(private val spriteSet: IAnimatedSprite) : IParticleFactory<BasicParticleType?> {
        override fun makeParticle(typeIn: BasicParticleType?, worldIn: World, x: Double, y: Double, z: Double, xSpeed: Double, ySpeed: Double, zSpeed: Double): Particle? {
            val bubbleparticle = ManaParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed)
            bubbleparticle.selectSpriteRandomly(spriteSet)
            return bubbleparticle
        }
    }*/

    @OnlyIn(Dist.CLIENT)
    class Factory(private val spriteSet: IAnimatedSprite) : IParticleFactory<BasicParticleType?> {
        override fun makeParticle(typeIn: BasicParticleType?, worldIn: World, x: Double, y: Double, z: Double, xSpeed: Double, ySpeed: Double, zSpeed: Double): Particle? {
            val manaparticle = ManaParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed)
            manaparticle.selectSpriteRandomly(spriteSet)
            return manaparticle
        }

    }


    init {
        setSize(0.02f, 0.02f)
        //setColor( 0.0f,  0.0f,  1.0f)
        particleScale *= rand.nextFloat() * 0.6f + 0.2f
        motionX = xSpeedIn * 0.2f.toDouble() + (Math.random() * 2.0 - 1.0) * 0.02f.toDouble()
        motionY = ySpeedIn * 0.2f.toDouble() + (Math.random() * 2.0 - 1.0) * 0.02f.toDouble()
        motionZ = zSpeedIn * 0.2f.toDouble() + (Math.random() * 2.0 - 1.0) * 0.02f.toDouble()
        maxAge = (8.0 / (Math.random() * 0.8 + 0.2)).toInt()
    }

    /*private val sprites: IAnimatedSprite? = null
    constructor(world: ClientWorld?, x: Double, y: Double, z: Double,
                velocityX: Double, velocityY: Double, velocityZ: Double,
                tint: Color, diameter: Double,
                sprites: IAnimatedSprite) {
        super(world, x, y, z)
        setColor(tint.getRed() / 255.0f, tint.getGreen() / 255.0f, tint.getBlue() / 255.0f)
        setSize(diameter.toFloat(), diameter.toFloat()) // the size (width, height) of the collision box.
        val PARTICLE_SCALE_FOR_ONE_METRE = 0.5f //  if the particleScale is 0.5, the texture will be rendered as 1 metre high
        particleScale = PARTICLE_SCALE_FOR_ONE_METRE * diameter.toFloat() // sets the rendering size of the particle for a TexturedParticle.
        maxAge = 100 // lifetime in ticks: 100 ticks = 5 seconds
        val ALPHA_VALUE = 1.0f
        particleAlpha = ALPHA_VALUE

        //the vanilla Particle constructor added random variation to our starting velocity.  Undo it!
        motionX = velocityX
        motionY = velocityY
        motionZ = velocityZ
        canCollide = true // the move() method will check for collisions with scenery
    }*/

}