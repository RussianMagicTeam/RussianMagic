package ru.rikgela.russianmagic.client.entity.model

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.entity.model.EntityModel
import net.minecraft.client.renderer.model.ModelRenderer
import net.minecraft.entity.Entity
import ru.rikgela.russianmagic.objects.entity.projectile.AbstractProjectileEntity

/*class ProjectileEntityModel<T : AbstractProjectileEntity> : EntityModel<T>() {
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
}*/

class ProjectileEntityModel<T : AbstractProjectileEntity> : EntityModel<T>() {

    private val bone = ModelRenderer(this)

    init {
        textureWidth = 32
        textureHeight = 32
        //bone = ModelRenderer(this)
        bone.setRotationPoint(-1.3f, 15.0f, -1.8f)
        setRotationAngle(bone, -0.6109f, -0.6981f, 0.4363f)
        bone.setTextureOffset(11, 11).addBox(1.3237f, -1.1018f, -2.7027f, 2.0f, 2.0f, 0.0f, 2.0f, true)
        bone.setTextureOffset(11, 0).addBox(0.3237f, -2.1018f, -1.7027f, 4.0f, 4.0f, 4.0f, 2.0f, true)
        bone.setTextureOffset(11, 8).addBox(-0.6763f, -1.1018f, -0.7027f, 0.0f, 2.0f, 2.0f, 2.0f, true)
        bone.setTextureOffset(11, 8).addBox(5.3237f, -1.1018f, -0.7027f, 0.0f, 2.0f, 2.0f, 2.0f, true)
        bone.setTextureOffset(11, 15).addBox(0.3237f, -2.1018f, 4.2973f, 4.0f, 4.0f, 0.0f, 1.0f, true)
        bone.setTextureOffset(11, 0).addBox(0.3237f, -4.1018f, -1.7027f, 4.0f, 0.0f, 4.0f, 1.0f, true)
        bone.setTextureOffset(11, 0).addBox(0.3237f, 3.8982f, -1.7027f, 4.0f, 0.0f, 4.0f, 1.0f, true)
    }
    override fun setRotationAngles(entityIn: T, limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float) {
        //previously the render function, render code was moved to a method below
    }

    override fun render(matrixStack: MatrixStack, buffer: IVertexBuilder, packedLight: Int, packedOverlay: Int, red: Float, green: Float, blue: Float, alpha: Float) {
        bone.render(matrixStack, buffer, packedLight, packedOverlay)
    }

    fun setRotationAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }


}