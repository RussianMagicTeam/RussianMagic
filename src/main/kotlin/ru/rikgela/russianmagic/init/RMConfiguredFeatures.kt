package ru.rikgela.russianmagic.init

import net.minecraft.core.Registry.CONFIGURED_FEATURE_REGISTRY
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import ru.rikgela.russianmagic.MOD_ID
import java.util.List


object RMConfiguredFeatures {
    val CONFIGURED_FEATURES: DeferredRegister<ConfiguredFeature<*, *>> =
        DeferredRegister.create(CONFIGURED_FEATURE_REGISTRY, MOD_ID)
    val EBONY_TREE: RegistryObject<ConfiguredFeature<*, *>> = CONFIGURED_FEATURES.register("ebony_tree") {
        ConfiguredFeature(
            Feature.TREE, TreeConfigurationBuilder(
                BlockStateProvider.simple(RMBlocks.EBONY_LOG.get()),
                StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(RMBlocks.EBONY_LEAVES.get()),
                BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                TwoLayersFeatureSize(1, 0, 2)
            ).build()
        )
    }

    val EBONY_TREE_SPAWN: RegistryObject<ConfiguredFeature<*, *>> = CONFIGURED_FEATURES.register("ebony_tree_spawn") {
        ConfiguredFeature(
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfiguration(
                List.of(
                    WeightedPlacedFeature(
                        RMPlacedFeatures.EBONY_TREE_CHECKED.holder.get(),
                        0.5f
                    )
                ), RMPlacedFeatures.EBONY_TREE_CHECKED.holder.get()
            )
        )
    }

}