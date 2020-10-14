package ru.rikgela.russianmagic.client.entity.model

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.entity.model.EntityModel
import net.minecraft.client.renderer.model.ModelRenderer
import ru.rikgela.russianmagic.objects.entity.projectile.AbstractProjectileEntity

class ProjectileEntityModel<T : AbstractProjectileEntity> : EntityModel<T>() {
    private val xuyGroup = ModelRenderer(this)

    init {
        textureWidth = 16
        textureHeight = 16
        xuyGroup.setTextureSize(textureWidth, textureHeight)
        xuyGroup.setRotationPoint(0.0f, 24.0f, 0.0f)
        xuyGroup.setTextureOffset(0, 0).addBox(-1.9f, -4.0f, 0.0f, 1.0f, 4.0f, 1.0f, -0.1f, false)
        xuyGroup.setTextureOffset(4, 0).addBox(-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, false)
        xuyGroup.setTextureOffset(3, 4).addBox(-2.8f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, false)
    }

    override fun setRotationAngles(entityIn: T, limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float) {
        //previously the render function, render code was moved to a method below
    }

    override fun render(matrixStack: MatrixStack, buffer: IVertexBuilder, packedLight: Int, packedOverlay: Int, red: Float, green: Float, blue: Float, alpha: Float) {
        xuyGroup.render(matrixStack, buffer, packedLight, packedOverlay)
    }

    fun setRotationAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }
}