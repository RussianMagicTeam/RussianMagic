package ru.rikgela.russianmagic.oregenerator


import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.placement.CountRangeConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.init.RMBlocks


object OreGeneration {
    fun setupOreGeneration() {
            for (biome in ForgeRegistries.BIOMES) {
                val naturalStone = OreFeatureConfig.FillerBlockType.NATURAL_STONE
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.AQUAMARINE_BLOCK_ORE.get().defaultState, 10))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.RHINESTONE_BLOCK_ORE.get().defaultState, 10))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.MARBLE_BLOCK.get().defaultState, 10))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.WHITE_JADE_BLOCK.get().defaultState, 10))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))
        }
    }
}