package ru.rikgela.russianmagic

import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity

object RMEntities {
    @JvmField
    val ENTITIES: DeferredRegister<EntityType<*>?> = DeferredRegister(ForgeRegistries.ENTITIES, MOD_ID)
    val PROJECTILE_ENTITY = ENTITIES.register("projectile_entity") { EntityType.Builder.create({ p_i50160_1_: EntityType<ProjectileEntity?>?, p_i50160_2_: World? -> ProjectileEntity(p_i50160_1_, p_i50160_2_) }, EntityClassification.AMBIENT).size(0.9f, 1.1f).build(ResourceLocation(MOD_ID, "projectile_entity").toString()) }
}