package ru.rikgela.russianmagic.oregenerator


import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.placement.CountRangeConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.BlocksInit


object OreGeneration {
    fun setupOreGeneration() {
            for (biome in ForgeRegistries.BIOMES) {
                val naturalStone = OreFeatureConfig.FillerBlockType.NATURAL_STONE
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, BlocksInit.AQUAMARINE_BLOCK_ORE.get().defaultState, 10))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, BlocksInit.RHINESTONE_BLOCK_ORE.get().defaultState, 10))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, BlocksInit.MARBLE_BLOCK.get().defaultState, 10))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, BlocksInit.WHITE_JADE_BLOCK.get().defaultState, 10))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))
        }
    }
}