package ru.rikgela.russianmagic.client.entity.render

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.client.renderer.RenderState
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderType.makeType
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.client.entity.model.ProjectileEntityModel
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity


@OnlyIn(Dist.CLIENT)
class ProjectileEntityRender(renderManagerIn: EntityRendererManager, private val TEXTURE: ResourceLocation) : EntityRenderer<ProjectileEntity>(renderManagerIn) {
    private val model = ProjectileEntityModel<ProjectileEntity>()

    override fun getEntityTexture(entity: ProjectileEntity): ResourceLocation {
        return TEXTURE
    }

    override fun render(entityIn: ProjectileEntity, entityYaw: Float, partialTicks: Float, matrixStackIn: MatrixStack, bufferIn: IRenderTypeBuffer, packedLightIn: Int) {
        @Suppress("INACCESSIBLE_TYPE")
        val renderType = makeType("projectile_entity",
                DefaultVertexFormats.POSITION_COLOR_LIGHTMAP,
                7,
                256,
                RenderType.State.getBuilder()
                        .texture(RenderState.TextureState(TEXTURE, false, false))
                        .cull(RenderState.CullState(false))
                        .lightmap(RenderState.LightmapState(true))
                        .build(false))
        model.render(matrixStackIn, bufferIn.getBuffer(renderType), packedLightIn, 10, 250F, 250F, 250F, 100F)
    }

}


