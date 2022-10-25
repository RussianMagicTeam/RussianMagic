package ru.rikgela.russianmagic.init

import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.*
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Block
import net.minecraftforge.common.ForgeTier
import net.minecraftforge.common.TierSortingRegistry
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import ru.rikgela.russianmagic.MOD_ID
import java.util.List


object RMForgeTier{
    val EBONY_PLANKS_TIER_TAG: TagKey<Block> =
        BlockTags.create(ResourceLocation("russianmagic:ebony_planks_tier_tag"))
    val EBONY_PLANKS_TIER = TierSortingRegistry.registerTier(
        ForgeTier(
            5, 350, 6.5f, 2.3f, 12, EBONY_PLANKS_TIER_TAG
        ) {
            Ingredient.of(RMItems.EBONY_PLANKS.get())
        },
        ResourceLocation("russianmagic:ebony_planks_tier"),
        List.of<Any>(Tiers.DIAMOND), List.of()
    )
    val MARBLE_TIER_TAG: TagKey<Block> =
        BlockTags.create(ResourceLocation("russianmagic:marble_tier_tag"))
    val MARBLE_TIER: Tier = TierSortingRegistry.registerTier(
        ForgeTier(
            5, 1061, 8.0f, 3.0f, 18, MARBLE_TIER_TAG
        ) {
            Ingredient.of(RMItems.MARBLE_BLOCK.get())
        },
        ResourceLocation("russianmagic:marble_tier"),
        List.of<Any>(EBONY_PLANKS_TIER), List.of()
    )
    val WHITE_JADE_TIER_TAG: TagKey<Block> =
        BlockTags.create(ResourceLocation("russianmagic:white_jade_tier_tag"))
    val WHITE_JADE_TIER = TierSortingRegistry.registerTier(
        ForgeTier(
            6, 1501, 8.0f, 3.0f, 25, WHITE_JADE_TIER_TAG
        ) {
            Ingredient.of(RMItems.WHITE_JADE_BLOCK.get())
        },
        ResourceLocation("russianmagic:white_jade_tier"),
        List.of<Any>(MARBLE_TIER), List.of()
    )
    val RHINESTONE_TIER_TAG: TagKey<Block> =
        BlockTags.create(ResourceLocation("russianmagic:rhinestone_tier_tag"))
    val RHINESTONE_TIER = TierSortingRegistry.registerTier(
        ForgeTier(
            7, 2001, 14.0f, 5.0f, 30, RHINESTONE_TIER_TAG
        ) {
            Ingredient.of(RMItems.RHINESTONE.get())
        },
        ResourceLocation("russianmagic:rhinestone_tier"),
        List.of<Any>(WHITE_JADE_TIER), List.of()
    )
    val AQUAMARINE_TIER_TAG: TagKey<Block> =
        BlockTags.create(ResourceLocation("russianmagic:aquamarine_tier_tag"))
    val AQUAMARINE_TIER = TierSortingRegistry.registerTier(
        ForgeTier(
            8, 3001, 18.0f, 6.0f, 35, AQUAMARINE_TIER_TAG
        ) {
            Ingredient.of(RMItems.AQUAMARINE.get())
        },
        ResourceLocation("russianmagic:aquamarine_tier"),
        List.of<Any>(RHINESTONE_TIER), List.of()
    )
}

//object RMFoods {
//    var SAUSAGE: Foods = Foods.Builder().hunger(5).saturation(0.5f).meat().build()
//    var ENCHANTED_SAUSAGE: Foods = Foods.Builder().hunger(8).saturation(0.7f).meat().setAlwaysEdible().fastToEat()
//            .effect({ EffectInstance(Effects.HEALTH_BOOST, 600, 0) }, 1f)
//            .effect({ EffectInstance(Effects.INSTANT_HEALTH, 600, 0) }, 1f).build()
//}

