package ru.rikgela.russianmagic

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import ru.rikgela.russianmagic.init.*
import ru.rikgela.russianmagic.network.RMMessages
import ru.rikgela.russianmagic.objects.blockentities.renderer.AbstractRMFurnaceBlockEntityRenderer


const val MOD_ID = "russianmagic"
var networkIndex = 0


//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(MOD_ID)
class RussianMagic {
    init {
        val bus = FMLJavaModLoadingContext.get().modEventBus
        // Register the setup method for modLoading
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLCommonSetupEvent ->
            commonSetup(event)
        }
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLClientSetupEvent ->
            clientSetup(event)
        }
        FMLJavaModLoadingContext.get().modEventBus.addListener {event: EntityRenderersEvent.RegisterRenderers ->
            registerRenderers(event)
        }
//        FMLJavaModLoadingContext.get().modEventBus.addListener { event: ParticleFactoryRegisterEvent ->
//            particleSetup(event)
//        }

        RMItems.ITEMS.register(bus)
        RMBlocks.BLOCKS.register(bus)
        RMBlockEntityTypes.BLOCK_ENTITY_TYPES.register(bus)
        RMMenuTypes.MENU_TYPES.register(bus)
        RMConfiguredFeatures.CONFIGURED_FEATURES.register(bus)
        RMPlacedFeatures.PLACED_FEATURES.register(bus)
//        bus.addListener(this::commonSetup)
//        RMEntities.ENTITIES.register(bus)
//        RMParticles.PARTICLES.register(bus)
//        MinecraftForge.EVENT_BUS.register(PlayerManaCapabilityHandler())
//        MinecraftForge.EVENT_BUS.register(PlayerManaEventHandler())
//        MinecraftForge.EVENT_BUS.register(MagicHealthCapabilityHandler())
//        MinecraftForge.EVENT_BUS.register(MagicHealthEventHandler())
//        MinecraftForge.EVENT_BUS.register(RebornCapabilityHandler())
//        MinecraftForge.EVENT_BUS.register(RebornEventHandler())
//        MinecraftForge.EVENT_BUS.register(MeditationRebornCapabilityHandler())
//        MinecraftForge.EVENT_BUS.register(MeditationRebornEventHandler())

    }


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    fun clientSetup(event: FMLClientSetupEvent?) {
        RMBlocks.clientSetup()
        RMScreens.setupScreens()
    }

    @OnlyIn(Dist.CLIENT)
    fun registerRenderers(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(
            RMBlockEntityTypes.RM_DIAMOND_FURNACE.get(),
            ::AbstractRMFurnaceBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            RMBlockEntityTypes.RM_ISOLATED_DIAMOND_FURNACE.get(),
            ::AbstractRMFurnaceBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            RMBlockEntityTypes.RM_EBONY_FURNACE.get(),
            ::AbstractRMFurnaceBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            RMBlockEntityTypes.RM_MARBLE_FURNACE.get(),
            ::AbstractRMFurnaceBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            RMBlockEntityTypes.RM_WHITE_JADE_FURNACE.get(),
            ::AbstractRMFurnaceBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            RMBlockEntityTypes.RM_AQUAMARINE_FURNACE.get(),
            ::AbstractRMFurnaceBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            RMBlockEntityTypes.RM_RHINESTONE_FURNACE.get(),
            ::AbstractRMFurnaceBlockEntityRenderer
        )
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        event.enqueueWork {
            RMMessages.register()
        }
//        CapabilityManager.INSTANCE.register(IMana::class.java, ManaStorage()) { Mana() }
    }

//    @SubscribeEvent
//    @OnlyIn(Dist.CLIENT)
//    fun particleSetup(event: ParticleFactoryRegisterEvent) {
//        Minecraft.getInstance().particles.registerFactory(RMParticles.MANA_PARTICLE.get()
//        ) { iAnimatedSprite: IAnimatedSprite ->
//            ManaParticle.Companion.Factory(iAnimatedSprite)
//        }
//    }

//    private fun setup(event: FMLCommonSetupEvent) {
//        //preInit
//        OreGeneration.setupOreGeneration()
//        CapabilityManager.INSTANCE.register(IMana::class.java, ManaStorage()) { Mana() }
//        CapabilityManager.INSTANCE.register(IMagicHealth::class.java, MagicHealthStorage()) { MagicHealth() }
//        CapabilityManager.INSTANCE.register(IPlayerMana::class.java, PlayerManaStorage()) { PlayerMana() }
//        CapabilityManager.INSTANCE.register(IReborn::class.java, RebornStorage()) { Reborn() }
//        CapabilityManager.INSTANCE.register(IMeditationReborn::class.java, MeditationRebornStorage()) { MeditationReborn() }
//
//        @Suppress("INACCESSIBLE_TYPE")
//        RMNetworkChannel.registerMessage(
//                networkIndex++,
//                RMNetworkMessage::class.java,
//                RMNetworkMessage::encoder,
//                RMNetworkMessage.Companion::fromPacketBuffer,
//                RMNetworkMessage::handle)
//        @Suppress("INACCESSIBLE_TYPE")
//        RMNetworkChannel.registerMessage(
//                networkIndex++,
//                ManaMessage::class.java,
//                ManaMessage::encoder,
//                ManaMessage.Companion::fromPacketBuffer,
//                ManaMessage::handle)
//        @Suppress("INACCESSIBLE_TYPE")
//        RMNetworkChannel.registerMessage(
//                networkIndex++,
//                RMCCMessage::class.java,
//                RMCCMessage::encoder,
//                RMCCMessage.Companion::fromPacketBuffer,
//                RMCCMessage::handle)
//        @Suppress("INACCESSIBLE_TYPE")
//        RMNetworkChannel.registerMessage(
//            networkIndex++,
//            MagicHealthNetwork::class.java,
//            MagicHealthNetwork::encoder,
//            MagicHealthNetwork.Companion::fromPacketBuffer,
//            MagicHealthNetwork::handle)
//        @Suppress("INACCESSIBLE_TYPE")
//        RMNetworkChannel.registerMessage(
//            networkIndex++,
//            PlayerManaNetwork::class.java,
//            PlayerManaNetwork::encoder,
//            PlayerManaNetwork.Companion::fromPacketBuffer,
//            PlayerManaNetwork::handle)
//        @Suppress("INACCESSIBLE_TYPE")
//        RMNetworkChannel.registerMessage(
//            networkIndex++,
//            RebornNetwork::class.java,
//            RebornNetwork::encoder,
//            RebornNetwork.Companion::fromPacketBuffer,
//            RebornNetwork::handle)
//        @Suppress("INACCESSIBLE_TYPE")
//        RMNetworkChannel.registerMessage(
//            networkIndex++,
//            MeditationRebornNetwork::class.java,
//            MeditationRebornNetwork::encoder,
//            MeditationRebornNetwork.Companion::fromPacketBuffer,
//            MeditationRebornNetwork::handle)
//    }
}