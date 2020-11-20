package ru.rikgela.russianmagic.init

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.tileentity.RMFurnacesTileEntity
import ru.rikgela.russianmagic.util.RMMekanism
import java.util.function.Supplier

object RMTileEntityTypes {
    val TILE_ENTITY_TYPES: DeferredRegister<TileEntityType<*>> = DeferredRegister(
            ForgeRegistries.TILE_ENTITIES, MOD_ID)

    @JvmField
    val RM_GOLD_FURNACE: RegistryObject<TileEntityType<RMFurnacesTileEntity.RMGoldFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_gold_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnacesTileEntity.RMGoldFurnaceTileEntity(RMMekanism(1, "rm_gold_furnace", arrayOf("smelting"))) }
                                , RMBlocks.RM_GOLD_FURNACE_BLOCK.get())
                        .build(null)
            }

    @JvmField
    val RM_ISOLATED_GOLD_FURNACE: RegistryObject<TileEntityType<RMFurnacesTileEntity.RMIsolatedGoldFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_isolated_gold_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnacesTileEntity.RMIsolatedGoldFurnaceTileEntity(RMMekanism(1, "rm_isolated_gold_furnace", arrayOf("smelting"))) }
                                , RMBlocks.RM_ISOLATED_GOLD_FURNACE_BLOCK.get())
                        .build(null)
            }

    @JvmField
    val RM_EBONY_FURNACE: RegistryObject<TileEntityType<RMFurnacesTileEntity.RMEbonyFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_ebony_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnacesTileEntity.RMEbonyFurnaceTileEntity(RMMekanism(1, "rm_ebony_furnace", arrayOf("smelting"))) }
                                , RMBlocks.RM_EBONY_FURNACE_BLOCK.get())
                        .build(null)
            }

    @JvmField
    val RM_MARBLE_FURNACE: RegistryObject<TileEntityType<RMFurnacesTileEntity.RMMarbleFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_marble_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnacesTileEntity.RMMarbleFurnaceTileEntity(RMMekanism(2, "rm_marble_furnace", arrayOf("smelting"))) }
                                , RMBlocks.RM_MARBLE_FURNACE_BLOCK.get()
                        )
                        .build(null)
            }

    @JvmField
    val RM_WHITE_JADE_FURNACE: RegistryObject<TileEntityType<RMFurnacesTileEntity.RMWhiteJadeFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_white_jade_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnacesTileEntity.RMWhiteJadeFurnaceTileEntity(RMMekanism(3, "rm_white_jade_furnace", arrayOf("smelting"))) }
                                , RMBlocks.RM_WHITE_JADE_FURNACE_BLOCK.get())
                        .build(null)
            }

    @JvmField
    val RM_RHINESTONE_FURNACE: RegistryObject<TileEntityType<RMFurnacesTileEntity.RMRhinestoneFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_rhinestone_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnacesTileEntity.RMRhinestoneFurnaceTileEntity(RMMekanism(4, "rm_rhinestone_furnace", arrayOf("smelting"))) }
                                , RMBlocks.RM_RHINESTONE_FURNACE_BLOCK.get())
                        .build(null)
            }

    @JvmField
    val RM_AQUAMARINE_FURNACE: RegistryObject<TileEntityType<RMFurnacesTileEntity.RMAquamarineFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_aquamarine_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnacesTileEntity.RMAquamarineFurnaceTileEntity(RMMekanism(5, "rm_aquamarine_furnace", arrayOf("smelting"))) }
                                , RMBlocks.RM_AQUAMARINE_FURNACE_BLOCK.get())
                        .build(null)
            }
}