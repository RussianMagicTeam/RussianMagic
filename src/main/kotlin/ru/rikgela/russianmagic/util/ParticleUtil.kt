/*package ru.rikgela.russianmagic.util

import net.minecraft.client.Minecraft
import net.minecraft.client.particle.IAnimatedSprite
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.SubscribeEvent
import ru.rikgela.russianmagic.client.particle.ManaParticle
import ru.rikgela.russianmagic.init.RMParticles

object ParticleUtil {
    /*
     * this is just a like any other RegistryEvent, however, we are binding the particle to the Particle Factory.
     * This also is similar to binding TileEntityRenderers to TileEntites.
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    fun registerParticles(event: ParticleFactoryRegisterEvent?) {
        Minecraft.getInstance().particles.registerFactory(RMParticles.MANA_PARTICLE.get()) { spriteSet: IAnimatedSprite? -> ManaParticle.Factory(spriteSet!!) }
    }
}*/