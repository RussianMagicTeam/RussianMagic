package ru.rikgela.russianmagic.blocks

import net.minecraft.block.Block
import net.minecraft.block.Blocks.*
import net.minecraft.block.FlowerPotBlock
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraftforge.common.ToolType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import java.util.function.Supplier


object Blocks {
    val BLOCKS: DeferredRegister<Block> = DeferredRegister<Block>(ForgeRegistries.BLOCKS, MOD_ID)
    val STONE_STICK_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("stone_stick_block") {
        Block(Block
                .Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val MARBLE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("marble_block") {
        Block(Block
                .Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val WHITE_JADE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("white_jade_block") {
        Block(Block
                .Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AQUAMARINE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("aquamarine_block") {
        Block(Block
                .Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val AQUAMARINE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("aquamarine_block_ore") {
        Block(Block
                .Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RHINESTONE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("rhinestone_block") {
        Block(Block
                .Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }
    val RHINESTONE_BLOCK_ORE: RegistryObject<Block> = BLOCKS.register<Block>("rhinestone_block_ore") {
        Block(Block
                .Properties
                .create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .harvestTool(ToolType.PICKAXE))
    }

    val EBONY_PLANKS: RegistryObject<Block> = BLOCKS.register("ebony_planks") { Block(Block.Properties.from(OAK_PLANKS)) }
    val EBONY_LEAVES: RegistryObject<RMLeavesBlock> = BLOCKS.register("ebony_leaves") { RMLeavesBlock(Block.Properties.from(OAK_LEAVES)) }
    val EBONY_SAPLING: RegistryObject<EbonySaplingBlock> = BLOCKS.register("ebony_sapling") { EbonySaplingBlock(Supplier { EbonyTree() }, Block.Properties.from(OAK_SAPLING)) }
    val POTTED_EBONY_SAPLING: RegistryObject<FlowerPotBlock> = BLOCKS.register("potted_ebony_sapling") { FlowerPotBlock(null, Supplier { EBONY_SAPLING.get() }, Block.Properties.create(Material.MISCELLANEOUS).notSolid()) }

    val EBONY_LOG: RegistryObject<EbonyLogBlock> = BLOCKS.register("ebony_log") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(OAK_LOG)) }
    val STRIPPED_EBONY_LOG: RegistryObject<EbonyLogBlock> = BLOCKS.register("stripped_ebony_log") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(OAK_LOG)) }
    val EBONY_WOOD: RegistryObject<EbonyLogBlock> = BLOCKS.register("ebony_wood") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(OAK_WOOD)) }
    val STRIPPED_EBONY_WOOD: RegistryObject<EbonyLogBlock> = BLOCKS.register("stripped_ebony_wood") { EbonyLogBlock(MaterialColor.WOOD, Block.Properties.from(OAK_WOOD)) }
}