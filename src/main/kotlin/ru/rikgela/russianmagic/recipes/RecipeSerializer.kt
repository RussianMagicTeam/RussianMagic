package ru.rikgela.russianmagic.recipes

import com.google.gson.JsonObject
import net.minecraft.item.crafting.IRecipeSerializer
import net.minecraft.item.crafting.Ingredient
import net.minecraft.network.PacketBuffer
import net.minecraft.util.JSONUtils
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.crafting.CraftingHelper
import net.minecraftforge.registries.ForgeRegistryEntry

class RecipeSerializer : ForgeRegistryEntry<IRecipeSerializer<*>>(), IRecipeSerializer<RMRecipe> {
    override fun read(recipeId: ResourceLocation, json: JsonObject): RMRecipe {
        val output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true)
        val input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"))
        return RMRecipe(recipeId, input, output)
    }

    override fun read(recipeId: ResourceLocation, buffer: PacketBuffer): RMRecipe {
        val output = buffer.readItemStack()
        val input = Ingredient.read(buffer)
        return RMRecipe(recipeId, input, output)
    }

    override fun write(buffer: PacketBuffer, recipe: RMRecipe) {
        val input = recipe.ingredients[0]
        input.write(buffer)
        buffer.writeItemStack(recipe.recipeOutput, false)
    }
}