package ru.rikgela.russianmagic

import net.minecraft.client.gui.ScreenManager
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import ru.rikgela.russianmagic.client.HUDEventHandler
import ru.rikgela.russianmagic.client.entity.render.ProjectileEntityRender
import ru.rikgela.russianmagic.client.gui.RMFurnaceScreen
import ru.rikgela.russianmagic.common.RMCCMessage
import ru.rikgela.russianmagic.common.RMNetworkChannel
import ru.rikgela.russianmagic.common.RMNetworkMessage
import ru.rikgela.russianmagic.init.RMBlocks
import ru.rikgela.russianmagic.init.RMContainerTypes
import ru.rikgela.russianmagic.init.RMItems
import ru.rikgela.russianmagic.init.RMTileEntityTypes
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
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLCommonSetupEvent ->
            setup(event)
        }
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLClientSetupEvent ->
            clientSetup(event)
        }

        RMItems.ITEMS.register(bus)
        RMBlocks.BLOCKS.register(bus)
        RMEntities.ENTITIES.register(bus)
        RMTileEntityTypes.TILE_ENTITY_TYPES.register(bus)
        RMContainerTypes.CONTAINER_TYPES.register(bus)
        MinecraftForge.EVENT_BUS.register(MyForgeEventHandler())
        MinecraftForge.EVENT_BUS.register(ManaCapabilityHandler())
        MinecraftForge.EVENT_BUS.register(ManaEventHandler())

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    fun clientSetup(event: FMLClientSetupEvent?) {
        MinecraftForge.EVENT_BUS.register(HUDEventHandler())
        ScreenManager.registerFactory(RMContainerTypes.RM_DIAMOND_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace1_screen.png"))
        }
        ScreenManager.registerFactory(RMContainerTypes.RM_ISOLATED_DIAMOND_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace2_screen.png"))
        }
        ScreenManager.registerFactory(RMContainerTypes.RM_EBONY_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace3_screen.png"))
        }
        ScreenManager.registerFactory(RMContainerTypes.RM_MARBLE_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace3_screen.png"))
        }
        ScreenManager.registerFactory(RMContainerTypes.RM_WHITE_JADE_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace3_screen.png"))
        }
        ScreenManager.registerFactory(RMContainerTypes.RM_RHINESTONE_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace3_screen.png"))
        }
        ScreenManager.registerFactory(RMContainerTypes.RM_AQUAMARINE_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace3_screen.png"))
        }
        //ScreenManager.registerFactory(RMContainerTypes.RM_MARBLE_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn -> RMMarbleFurnaceScreen(screenContainer, inv, titleIn) }
        RMBlocks.clientSetup()
        RenderingRegistry.registerEntityRenderingHandler(RMEntities.PROJECTILE_ENTITY.get()) { renderManagerIn: EntityRendererManager -> ProjectileEntityRender(renderManagerIn, ResourceLocation(MOD_ID, "textures/entity/projectile_entity.png")) }
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
        @Suppress("INACCESSIBLE_TYPE")
        RMNetworkChannel.registerMessage(
                networkIndex++,
                RMCCMessage::class.java,
                RMCCMessage::encoder,
                RMCCMessage.Companion::fromPacketBuffer,
                RMCCMessage::handle)
    }
}