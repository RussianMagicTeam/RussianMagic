package ru.rikgela.russianmagic.recipes

import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.crafting.IRecipeType
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry
import net.minecraftforge.items.wrapper.RecipeWrapper
import ru.rikgela.russianmagic.MOD_ID
import javax.annotation.Nonnull

interface IRMRecipe : IRecipe<RecipeWrapper> {
    @Nonnull
    override fun getType(): IRecipeType<*> {
        return Registry.RECIPE_TYPE.getValue(RECIPE_TYPE_ID).get()
    }

    override fun canFit(width: Int, height: Int): Boolean {
        return false
    }

    val input: Ingredient

    companion object {
        @JvmField
        val RECIPE_TYPE_ID = ResourceLocation(MOD_ID, "example")
    }
}