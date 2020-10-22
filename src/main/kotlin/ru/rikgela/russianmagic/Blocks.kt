package ru.rikgela.russianmagic

import net.minecraft.block.Block
import net.minecraftforge.common.ToolType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraft.block.material.Material

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
}