package ru.rikgela.russianmagic

import net.minecraft.item.*
import net.minecraft.item.crafting.Ingredient
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.util.LazyValue
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Supplier


class ModItemGroup(name: String, private val iconSupplier: Supplier<ItemStack?>?) : ItemGroup(name) {
    override fun createIcon(): ItemStack? {
        if (iconSupplier != null) {
            return iconSupplier.get()
        }
        return null
    }
}

object ModItemGroups {
    val MOD_ITEMS_ITEM_GROUP: ItemGroup = ModItemGroup("russian_magic_items", Supplier { ItemStack(ModItems.STONE_STICK.get()) })
}


object ModItems {
    val ITEMS: DeferredRegister<Item> = DeferredRegister(ForgeRegistries.ITEMS, "russianmagic")
    val STONE_STICK: RegistryObject<Item> = ITEMS.register<Item>("stone_magic_object") {
        Item(Item.Properties()
                .group(ModItemGroups.MOD_ITEMS_ITEM_GROUP))
    }
    val SAUSAGE: RegistryObject<Item> = ITEMS.register("sausage") {
        Item(Item.Properties()
                .food(ModFoods.SAUSAGE)
                .group(ModItemGroups.MOD_ITEMS_ITEM_GROUP))
    }

    val ENCHANTED_SAUSAGE: RegistryObject<Item> = ITEMS.register<Item>("enchanted_sausage") {
        object : Item(Properties()
                .food(ModFoods.ENCHANTED_SAUSAGE).group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) {
            override fun hasEffect(stack: ItemStack): Boolean {
                return true
            }
        }
    }

    val EBONY_PLANKS: RegistryObject<Item> = ITEMS.register("ebony_planks") { Item(Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val MARBLE: RegistryObject<Item> = ITEMS.register("marble") { Item(Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val WHITE_JADE: RegistryObject<Item> = ITEMS.register("white_jade") { Item(Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }

    val EBONY_PLANKS_AXE: RegistryObject<Item> = ITEMS.register("ebony_planks_axe") { AxeItem(ModItemTier.EBONY_PLANKS, 6.0f, -3.1f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val EBONY_PLANKS_SHOVEL: RegistryObject<Item> = ITEMS.register("ebony_planks_shovel") { ShovelItem(ModItemTier.EBONY_PLANKS, 1.5f, -3.0f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val EBONY_PLANKS_SWORD: RegistryObject<Item> = ITEMS.register("ebony_planks_sword") { SwordItem(ModItemTier.EBONY_PLANKS, 3, -2.4f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val EBONY_PLANKS_PICKAXE: RegistryObject<Item> = ITEMS.register("ebony_planks_pickaxe") { PickaxeItem(ModItemTier.EBONY_PLANKS, 1, -2.8f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val EBONY_PLANKS_HOE: RegistryObject<Item> = ITEMS.register("ebony_planks_hoe") { HoeItem(ModItemTier.EBONY_PLANKS, (-2).toFloat(), Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }

    val MARBLE_AXE: RegistryObject<Item> = ITEMS.register("marble_axe") { AxeItem(ModItemTier.MARBLE, 6.0f, -3.1f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val MARBLE_SHOVEL: RegistryObject<Item> = ITEMS.register("marble_shovel") { ShovelItem(ModItemTier.MARBLE, 1.5f, -3.0f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val MARBLE_SWORD: RegistryObject<Item> = ITEMS.register("marble_sword") { SwordItem(ModItemTier.MARBLE, 3, -2.4f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val MARBLE_PICKAXE: RegistryObject<Item> = ITEMS.register("marble_pickaxe") { PickaxeItem(ModItemTier.MARBLE, 1, -2.8f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val MARBLE_HOE: RegistryObject<Item> = ITEMS.register("marble_hoe") { HoeItem(ModItemTier.MARBLE, (-2).toFloat(), Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }

    val WHITE_JADE_AXE: RegistryObject<Item> = ITEMS.register("white_jade_axe") { AxeItem(ModItemTier.WHITE_JADE, 6.0f, -3.1f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val WHITE_JADE_SHOVEL: RegistryObject<Item> = ITEMS.register("white_jade_shovel") { ShovelItem(ModItemTier.WHITE_JADE, 1.5f, -3.0f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val WHITE_JADE_SWORD: RegistryObject<Item> = ITEMS.register("white_jade_sword") { SwordItem(ModItemTier.WHITE_JADE, 3, -2.4f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val WHITE_JADE_PICKAXE: RegistryObject<Item> = ITEMS.register("white_jade_pickaxe") { PickaxeItem(ModItemTier.WHITE_JADE, 1, -2.8f, Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }
    val WHITE_JADE_HOE: RegistryObject<Item> = ITEMS.register("white_jade_hoe") { HoeItem(ModItemTier.WHITE_JADE, (-2).toFloat(), Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) }

}

object ModFoods {
    var SAUSAGE: Food = Food.Builder().hunger(5).saturation(0.5f).meat().build()
    var ENCHANTED_SAUSAGE: Food = Food.Builder().hunger(8).saturation(0.7f).meat().setAlwaysEdible().fastToEat()
            .effect({ EffectInstance(Effects.HEALTH_BOOST, 600, 0) }, 1f)
            .effect({ EffectInstance(Effects.INSTANT_HEALTH, 600, 0) }, 1f).build()
}

enum class ModItemTier(
        private val harvestLevel: Int,
        private val maxUses: Int,
        private val efficiency: Float,
        private val attackDamage: Float,
        private val enchantability: Int,
        repairMaterialIn: Supplier<Ingredient>) : IItemTier {
    EBONY_PLANKS(2, 350, 6.5f, 2.3f, 12, Supplier<Ingredient> { Ingredient.fromItems(ModItems.EBONY_PLANKS.get()) }),
    MARBLE(3, 1061, 8.0f, 3.0f, 18, Supplier<Ingredient> { Ingredient.fromItems(ModItems.MARBLE.get()) }),
    WHITE_JADE(4, 1501, 8.0f, 3.0f, 25, Supplier<Ingredient> { Ingredient.fromItems(ModItems.WHITE_JADE.get()) });

    private val repairMaterial = LazyValue(repairMaterialIn)
    override fun getMaxUses(): Int {
        return maxUses
    }

    override fun getEfficiency(): Float {
        return efficiency
    }

    override fun getAttackDamage(): Float {
        return attackDamage
    }

    override fun getHarvestLevel(): Int {
        return harvestLevel
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getRepairMaterial(): Ingredient {
        return repairMaterial.value
    }

}

// The value here should match an entry in the META-INF/mods.toml file
@Mod("russianmagic")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
class RussianMagic {
    init {
        val bus = FMLJavaModLoadingContext.get().modEventBus
        ModItems.ITEMS.register(bus)
    }
}