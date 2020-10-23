package ru.rikgela.russianmagic.init

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.tileentity.RMEbonyFurnaceTileEntity
import ru.rikgela.russianmagic.tileentity.RMMarbleFurnaceTileEntity
import java.util.function.Supplier

object RMTileEntityTypes {
    val TILE_ENTITY_TYPES: DeferredRegister<TileEntityType<*>> = DeferredRegister(
            ForgeRegistries.TILE_ENTITIES, MOD_ID)

    @JvmField
    val RM_MARBLE_FURNACE: RegistryObject<TileEntityType<RMMarbleFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_marble_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMMarbleFurnaceTileEntity() }, RMBlocks.RM_MARBLE_FURNACE_BLOCK.get())
                        .build(null)
            }
    @JvmField
    val RM_EBONY_FURNACE: RegistryObject<TileEntityType<RMEbonyFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_ebony_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMEbonyFurnaceTileEntity() }, RMBlocks.RM_EBONY_FURNACE_BLOCK.get())
                        .build(null)
            }
}