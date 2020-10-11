package ru.rikgela.russianmagic

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.FireballEntity
import net.minecraft.item.*
import net.minecraft.item.crafting.Ingredient
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.util.LazyValue
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.objects.entity.projectile.ProjectileEntity
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
    EBONY_PLANKS(4, 350, 6.5f, 2.3f, 12, Supplier<Ingredient> { Ingredient.fromItems(Items.EBONY_PLANKS.get()) }),
    MARBLE(5, 1061, 8.0f, 3.0f, 18, Supplier<Ingredient> { Ingredient.fromItems(Items.MARBLE.get()) }),
    WHITE_JADE(6, 1501, 8.0f, 3.0f, 25, Supplier<Ingredient> { Ingredient.fromItems(Items.WHITE_JADE.get()) }),
    RHINESTONE(7, 2001, 14.0f, 5.0f, 30, Supplier<Ingredient> {Ingredient.fromItems(Items.RHINESTONE.get()) }),
    AQUAMARINE(8, 3001, 18.0f, 6.0f, 35, Supplier<Ingredient> {Ingredient.fromItems(Items.AQUAMARINE.get()) });

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
    val ENTITIES = DeferredRegister(ForgeRegistries.ENTITIES, MOD_ID)


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
    val RHINESTONE: RegistryObject<Item> = ITEMS.register("rhinestone") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE: RegistryObject<Item> = ITEMS.register("aquamarine") { Item(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

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

    val RHINESTONE_AXE: RegistryObject<Item> = ITEMS.register("rhinestone_axe") { AxeItem(ItemTier.RHINESTONE, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_SHOVEL: RegistryObject<Item> = ITEMS.register("rhinestone_shovel") { ShovelItem(ItemTier.RHINESTONE, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_SWORD: RegistryObject<Item> = ITEMS.register("rhinestone_sword") { SwordItem(ItemTier.RHINESTONE, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_PICKAXE: RegistryObject<Item> = ITEMS.register("rhinestone_pickaxe") { PickaxeItem(ItemTier.RHINESTONE, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_HOE: RegistryObject<Item> = ITEMS.register("rhinestone_hoe") { HoeItem(ItemTier.RHINESTONE, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val AQUAMARINE_AXE: RegistryObject<Item> = ITEMS.register("aquamarine_axe") { AxeItem(ItemTier.AQUAMARINE, 6.0f, -3.1f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_SHOVEL: RegistryObject<Item> = ITEMS.register("aquamarine_shovel") { ShovelItem(ItemTier.AQUAMARINE, 1.5f, -3.0f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_SWORD: RegistryObject<Item> = ITEMS.register("aquamarine_sword") { SwordItem(ItemTier.AQUAMARINE, 3, -2.4f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_PICKAXE: RegistryObject<Item> = ITEMS.register("aquamarine_pickaxe") { PickaxeItem(ItemTier.AQUAMARINE, 1, -2.8f, Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_HOE: RegistryObject<Item> = ITEMS.register("aquamarine_hoe") { HoeItem(ItemTier.AQUAMARINE, (-2).toFloat(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }


    //Blocks
    val STONE_STICK_BLOCK: RegistryObject<Item?>? = ITEMS.register("stone_stick_block") { BlockItem(Blocks.STONE_STICK_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val MARBLE_BLOCK: RegistryObject<Item?>? = ITEMS.register("marble_block") { BlockItem(Blocks.MARBLE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val WHITE_JADE_BLOCK: RegistryObject<Item?>? = ITEMS.register("white_jade_block") { BlockItem(Blocks.WHITE_JADE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val AQUAMARINE_BLOCK: RegistryObject<Item?>? = ITEMS.register("aquamarine_block") { BlockItem(Blocks.AQUAMARINE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val AQUAMARINE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("aquamarine_block_ore") { BlockItem(Blocks.AQUAMARINE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val RHINESTONE_BLOCK: RegistryObject<Item?>? = ITEMS.register("rhinestone_block") { BlockItem(Blocks.RHINESTONE_BLOCK.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }
    val RHINESTONE_BLOCK_ORE: RegistryObject<Item?>? = ITEMS.register("rhinestone_block_ore") { BlockItem(Blocks.RHINESTONE_BLOCK_ORE.get(), Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    // Spell_Scrolls
    val SPELL_OF_REGENERATION: RegistryObject<Item> = ITEMS.register("spell_of_regeneration") {
        SpellScroll(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    val SPELL_OF_FIREBALL: RegistryObject<Item> = ITEMS.register("spell_of_fireball") {
        FireballScroll(Item.Properties().group(ItemGroups.RUSSIAN_MAGIC_ITEM_GROUP)) }

    //val PROJECTILE_ENTITY: RegistryObject<EntityType<ProjectileEntity>> = ENTITIES.register("projectile_entity", Supplier<I> { EntityType.Builder.create<Entity>({ ProjectileEntity() }, EntityClassification.AMBIENT).size(0.5f, 0.9f).build(null) })

    //val ENT_PROJECTILE RegistryObject<EntityType<ProjectileEntity>> = ENTITIES.register(EntityType.Builder.<EntityModProjectile>create(EntityClassification.MISC).setCustomClientFactory(EntityModProjectile::new).size(0.25F, 0.25F), "ent_projectile");

    //val PROJECTILE_ENTITY: RegistryObject<TileEntityType<ProjectileEntity>> = ENTITIES.register(
    //        "projectile_entity", { TileEntityType.Builder.create({ ProjectileEntity() }, EXAMPLE_BLOCK.get()).build(null) }
    //)
    val s : RegistryObject<EntityType<ProjectileEntity>> = ENTITIES.register("projectile_entity", {
        { EntityType.Builder.create<Entity>({ ProjectileEntity() }, EntityClassification.AMBIENT).size(0.5f, 0.9f).build(null))

    //val PROJECTILE_ENTITY: RegistryObject<EntityType<ProjectileEntity>> = ENTITIES.register("projectile_entity")

}
