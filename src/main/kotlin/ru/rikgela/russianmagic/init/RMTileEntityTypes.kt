package ru.rikgela.russianmagic.init

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.tileentity.RMFurnaceTileEntity
import java.util.function.Supplier

object RMTileEntityTypes {
    val TILE_ENTITY_TYPES = DeferredRegister(
            ForgeRegistries.TILE_ENTITIES, MOD_ID)

    //public static final RegistryObject<TileEntityType<ExampleChestTileEntity>> EXAMPLE_CHEST = TILE_ENTITY_TYPES
    //        .register("example_chest", () -> TileEntityType.Builder
    //                .create(ExampleChestTileEntity::new, BlockInit.EXAMPLE_CHEST.get()).build(null));
    @JvmField
    val RM_FURNACE = TILE_ENTITY_TYPES
            .register("rm_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnaceTileEntity() }, BlocksInit.RM_FURNACE_BLOCK.get()).build(null)
            }
}