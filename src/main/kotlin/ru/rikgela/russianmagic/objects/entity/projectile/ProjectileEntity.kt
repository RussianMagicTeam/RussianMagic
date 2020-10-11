package ru.rikgela.russianmagic.objects.entity.projectile

import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MobEntity
import net.minecraft.entity.projectile.AbstractFireballEntity
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.util.DamageSource
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.util.math.EntityRayTraceResult
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import net.minecraftforge.event.ForgeEventFactory
import ru.rikgela.russianmagic.RMEntities
import ru.rikgela.russianmagic.RMEntities.PROJECTILE_ENTITY

class ProjectileEntity : AbstractFireballEntity {
    constructor(p_i50160_1_: EntityType<out ProjectileEntity?>?, p_i50160_2_: World?) : super(p_i50160_1_, p_i50160_2_) {}
    constructor(worldIn: World?, shooter: LivingEntity?, accelX: Double, accelY: Double, accelZ: Double) : super(RMEntities.PROJECTILE_ENTITY.get(), shooter, accelX, accelY, accelZ, worldIn) {}
    constructor(worldIn: World?, x: Double, y: Double, z: Double, accelX: Double, accelY: Double, accelZ: Double) : super(RMEntities.PROJECTILE_ENTITY.get(), x, y, z, accelX, accelY, accelZ, worldIn) {}

    override fun onImpact(result: RayTraceResult) {
        super.onImpact(result)
        if (!world.isRemote) {
            if (result.type == RayTraceResult.Type.ENTITY) {
                val entity = (result as EntityRayTraceResult).entity
                if (!entity.isImmuneToFire) {
                    val i = entity.fireTimer
                    entity.setFire(5)
                    val flag = entity.attackEntityFrom(DamageSource.causeFireballDamage(this, shootingEntity), 5.0f)
                    if (flag) {
                        applyEnchantments(shootingEntity, entity)
                    } else {
                        entity.fireTimer = i
                    }
                }
            } else if (shootingEntity == null || shootingEntity !is MobEntity || ForgeEventFactory.getMobGriefingEvent(world, shootingEntity)) {
                val blockraytraceresult = result as BlockRayTraceResult
                val blockpos = blockraytraceresult.pos.offset(blockraytraceresult.face)
                if (world.isAirBlock(blockpos)) {
                    world.setBlockState(blockpos, Blocks.FIRE.defaultState)
                }
            }
            this.remove()
        }
    }

    override fun canBeCollidedWith(): Boolean {
        return false
    }

    override fun attackEntityFrom(source: DamageSource, amount: Float): Boolean {
        return false
    }
}