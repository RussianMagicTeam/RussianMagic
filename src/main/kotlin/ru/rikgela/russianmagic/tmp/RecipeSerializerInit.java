/*package ru.rikgela.russianmagic.tmp;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.rikgela.russianmagic.recipes.IRMRecipe;
import ru.rikgela.russianmagic.recipes.RMRecipe;
import ru.rikgela.russianmagic.recipes.RecipeSerializer;

import static ru.rikgela.russianmagic.RussianMagicKt.MOD_ID;

public class RecipeSerializerInit {

    public static final IRecipeSerializer<RMRecipe> EXAMPLE_RECIPE_SERIALIZER = new RecipeSerializer();
    public static final IRecipeType<IRMRecipe> EXAMPLE_TYPE = registerType(IRMRecipe.RECIPE_TYPE_ID);

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(
            ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> EXAMPLE_SERIALIZER = RECIPE_SERIALIZERS.register("example",
            () -> EXAMPLE_RECIPE_SERIALIZER);

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }
}

*/