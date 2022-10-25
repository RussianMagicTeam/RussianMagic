package ru.rikgela.russianmagic

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import ru.rikgela.russianmagic.init.RMBlocks
import ru.rikgela.russianmagic.init.RMConfiguredFeatures
import ru.rikgela.russianmagic.init.RMItems
import ru.rikgela.russianmagic.init.RMPlacedFeatures


const val MOD_ID = "russianmagic"
var networkIndex = 0


//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(MOD_ID)
class RussianMagic {
    init {
        val bus = FMLJavaModLoadingContext.get().modEventBus
        // Register the setup method for modLoading
//        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLCommonSetupEvent ->
//            setup(event)
//        }
//        FMLJavaModLoadingContext.get().modEventBus.addListener { event: FMLClientSetupEvent ->
//            clientSetup(event)
//        }
//        FMLJavaModLoadingContext.get().modEventBus.addListener { event: ParticleFactoryRegisterEvent ->
//            particleSetup(event)
//        }

        RMItems.ITEMS.register(bus)
        RMBlocks.BLOCKS.register(bus)
        RMConfiguredFeatures.CONFIGURED_FEATURES.register(bus)
        RMPlacedFeatures.PLACED_FEATURES.register(bus)
//        RMEntities.ENTITIES.register(bus)
//        RMTileEntityTypes.TILE_ENTITY_TYPES.register(bus)
//        RMContainerTypes.CONTAINER_TYPES.register(bus)
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

//    @SubscribeEvent
//    @OnlyIn(Dist.CLIENT)
//    fun clientSetup(event: FMLClientSetupEvent?) {
//        RMBlocks.clientSetup()
//        RMRenderInit.setupScreens()
//    }

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