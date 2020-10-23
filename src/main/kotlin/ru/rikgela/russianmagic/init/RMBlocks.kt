package ru.rikgela.russianmagic.init

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.FlowerPotBlock
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderTypeLookup
import net.minecraftforge.common.ToolType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.blocks.EbonyLogBlock
import ru.rikgela.russianmagic.blocks.EbonySaplingBlock
import ru.rikgela.russianmagic.blocks.EbonyTree
import ru.rikgela.russianmagic.blocks.RMLeavesBlock
import ru.rikgela.russianmagic.objects.blocks.AbstractRMFurnace
import ru.rikgela.russianmagic.objects.blocks.RMEbonyFurnaceBlock
import ru.rikgela.russianmagic.objects.blocks.RMMarbleFurnaceBlock
import java.util.function.Supplier

object RMBlocks {
    @JvmStatic
    val BLOCKS: DeferredRegister<Block> = DeferredRegister<Block>(ForgeRegistries.BLOCKS, MOD_ID)
    val STONE_STICK_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("stone_stick_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val MARBLE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("marble_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val WHITE_JADE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("white_jade_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AQUAMARINE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("aquamarine_block") {
      Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AQUAMARINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("aquamarine_block_ore") {
       Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RHINESTONE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("rhinestone_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RHINESTONE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("rhinestone_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val JASPER_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("jasper_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val JASPER_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("jasper_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHRYSOLITE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("chrysolite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHRYSOLITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("chrysolite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val GARNET_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("garnet_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val GARNET_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("garnet_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val TOPAZ_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("topaz_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val TOPAZ_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("topaz_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val PERUNITE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("perunite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val PERUNITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("perunite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val IOLITE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("iolite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val IOLITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("iolite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AGATE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("agate_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AGATE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("agate_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CITRINE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("citrine_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CITRINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("citrine_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHALCEDONY_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("chalcedony_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHALCEDONY_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("chalcedony_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val BERYL_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("beryl_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val BERYL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("beryl_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val HELIODOR_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("heliodor_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val HELIODOR_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("heliodor_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val SITALL_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("sitall_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val SITALL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("sitall_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AMETHYST_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("amethyst_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AMETHYST_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("amethyst_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CARNELIAN_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("carnelian_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CARNELIAN_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("carnelian_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val TANZANITE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("tanzanite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val TANZANITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("tanzanite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AMETRINE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("ametrine_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AMETRINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("ametrine_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHAROITE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("charoite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHAROITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("charoite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CORAL_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("coral_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CORAL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("coral_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RUBY_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("ruby_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RUBY_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("ruby_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val ONYX_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("onyx_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val ONYX_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("onyx_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val SAPPHIRE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("sapphire_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val SAPPHIRE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("sapphire_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val OPAL_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("opal_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val OPAL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("opal_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val KUNZITE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("kunzite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val KUNZITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("kunzite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }

    val RM_MARBLE_FURNACE_BLOCK: RegistryObject<AbstractRMFurnace> = BLOCKS.register<AbstractRMFurnace>("rm_marble_furnace") {
        RMMarbleFurnaceBlock(Block
                .Properties
                .from(Blocks.FURNACE))
    }

    val RM_EBONY_FURNACE_BLOCK: RegistryObject<AbstractRMFurnace> = BLOCKS.register<AbstractRMFurnace>("rm_ebony_furnace") {
        RMEbonyFurnaceBlock(Block
                .Properties
                .from(Blocks.FURNACE))
    }

    val EBONY_PLANKS: RegistryObject<Block> = BLOCKS.register("ebony_planks") { Block(Block.Properties.from(Blocks.OAK_PLANKS)) }
    val EBONY_LEAVES: RegistryObject<RMLeavesBlock> = BLOCKS.register("ebony_leaves") { RMLeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)) }
    val EBONY_SAPLING: RegistryObject<EbonySaplingBlock> = BLOCKS.register("ebony_sapling") { EbonySaplingBlock(Supplier { EbonyTree() }, Block.Properties.from(Blocks.OAK_SAPLING)) }
    val POTTED_EBONY_SAPLING: RegistryObject<FlowerPotBlock> = BLOCKS.register("potted_ebony_sapling") { FlowerPotBlock(null, Supplier { EBONY_SAPLING.get() }, Block.Properties.create(Material.MISCELLANEOUS).notSolid()) }

    val EBONY_LOG: RegistryObject<EbonyLogBlock> = BLOCKS.register("ebony_log") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)) }
    val STRIPPED_EBONY_LOG: RegistryObject<EbonyLogBlock> = BLOCKS.register("stripped_ebony_log") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)) }
    val EBONY_WOOD: RegistryObject<EbonyLogBlock> = BLOCKS.register("ebony_wood") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_WOOD)) }
    val STRIPPED_EBONY_WOOD: RegistryObject<EbonyLogBlock> = BLOCKS.register("stripped_ebony_wood") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_WOOD)) }
    fun clientSetup() {
        RenderTypeLookup.setRenderLayer(EBONY_SAPLING.get(), RenderType.getCutout())
        RenderTypeLookup.setRenderLayer(EBONY_LEAVES.get(), RenderType.getCutout())
    }
}