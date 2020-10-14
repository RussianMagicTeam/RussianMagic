package ru.rikgela.russianmagic

import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity

object RMEntities {
    val ENTITIES: DeferredRegister<EntityType<*>?> = DeferredRegister(ForgeRegistries.ENTITIES, MOD_ID)

    val PROJECTILE_ENTITY: RegistryObject<EntityType<ProjectileEntity>> = ENTITIES
            .register("projectile_entity"
            ) {
                EntityType.Builder.create({ a: EntityType<ProjectileEntity>, b: World -> ProjectileEntity(a, b) }, EntityClassification.MISC)
                        .size(0.9f, 1.1f)
                        .build(ResourceLocation(MOD_ID, "textures/entity/projectile_entity.png")
                                .toString())
            }
}

