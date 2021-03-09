package ru.rikgela.russianmagic.client.particle


/*class ManaParticleRenderType : IParticleRenderType {
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
}*/