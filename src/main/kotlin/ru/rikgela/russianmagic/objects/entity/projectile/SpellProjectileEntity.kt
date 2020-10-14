package ru.rikgela.russianmagic.objects.entity.projectile

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.ProjectileHelper
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.IPacket
import net.minecraft.network.play.server.SSpawnObjectPacket
import net.minecraft.particles.IParticleData
import net.minecraft.particles.ParticleTypes
import net.minecraft.util.DamageSource
import net.minecraft.util.math.*
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.event.ForgeEventFactory

abstract class SpellProjectileEntity protected constructor(p_i50173_1_: EntityType<out SpellProjectileEntity?>?, p_i50173_2_: World?) : Entity(p_i50173_1_, p_i50173_2_) {
    var shootingEntity: LivingEntity? = null
    private var ticksAlive = 0
    private var ticksInAir = 0
    var accelerationX = 0.0
    var accelerationY = 0.0
    var accelerationZ = 0.0

    constructor(p_i50174_1_: EntityType<out SpellProjectileEntity?>?, p_i50174_2_: Double, p_i50174_4_: Double, p_i50174_6_: Double, p_i50174_8_: Double, p_i50174_10_: Double, p_i50174_12_: Double, p_i50174_14_: World?) : this(p_i50174_1_, p_i50174_14_) {
        setLocationAndAngles(p_i50174_2_, p_i50174_4_, p_i50174_6_, rotationYaw, rotationPitch)
        setPosition(p_i50174_2_, p_i50174_4_, p_i50174_6_)
        val d0 = MathHelper.sqrt(p_i50174_8_ * p_i50174_8_ + p_i50174_10_ * p_i50174_10_ + p_i50174_12_ * p_i50174_12_).toDouble()
        accelerationX = p_i50174_8_ / d0 * 0.1
        accelerationY = p_i50174_10_ / d0 * 0.1
        accelerationZ = p_i50174_12_ / d0 * 0.1
    }

    constructor(p_i50175_1_: EntityType<out SpellProjectileEntity?>?, p_i50175_2_: LivingEntity, p_i50175_3_: Double, p_i50175_5_: Double, p_i50175_7_: Double, p_i50175_9_: World?) : this(p_i50175_1_, p_i50175_9_) {
        shootingEntity = p_i50175_2_
        setLocationAndAngles(p_i50175_2_.posX, p_i50175_2_.posY + 1.0F, p_i50175_2_.posZ, p_i50175_2_.rotationYaw, p_i50175_2_.rotationPitch)
        recenterBoundingBox()
        motion = Vec3d.ZERO
        //p_i50175_3_ = p_i50175_3_ + this.rand.nextGaussian() * 0.4D;
        //p_i50175_5_ = p_i50175_5_ + this.rand.nextGaussian() * 0.4D;
        //p_i50175_7_ = p_i50175_7_ + this.rand.nextGaussian() * 0.4D;
        val d0 = MathHelper.sqrt(p_i50175_3_ * p_i50175_3_ + p_i50175_5_ * p_i50175_5_ + p_i50175_7_ * p_i50175_7_).toDouble()
        accelerationX = p_i50175_3_ / d0 * 0.1
        accelerationY = p_i50175_5_ / d0 * 0.1
        accelerationZ = p_i50175_7_ / d0 * 0.1
    }

    override fun registerData() {}

    /**
     * Checks if the entity is in range to render.
     */
    @OnlyIn(Dist.CLIENT)
    override fun isInRangeToRenderDist(distance: Double): Boolean {
        var d0 = boundingBox.averageEdgeLength * 4.0
        if (java.lang.Double.isNaN(d0)) {
            d0 = 4.0
        }
        d0 = d0 * 64.0
        return distance < d0 * d0
    }

