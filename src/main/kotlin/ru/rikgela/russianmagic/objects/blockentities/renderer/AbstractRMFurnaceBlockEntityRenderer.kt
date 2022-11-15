package ru.rikgela.russianmagic.objects.blockentities.renderer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Vector3f
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.block.model.ItemTransforms
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.LightLayer
import ru.rikgela.russianmagic.objects.blockentities.AbstractRMFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.blocks.AbstractRMFurnace


class AbstractRMFurnaceBlockEntityRenderer(context: BlockEntityRendererProvider.Context?) :
    BlockEntityRenderer<AbstractRMFurnaceBlockEntity> {
    override fun render(
        blockEntity: AbstractRMFurnaceBlockEntity, partialTick: Float, poseStack: PoseStack,
        bufferSource: MultiBufferSource, packedLight: Int, packedOverlay: Int
    ) {
        val itemRenderer: ItemRenderer = Minecraft.getInstance().itemRenderer
        val itemStack: ItemStack = blockEntity.getRenderStack()
        poseStack.pushPose()
        poseStack.translate(0.5, 0.65, 0.5)
        poseStack.scale(0.25f, 0.25f, 0.25f)
        poseStack.mulPose(Vector3f.XP.rotationDegrees(90f))
        when (blockEntity.blockState.getValue(AbstractRMFurnace.FACING)) {
            Direction.NORTH -> poseStack.mulPose(Vector3f.ZP.rotationDegrees(0f))
            Direction.EAST -> poseStack.mulPose(Vector3f.ZP.rotationDegrees(90f))
            Direction.SOUTH -> poseStack.mulPose(Vector3f.ZP.rotationDegrees(180f))
            Direction.WEST -> poseStack.mulPose(Vector3f.ZP.rotationDegrees(270f))
            else -> poseStack.mulPose(Vector3f.ZP.rotationDegrees(0f))
        }
//        when (pBlockEntity.getBlockState().getValue(AbstractRMFurnace.FACING)) {
//            NORTH -> pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(0f))
//            EAST -> pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90f))
//            SOUTH -> pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180f))
//            WEST -> pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(270f))
//        }
        itemRenderer.renderStatic(
            itemStack, ItemTransforms.TransformType.GUI, getLightLevel(
                blockEntity.level!!,
                blockEntity.blockPos
            ),
            OverlayTexture.NO_OVERLAY, poseStack, bufferSource, 1
        )
        poseStack.popPose()
    }

    private fun getLightLevel(level: Level, pos: BlockPos): Int {
        val bLight: Int = level.getBrightness(LightLayer.BLOCK, pos)
        val sLight: Int = level.getBrightness(LightLayer.SKY, pos)
        return LightTexture.pack(bLight, sLight)
    }
}