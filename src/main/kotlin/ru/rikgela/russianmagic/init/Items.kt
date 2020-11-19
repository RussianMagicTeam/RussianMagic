package ru.rikgela.russianmagic.init

import net.minecraft.item.*
import net.minecraft.item.crafting.Ingredient
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.util.LazyValue
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.ItemGroups
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.items.RMAxeItem
import ru.rikgela.russianmagic.objects.items.FireballScroll
import ru.rikgela.russianmagic.objects.items.SpellScroll
import java.util.function.Supplier


enum class ItemTier(
        private val harvestLevel: Int,
        private val maxUses: Int,
        private val efficiency: Float,
        private val attackDamage: Float,
        private val enchantability: Int,
        repairMaterialIn: Supplier<Ingredient>) : IItemTier {
    EBONY_PLANKS(4, 350, 6.5f, 2.3f, 12, Supplier<Ingredient> { Ingredient.fromItems(RMItems.EBONY_PLANKS.get()) }),
    MARBLE(5, 1061, 8.0f, 3.0f, 18, Supplier<Ingredient> { Ingredient.fromItems(RMItems.MARBLE.get()) }),
    WHITE_JADE(6, 1501, 8.0f, 3.0f, 25, Supplier<Ingredient> { Ingredient.fromItems(RMItems.WHITE_JADE.get()) }),
    RHINESTONE(7, 2001, 14.0f, 5.0f, 30, Supplier<Ingredient> { Ingredient.fromItems(RMItems.RHINESTONE.get()) }),
    AQUAMARINE(8, 3001, 18.0f, 6.0f, 35, Supplier<Ingredient> { Ingredient.fromItems(RMItems.AQUAMARINE.get()) });

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

object RMItems {
    @JvmStatic
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

    val MARBLE: RegistryObject<Item> = ITEMS.register("marble") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE: RegistryObject<Item> = ITEMS.register("white_jade") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE: RegistryObject<Item> = ITEMS.register("rhinestone") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE: RegistryObject<Item> = ITEMS.register("aquamarine") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val JASPER: RegistryObject<Item> = ITEMS.register("jasper") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CHRYSOLITE: RegistryObject<Item> = ITEMS.register("chrysolite") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val GARNET: RegistryObject<Item> = ITEMS.register("garnet") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val TOPAZ: RegistryObject<Item> = ITEMS.register("topaz") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val PERUNITE: RegistryObject<Item> = ITEMS.register("perunite") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val IOLITE: RegistryObject<Item> = ITEMS.register("iolite") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AGATE: RegistryObject<Item> = ITEMS.register("agate") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CITRINE: RegistryObject<Item> = ITEMS.register("citrine") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CHALCEDONY: RegistryObject<Item> = ITEMS.register("chalcedony") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val BERYL: RegistryObject<Item> = ITEMS.register("beryl") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val HELIODOR: RegistryObject<Item> = ITEMS.register("heliodor") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val SITALL: RegistryObject<Item> = ITEMS.register("sitall") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AMETHYST: RegistryObject<Item> = ITEMS.register("amethyst") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CARNELIAN: RegistryObject<Item> = ITEMS.register("carnelian") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val TANZANITE: RegistryObject<Item> = ITEMS.register("tanzanite") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AMETRINE: RegistryObject<Item> = ITEMS.register("ametrine") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CHAROITE: RegistryObject<Item> = ITEMS.register("charoite") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CORAL: RegistryObject<Item> = ITEMS.register("coral") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RUBY: RegistryObject<Item> = ITEMS.register("ruby") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val ONYX: RegistryObject<Item> = ITEMS.register("onyx") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val SAPPHIRE: RegistryObject<Item> = ITEMS.register("sapphire") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val OPAL: RegistryObject<Item> = ITEMS.register("opal") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val KUNZITE: RegistryObject<Item> = ITEMS.register("kunzite") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }


    val EBONY_PLANKS_AXE: RegistryObject<Item> = ITEMS.register("ebony_planks_axe") { RMAxeItem(ItemTier.EBONY_PLANKS, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS_SHOVEL: RegistryObject<Item> = ITEMS.register("ebony_planks_shovel") { ShovelItem(ItemTier.EBONY_PLANKS, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS_SWORD: RegistryObject<Item> = ITEMS.register("ebony_planks_sword") { SwordItem(ItemTier.EBONY_PLANKS, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS_PICKAXE: RegistryObject<Item> = ITEMS.register("ebony_planks_pickaxe") { PickaxeItem(ItemTier.EBONY_PLANKS, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS_HOE: RegistryObject<Item> = ITEMS.register("ebony_planks_hoe") { HoeItem(ItemTier.EBONY_PLANKS, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val MARBLE_AXE: RegistryObject<Item> = ITEMS.register("marble_axe") { RMAxeItem(ItemTier.MARBLE, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_SHOVEL: RegistryObject<Item> = ITEMS.register("marble_shovel") { ShovelItem(ItemTier.MARBLE, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_SWORD: RegistryObject<Item> = ITEMS.register("marble_sword") { SwordItem(ItemTier.MARBLE, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_PICKAXE: RegistryObject<Item> = ITEMS.register("marble_pickaxe") { PickaxeItem(ItemTier.MARBLE, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_HOE: RegistryObject<Item> = ITEMS.register("marble_hoe") { HoeItem(ItemTier.MARBLE, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val WHITE_JADE_AXE: RegistryObject<Item> = ITEMS.register("white_jade_axe") { RMAxeItem(ItemTier.WHITE_JADE, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_SHOVEL: RegistryObject<Item> = ITEMS.register("white_jade_shovel") { ShovelItem(ItemTier.WHITE_JADE, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_SWORD: RegistryObject<Item> = ITEMS.register("white_jade_sword") { SwordItem(ItemTier.WHITE_JADE, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_PICKAXE: RegistryObject<Item> = ITEMS.register("white_jade_pickaxe") { PickaxeItem(ItemTier.WHITE_JADE, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_HOE: RegistryObject<Item> = ITEMS.register("white_jade_hoe") { HoeItem(ItemTier.WHITE_JADE, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val RHINESTONE_AXE: RegistryObject<Item> = ITEMS.register("rhinestone_axe") { RMAxeItem(ItemTier.RHINESTONE, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_SHOVEL: RegistryObject<Item> = ITEMS.register("rhinestone_shovel") { ShovelItem(ItemTier.RHINESTONE, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_SWORD: RegistryObject<Item> = ITEMS.register("rhinestone_sword") { SwordItem(ItemTier.RHINESTONE, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_PICKAXE: RegistryObject<Item> = ITEMS.register("rhinestone_pickaxe") { PickaxeItem(ItemTier.RHINESTONE, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_HOE: RegistryObject<Item> = ITEMS.register("rhinestone_hoe") { HoeItem(ItemTier.RHINESTONE, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val AQUAMARINE_AXE: RegistryObject<Item> = ITEMS.register("aquamarine_axe") { RMAxeItem(ItemTier.AQUAMARINE, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_SHOVEL: RegistryObject<Item> = ITEMS.register("aquamarine_shovel") { ShovelItem(ItemTier.AQUAMARINE, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_SWORD: RegistryObject<Item> = ITEMS.register("aquamarine_sword") { SwordItem(ItemTier.AQUAMARINE, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_PICKAXE: RegistryObject<Item> = ITEMS.register("aquamarine_pickaxe") { PickaxeItem(ItemTier.AQUAMARINE, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_HOE: RegistryObject<Item> = ITEMS.register("aquamarine_hoe") { HoeItem(ItemTier.AQUAMARINE, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }


    //Blocks
    val STONE_STICK_BLOCK: RegistryObject<Item> = ITEMS.register("stone_stick_block") { BlockItem(RMBlocks.STONE_STICK_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_BLOCK: RegistryObject<Item> = ITEMS.register("marble_block") { BlockItem(RMBlocks.MARBLE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_BLOCK: RegistryObject<Item> = ITEMS.register("white_jade_block") { BlockItem(RMBlocks.WHITE_JADE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val AQUAMARINE_BLOCK: RegistryObject<Item?>? = ITEMS.register("aquamarine_block") { BlockItem(RMBlocks.AQUAMARINE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("aquamarine_block_ore") { BlockItem(RMBlocks.AQUAMARINE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val RHINESTONE_BLOCK: RegistryObject<Item?>? = ITEMS.register("rhinestone_block") { BlockItem(RMBlocks.RHINESTONE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("rhinestone_block_ore") { BlockItem(RMBlocks.RHINESTONE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val JASPER_BLOCK: RegistryObject<Item?>? = ITEMS.register("jasper_block") { BlockItem(RMBlocks.JASPER_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val JASPER_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("jasper_block_ore") { BlockItem(RMBlocks.JASPER_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val CHRYSOLITE_BLOCK: RegistryObject<Item?>? = ITEMS.register("chrysolite_block") { BlockItem(RMBlocks.CHRYSOLITE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CHRYSOLITE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("chrysolite_block_ore") { BlockItem(RMBlocks.CHRYSOLITE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val GARNET_BLOCK: RegistryObject<Item?>? = ITEMS.register("garnet_block") { BlockItem(RMBlocks.GARNET_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val GARNET_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("garnet_block_ore") { BlockItem(RMBlocks.GARNET_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val TOPAZ_BLOCK: RegistryObject<Item?>? = ITEMS.register("topaz_block") { BlockItem(RMBlocks.TOPAZ_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val TOPAZ_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("topaz_block_ore") { BlockItem(RMBlocks.TOPAZ_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val PERUNITE_BLOCK: RegistryObject<Item?>? = ITEMS.register("perunite_block") { BlockItem(RMBlocks.PERUNITE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val PERUNITE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("perunite_block_ore") { BlockItem(RMBlocks.PERUNITE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val IOLITE_BLOCK: RegistryObject<Item?>? = ITEMS.register("iolite_block") { BlockItem(RMBlocks.IOLITE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val IOLITE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("iolite_block_ore") { BlockItem(RMBlocks.IOLITE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val AGATE_BLOCK: RegistryObject<Item?>? = ITEMS.register("agate_block") { BlockItem(RMBlocks.AGATE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AGATE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("agate_block_ore") { BlockItem(RMBlocks.AGATE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val CITRINE_BLOCK: RegistryObject<Item?>? = ITEMS.register("citrine_block") { BlockItem(RMBlocks.CITRINE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CITRINE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("citrine_block_ore") { BlockItem(RMBlocks.CITRINE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val CHALCEDONY_BLOCK: RegistryObject<Item?>? = ITEMS.register("chalcedony_block") { BlockItem(RMBlocks.CHALCEDONY_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CHALCEDONY_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("chalcedony_block_ore") { BlockItem(RMBlocks.CHALCEDONY_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val BERYL_BLOCK: RegistryObject<Item?>? = ITEMS.register("beryl_block") { BlockItem(RMBlocks.BERYL_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val BERYL_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("beryl_block_ore") { BlockItem(RMBlocks.BERYL_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val HELIODOR_BLOCK: RegistryObject<Item?>? = ITEMS.register("heliodor_block") { BlockItem(RMBlocks.HELIODOR_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val HELIODOR_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("heliodor_block_ore") { BlockItem(RMBlocks.HELIODOR_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val SITALL_BLOCK: RegistryObject<Item?>? = ITEMS.register("sitall_block") { BlockItem(RMBlocks.SITALL_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val SITALL_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("sitall_block_ore") { BlockItem(RMBlocks.SITALL_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val AMETHYST_BLOCK: RegistryObject<Item?>? = ITEMS.register("amethyst_block") { BlockItem(RMBlocks.AMETHYST_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AMETHYST_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("amethyst_block_ore") { BlockItem(RMBlocks.AMETHYST_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val CARNELIAN_BLOCK: RegistryObject<Item?>? = ITEMS.register("carnelian_block") { BlockItem(RMBlocks.CARNELIAN_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CARNELIAN_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("carnelian_block_ore") { BlockItem(RMBlocks.CARNELIAN_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val TANZANITE_BLOCK: RegistryObject<Item?>? = ITEMS.register("tanzanite_block") { BlockItem(RMBlocks.TANZANITE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val TANZANITE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("tanzanite_block_ore") { BlockItem(RMBlocks.TANZANITE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val AMETRINE_BLOCK: RegistryObject<Item?>? = ITEMS.register("ametrine_block") { BlockItem(RMBlocks.AMETRINE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AMETRINE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("ametrine_block_ore") { BlockItem(RMBlocks.AMETRINE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val CHAROITE_BLOCK: RegistryObject<Item?>? = ITEMS.register("charoite_block") { BlockItem(RMBlocks.CHAROITE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CHAROITE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("charoite_block_ore") { BlockItem(RMBlocks.CHAROITE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val CORAL_BLOCK: RegistryObject<Item?>? = ITEMS.register("coral_block") { BlockItem(RMBlocks.CORAL_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val CORAL_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("coral_block_ore") { BlockItem(RMBlocks.CORAL_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val RUBY_BLOCK: RegistryObject<Item?>? = ITEMS.register("ruby_block") { BlockItem(RMBlocks.RUBY_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RUBY_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("ruby_block_ore") { BlockItem(RMBlocks.RUBY_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val ONYX_BLOCK: RegistryObject<Item?>? = ITEMS.register("onyx_block") { BlockItem(RMBlocks.ONYX_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val ONYX_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("onyx_block_ore") { BlockItem(RMBlocks.ONYX_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val SAPPHIRE_BLOCK: RegistryObject<Item?>? = ITEMS.register("sapphire_block") { BlockItem(RMBlocks.SAPPHIRE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val SAPPHIRE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("sapphire_block_ore") { BlockItem(RMBlocks.SAPPHIRE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val OPAL_BLOCK: RegistryObject<Item?>? = ITEMS.register("opal_block") { BlockItem(RMBlocks.OPAL_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val OPAL_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("opal_block_ore") { BlockItem(RMBlocks.OPAL_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val KUNZITE_BLOCK: RegistryObject<Item?>? = ITEMS.register("kunzite_block") { BlockItem(RMBlocks.KUNZITE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val KUNZITE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("kunzite_block_ore") { BlockItem(RMBlocks.KUNZITE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    //Mekanisms
    val RM_GOLD_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_gold_furnace") { BlockItem(RMBlocks.RM_GOLD_FURNACE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RM_ISOLATED_GOLD_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_isolated_gold_furnace") { BlockItem(RMBlocks.RM_ISOLATED_GOLD_FURNACE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val RM_EBONY_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_ebony_furnace") { BlockItem(RMBlocks.RM_EBONY_FURNACE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RM_MARBLE_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_marble_furnace") { BlockItem(RMBlocks.RM_MARBLE_FURNACE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RM_WHITE_JADE_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_white_jade_furnace") { BlockItem(RMBlocks.RM_WHITE_JADE_FURNACE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RM_RHINESTONE_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_rhinestone_furnace") { BlockItem(RMBlocks.RM_RHINESTONE_FURNACE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RM_AQUAMARINE_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_aquamarine_furnace") { BlockItem(RMBlocks.RM_AQUAMARINE_FURNACE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    // Spell_Scrolls
    val SPELL_OF_REGENERATION: RegistryObject<Item> = ITEMS.register("spell_of_regeneration") {
        SpellScroll(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val SPELL_OF_FIREBALL: RegistryObject<Item> = ITEMS.register("spell_of_fireball") {
        FireballScroll(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    //tree
    val EBONY_SAPLING: RegistryObject<Item?>? = ITEMS.register("ebony_sapling") { BlockItem(RMBlocks.EBONY_SAPLING.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_PLANKS: RegistryObject<Item> = ITEMS.register("ebony_planks") { BlockItem(RMBlocks.EBONY_PLANKS.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_LOG: RegistryObject<Item> = ITEMS.register("ebony_log") { BlockItem(RMBlocks.EBONY_LOG.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val STRIPPED_EBONY_LOG: RegistryObject<Item> = ITEMS.register("stripped_ebony_log") { BlockItem(RMBlocks.STRIPPED_EBONY_LOG.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_WOOD: RegistryObject<Item> = ITEMS.register("ebony_wood") { BlockItem(RMBlocks.EBONY_WOOD.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val STRIPPED_EBONY_WOOD: RegistryObject<Item> = ITEMS.register("stripped_ebony_wood") { BlockItem(RMBlocks.STRIPPED_EBONY_WOOD.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val EBONY_LEAVES: RegistryObject<Item> = ITEMS.register("ebony_leaves") { BlockItem(RMBlocks.EBONY_LEAVES.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

}