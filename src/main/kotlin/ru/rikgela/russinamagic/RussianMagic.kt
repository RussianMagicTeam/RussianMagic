package ru.rikgela.russinamagic

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries


object ModItems {
    val ITEMS: DeferredRegister<Item> = DeferredRegister(ForgeRegistries.ITEMS, "russianmagic")
    val STONE_STICK: RegistryObject<Item> = ITEMS.register<Item>("stone_magic_object") { Item(Item.Properties().group(ItemGroup.TOOLS)) }
}
// The value here should match an entry in the META-INF/mods.toml file
@Mod("russianmagic")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class RussianMagic {
    init{
        val bus = FMLJavaModLoadingContext.get().modEventBus
        ModItems.ITEMS.register(bus)
    }
}