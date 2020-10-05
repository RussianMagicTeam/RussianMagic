package ru.rikgela.russianmagic

import net.minecraft.item.*
import net.minecraft.item.crafting.Ingredient
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.util.LazyValue
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Supplier


enum class ItemTier(
        private val harvestLevel: Int,
        private val maxUses: Int,
        private val efficiency: Float,
        private val attackDamage: Float,
        private val enchantability: Int,
        repairMaterialIn: Supplier<Ingredient>) : IItemTier {
    EBONY_PLANKS(2, 350, 6.5f, 2.3f, 12, Supplier<Ingredient> { Ingredient.fromItems(Items.EBONY_PLANKS.get()) }),
    MARBLE(3, 1061, 8.0f, 3.0f, 18, Supplier<Ingredient> { Ingredient.fromItems(Items.MARBLE.get()) }),
    WHITE_JADE(4, 1501, 8.0f, 3.0f, 25, Supplier<Ingredient> { Ingredient.fromItems(Items.WHITE_JADE.get()) });

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

object Foods {
    var SAUSAGE: Food = Food.Builder().hunger(5).saturation(0.5f).meat().build()
    var ENCHANTED_SAUSAGE: Food = Food.Builder().hunger(8).saturation(0.7f).meat().setAlwaysEdible().fastToEat()
            .effect({ EffectInstance(Effects.HEALTH_BOOST, 600, 0) }, 1f)
            .effect({ EffectInstance(Effects.INSTANT_HEALTH, 600, 0) }, 1f).build()
}

object Items {
    val ITEMS: DeferredRegister<Item> = DeferredRegister(ForgeRegistries.ITEMS, MOD_ID)
    val STONE_MAGIC_OBJECT: RegistryObject<Item> = ITEMS.register<Item>("stone_magic_object") {
        Item(Item.Properties()
                .group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val SAUSAGE: RegistryObject<Item> = ITEMS.register("sausage") {
        Item(Item.Properties()
                .food(Foods.SAUSAGE)
                .group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val ENCHANTED_SAUSAGE: RegistryObject<Item> = ITEMS.register<Item>("enchanted_sausage") {
        object : Item(Properties()
                .food(Foods.ENCHANTED_SAUSAGE).group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) {
            override fun hasEffect(stack: ItemStack): Boolean {
                return true
            }
        }
    }

    val EBONY_PLANKS: RegistryObject<Item> = ITEMS.register("ebony_planks") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE: RegistryObject<Item> = ITEMS.register("marble") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE: RegistryObject<Item> = ITEMS.register("white_jade") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val EBONY_PLANKS_AXE: RegistryObject<Item> = ITEMS.register("ebony_planks_axe") { AxeItem(ItemTier.EBONY_PLANKS, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS_SHOVEL: RegistryObject<Item> = ITEMS.register("ebony_planks_shovel") { ShovelItem(ItemTier.EBONY_PLANKS, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS_SWORD: RegistryObject<Item> = ITEMS.register("ebony_planks_sword") { SwordItem(ItemTier.EBONY_PLANKS, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS_PICKAXE: RegistryObject<Item> = ITEMS.register("ebony_planks_pickaxe") { PickaxeItem(ItemTier.EBONY_PLANKS, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS_HOE: RegistryObject<Item> = ITEMS.register("ebony_planks_hoe") { HoeItem(ItemTier.EBONY_PLANKS, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val MARBLE_AXE: RegistryObject<Item> = ITEMS.register("marble_axe") { AxeItem(ItemTier.MARBLE, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_SHOVEL: RegistryObject<Item> = ITEMS.register("marble_shovel") { ShovelItem(ItemTier.MARBLE, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_SWORD: RegistryObject<Item> = ITEMS.register("marble_sword") { SwordItem(ItemTier.MARBLE, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_PICKAXE: RegistryObject<Item> = ITEMS.register("marble_pickaxe") { PickaxeItem(ItemTier.MARBLE, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_HOE: RegistryObject<Item> = ITEMS.register("marble_hoe") { HoeItem(ItemTier.MARBLE, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val WHITE_JADE_AXE: RegistryObject<Item> = ITEMS.register("white_jade_axe") { AxeItem(ItemTier.WHITE_JADE, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_SHOVEL: RegistryObject<Item> = ITEMS.register("white_jade_shovel") { ShovelItem(ItemTier.WHITE_JADE, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_SWORD: RegistryObject<Item> = ITEMS.register("white_jade_sword") { SwordItem(ItemTier.WHITE_JADE, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_PICKAXE: RegistryObject<Item> = ITEMS.register("white_jade_pickaxe") { PickaxeItem(ItemTier.WHITE_JADE, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_HOE: RegistryObject<Item> = ITEMS.register("white_jade_hoe") { HoeItem(ItemTier.WHITE_JADE, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    //Blocks
    val STONE_STICK_BLOCK: RegistryObject<Item?>? = ITEMS.register("stone_stick_block") { BlockItem(Blocks.STONE_STICK_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
}
