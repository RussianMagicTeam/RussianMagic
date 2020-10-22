package ru.rikgela.russianmagic.blocks

import net.minecraft.block.trees.Tree
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer
import net.minecraftforge.common.IPlantable
import ru.rikgela.russianmagic.init.RMBlocks.EBONY_LEAVES
import ru.rikgela.russianmagic.init.RMBlocks.EBONY_LOG
import ru.rikgela.russianmagic.init.RMBlocks.EBONY_SAPLING
import java.util.*


class EbonyTree : Tree() {
    override fun getTreeFeature(randIn: Random?, b: Boolean): ConfiguredFeature<TreeFeatureConfig, *> {
        return Feature.NORMAL_TREE.withConfiguration(TREE_CONFIG)
    }

    companion object {
        val TREE_CONFIG: TreeFeatureConfig =
                TreeFeatureConfig.Builder(
                        SimpleBlockStateProvider(EBONY_LOG.get().defaultState),
                        SimpleBlockStateProvider(EBONY_LEAVES.get().defaultState),
                        BlobFoliagePlacer(2, 0))
                        .baseHeight(4)
                        .heightRandA(2)
                        .foliageHeight(3)
                        .ignoreVines()
                        .setSapling(EBONY_SAPLING.get() as IPlantable).build()
    }
}

