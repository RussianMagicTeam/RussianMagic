package ru.rikgela.russinamagic

import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Supplier


class ModItemGroup(name: String?, iconSupplier: Supplier<ItemStack?>?) : ItemGroup(name) {
    private val iconSupplier: Supplier<ItemStack?>? = iconSupplier
    override fun createIcon(): ItemStack? {
        if (iconSupplier != null) {
            return iconSupplier.get()
        }
        return null
    }
}

object ModItemGroups {
    val MOD_ITEMS_ITEM_GROUP: ItemGroup = ModItemGroup("tutorial_mod_gs_items", Supplier { ItemStack(ModItems.STONE_STICK.get()) })
}



object ModItems {
    val ITEMS: DeferredRegister<Item> = DeferredRegister(ForgeRegistries.ITEMS, "russianmagic")
    val STONE_STICK: RegistryObject<Item> = ITEMS.register<Item>("stone_magic_object") { Item(Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val SAUSAGE = ITEMS.register("sausage") {
        Item(Item.Properties()
                .food(ModFoods.SAUSAGE)
                .group(ModItemGroups.MOD_ITEMS_ITEM_GROUP))
    }

    val ENCHANTED_SAUSAGE = ITEMS.register<Item>("enchanted_sausage") {
        object : Item(Properties().food(ModFoods.ENCHANTED_SAUSAGE).group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) {
            override fun hasEffect(stack: ItemStack): Boolean {
                return true
            }
        }
    }
}

object ModFoods {
    var SAUSAGE = Food.Builder().hunger(5).saturation(0.5f).meat().build()
    var ENCHANTED_SAUSAGE = Food.Builder().hunger(8).saturation(0.7f).meat().setAlwaysEdible().fastToEat()
            .effect({ EffectInstance(Effects.HEALTH_BOOST, 600, 0) }, 1f)
            .effect({ EffectInstance(Effects.INSTANT_HEALTH, 600, 0) }, 1f).build()
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