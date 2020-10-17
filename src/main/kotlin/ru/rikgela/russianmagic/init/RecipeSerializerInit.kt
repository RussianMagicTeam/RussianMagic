package ru.rikgela.russianmagic.init

import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.crafting.IRecipeSerializer
import net.minecraft.item.crafting.IRecipeType
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.recipes.IRMRecipe
import ru.rikgela.russianmagic.recipes.RMRecipe
import ru.rikgela.russianmagic.recipes.RecipeSerializer

object RecipeSerializerInit {
    val EXAMPLE_RECIPE_SERIALIZER: IRecipeSerializer<RMRecipe> = RecipeSerializer()
    @JvmField
    val EXAMPLE_TYPE = registerType<IRecipeType<IRMRecipe>>(IRMRecipe.RECIPE_TYPE_ID)
    val RECIPE_SERIALIZERS = DeferredRegister(
            ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID)
    @JvmField
    val EXAMPLE_SERIALIZER = RECIPE_SERIALIZERS.register<IRecipeSerializer<*>>("example"
    ) { EXAMPLE_RECIPE_SERIALIZER }

    private fun <T : IRecipeType<*>?> registerType(recipeTypeId: ResourceLocation): T {
        return Registry.register(Registry.RECIPE_TYPE, recipeTypeId, RecipeType<IRecipe<*>>()) as T
    }

    private class RecipeType<T : IRecipe<*>?> : IRecipeType<T> {
        override fun toString(): String {
            return Registry.RECIPE_TYPE.getKey(this).toString()
        }
    }
}