    /**
     * Called to update the entity's position/logic.
     */
    override fun tick() {
        if (world.isRemote || (shootingEntity == null || !shootingEntity!!.removed) && world.isBlockLoaded(BlockPos(this))) {
            super.tick()
            if (isFireballFiery) {
                setFire(1)
            }
            ++ticksInAir
            val raytraceresult = ProjectileHelper.rayTrace(this, true, ticksInAir >= 25, shootingEntity, RayTraceContext.BlockMode.COLLIDER)
            if (raytraceresult.type != RayTraceResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                onImpact(raytraceresult)
            }
            val vec3d = motion
            val d0 = posX + vec3d.x
            val d1 = posY + vec3d.y
            val d2 = posZ + vec3d.z
            ProjectileHelper.rotateTowardsMovement(this, 0.2f)
            var f = motionFactor
            if (this.isInWater) {
                for (i in 0..3) {
                    val f1 = 0.25f
                    world.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25, d1 - vec3d.y * 0.25, d2 - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z)
                }
                f = 0.8f
            }
            motion = vec3d.add(accelerationX, accelerationY, accelerationZ).scale(f.toDouble())
            world.addParticle(particle, d0, d1 + 0.5, d2, 0.0, 0.0, 0.0)
            setPosition(d0, d1, d2)
        } else {
            this.remove()
        }
    }

    protected val isFireballFiery: Boolean
        protected get() = false

    protected val particle: IParticleData
        protected get() = ParticleTypes.SMOKE

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected val motionFactor: Float
        protected get() = 0.95f

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected open fun onImpact(result: RayTraceResult) {
        val `raytraceresult$type` = result.type
        if (`raytraceresult$type` == RayTraceResult.Type.BLOCK) {
            val blockraytraceresult = result as BlockRayTraceResult
            val blockstate = world.getBlockState(blockraytraceresult.pos)
            blockstate.onProjectileCollision(world, blockstate, blockraytraceresult, this)
        }
    }

    public override fun writeAdditional(compound: CompoundNBT) {
        val vec3d = motion
        compound.put("direction", newDoubleNBTList(*doubleArrayOf(vec3d.x, vec3d.y, vec3d.z)))
        compound.put("power", newDoubleNBTList(*doubleArrayOf(accelerationX, accelerationY, accelerationZ)))
        compound.putInt("life", ticksAlive)
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public override fun readAdditional(compound: CompoundNBT) {
        if (compound.contains("power", 9)) {
            val listnbt = compound.getList("power", 6)
            if (listnbt.size == 3) {
                accelerationX = listnbt.getDouble(0)
                accelerationY = listnbt.getDouble(1)
                accelerationZ = listnbt.getDouble(2)
            }
        }
        ticksAlive = compound.getInt("life")
        if (compound.contains("direction", 9) && compound.getList("direction", 6).size == 3) {
            val listnbt1 = compound.getList("direction", 6)
            this.setMotion(listnbt1.getDouble(0), listnbt1.getDouble(1), listnbt1.getDouble(2))
        } else {
            this.remove()
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    override fun canBeCollidedWith(): Boolean {
        return true
    }

    override fun getCollisionBorderSize(): Float {
        return 1.0f
    }

    /**
     * Called when the entity is attacked.
     */
    override fun attackEntityFrom(source: DamageSource, amount: Float): Boolean {
        return if (isInvulnerableTo(source)) {
            false
        } else {
            markVelocityChanged()
            if (source.trueSource != null) {
                val vec3d = source.trueSource!!.lookVec
                motion = vec3d
                accelerationX = vec3d.x * 0.1
                accelerationY = vec3d.y * 0.1
                accelerationZ = vec3d.z * 0.1
                if (source.trueSource is LivingEntity) {
                    shootingEntity = source.trueSource as LivingEntity?
                }
                true
            } else {
                false
            }
        }
    }

    /**
     * Gets how bright this entity is.
     */
    override fun getBrightness(): Float {
        return 1.0f
    }

    override fun createSpawnPacket(): IPacket<*> {
        val i = if (shootingEntity == null) 0 else shootingEntity!!.entityId
        return SSpawnObjectPacket(entityId, this.uniqueID, posX, posY, posZ, rotationPitch, rotationYaw, type, i, Vec3d(accelerationX, accelerationY, accelerationZ))
    }
}