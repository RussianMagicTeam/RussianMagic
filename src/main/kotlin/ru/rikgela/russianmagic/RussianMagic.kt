package ru.rikgela.russianmagic

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

const val MOD_ID = "russianmagic"

@Mod(MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class RussianMagic {
    init {
        val bus = FMLJavaModLoadingContext.get().modEventBus
        Items.ITEMS.register(bus)
        Blocks.BLOCKS.register(bus)
        MinecraftForge.EVENT_BUS.register(MyForgeEventHandler())
    }
}