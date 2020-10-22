/*package ru.rikgela.russianmagic.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity;

import static ru.rikgela.russianmagic.RussianMagicKt.MOD_ID;

@OnlyIn(Dist.CLIENT)
public class ProjectileEntityRender2 extends EntityRenderer<ProjectileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/projectile_entity.png");

    public ProjectileEntityRender2(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn);
    }
    public ResourceLocation getEntityTexture(ProjectileEntity entity)
    {
        return TEXTURE;
    }
}

/*package ru.rikgela.russianmagic.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.matrix.MatrixStack.Entry;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix3f;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity;

@OnlyIn(Dist.CLIENT)
public class ProjectileEntityRender extends EntityRenderer<ProjectileEntity> {
    private static final ResourceLocation DRAGON_FIREBALL_TEXTURE = new ResourceLocation("textures/entity/enderdragon/dragon_fireball.png");
    private static final RenderType field_229044_e_;

    public ProjectileEntityRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    protected int getBlockLight(DragonFireballEntity entityIn, float partialTicks) {
        return 15;
    }

    public void render(DragonFireballEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.scale(2.0F, 2.0F, 2.0F);
        matrixStackIn.rotate(this.renderManager.getCameraOrientation());
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
        Entry matrixstack$entry = matrixStackIn.getLast();
        Matrix4f matrix4f = matrixstack$entry.getMatrix();
        Matrix3f matrix3f = matrixstack$entry.getNormal();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(field_229044_e_);
        func_229045_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
        func_229045_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
        func_229045_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
        func_229045_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private static void func_229045_a_(IVertexBuilder p_229045_0_, Matrix4f p_229045_1_, Matrix3f p_229045_2_, int p_229045_3_, float p_229045_4_, int p_229045_5_, int p_229045_6_, int p_229045_7_) {
        p_229045_0_.pos(p_229045_1_, p_229045_4_ - 0.5F, (float)p_229045_5_ - 0.25F, 0.0F).color(255, 255, 255, 255).tex((float)p_229045_6_, (float)p_229045_7_).overlay(OverlayTexture.NO_OVERLAY).lightmap(p_229045_3_).normal(p_229045_2_, 0.0F, 1.0F, 0.0F).endVertex();
    }

    public ResourceLocation getEntityTexture(DragonFireballEntity entity) {
        return DRAGON_FIREBALL_TEXTURE;
    }

    static {
        field_229044_e_ = RenderType.getEntityCutoutNoCull(DRAGON_FIREBALL_TEXTURE);
    }
}
*/


public class RenderBlank extends EntityRenderer {
    private final ResourceLocation entityTexture; // new ResourceLocation(ExampleMod.MODID, "textures/entity/spell_proj.png");

    protected RenderBlank(EntityRendererManager renderManager, ResourceLocation entityTexture) {
        super(renderManager);
        this.entityTexture = entityTexture;
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return entityTexture;
    }
}