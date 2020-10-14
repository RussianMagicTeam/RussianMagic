package ru.rikgela.russianmagic.client.entity.render

import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.util.ResourceLocation
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity
import sun.java2d.pipe.hw.AccelSurface


class ProjectileEntityRender(renderManagerIn: EntityRendererManager?) : EntityRenderer<ProjectileEntity?>(renderManagerIn) {
    override fun getEntityTexture(entity: ProjectileEntity?): ResourceLocation {
        return TEXTURE
    }
    //override fun getEntityModel(entity: ProjectileEntity): ResourceLocation{
    //    return MODEL
    //}

    companion object {
        protected val TEXTURE = ResourceLocation(MOD_ID, "textures/entity/projectile_entity.png")
        //protected val MODEL = ResourceLocation(MOD_ID, "models/entity/projectile_entity.json")
    }
}