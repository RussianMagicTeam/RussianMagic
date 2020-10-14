package ru.rikgela.russianmagic.client.entity.render

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.client.renderer.entity.LivingRenderer
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.entity.LivingEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.client.entity.model.ProjectileEntityModel
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity


@OnlyIn(Dist.CLIENT)
class ProjectileEntityRender(renderManagerIn: EntityRendererManager, private val TEXTURE: ResourceLocation) : EntityRenderer<ProjectileEntity>(renderManagerIn)
{
    override fun getEntityTexture(entity: ProjectileEntity): ResourceLocation
    {
        return TEXTURE
    }

    override fun render(entityIn: ProjectileEntity, entityYaw: Float, partialTicks: Float, matrixStackIn: MatrixStack, bufferIn: IRenderTypeBuffer, packedLightIn: Int) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn)
    }

}


