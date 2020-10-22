package ru.rikgela.russianmagic.init

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.tileentity.RMFurnaceTileEntity
import java.util.function.Supplier

object RMTileEntityTypes {
    val TILE_ENTITY_TYPES: DeferredRegister<TileEntityType<*>> = DeferredRegister(
            ForgeRegistries.TILE_ENTITIES, MOD_ID)

    @JvmField
    val RM_FURNACE: RegistryObject<TileEntityType<RMFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMFurnaceTileEntity() }, RMBlocks.RM_FURNACE_BLOCK.get())
                        .build(null)
            }
}