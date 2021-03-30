package ru.rikgela.russianmagic.client.block.render

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.Vector3f
import net.minecraft.client.renderer.tileentity.TileEntityRenderer
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.tileentity.AbstractRMMagicSourceTileEntity


@OnlyIn(Dist.CLIENT)
class RMBasicMagicSourceBlock(p_i226016_1_: TileEntityRendererDispatcher?) : TileEntityRenderer<AbstractRMMagicSourceTileEntity>(p_i226016_1_) {
    val MAGICBLOCK_TEXTURE = ResourceLocation(MOD_ID, "block/magicblock")
    override fun render(tileEntityIn: AbstractRMMagicSourceTileEntity, partialTicks: Float, matrixStackIn: MatrixStack, bufferIn: IRenderTypeBuffer, combinedLightIn: Int, combinedOverlayIn: Int) {
        //val sprite: TextureAtlasSprite = Minecraft.getInstance().getTextureGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(MAGICBLOCK_TEXTURE)
        val builder: IVertexBuilder = bufferIn.getBuffer(RenderType.getTranslucent())
        matrixStackIn.push()
        matrixStackIn.translate(0.5, 0.5, 0.5)
        val size = tileEntityIn.currentMana.toFloat() / tileEntityIn.maxMana.toFloat()
        val rotation = Vector3f.YP.rotationDegrees(45.0F)
        //matrixStackIn.translate(0.0, 0.4f.toDouble(), 0.0)
        matrixStackIn.rotate(rotation)
        matrixStackIn.scale(size, size, size)
        builder.pos(matrixStackIn.last.matrix, 0.5F, 0.5F, 0.5F)
                .color(1.0f, 1.0f, 1.0f, 1.0f)
                .tex(0.5F, 0.5F)
                .lightmap(0, 240)
                .normal(1.0F, 0.0F, 0.0F)
                .endVertex()
        //Minecraft.getInstance().renderManager.
        //renderEntityStatic(tileEntityIn, 0.0, 0.0, 0.0, 0.0f, partialTicks, matrixStackIn, bufferIn, combinedLightIn)
        matrixStackIn.pop()
    }

    //fun register() {
    //    ClientRegistry.bindTileEntityRenderer(RMTileEntityTypes.RM_BASIC_MAGIC_SOURCE.get(), Function<TileEntityRendererDispatcher, TileEntityRenderer<in T?>> { RMBasicMagicSourceBlock() })
    //}

}