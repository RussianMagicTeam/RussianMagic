/*package ru.rikgela.russianmagic.tmp;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.rikgela.russianmagic.init.BlocksInit;
import ru.rikgela.russianmagic.tileentity.RMFurnaceTileEntity;

import static ru.rikgela.russianmagic.RussianMagicKt.MOD_ID;

public class RMTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
            ForgeRegistries.TILE_ENTITIES, MOD_ID);

    //public static final RegistryObject<TileEntityType<ExampleChestTileEntity>> EXAMPLE_CHEST = TILE_ENTITY_TYPES
    //        .register("example_chest", () -> TileEntityType.Builder
    //                .create(ExampleChestTileEntity::new, BlockInit.EXAMPLE_CHEST.get()).build(null));

    public static final RegistryObject<TileEntityType<RMFurnaceTileEntity>> RM_FURNACE = TILE_ENTITY_TYPES
            .register("example_furnace", () -> TileEntityType.Builder
                    .create(RMFurnaceTileEntity::new, BlocksInit.RM_FURNACE_BLOCK.get()).build(null));
}*/