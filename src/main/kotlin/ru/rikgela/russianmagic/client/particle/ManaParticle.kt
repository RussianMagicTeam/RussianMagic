import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.particle.IParticleRenderType
import net.minecraft.client.particle.Particle
import net.minecraft.client.renderer.ActiveRenderInfo
import net.minecraft.world.World
import ru.rikgela.russianmagic.client.particle.ManaParticleRenderType


class ManaParticle constructor(worldIn: World?, posXIn: Double, posYIn: Double, posZIn: Double) : Particle(worldIn, posXIn, posYIn, posZIn) {
    override fun renderParticle(buffer: IVertexBuilder, renderInfo: ActiveRenderInfo?, partialTicks: Float) {
        buffer.pos(0.0, 0.0, 0.0)
                .color(1, 0, 0, 1)
                .endVertex()
        buffer.pos(1.0, 0.0, 0.0)
                .color(1, 0, 0, 1)
                .endVertex()
        buffer.pos(1.0, 1.0, 0.0)
                .color(1, 0, 0, 1)
                .endVertex()
        buffer.pos(1.0, 1.0, 1.0)
                .color(1, 0, 0, 1)
                .endVertex()
    }

    override fun getRenderType(): IParticleRenderType? {
        return ManaParticleRenderType.INSTANCE
    }
}