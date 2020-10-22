/*package ru.rikgela.russianmagic.util.helpers;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ru.rikgela.russianmagic.RMEntities;
import ru.rikgela.russianmagic.client.entity.render.ProjectileEntityRender;

import static ru.rikgela.russianmagic.RussianMagicKt.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(RMEntities.PROJECTILE_ENTITY.get(), ProjectileEntityRender::new);
    }
}
*/