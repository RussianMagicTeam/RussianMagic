package ru.rikgela.russianmagic.init


import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.blocks.EbonyRotatedPillarBlock
import ru.rikgela.russianmagic.objects.blocks.EbonyTreeGrower
import ru.rikgela.russianmagic.objects.blocks.RMLeavesBlock


object RMBlocks {
    val BLOCKS: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID)
    val STONE_STICK_BLOCK: RegistryObject<Block> = BLOCKS.register(
        "stone_stick_block") {
        Block(
            BlockBehaviour.Properties.of(
                Material.STONE
            ).requiresCorrectToolForDrops().strength(3.0f, 3.0f)
        )
    }
    val MARBLE_BLOCK: RegistryObject<Block> = BLOCKS.register("marble_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val WHITE_JADE_BLOCK: RegistryObject<Block> = BLOCKS.register("white_jade_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val AQUAMARINE_BLOCK: RegistryObject<GlassBlock> = BLOCKS.register("aquamarine_block") {
        GlassBlock(
            BlockBehaviour.Properties.of(
                Material.GLASS
            ).noOcclusion()
        )
    }

    val AQUAMARINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("aquamarine_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val RHINESTONE_BLOCK: RegistryObject<Block> = BLOCKS.register("rhinestone_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val RHINESTONE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("rhinestone_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val JASPER_BLOCK: RegistryObject<Block> = BLOCKS.register("jasper_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val JASPER_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("jasper_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CHRYSOLITE_BLOCK: RegistryObject<Block> = BLOCKS.register("chrysolite_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CHRYSOLITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("chrysolite_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val GARNET_BLOCK: RegistryObject<Block> = BLOCKS.register("garnet_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val GARNET_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("garnet_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val TOPAZ_BLOCK: RegistryObject<Block> = BLOCKS.register("topaz_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val TOPAZ_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("topaz_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val PERUNITE_BLOCK: RegistryObject<Block> = BLOCKS.register("perunite_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val PERUNITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("perunite_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val IOLITE_BLOCK: RegistryObject<Block> = BLOCKS.register("iolite_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val IOLITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("iolite_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val AGATE_BLOCK: RegistryObject<Block> = BLOCKS.register("agate_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val AGATE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("agate_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CITRINE_BLOCK: RegistryObject<Block> = BLOCKS.register("citrine_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CITRINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("citrine_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CHALCEDONY_BLOCK: RegistryObject<Block> = BLOCKS.register("chalcedony_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CHALCEDONY_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("chalcedony_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val BERYL_BLOCK: RegistryObject<Block> = BLOCKS.register("beryl_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val BERYL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("beryl_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val HELIODOR_BLOCK: RegistryObject<Block> = BLOCKS.register("heliodor_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val HELIODOR_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("heliodor_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val SITALL_BLOCK: RegistryObject<Block> = BLOCKS.register("sitall_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val SITALL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("sitall_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val AMETHYST_BLOCK: RegistryObject<Block> = BLOCKS.register("amethyst_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val AMETHYST_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("amethyst_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CARNELIAN_BLOCK: RegistryObject<Block> = BLOCKS.register("carnelian_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CARNELIAN_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("carnelian_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val TANZANITE_BLOCK: RegistryObject<Block> = BLOCKS.register("tanzanite_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val TANZANITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("tanzanite_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val AMETRINE_BLOCK: RegistryObject<Block> = BLOCKS.register("ametrine_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val AMETRINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("ametrine_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CHAROITE_BLOCK: RegistryObject<Block> = BLOCKS.register("charoite_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CHAROITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("charoite_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CORAL_BLOCK: RegistryObject<Block> = BLOCKS.register("coral_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val CORAL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("coral_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val RUBY_BLOCK: RegistryObject<Block> = BLOCKS.register("ruby_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val RUBY_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("ruby_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val ONYX_BLOCK: RegistryObject<Block> = BLOCKS.register("onyx_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val ONYX_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("onyx_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val SAPPHIRE_BLOCK: RegistryObject<Block> = BLOCKS.register("sapphire_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val SAPPHIRE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("sapphire_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val OPAL_BLOCK: RegistryObject<Block> = BLOCKS.register("opal_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val OPAL_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("opal_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val KUNZITE_BLOCK: RegistryObject<Block> = BLOCKS.register("kunzite_block") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0f, 3.0f)
        )
    }
    val KUNZITE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register("kunzite_block_ore") {
        Block(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(1.0f, 3.0f)
        )
    }

//    val RM_DIAMOND_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMDiamondFurnaceBlock> = BLOCKS
//            .register("rm_diamond_furnace") {
//                RMFurnacesBlock.RMDiamondFurnaceBlock(Block
//                        .Properties
//                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_DIAMOND_FURNACE)
//            }
//
//    val RM_ISOLATED_DIAMOND_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMIsolatedDiamondFurnaceBlock> = BLOCKS
//            .register("rm_isolated_diamond_furnace") {
//                RMFurnacesBlock.RMIsolatedDiamondFurnaceBlock(Block
//                        .Properties
//                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_ISOLATED_DIAMOND_FURNACE)
//            }
//
//    val RM_EBONY_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMEbonyFurnaceBlock> = BLOCKS
//            .register("rm_ebony_furnace") {
//                RMFurnacesBlock.RMEbonyFurnaceBlock(Block
//                        .Properties
//                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_EBONY_FURNACE)
//            }
//
//    val RM_MARBLE_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMMarbleFurnaceBlock> = BLOCKS
//            .register("rm_marble_furnace") {
//                RMFurnacesBlock.RMMarbleFurnaceBlock(Block
//                        .Properties
//                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_MARBLE_FURNACE)
//            }
//
//    val RM_WHITE_JADE_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMWhiteJadeFurnaceBlock> = BLOCKS
//            .register("rm_white_jade_furnace") {
//                RMFurnacesBlock.RMWhiteJadeFurnaceBlock(Block
//                        .Properties
//                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_WHITE_JADE_FURNACE)
//            }
//
//    val RM_RHINESTONE_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMRhinestoneFurnaceBlock> = BLOCKS
//            .register("rm_rhinestone_furnace") {
//                RMFurnacesBlock.RMRhinestoneFurnaceBlock(Block
//                        .Properties
//                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_RHINESTONE_FURNACE)
//            }
//
//    val RM_AQUAMARINE_FURNACE_BLOCK: RegistryObject<RMFurnacesBlock.RMAquamarineFurnaceBlock> = BLOCKS
//            .register("rm_aquamarine_furnace") {
//                RMFurnacesBlock.RMAquamarineFurnaceBlock(Block
//                        .Properties
//                        .from(Blocks.FURNACE), RMTileEntityTypes.RM_AQUAMARINE_FURNACE)
//            }
//    val RM_BASIC_MAGIC_SOURCE_BLOCK: RegistryObject<RMMagicSourceBlock.RMBasicMagicSource> = BLOCKS
//            .register("rm_basic_magic_source") {
//                RMMagicSourceBlock.RMBasicMagicSource(Block
//                        .Properties
//                        .create(Material.STONE)
//                        .hardnessAndResistance(3.0f, 3.0f)
//                        .harvestTool(ToolType.PICKAXE), RMTileEntityTypes.RM_BASIC_MAGIC_SOURCE)
//            }

    val EBONY_PLANKS: RegistryObject<Block> = BLOCKS.register("ebony_planks") {
        Block(
            BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)
        )
    }
//    val EBONY_LEAVES: RegistryObject<RMLeavesBlock> = BLOCKS.register("ebony_leaves") {
//        Blocks.OAK_LOG
//        RMLeavesBlock(BlockBehaviour.Properties.of(Blocks.OAK_LEAVES))
//    }

    val EBONY_LEAVES: RegistryObject<RMLeavesBlock> = BLOCKS.register("ebony_leaves") {
        RMLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES))
    }

//    val POTTED_EBONY_SAPLING: RegistryObject<FlowerPotBlock> = BLOCKS.register("potted_ebony_sapling") {
//        FlowerPotBlock(null, { EBONY_SAPLING.get() }, Block.Properties.create(Material.MISCELLANEOUS).notSolid())
//    }
    val EBONY_SAPLING: RegistryObject<Block> = BLOCKS.register("ebony_sapling") {
        SaplingBlock(
            EbonyTreeGrower(),
            BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
        )
    }
    val EBONY_LOG: RegistryObject<EbonyRotatedPillarBlock> = BLOCKS.register("ebony_log") {
        EbonyRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    }
    val STRIPPED_EBONY_LOG: RegistryObject<EbonyRotatedPillarBlock> = BLOCKS.register("stripped_ebony_log") {
        EbonyRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG))
    }
    val EBONY_WOOD: RegistryObject<EbonyRotatedPillarBlock> = BLOCKS.register("ebony_wood") {
        EbonyRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD))
    }
    val STRIPPED_EBONY_WOOD: RegistryObject<EbonyRotatedPillarBlock> = BLOCKS.register("stripped_ebony_wood") {
        EbonyRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD))
    }
//    fun clientSetup() {
////        RenderTypeLookup.setRenderLayer(EBONY_SAPLING.get(), RenderType.getCutout())
////        RenderTypeLookup.setRenderLayer(EBONY_LEAVES.get(), RenderType.getCutout())
//        RenderTypeLookup.setRenderLayer(RHINESTONE_BLOCK.get(), RenderType.getTranslucent())
////        RenderTypeLookup.setRenderLayer(RM_BASIC_MAGIC_SOURCE_BLOCK.get(), RenderType.getTranslucent())
//    }
}