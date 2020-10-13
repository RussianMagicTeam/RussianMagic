package ru.rikgela.russianmagic.client.entity.render

import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.util.ResourceLocation
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity

class ProjectileEntityRender(renderManagerIn: EntityRendererManager?) : EntityRenderer<ProjectileEntity?>(renderManagerIn) {
    override fun getEntityTexture(entity: ProjectileEntity?): ResourceLocation {
        return TEXTURE
    }

    companion object {
        protected val TEXTURE = ResourceLocation(MOD_ID, "textures/entity/projectile_entity.png")
    }
}