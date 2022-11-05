package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.world.item.crafting.RecipeType

data class RMMekanism(
        val tier: Int,
        val supportSlots: Int,
        val name: String,
        val recipe_types: Set<RecipeType<*>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as RMMekanism

        if (tier != other.tier) return false
        if (supportSlots != other.supportSlots) return false
        if (name != other.name) return false
        if (recipe_types == other.recipe_types) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tier
        result = 31 * result + supportSlots
        result = 31 * result + name.hashCode()
        result = 31 * result + recipe_types.hashCode()
        return result
    }
}