object RMItems {
//    val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID)
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID)

    val STONE_MAGIC_OBJECT: RegistryObject<Item> = ITEMS.register("stone_magic_object") {
        Item(Item.Properties()
                .tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
//    val SAUSAGE: RegistryObject<Item> = ITEMS.register("sausage") {
//        Item(Item.Properties()
//                .food(RMFoods.SAUSAGE)
//                .tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
//    }
//    val ENCHANTED_SAUSAGE: RegistryObject<Item> = ITEMS.register("enchanted_sausage") {
//        object : Item(Properties()
//                .food(RMFoods.ENCHANTED_SAUSAGE).tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) {
//            override fun hasEffect(stack: ItemStack): Boolean {
//                return true
//            }
//        }
//    }

    // Main Materials
    val RHINESTONE: RegistryObject<Item> = ITEMS.register("rhinestone") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val AQUAMARINE: RegistryObject<Item> = ITEMS.register("aquamarine") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    // Gems
    val JASPER: RegistryObject<Item> = ITEMS.register("jasper") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CHRYSOLITE: RegistryObject<Item> = ITEMS.register("chrysolite") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val GARNET: RegistryObject<Item> = ITEMS.register("garnet") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val TOPAZ: RegistryObject<Item> = ITEMS.register("topaz") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val PERUNITE: RegistryObject<Item> = ITEMS.register("perunite") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val IOLITE: RegistryObject<Item> = ITEMS.register("iolite") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val AGATE: RegistryObject<Item> = ITEMS.register("agate") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CITRINE: RegistryObject<Item> = ITEMS.register("citrine") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CHALCEDONY: RegistryObject<Item> = ITEMS.register("chalcedony") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val BERYL: RegistryObject<Item> = ITEMS.register("beryl") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val HELIODOR: RegistryObject<Item> = ITEMS.register("heliodor") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val SITALL: RegistryObject<Item> = ITEMS.register("sitall") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val AMETHYST: RegistryObject<Item> = ITEMS.register("amethyst") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CARNELIAN: RegistryObject<Item> = ITEMS.register("carnelian") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val TANZANITE: RegistryObject<Item> = ITEMS.register("tanzanite") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val AMETRINE: RegistryObject<Item> = ITEMS.register("ametrine") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CHAROITE: RegistryObject<Item> = ITEMS.register("charoite") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CORAL: RegistryObject<Item> = ITEMS.register("coral") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val RUBY: RegistryObject<Item> = ITEMS.register("ruby") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val ONYX: RegistryObject<Item> = ITEMS.register("onyx") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val SAPPHIRE: RegistryObject<Item> = ITEMS.register("sapphire") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val OPAL: RegistryObject<Item> = ITEMS.register("opal") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val KUNZITE: RegistryObject<Item> = ITEMS.register("kunzite") {
        Item(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    // Instruments
//    val EBONY_PLANKS_AXE: RegistryObject<Item> = ITEMS.register("ebony_planks_axe") {
//        RMAxeItem(RMForgeTier.EBONY_PLANKS_TIER, 6.0f, -3.1f, Item.Properties().tab(
//            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
//        )
//    }
    val EBONY_PLANKS_SHOVEL: RegistryObject<Item> = ITEMS.register("ebony_planks_shovel") {
        ShovelItem(RMForgeTier.EBONY_PLANKS_TIER, 1.5f, -3.0f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val EBONY_PLANKS_SWORD: RegistryObject<Item> = ITEMS.register("ebony_planks_sword") {
        SwordItem(RMForgeTier.EBONY_PLANKS_TIER, 3, -2.4f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val EBONY_PLANKS_PICKAXE: RegistryObject<Item> = ITEMS.register("ebony_planks_pickaxe") {
        PickaxeItem(RMForgeTier.EBONY_PLANKS_TIER, 1, -2.8f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val EBONY_PLANKS_HOE: RegistryObject<Item> = ITEMS.register("ebony_planks_hoe") {
        HoeItem(RMForgeTier.EBONY_PLANKS_TIER, 1, (-2).toFloat(), Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }

//    val MARBLE_AXE: RegistryObject<Item> = ITEMS.register("marble_axe") {
//        RMAxeItem(RMForgeTier.MARBLE_TIER, 6.0f, -3.1f, Item.Properties().tab(
//            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
//        )
//    }
    val MARBLE_SHOVEL: RegistryObject<Item> = ITEMS.register("marble_shovel") {
        ShovelItem(RMForgeTier.MARBLE_TIER, 1.5f, -3.0f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val MARBLE_SWORD: RegistryObject<Item> = ITEMS.register("marble_sword") {
        SwordItem(RMForgeTier.MARBLE_TIER, 3, -2.4f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val MARBLE_PICKAXE: RegistryObject<Item> = ITEMS.register("marble_pickaxe") {
        PickaxeItem(RMForgeTier.MARBLE_TIER, 1, -2.8f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val MARBLE_HOE: RegistryObject<Item> = ITEMS.register("marble_hoe") {
        HoeItem(RMForgeTier.MARBLE_TIER, 2, (-2).toFloat(), Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }

//    val WHITE_JADE_AXE: RegistryObject<Item> = ITEMS.register("white_jade_axe") {
//        RMAxeItem(RMForgeTier.WHITE_JADE_TIER, 6.0f, -3.1f, Item.Properties().tab(
//            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
//        )
//    }
    val WHITE_JADE_SHOVEL: RegistryObject<Item> = ITEMS.register("white_jade_shovel") {
        ShovelItem(RMForgeTier.WHITE_JADE_TIER, 1.5f, -3.0f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val WHITE_JADE_SWORD: RegistryObject<Item> = ITEMS.register("white_jade_sword") {
        SwordItem(RMForgeTier.WHITE_JADE_TIER, 3, -2.4f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val WHITE_JADE_PICKAXE: RegistryObject<Item> = ITEMS.register("white_jade_pickaxe") {
        PickaxeItem(RMForgeTier.WHITE_JADE_TIER, 1, -2.8f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val WHITE_JADE_HOE: RegistryObject<Item> = ITEMS.register("white_jade_hoe") {
        HoeItem(RMForgeTier.WHITE_JADE_TIER, 3, (-2).toFloat(), Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }

//    val RHINESTONE_AXE: RegistryObject<Item> = ITEMS.register("rhinestone_axe") {
//        RMAxeItem(RMForgeTier.RHINESTONE_TIER, 6.0f, -3.1f, Item.Properties().tab(
//            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
//        )
//    }
    val RHINESTONE_SHOVEL: RegistryObject<Item> = ITEMS.register("rhinestone_shovel") {
        ShovelItem(RMForgeTier.RHINESTONE_TIER, 1.5f, -3.0f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val RHINESTONE_SWORD: RegistryObject<Item> = ITEMS.register("rhinestone_sword") {
        SwordItem(RMForgeTier.RHINESTONE_TIER, 3, -2.4f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val RHINESTONE_PICKAXE: RegistryObject<Item> = ITEMS.register("rhinestone_pickaxe") {
        PickaxeItem(RMForgeTier.RHINESTONE_TIER, 1, -2.8f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val RHINESTONE_HOE: RegistryObject<Item> = ITEMS.register("rhinestone_hoe") {
        HoeItem(RMForgeTier.RHINESTONE_TIER, 4, (-2).toFloat(), Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }

//    val AQUAMARINE_AXE: RegistryObject<Item> = ITEMS.register("aquamarine_axe") {
//        RMAxeItem(RMForgeTier.AQUAMARINE_TIER, 6.0f, -3.1f, Item.Properties().tab(
//            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
//        )
//    }
    val AQUAMARINE_SHOVEL: RegistryObject<Item> = ITEMS.register("aquamarine_shovel") {
        ShovelItem(RMForgeTier.AQUAMARINE_TIER, 1.5f, -3.0f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val AQUAMARINE_SWORD: RegistryObject<Item> = ITEMS.register("aquamarine_sword") {
        SwordItem(RMForgeTier.AQUAMARINE_TIER, 3, -2.4f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val AQUAMARINE_PICKAXE: RegistryObject<Item> = ITEMS.register("aquamarine_pickaxe") {
        PickaxeItem(RMForgeTier.AQUAMARINE_TIER, 1, -2.8f, Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }
    val AQUAMARINE_HOE: RegistryObject<Item> = ITEMS.register("aquamarine_hoe") {
        HoeItem(RMForgeTier.AQUAMARINE_TIER, 5, (-2).toFloat(), Item.Properties().tab(
            RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)
        )
    }

    //Blocks
    val EBONY_PLANKS: RegistryObject<BlockItem> = ITEMS.register("ebony_planks") {
        BlockItem(RMBlocks.EBONY_PLANKS.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val STONE_STICK_BLOCK: RegistryObject<BlockItem> = ITEMS.register("stone_stick_block") {
        BlockItem(RMBlocks.STONE_STICK_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val MARBLE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("marble_block") {
        BlockItem(RMBlocks.MARBLE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val WHITE_JADE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("white_jade_block") {
        BlockItem(RMBlocks.WHITE_JADE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val AQUAMARINE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("aquamarine_block") {
        BlockItem(RMBlocks.AQUAMARINE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val AQUAMARINE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("aquamarine_block_ore") {
        BlockItem(RMBlocks.AQUAMARINE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val RHINESTONE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("rhinestone_block") {
        BlockItem(RMBlocks.RHINESTONE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val RHINESTONE_BLOCK_ORE: RegistryObject<BlockItem?> = ITEMS.register("rhinestone_block_ore") {
        BlockItem(RMBlocks.RHINESTONE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val JASPER_BLOCK: RegistryObject<BlockItem?> = ITEMS.register("jasper_block") {
        BlockItem(RMBlocks.JASPER_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val JASPER_BLOCK_ORE: RegistryObject<BlockItem?> = ITEMS.register("jasper_block_ore") {
        BlockItem(RMBlocks.JASPER_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val CHRYSOLITE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("chrysolite_block") {
        BlockItem(RMBlocks.CHRYSOLITE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CHRYSOLITE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("chrysolite_block_ore") {
        BlockItem(RMBlocks.CHRYSOLITE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val GARNET_BLOCK: RegistryObject<BlockItem> = ITEMS.register("garnet_block") {
        BlockItem(RMBlocks.GARNET_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val GARNET_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("garnet_block_ore") {
        BlockItem(RMBlocks.GARNET_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val TOPAZ_BLOCK: RegistryObject<BlockItem> = ITEMS.register("topaz_block") {
        BlockItem(RMBlocks.TOPAZ_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val TOPAZ_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("topaz_block_ore") {
        BlockItem(RMBlocks.TOPAZ_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val PERUNITE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("perunite_block") {
        BlockItem(RMBlocks.PERUNITE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val PERUNITE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("perunite_block_ore") {
        BlockItem(RMBlocks.PERUNITE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val IOLITE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("iolite_block") {
        BlockItem(RMBlocks.IOLITE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val IOLITE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("iolite_block_ore") {
        BlockItem(RMBlocks.IOLITE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val AGATE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("agate_block") {
        BlockItem(RMBlocks.AGATE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val AGATE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("agate_block_ore") {
        BlockItem(RMBlocks.AGATE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val CITRINE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("citrine_block") {
        BlockItem(RMBlocks.CITRINE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CITRINE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("citrine_block_ore") {
        BlockItem(RMBlocks.CITRINE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val CHALCEDONY_BLOCK: RegistryObject<BlockItem> = ITEMS.register("chalcedony_block") {
        BlockItem(RMBlocks.CHALCEDONY_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CHALCEDONY_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("chalcedony_block_ore") {
        BlockItem(RMBlocks.CHALCEDONY_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val BERYL_BLOCK: RegistryObject<BlockItem> = ITEMS.register("beryl_block") {
        BlockItem(RMBlocks.BERYL_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val BERYL_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("beryl_block_ore") {
        BlockItem(RMBlocks.BERYL_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val HELIODOR_BLOCK: RegistryObject<BlockItem> = ITEMS.register("heliodor_block") {
        BlockItem(RMBlocks.HELIODOR_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val HELIODOR_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("heliodor_block_ore") {
        BlockItem(RMBlocks.HELIODOR_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val SITALL_BLOCK: RegistryObject<BlockItem> = ITEMS.register("sitall_block") {
        BlockItem(RMBlocks.SITALL_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val SITALL_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("sitall_block_ore") {
        BlockItem(RMBlocks.SITALL_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val AMETHYST_BLOCK: RegistryObject<BlockItem> = ITEMS.register("amethyst_block") {
        BlockItem(RMBlocks.AMETHYST_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val AMETHYST_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("amethyst_block_ore") {
        BlockItem(RMBlocks.AMETHYST_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val CARNELIAN_BLOCK: RegistryObject<BlockItem> = ITEMS.register("carnelian_block") {
        BlockItem(RMBlocks.CARNELIAN_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CARNELIAN_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("carnelian_block_ore") {
        BlockItem(RMBlocks.CARNELIAN_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val TANZANITE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("tanzanite_block") {
        BlockItem(RMBlocks.TANZANITE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val TANZANITE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("tanzanite_block_ore") {
        BlockItem(RMBlocks.TANZANITE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val AMETRINE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("ametrine_block") {
        BlockItem(RMBlocks.AMETRINE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val AMETRINE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("ametrine_block_ore") {
        BlockItem(RMBlocks.AMETRINE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val CHAROITE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("charoite_block") {
        BlockItem(RMBlocks.CHAROITE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CHAROITE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("charoite_block_ore") {
        BlockItem(RMBlocks.CHAROITE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val CORAL_BLOCK: RegistryObject<BlockItem> = ITEMS.register("coral_block") {
        BlockItem(RMBlocks.CORAL_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val CORAL_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("coral_block_ore") {
        BlockItem(RMBlocks.CORAL_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val RUBY_BLOCK: RegistryObject<BlockItem> = ITEMS.register("ruby_block") {
        BlockItem(RMBlocks.RUBY_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val RUBY_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("ruby_block_ore") {
        BlockItem(RMBlocks.RUBY_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val ONYX_BLOCK: RegistryObject<BlockItem> = ITEMS.register("onyx_block") {
        BlockItem(RMBlocks.ONYX_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val ONYX_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("onyx_block_ore") {
        BlockItem(RMBlocks.ONYX_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val SAPPHIRE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("sapphire_block") {
        BlockItem(RMBlocks.SAPPHIRE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val SAPPHIRE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("sapphire_block_ore") {
        BlockItem(RMBlocks.SAPPHIRE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val OPAL_BLOCK: RegistryObject<BlockItem> = ITEMS.register("opal_block") {
        BlockItem(RMBlocks.OPAL_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val OPAL_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("opal_block_ore") {
        BlockItem(RMBlocks.OPAL_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }

    val KUNZITE_BLOCK: RegistryObject<BlockItem> = ITEMS.register("kunzite_block") {
        BlockItem(RMBlocks.KUNZITE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
    val KUNZITE_BLOCK_ORE: RegistryObject<BlockItem> = ITEMS.register("kunzite_block_ore") {
        BlockItem(RMBlocks.KUNZITE_BLOCK_ORE.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
    }
//
//    //Mekanisms
//    val RM_DIAMOND_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_diamond_furnace") { BlockItem(RMBlocks.RM_DIAMOND_FURNACE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val RM_ISOLATED_DIAMOND_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_isolated_diamond_furnace") { BlockItem(RMBlocks.RM_ISOLATED_DIAMOND_FURNACE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//
//    val RM_EBONY_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_ebony_furnace") { BlockItem(RMBlocks.RM_EBONY_FURNACE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val RM_MARBLE_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_marble_furnace") { BlockItem(RMBlocks.RM_MARBLE_FURNACE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val RM_WHITE_JADE_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_white_jade_furnace") { BlockItem(RMBlocks.RM_WHITE_JADE_FURNACE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val RM_RHINESTONE_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_rhinestone_furnace") { BlockItem(RMBlocks.RM_RHINESTONE_FURNACE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val RM_AQUAMARINE_FURNACE_BLOCK: RegistryObject<Item> = ITEMS.register("rm_aquamarine_furnace") { BlockItem(RMBlocks.RM_AQUAMARINE_FURNACE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//
//    val RM_BASIC_MAGIC_SOURCE: RegistryObject<Item> = ITEMS.register("rm_basic_magic_source") { BlockItem(RMBlocks.RM_BASIC_MAGIC_SOURCE_BLOCK.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val RM_ANALYZER: RegistryObject<Item> = ITEMS.register("rm_analyzer") { RMAnalyzer(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val RM_LINK_CREATOR: RegistryObject<Item> = ITEMS.register("rm_link_creator") { RMLinkCreator(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val RM_LINK_DESTROYER: RegistryObject<Item> = ITEMS.register("rm_link_destroyer") { RMLinkDestroyer(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//
//    // Spell_Scrolls
//    val SPELL_OF_REGENERATION: RegistryObject<Item> = ITEMS.register("spell_of_regeneration") {
//        SpellScroll(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
//    }
//    val SPELL_OF_FIREBALL: RegistryObject<Item> = ITEMS.register("spell_of_fireball") {
//        FireballScroll(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
//    }
//    val MEDITATION_REBORN_SCROLL: RegistryObject<Item> = ITEMS.register("meditation_reborn_scroll") {
//        MeditationRebornScroll(Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP))
//    }
//
//    //tree
//    val EBONY_SAPLING: RegistryObject<BlockItem> = ITEMS.register("ebony_sapling") { BlockItem(RMBlocks.EBONY_SAPLING.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val EBONY_LOG: RegistryObject<BlockItem> = ITEMS.register("ebony_log") { BlockItem(RMBlocks.EBONY_LOG.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val STRIPPED_EBONY_LOG: RegistryObject<BlockItem> = ITEMS.register("stripped_ebony_log") { BlockItem(RMBlocks.STRIPPED_EBONY_LOG.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val EBONY_WOOD: RegistryObject<BlockItem> = ITEMS.register("ebony_wood") { BlockItem(RMBlocks.EBONY_WOOD.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val STRIPPED_EBONY_WOOD: RegistryObject<BlockItem> = ITEMS.register("stripped_ebony_wood") { BlockItem(RMBlocks.STRIPPED_EBONY_WOOD.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }
//    val EBONY_LEAVES: RegistryObject<BlockItem> = ITEMS.register("ebony_leaves") { BlockItem(RMBlocks.EBONY_LEAVES.get(), Item.Properties().tab(RMCreativeModeTabs.RUSSIAN_MAGIC_ITEM_GROUP)) }

}