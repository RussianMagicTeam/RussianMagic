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
import ru.rikgela.russianmagic.objects.blocks.*
import ru.rikgela.russianmagic.objects.structures.EbonyTree

object RMBlocks {
    @JvmStatic
    val BLOCKS: DeferredRegister<Block> = DeferredRegister(ForgeRegistries.BLOCKS, MOD_ID)
    val STONE_STICK_BLOCK: RegistryObject<Block> = BLOCKS.register("stone_stick_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val MARBLE_BLOCK: RegistryObject<Block> = BLOCKS.register("marble_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val WHITE_JADE_BLOCK: RegistryObject<Block> = BLOCKS.register("white_jade_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AQUAMARINE_BLOCK: RegistryObject<Block> = BLOCKS.register("aquamarine_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AQUAMARINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("aquamarine_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RHINESTONE_BLOCK: RegistryObject<Block> = BLOCKS.register("rhinestone_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RHINESTONE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("rhinestone_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val JASPER_BLOCK: RegistryObject<Block> = BLOCKS.register("jasper_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val JASPER_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("jasper_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHRYSOLITE_BLOCK: RegistryObject<Block> = BLOCKS.register("chrysolite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHRYSOLITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("chrysolite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val GARNET_BLOCK: RegistryObject<Block> = BLOCKS.register("garnet_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val GARNET_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("garnet_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val TOPAZ_BLOCK: RegistryObject<Block> = BLOCKS.register("topaz_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val TOPAZ_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("topaz_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val PERUNITE_BLOCK: RegistryObject<Block> = BLOCKS.register("perunite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val PERUNITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("perunite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val IOLITE_BLOCK: RegistryObject<Block> = BLOCKS.register("iolite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val IOLITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("iolite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AGATE_BLOCK: RegistryObject<Block> = BLOCKS.register("agate_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AGATE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("agate_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CITRINE_BLOCK: RegistryObject<Block> = BLOCKS.register("citrine_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CITRINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("citrine_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHALCEDONY_BLOCK: RegistryObject<Block> = BLOCKS.register("chalcedony_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHALCEDONY_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("chalcedony_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val BERYL_BLOCK: RegistryObject<Block> = BLOCKS.register("beryl_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val BERYL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("beryl_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val HELIODOR_BLOCK: RegistryObject<Block> = BLOCKS.register("heliodor_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val HELIODOR_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("heliodor_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val SITALL_BLOCK: RegistryObject<Block> = BLOCKS.register("sitall_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val SITALL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("sitall_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AMETHYST_BLOCK: RegistryObject<Block> = BLOCKS.register("amethyst_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AMETHYST_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("amethyst_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CARNELIAN_BLOCK: RegistryObject<Block> = BLOCKS.register("carnelian_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CARNELIAN_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("carnelian_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val TANZANITE_BLOCK: RegistryObject<Block> = BLOCKS.register("tanzanite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val TANZANITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("tanzanite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AMETRINE_BLOCK: RegistryObject<Block> = BLOCKS.register("ametrine_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AMETRINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("ametrine_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHAROITE_BLOCK: RegistryObject<Block> = BLOCKS.register("charoite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CHAROITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("charoite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CORAL_BLOCK: RegistryObject<Block> = BLOCKS.register("coral_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val CORAL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("coral_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RUBY_BLOCK: RegistryObject<Block> = BLOCKS.register("ruby_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RUBY_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("ruby_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val ONYX_BLOCK: RegistryObject<Block> = BLOCKS.register("onyx_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val ONYX_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("onyx_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val SAPPHIRE_BLOCK: RegistryObject<Block> = BLOCKS.register("sapphire_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val SAPPHIRE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("sapphire_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val OPAL_BLOCK: RegistryObject<Block> = BLOCKS.register("opal_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val OPAL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("opal_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val KUNZITE_BLOCK: RegistryObject<Block> = BLOCKS.register("kunzite_block") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val KUNZITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("kunzite_block_ore") {
        Block(Block
                .Properties
                .create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }

    val RM_DIAMOND_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMDiamondFurnaceBlock> = BLOCKS
            .register("rm_diamond_furnace") {
                RMFurnacesBlock.RMDiamondFurnaceBlock(Block
                        .Properties
                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_DIAMOND_FURNACE)
            }

    val RM_ISOLATED_DIAMOND_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMIsolatedDiamondFurnaceBlock> = BLOCKS
            .register("rm_isolated_diamond_furnace") {
                RMFurnacesBlock.RMIsolatedDiamondFurnaceBlock(Block
                        .Properties
                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_ISOLATED_DIAMOND_FURNACE)
            }

    val RM_EBONY_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMEbonyFurnaceBlock> = BLOCKS
            .register("rm_ebony_furnace") {
                RMFurnacesBlock.RMEbonyFurnaceBlock(Block
                        .Properties
                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_EBONY_FURNACE)
            }

    val RM_MARBLE_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMMarbleFurnaceBlock> = BLOCKS
            .register("rm_marble_furnace") {
                RMFurnacesBlock.RMMarbleFurnaceBlock(Block
                        .Properties
                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_MARBLE_FURNACE)
            }

    val RM_WHITE_JADE_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMWhiteJadeFurnaceBlock> = BLOCKS
            .register("rm_white_jade_furnace") {
                RMFurnacesBlock.RMWhiteJadeFurnaceBlock(Block
                        .Properties
                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_WHITE_JADE_FURNACE)
            }

    val RM_RHINESTONE_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMRhinestoneFurnaceBlock> = BLOCKS
            .register("rm_rhinestone_furnace") {
                RMFurnacesBlock.RMRhinestoneFurnaceBlock(Block
                        .Properties
                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_RHINESTONE_FURNACE)
            }

    val RM_AQUAMARINE_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMAquamarineFurnaceBlock> = BLOCKS
            .register("rm_aquamarine_furnace") {
                RMFurnacesBlock.RMAquamarineFurnaceBlock(Block
                        .Properties
                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_AQUAMARINE_FURNACE)
            }
    val RM_BASIC_MAGIC_SOURCE_BLOCK: RegistryObject<RMMagicSourceBlock.RMBasicMagicSource> = BLOCKS
            .register("rm_basic_magic_source") {
                RMMagicSourceBlock.RMBasicMagicSource(Block
                        .Properties
                        .create(Material.IRON)
                        .hardnessAndResistance(3.0f, 3.0f)
                        .harvestTool(ToolType.PICKAXE), RMTileEntityTypes.RM_BASIC_MAGIC_SOURCE)
            }

    val EBONY_PLANKS: RegistryObject<Block> = BLOCKS.register("ebony_planks") { Block(Block.Properties.from(Blocks.OAK_PLANKS)) }
    val EBONY_LEAVES: RegistryObject<RMLeavesBlock> = BLOCKS.register("ebony_leaves") { RMLeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)) }
    val EBONY_SAPLING: RegistryObject<EbonySaplingBlock> = BLOCKS.register("ebony_sapling") { EbonySaplingBlock({ EbonyTree() }, Block.Properties.from(Blocks.OAK_SAPLING)) }
    val POTTED_EBONY_SAPLING: RegistryObject<FlowerPotBlock> = BLOCKS.register("potted_ebony_sapling") { FlowerPotBlock(null, { EBONY_SAPLING.get() }, Block.Properties.create(Material.MISCELLANEOUS).notSolid()) }
    val EBONY_LOG: RegistryObject<EbonyLogBlock> = BLOCKS.register("ebony_log") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)) }
    val STRIPPED_EBONY_LOG: RegistryObject<EbonyLogBlock> = BLOCKS.register("stripped_ebony_log") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)) }
    val EBONY_WOOD: RegistryObject<EbonyLogBlock> = BLOCKS.register("ebony_wood") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_WOOD)) }
    val STRIPPED_EBONY_WOOD: RegistryObject<EbonyLogBlock> = BLOCKS.register("stripped_ebony_wood") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_WOOD)) }
    fun clientSetup() {
        RenderTypeLookup.setRenderLayer(EBONY_SAPLING.get(), RenderType.getCutout())
        RenderTypeLookup.setRenderLayer(EBONY_LEAVES.get(), RenderType.getCutout())
        RenderTypeLookup.setRenderLayer(RHINESTONE_BLOCK.get(), RenderType.getTranslucent())
        RenderTypeLookup.setRenderLayer(RM_BASIC_MAGIC_SOURCE_BLOCK.get(), RenderType.getTranslucent())
        RenderTypeLookup.setRenderLayer(RM_RHINESTONE_FURNACE_BLOCK.get(), RenderType.getTranslucent())
    }
}