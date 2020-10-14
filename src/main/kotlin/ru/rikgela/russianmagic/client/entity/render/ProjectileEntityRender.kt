package ru.rikgela.russianmagic.client.entity.render

import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity


@OnlyIn(Dist.CLIENT)
class ProjectileEntityRender(renderManagerIn: EntityRendererManager) : EntityRenderer<ProjectileEntity>(renderManagerIn) {

    override fun getEntityTexture(entity: ProjectileEntity): ResourceLocation {
        return TEXTURE
    }

    companion object {
        private val TEXTURE = ResourceLocation(MOD_ID, "textures/entity/projectile_entity.png")
    }

}