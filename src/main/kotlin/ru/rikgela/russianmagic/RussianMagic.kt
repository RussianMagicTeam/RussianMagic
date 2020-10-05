package ru.rikgela.russianmagic

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod("russianmagic")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class RussianMagic {
    init {
        val bus = FMLJavaModLoadingContext.get().modEventBus
        ModItems.ITEMS.register(bus)
    }
}