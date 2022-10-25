package ru.rikgela.russianmagic.init

import net.minecraft.core.Registry.PLACED_FEATURE_REGISTRY
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import ru.rikgela.russianmagic.MOD_ID
import java.util.List


object RMPlacedFeatures {
    val PLACED_FEATURES: DeferredRegister<PlacedFeature> =
        DeferredRegister.create(PLACED_FEATURE_REGISTRY, MOD_ID)

    val EBONY_TREE_CHECKED: RegistryObject<PlacedFeature> = PLACED_FEATURES.register(
        "ebony_tree_checked"
    ) {
        PlacedFeature(
            RMConfiguredFeatures.EBONY_TREE.holder.get(),
            List.of<PlacementModifier>(PlacementUtils.filteredByBlockSurvival(RMBlocks.EBONY_SAPLING.get()))
        )
    }

    val EBONY_TREE_PLACED: RegistryObject<PlacedFeature> = PLACED_FEATURES.register(
        "ebony_tree_placed"
    ) {
        PlacedFeature(
            RMConfiguredFeatures.EBONY_TREE_SPAWN.holder.get(), VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(3, 0.1f, 2)
            )
        )
    }
}