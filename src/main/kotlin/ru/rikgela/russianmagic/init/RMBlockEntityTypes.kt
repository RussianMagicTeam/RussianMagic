package ru.rikgela.russianmagic.init

import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity.RMAquamarineFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity.RMDiamondFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity.RMEbonyFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity.RMIsolatedDiamondFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity.RMMarbleFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity.RMRhinestoneFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.blockentities.RMFurnacesBlockEntity.RMWhiteJadeFurnaceBlockEntity

object RMBlockEntityTypes {
    val BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>> = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID)

    val RM_DIAMOND_FURNACE: RegistryObject<BlockEntityType<RMDiamondFurnaceBlockEntity>> = BLOCK_ENTITY_TYPES
            .register("rm_diamond_furnace") {
                BlockEntityType.Builder.of(
                    {blockPos, blockState -> RMDiamondFurnaceBlockEntity(blockPos, blockState)},
                    RMBlocks.RM_DIAMOND_FURNACE_BLOCK.get()
                ).build(null)
            }

    val RM_ISOLATED_DIAMOND_FURNACE: RegistryObject<BlockEntityType<RMIsolatedDiamondFurnaceBlockEntity>> = BLOCK_ENTITY_TYPES
            .register("rm_isolated_diamond_furnace") {
                BlockEntityType.Builder.of(
                    {blockPos, blockState -> RMIsolatedDiamondFurnaceBlockEntity(blockPos, blockState)},
                    RMBlocks.RM_ISOLATED_DIAMOND_FURNACE_BLOCK.get())
                    .build(null)
            }

    val RM_EBONY_FURNACE: RegistryObject<BlockEntityType<RMEbonyFurnaceBlockEntity>> = BLOCK_ENTITY_TYPES
            .register("rm_ebony_furnace") {
                BlockEntityType.Builder.of(
                    {blockPos, blockState -> RMEbonyFurnaceBlockEntity(blockPos, blockState)},
                    RMBlocks.RM_EBONY_FURNACE_BLOCK.get())
                    .build(null)
            }

    val RM_MARBLE_FURNACE: RegistryObject<BlockEntityType<RMMarbleFurnaceBlockEntity>> = BLOCK_ENTITY_TYPES
            .register("rm_marble_furnace") {
                BlockEntityType.Builder.of(
                    {blockPos, blockState -> RMMarbleFurnaceBlockEntity(blockPos, blockState)},
                    RMBlocks.RM_MARBLE_FURNACE_BLOCK.get())
                    .build(null)
            }

    val RM_WHITE_JADE_FURNACE: RegistryObject<BlockEntityType<RMWhiteJadeFurnaceBlockEntity>> = BLOCK_ENTITY_TYPES
            .register("rm_white_jade_furnace") {
                BlockEntityType.Builder.of(
                    {blockPos, blockState -> RMWhiteJadeFurnaceBlockEntity(blockPos, blockState)},
                    RMBlocks.RM_WHITE_JADE_FURNACE_BLOCK.get())
                    .build(null)
            }

    val RM_RHINESTONE_FURNACE: RegistryObject<BlockEntityType<RMRhinestoneFurnaceBlockEntity>> = BLOCK_ENTITY_TYPES
            .register("rm_rhinestone_furnace") {
                BlockEntityType.Builder.of(
                    {blockPos, blockState -> RMRhinestoneFurnaceBlockEntity(blockPos, blockState)},
                    RMBlocks.RM_RHINESTONE_FURNACE_BLOCK.get())
                    .build(null)
            }

    val RM_AQUAMARINE_FURNACE: RegistryObject<BlockEntityType<RMAquamarineFurnaceBlockEntity>> = BLOCK_ENTITY_TYPES
            .register("rm_aquamarine_furnace") {
                BlockEntityType.Builder.of(
                    {blockPos, blockState -> RMAquamarineFurnaceBlockEntity(blockPos, blockState)},
                    RMBlocks.RM_AQUAMARINE_FURNACE_BLOCK.get())
                    .build(null)
            }

//    @JvmField
//    val RM_BASIC_MAGIC_SOURCE: RegistryObject<BlockEntityType<RMMagicSourcesTileEntity.RMBasicMagicSourceTileEntity>> = BLOCK_ENTITY_TYPES
//            .register("rm_basic_magic_source") {
//                BlockEntityType.Builder
//                        .create({ RMMagicSourcesTileEntity.RMBasicMagicSourceTileEntity() }, RMBlocks.RM_BASIC_MAGIC_SOURCE_BLOCK.get())
//                        .build(null)
//            }
}