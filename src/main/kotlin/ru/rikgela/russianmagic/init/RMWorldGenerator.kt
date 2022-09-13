package ru.rikgela.russianmagic.oregenerator


import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.placement.CountRangeConfig
import net.minecraft.world.gen.placement.FrequencyConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.init.RMBlocks
import ru.rikgela.russianmagic.objects.structures.EbonyTree


object OreGeneration {
        fun setupOreGeneration() {
            for (biome in ForgeRegistries.BIOMES) {
                val naturalStone = OreFeatureConfig.FillerBlockType.NATURAL_STONE
                Biomes.OCEAN.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.AQUAMARINE_BLOCK_ORE.get().defaultState, 50))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 10, 256))))

                Biomes.RIVER.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.AQUAMARINE_BLOCK_ORE.get().defaultState, 50))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 10, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.RHINESTONE_BLOCK_ORE.get().defaultState, 50))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 10, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.MARBLE_BLOCK.get().defaultState, 50))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 10, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (naturalStone, RMBlocks.WHITE_JADE_BLOCK.get().defaultState, 50))
                        .withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 10, 256))))
            }
        Biomes.JUNGLE.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.NORMAL_TREE.withConfiguration(EbonyTree.TREE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(FrequencyConfig(1))))
        Biomes.JUNGLE_EDGE.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.NORMAL_TREE.withConfiguration(EbonyTree.TREE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(FrequencyConfig(1))))
        Biomes.JUNGLE_HILLS.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.NORMAL_TREE.withConfiguration(EbonyTree.TREE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(FrequencyConfig(1))))
    }
}