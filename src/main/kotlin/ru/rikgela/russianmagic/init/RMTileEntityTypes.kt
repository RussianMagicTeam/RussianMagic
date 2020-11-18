package ru.rikgela.russianmagic.init

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.tileentity.RMMarbleFurnaceTileEntity
import ru.rikgela.russianmagic.util.RMMekanism
import java.util.function.Supplier

object RMTileEntityTypes {
    val TILE_ENTITY_TYPES: DeferredRegister<TileEntityType<*>> = DeferredRegister(
            ForgeRegistries.TILE_ENTITIES, MOD_ID)

    @JvmField
    val RM_MARBLE_FURNACE: RegistryObject<TileEntityType<RMMarbleFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_marble_furnace") {
                TileEntityType.Builder
                        .create(Supplier {RMMarbleFurnaceTileEntity(RMMekanism(2, "furnace", arrayOf("smelting"), )) }, RMBlocks.RM_MARBLE_FURNACE_BLOCK.get())
                        .build(null)
            }
    @JvmField
    val RM_EBONY_FURNACE: RegistryObject<TileEntityType<RMMarbleFurnaceTileEntity>> = TILE_ENTITY_TYPES
            .register("rm_ebony_furnace") {
                TileEntityType.Builder
                        .create(Supplier { RMMarbleFurnaceTileEntity(RMMekanism(1, "furnace", arrayOf("smelting"))) }, RMBlocks.RM_EBONY_FURNACE_BLOCK.get())
                        .build(null)
            }
}