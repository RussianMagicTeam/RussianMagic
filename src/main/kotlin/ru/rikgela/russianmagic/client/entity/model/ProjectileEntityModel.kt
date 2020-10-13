package ru.rikgela.russianmagic.client.entity.model

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.entity.model.EntityModel
import net.minecraft.client.renderer.model.ModelRenderer
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity

class ProjectileEntityModel<T : ProjectileEntity?> : EntityModel<T>() {
    val bb_main: ModelRenderer
    override fun setRotationAngles(entity: T, limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float) {
        //previously the render function, render code was moved to a method below
    }

    override fun render(matrixStack: MatrixStack, buffer: IVertexBuilder, packedLight: Int, packedOverlay: Int, red: Float, green: Float, blue: Float, alpha: Float) {
        bb_main.render(matrixStack, buffer, packedLight, packedOverlay)
    }

    fun setRotationAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }

    init {
        textureWidth = 8
        textureHeight = 8
        bb_main = ModelRenderer(this)
        bb_main.setRotationPoint(0.0f, 24.0f, 0.0f)
    }
}