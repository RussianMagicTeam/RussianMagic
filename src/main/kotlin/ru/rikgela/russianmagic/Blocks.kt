package ru.rikgela.russianmagic

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.common.ToolType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.objects.blocks.RMFurnaceBlock

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

    val RM_FURNACE_BLOCK: RegistryObject<Block> = BLOCKS.register("rm_furnace_block") {
        RMFurnaceBlock(Block
                .Properties.
                from(Blocks.FURNACE))
    }

    val EXAMPLE_FURNACE: RegistryObject<TileEntityType<RMFurnaceTileENtity>> = TILE_ENTITY_TYPES.register("rm_furnace_block",
            { TileEntityType.Builder.create({ RMFurnaceTileENtity() }, BlockInit.RM_FURNACE_BLOCK.get()) })

}