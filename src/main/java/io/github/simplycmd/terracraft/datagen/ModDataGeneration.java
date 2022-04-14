package io.github.simplycmd.terracraft.datagen;

import com.simplycmd.featherlib.FeatherLib;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class ModDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(new FabricModelProvider(fabricDataGenerator) {
            @Override
            public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

            }

            @Override
            public void generateItemModels(ItemModelGenerator itemModelGenerator) {

            }
        });

        fabricDataGenerator.addProvider(new FabricRecipeProvider(fabricDataGenerator) {
            @Override
            protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
                offerAxeRecipe(exporter, ItemRegistry.cactus_axe.getItem(), Items.CACTUS);
                offerPickaxeRecipe(exporter, ItemRegistry.cactus_pickaxe.getItem(), Items.CACTUS);
                offerShovelRecipe(exporter, ItemRegistry.cactus_shovel.getItem(), Items.CACTUS);
                offerSwordRecipe(exporter, ItemRegistry.cactus_sword.getItem(), Items.CACTUS);
                offerHoeRecipe(exporter, ItemRegistry.cactus_hoe.getItem(), Items.CACTUS);
            }

            public void offerAxeRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
                ShapedRecipeJsonBuilder.create(output).input('#', Items.STICK).input('X', input).pattern("XX").pattern("X#").pattern(" #").criterion("has_" + Registry.ITEM.getId(input.asItem()).getPath(), conditionsFromItem(input)).offerTo(exporter);
            }

            public void offerPickaxeRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
                ShapedRecipeJsonBuilder.create(output).input('#', Items.STICK).input('X', input).pattern("XXX").pattern(" # ").pattern(" # ").criterion("has_" + Registry.ITEM.getId(input.asItem()).getPath(), conditionsFromItem(input)).offerTo(exporter);
            }

            public void offerShovelRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
                ShapedRecipeJsonBuilder.create(output).input('#', Items.STICK).input('X', input).pattern("X").pattern("#").pattern("#").criterion("has_" + Registry.ITEM.getId(input.asItem()).getPath(), conditionsFromItem(input)).offerTo(exporter);
            }

            public void offerSwordRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
                ShapedRecipeJsonBuilder.create(output).input('#', Items.STICK).input('X', input).pattern("X").pattern("X").pattern("#").criterion("has_" + Registry.ITEM.getId(input.asItem()).getPath(), conditionsFromItem(input)).offerTo(exporter);
            }

            public void offerHoeRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
                ShapedRecipeJsonBuilder.create(output).input('#', Items.STICK).input('X', input).pattern("XX").pattern("# ").pattern("# ").criterion("has_" + Registry.ITEM.getId(input.asItem()).getPath(), conditionsFromItem(input)).offerTo(exporter);
            }
        });
    }
}
