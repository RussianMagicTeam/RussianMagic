package ru.rikgela.russianmagic.oregenerator


import ru.rikgela.russianmagic.Blocks
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.placement.CountRangeConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraftforge.registries.ForgeRegistries


object OreGeneration {
    fun setupOreGeneration() {
            for (biome in ForgeRegistries.BIOMES) {
                val s = OreFeatureConfig.FillerBlockType.NATURAL_STONE
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (s , Blocks.AQUAMARINE_BLOCK_ORE.get().getDefaultState(),10)).
                withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (s , Blocks.RHINESTONE_BLOCK_ORE.get().getDefaultState(), 10)).
                withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                ( s , Blocks.MARBLE_BLOCK.get().getDefaultState(), 10)).
                withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(OreFeatureConfig
                (s, Blocks.WHITE_JADE_BLOCK.get().getDefaultState(), 10)).
                withPlacement(Placement.COUNT_RANGE.configure(CountRangeConfig(20, 0, 100, 256))))
        }
    }
}