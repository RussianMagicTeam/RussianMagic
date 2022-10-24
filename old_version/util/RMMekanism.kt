package ru.rikgela.russianmagic.util

data class RMMekanism(
        val tier: Int,
        val supportSlots: Int,
        val name: String,
        val recipe_shape: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RMMekanism

        if (tier != other.tier) return false
        if (supportSlots != other.supportSlots) return false
        if (name != other.name) return false
        if (!recipe_shape.contentEquals(other.recipe_shape)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tier
        result = 31 * result + supportSlots
        result = 31 * result + name.hashCode()
        result = 31 * result + recipe_shape.contentHashCode()
        return result
    }
}