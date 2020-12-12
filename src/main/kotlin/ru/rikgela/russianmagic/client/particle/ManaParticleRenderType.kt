package ru.rikgela.russianmagic.client.particle

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.particle.IParticleRenderType
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11


class ManaParticleRenderType : IParticleRenderType {
    override fun beginRender(bufferBuilder: BufferBuilder, textureManager: TextureManager) {
        RenderSystem.disableLighting()
        RenderSystem.disableDepthTest()
        RenderSystem.disableCull()
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        bufferBuilder.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR)
    }

    override fun finishRender(tessellator: Tessellator) {
        tessellator.draw()
    }

    companion object {
        val INSTANCE = ManaParticleRenderType()
    }
}