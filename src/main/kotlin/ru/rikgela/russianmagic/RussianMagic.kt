package ru.rikgela.russianmagic

import net.minecraft.client.gui.ScreenManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import ru.rikgela.russianmagic.client.gui.RMFurnaceScreen
import ru.rikgela.russianmagic.init.*
import ru.rikgela.russianmagic.mana.EventHandler
import ru.rikgela.russianmagic.mana.ManaCapabilityHandler


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

    @SubscribeEvent
    fun clientSetup(event: FMLClientSetupEvent?) {
        ScreenManager.registerFactory(RMContainerTypes.RM_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn -> RMFurnaceScreen(screenContainer, inv, titleIn) }
    }

    //private fun clientSetup(event: FMLClientSetupEvent) {
//        ScreenManager.registerFactory(RMContainerTypes.RM_FURNACE_CONTAINER.get(), ScreenManager.IScreenFactory<RMFurnaceContainer, RMFurnaceScreen> { screenContainer, inv, titleIn -> RMFurnaceScreen(screenContainer, inv, titleIn) })
    //}

    private fun setup(event: FMLCommonSetupEvent) {
        //preinit
//        CapabilityManager.INSTANCE.register(IMana::class.java, ManaStorage()) { Mana() }
    }
}
