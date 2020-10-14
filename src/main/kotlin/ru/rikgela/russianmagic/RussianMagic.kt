package ru.rikgela.russianmagic

import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import ru.rikgela.russianmagic.client.HUDEventHandler
import ru.rikgela.russianmagic.client.entity.render.ProjectileEntityRender
import ru.rikgela.russianmagic.common.RMNetworkChannel
import ru.rikgela.russianmagic.common.RMNetworkMessage
import ru.rikgela.russianmagic.mana.*
import ru.rikgela.russianmagic.oregenerator.OreGeneration


const val MOD_ID = "russianmagic"
var networkIndex = 0

@Mod(MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class RussianMagic {
    init {
        val bus = FMLJavaModLoadingContext.get().modEventBus

        // Register the setup method for modloading

        Blocks.BLOCKS.register(bus)
        RMEntities.ENTITIES.register(bus)
        Items.ITEMS.register(bus)
        MinecraftForge.EVENT_BUS.register(MyForgeEventHandler())
        MinecraftForge.EVENT_BUS.register(ManaCapabilityHandler())
        MinecraftForge.EVENT_BUS.register(ManaEventHandler())
        MinecraftForge.EVENT_BUS.register(HUDEventHandler())
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLCommonSetupEvent ->
            setup(event)
        }
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLClientSetupEvent ->
            clientSetup(event)
        }
//        MinecraftForge.EVENT_BUS.register(ClientEventBusSubscriber())

    }

    private fun clientSetup(event: FMLClientSetupEvent) {
        RenderingRegistry.registerEntityRenderingHandler(RMEntities.PROJECTILE_ENTITY.get()) { renderManagerIn: EntityRendererManager -> ProjectileEntityRender(renderManagerIn) }
    }

    private fun setup(event: FMLCommonSetupEvent) {
        //preinit

        OreGeneration.setupOreGeneration()
        CapabilityManager.INSTANCE.register(IMana::class.java, ManaStorage()) { Mana() }
        @Suppress("INACCESSIBLE_TYPE")
        RMNetworkChannel.registerMessage(
                networkIndex++,
                RMNetworkMessage::class.java,
                RMNetworkMessage::encoder,
                RMNetworkMessage.Companion::fromPacketBuffer,
                RMNetworkMessage::handle)
        @Suppress("INACCESSIBLE_TYPE")
        RMNetworkChannel.registerMessage(
                networkIndex++,
                ManaMessage::class.java,
                ManaMessage::encoder,
                ManaMessage.Companion::fromPacketBuffer,
                ManaMessage::handle)
    }
}