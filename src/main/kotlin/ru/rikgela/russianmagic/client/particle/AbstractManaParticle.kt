package ru.rikgela.russianmagic.client.particle

import net.minecraft.client.particle.IParticleRenderType
import net.minecraft.client.particle.SpriteTexturedParticle
import net.minecraft.tags.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn


@OnlyIn(Dist.CLIENT)
abstract class AbstractManaParticle constructor(worldIn: World, xCoordIn: Double, yCoordIn: Double, zCoordIn: Double, xSpeedIn: Double, ySpeedIn: Double, zSpeedIn: Double) : SpriteTexturedParticle(worldIn, xCoordIn, yCoordIn, zCoordIn) {
    var red: Float = 0.0f
    var blue: Float = 0.0f
    var green: Float = 0.0f
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
            if (world.getFluidState(BlockPos(posX, posY, posZ)).isTagged(FluidTags.WATER)) {
                setExpired()
            }
        }
    }

    override fun getRenderType(): IParticleRenderType {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE
    }

    fun updateColor() {
        setColor(red / 255.0f, green / 255.0f, blue / 255.0f)
    }

    init {
        particleScale *= rand.nextFloat() * 0.6f + 0.2f
        motionX = xSpeedIn * 0.2f.toDouble() + (Math.random() * 2.0 - 1.0) * 0.02f.toDouble()
        motionY = ySpeedIn * 0.2f.toDouble() + (Math.random() * 2.0 - 1.0) * 0.02f.toDouble()
        motionZ = zSpeedIn * 0.2f.toDouble() + (Math.random() * 2.0 - 1.0) * 0.02f.toDouble()
        maxAge = 20
    }
}