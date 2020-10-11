package ru.rikgela.russianmagic.objects.entity.projectile;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static ru.rikgela.russianmagic.RussianMagicKt.MOD_ID;

public class ProjectileEntity extends AbstractFireballEntity {
    public ProjectileEntity(EntityType<? extends net.minecraft.entity.projectile.SmallFireballEntity> p_i50160_1_, World p_i50160_2_) {
        super(p_i50160_1_, p_i50160_2_);
    }

    public ProjectileEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(EntityType.SMALL_FIREBALL, shooter, accelX, accelY, accelZ, worldIn);
    }

    public ProjectileEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(EntityType.SMALL_FIREBALL, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            if (result.getType() == Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult)result).getEntity();
                if (!entity.isImmuneToFire()) {
                    int i = entity.getFireTimer();
                    entity.setFire(5);
                    boolean flag = entity.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F);
                    if (flag) {
                        this.applyEnchantments(this.shootingEntity, entity);
                    } else {
                        entity.setFireTimer(i);
                    }
                }
            } else if (this.shootingEntity == null || !(this.shootingEntity instanceof MobEntity) || ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity)) {
                BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)result;
                BlockPos blockpos = blockraytraceresult.getPos().offset(blockraytraceresult.getFace());
                if (this.world.isAirBlock(blockpos)) {
                    this.world.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                }
            }

            this.remove();
        }

    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}


    private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE, key, builder.build(key));
    }
