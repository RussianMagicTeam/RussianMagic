package ru.rikgela.russianmagic

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScreenManager
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.inventory.container.ContainerType
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import ru.rikgela.russianmagic.client.gui.RMFurnaceScreen
import ru.rikgela.russianmagic.container.RMFurnaceContainer
import ru.rikgela.russianmagic.init.*
import ru.rikgela.russianmagic.mana.*


const val MOD_ID = "russianmagic"

@Mod(MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class RussianMagic {
    init {
        val bus = FMLJavaModLoadingContext.get().modEventBus

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().modEventBus.addListener {
            event: FMLCommonSetupEvent? -> setup(event!!)
        }
        Items.ITEMS.register(bus)
        BlocksInit.BLOCKS.register(bus)
        RecipeSerializerInit.RECIPE_SERIALIZERS.register(bus)
        RMTileEntityTypes.TILE_ENTITY_TYPES.register(bus)
        RMContainerTypes.CONTAINER_TYPES.register(bus)
        MinecraftForge.EVENT_BUS.register(MyForgeEventHandler())
        MinecraftForge.EVENT_BUS.register(ManaCapabilityHandler())
        MinecraftForge.EVENT_BUS.register(EventHandler())
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLCommonSetupEvent ->
            setup(event)
        }
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLClientSetupEvent ->
            clientSetup(event)
        }

    }

    private fun clientSetup(event: FMLClientSetupEvent) {
    //RenderingRegistry.registerEntityRenderingHandler(RMEntities.PROJECTILE_ENTITY.get()) { renderManagerIn: EntityRendererManager -> ProjectileEntityRender(renderManagerIn, ResourceLocation(MOD_ID, "textures/entity/projectile_entity.png")) }
    }

    private fun setup(event: FMLCommonSetupEvent) {
    //preinit
        ScreenManager.registerFactory(RMContainerTypes.RM_FURNACE_CONTAINER.get(), ScreenManager.IScreenFactory<RMFurnaceContainer, RMFurnaceScreen> { screenContainer, inv, titleIn -> RMFurnaceScreen(screenContainer, inv, titleIn) })
        //CapabilityManager.INSTANCE.register(IMana::class.java, ManaStorage()) { Mana() }
        @Suppress("INACCESSIBLE_TYPE")
        RMNetworkChannel.registerMessage(
            networkIndex++,
            ManaMessage::class.java,
            ManaMessage::encoder,
            ManaMessage.Companion::fromPacketBuffer,
            ManaMessage::handle)
    }
}