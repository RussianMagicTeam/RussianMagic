package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.core.Holder
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.grower.AbstractTreeGrower
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import ru.rikgela.russianmagic.init.RMConfiguredFeatures


class EbonyTreeGrower : AbstractTreeGrower() {
    override fun getConfiguredFeature(
        pRandom: RandomSource,
        pLargeHive: Boolean
    ): Holder<out ConfiguredFeature<*, *>?> {
        return RMConfiguredFeatures.EBONY_TREE.holder.get()
    }
}