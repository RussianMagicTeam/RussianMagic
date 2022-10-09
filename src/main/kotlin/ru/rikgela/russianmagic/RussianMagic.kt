package ru.rikgela.russianmagic

import IMeditationReborn
import MagicHealthEventHandler
import MeditationReborn
import MeditationRebornEventHandler
import MeditationRebornNetwork
import MeditationRebornStorage
import Reborn
import net.minecraft.client.Minecraft
import net.minecraft.client.particle.IAnimatedSprite
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import ru.rikgela.russianmagic.client.particle.ColoredParticleType
import ru.rikgela.russianmagic.client.particle.ManaParticle
import ru.rikgela.russianmagic.common.RMCCMessage
import ru.rikgela.russianmagic.common.RMNetworkChannel
import ru.rikgela.russianmagic.common.RMNetworkMessage
import ru.rikgela.russianmagic.init.*
import ru.rikgela.russianmagic.objects.mana.*
import ru.rikgela.russianmagic.objects.player.IMagicHealth
import ru.rikgela.russianmagic.objects.player.MagicHealth
import ru.rikgela.russianmagic.objects.player.magichealth.MagicHealthCapabilityHandler
import ru.rikgela.russianmagic.objects.player.magichealth.MagicHealthNetwork
import ru.rikgela.russianmagic.objects.player.magichealth.MagicHealthStorage
import ru.rikgela.russianmagic.objects.player.mana.*
import ru.rikgela.russianmagic.objects.player.meditation.reborn.MeditationRebornCapabilityHandler
import ru.rikgela.russianmagic.objects.player.reborn.*
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
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: ParticleFactoryRegisterEvent ->
            particleSetup(event)
        }

        RMItems.ITEMS.register(bus)
        RMBlocks.BLOCKS.register(bus)
        RMEntities.ENTITIES.register(bus)
        RMTileEntityTypes.TILE_ENTITY_TYPES.register(bus)
        RMContainerTypes.CONTAINER_TYPES.register(bus)
        RMParticles.PARTICLES.register(bus)
        MinecraftForge.EVENT_BUS.register(PlayerManaCapabilityHandler())
        MinecraftForge.EVENT_BUS.register(PlayerManaEventHandler())
        MinecraftForge.EVENT_BUS.register(MagicHealthCapabilityHandler())
        MinecraftForge.EVENT_BUS.register(MagicHealthEventHandler())
        MinecraftForge.EVENT_BUS.register(RebornCapabilityHandler())
        MinecraftForge.EVENT_BUS.register(RebornEventHandler())
        MinecraftForge.EVENT_BUS.register(MeditationRebornCapabilityHandler())
        MinecraftForge.EVENT_BUS.register(MeditationRebornEventHandler())

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    fun clientSetup(event: FMLClientSetupEvent?) {
        RMBlocks.clientSetup()
        RMRenderInit.setupScreens()
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    fun particleSetup(event: ParticleFactoryRegisterEvent) {
        Minecraft.getInstance().particles.registerFactory<ColoredParticleType>(RMParticles.MANA_PARTICLE.get()
        ) { iAnimatedSprite: IAnimatedSprite ->
            ManaParticle.Companion.Factory(iAnimatedSprite)
        }
    }

    private fun setup(event: FMLCommonSetupEvent) {
        //preinit
        OreGeneration.setupOreGeneration()
        CapabilityManager.INSTANCE.register(IMana::class.java, ManaStorage()) { Mana() }
        CapabilityManager.INSTANCE.register(IMagicHealth::class.java, MagicHealthStorage()) { MagicHealth() }
        CapabilityManager.INSTANCE.register(IPlayerMana::class.java, PlayerManaStorage()) { PlayerMana() }
        CapabilityManager.INSTANCE.register(IReborn::class.java, RebornStorage()) { Reborn() }
        CapabilityManager.INSTANCE.register(IMeditationReborn::class.java, MeditationRebornStorage()) { MeditationReborn() }

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
        @Suppress("INACCESSIBLE_TYPE")
        RMNetworkChannel.registerMessage(
            networkIndex++,
            MagicHealthNetwork::class.java,
            MagicHealthNetwork::encoder,
            MagicHealthNetwork.Companion::fromPacketBuffer,
            MagicHealthNetwork::handle)
        @Suppress("INACCESSIBLE_TYPE")
        RMNetworkChannel.registerMessage(
            networkIndex++,
            PlayerManaNetwork::class.java,
            PlayerManaNetwork::encoder,
            PlayerManaNetwork.Companion::fromPacketBuffer,
            PlayerManaNetwork::handle)
        @Suppress("INACCESSIBLE_TYPE")
        RMNetworkChannel.registerMessage(
            networkIndex++,
            RebornNetwork::class.java,
            RebornNetwork::encoder,
            RebornNetwork.Companion::fromPacketBuffer,
            RebornNetwork::handle)
        @Suppress("INACCESSIBLE_TYPE")
        RMNetworkChannel.registerMessage(
            networkIndex++,
            MeditationRebornNetwork::class.java,
            MeditationRebornNetwork::encoder,
            MeditationRebornNetwork.Companion::fromPacketBuffer,
            MeditationRebornNetwork::handle)
    }
}