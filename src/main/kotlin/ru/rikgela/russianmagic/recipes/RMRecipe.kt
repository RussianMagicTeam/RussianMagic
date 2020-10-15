package ru.rikgela.russianmagic.recipes

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipeSerializer
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.items.wrapper.RecipeWrapper
import ru.rikgela.russianmagic.init.RecipeSerializerInit

class RMRecipe(private val id: ResourceLocation, override val input: Ingredient, private val output: ItemStack) : IRMRecipe {
    override fun matches(inv: RecipeWrapper, worldIn: World): Boolean {
        return if (input.test(inv.getStackInSlot(0))) {
            true
        } else false
    }

    override fun getCraftingResult(inv: RecipeWrapper): ItemStack {
        return output
    }

    override fun getRecipeOutput(): ItemStack {
        return output
    }

    override fun getId(): ResourceLocation {
        return id
    }

    override fun getSerializer(): IRecipeSerializer<*> {
        return RecipeSerializerInit.EXAMPLE_SERIALIZER.get()
    }

    override fun getIngredients(): NonNullList<Ingredient> {
        return NonNullList.from(null, input)
    }

}