package ru.rikgela.russianmagic

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.material.Material
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.common.ToolType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.init.RMTileEntityTypes.TILE_ENTITY_TYPES
import ru.rikgela.russianmagic.objects.blocks.RMFurnaceBlock
import ru.rikgela.russianmagic.tileentity.RMFurnaceTileEntity

object BlocksInit {
    public val BLOCKS: DeferredRegister<Block> = DeferredRegister<Block>(ForgeRegistries.BLOCKS, MOD_ID)
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

    val RM_FURNACE_BLOCK: RegistryObject<Block> = BLOCKS.register<Block>("rm_furnace") {
        RMFurnaceBlock(Block
                .Properties
                .from(Blocks.FURNACE))
    }

    //val RM_FURNACE: RegistryObject<TileEntityType<RMFurnaceTileEntity>> = TILE_ENTITY_TYPES.register("rm_furnace_block",
    //        { TileEntityType.Builder.create({ RMFurnaceTileEntity() }, RM_FURNACE_BLOCK.get()) })

}