package ru.rikgela.russianmagic;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity;

import static ru.rikgela.russianmagic.RussianMagicKt.MOD_ID;

public class RMEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister(ForgeRegistries.ENTITIES, MOD_ID);
    public static final RegistryObject<EntityType<ProjectileEntity>> PROJECTILE_ENTITY = ENTITIES.register("projectile_entity", () -> EntityType.Builder.<ProjectileEntity>create(ProjectileEntity::new, EntityClassification.AMBIENT).size(0.9F, 1.1F).build(new ResourceLocation(MOD_ID, "projectile_entity").toString()));
}
