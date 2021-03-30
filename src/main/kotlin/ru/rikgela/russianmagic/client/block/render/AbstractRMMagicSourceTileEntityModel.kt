package ru.rikgela.russianmagic.client.block.render

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.model.Model
import net.minecraft.client.renderer.model.ModelRenderer
import net.minecraft.client.renderer.tileentity.TileEntityRenderer
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.tileentity.AbstractRMMagicSourceTileEntity
import java.util.function.Function


class AbstractRMMagicSourceTileEntityModel : Model(Function { locationIn: ResourceLocation? -> RenderType.getEntityTranslucent(locationIn) }) {

    private val model: ModelRenderer
    fun renderCrystal(ms: MatrixStack, buffer: IVertexBuilder, light: Int, overlay: Int) {
        model.render(ms, buffer, light, overlay)
    }

    override fun render(ms: MatrixStack, buffer: IVertexBuilder, light: Int, overlay: Int, r: Float, g: Float, b: Float, a: Float) {
        throw UnsupportedOperationException("unimplemented")
    }

    init {
        textureWidth = 64
        textureHeight = 64
        model = ModelRenderer(this, 22, 0)
        model.setRotationPoint(0.0f, 0.0f, 0.0f)
        model.addBox(0f, 0f, 0f, 2f, 2f, 2f, 0.0f)
    }
}

@OnlyIn(Dist.CLIENT)
class AbstractRMMagicSourceTileEntityRenderer(p_i226016_1_: TileEntityRendererDispatcher) : TileEntityRenderer<AbstractRMMagicSourceTileEntity>(p_i226016_1_) {
    private val model = AbstractRMMagicSourceTileEntityModel()

    override fun render(tileEntityIn: AbstractRMMagicSourceTileEntity, pticks: Float, matrixStack: MatrixStack, buffers: IRenderTypeBuffer, light: Int, overlay: Int) {
        val texture = ResourceLocation(MOD_ID, "textures/block/rm_basic_magic_source.png")
        val layer = RenderType.getEntityTranslucent(texture)
        val buffer = buffers.getBuffer(layer)
        matrixStack.push()
        val shift: Float = tileEntityIn.currentMana.toFloat() / tileEntityIn.maxMana.toFloat()
        matrixStack.translate((7.0 - 7.0 * shift) / 16.0, (7.0 - 7.0 * shift) / 16.0, (7.0 - 7.0 * shift) / 16.0)
        matrixStack.scale(1F + 7.0F * (shift), 1F + 7.0F * shift, 1F + 7.0F * shift)
        model.renderCrystal(matrixStack, buffer, light, overlay)
        matrixStack.pop()
    }
}
