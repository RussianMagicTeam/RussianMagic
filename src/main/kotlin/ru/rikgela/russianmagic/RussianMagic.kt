package ru.rikgela.russianmagic

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import ru.rikgela.russianmagic.init.BlocksInit
import ru.rikgela.russianmagic.init.Items
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
        MinecraftForge.EVENT_BUS.register(MyForgeEventHandler())
        MinecraftForge.EVENT_BUS.register(ManaCapabilityHandler())
        MinecraftForge.EVENT_BUS.register(EventHandler())
    }

    private fun setup(event: FMLCommonSetupEvent) {
        //preinit
        CapabilityManager.INSTANCE.register(IMana::class.java, ManaStorage()) { Mana() }
    }




}