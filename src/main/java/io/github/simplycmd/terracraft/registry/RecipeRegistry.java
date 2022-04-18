package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.terracraft.recipes.MoneyConversionRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;

public class RecipeRegistry {
    public static SpecialRecipeSerializer<MoneyConversionRecipe> PLATINUM;
    public static SpecialRecipeSerializer<MoneyConversionRecipe> GOLD;
    public static SpecialRecipeSerializer<MoneyConversionRecipe> SILVER;
    public static void registerRecipeSerializers() {
        //System.out.println(Registry.RECIPE_SERIALIZER.size());
        PLATINUM = MoneyConversionRecipe.register(ItemRegistry.platinum_coin.getItem(), ItemRegistry.gold_coin.getItem());
        GOLD = MoneyConversionRecipe.register(ItemRegistry.gold_coin.getItem(), ItemRegistry.silver_coin.getItem());
        SILVER = MoneyConversionRecipe.register(ItemRegistry.silver_coin.getItem(), ItemRegistry.copper_coin.getItem());
        //System.out.println(Registry.RECIPE_SERIALIZER.size());
        //System.exit(0);
    }
}
