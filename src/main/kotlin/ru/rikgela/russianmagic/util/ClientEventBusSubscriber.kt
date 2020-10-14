package ru.rikgela.russianmagic.util

import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.client.registry.IRenderFactory
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.RMEntities
import ru.rikgela.russianmagic.client.entity.render.ProjectileEntityRender

@Mod(MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class ClientEventBusSubscriber {
    init {}
    @SubscribeEvent
    fun clientSetup(event: FMLClientSetupEvent?) {
        RenderingRegistry.registerEntityRenderingHandler(RMEntities.PROJECTILE_ENTITY.get()) { renderManagerIn: EntityRendererManager -> ProjectileEntityRender(renderManagerIn) }
    }
}
