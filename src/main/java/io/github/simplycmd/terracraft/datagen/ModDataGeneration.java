package io.github.simplycmd.terracraft.datagen;

import com.simplycmd.featherlib.registry.SimpleBlock;
import com.simplycmd.featherlib.registry.SimpleItem;
import com.simplycmd.featherlib.registry.SimpleRegistry;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.registry.BlockRegistry;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.function.Consumer;

import static io.github.simplycmd.terracraft.registry.ItemRegistry.*;

public class ModDataGeneration extends SimpleRegistry implements DataGeneratorEntrypoint {
    public ModDataGeneration() {
        super(BlockRegistry.getAllBlocks(), ItemRegistry.getAllItems());
        //System.out.println(getAllItems().length);
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        super.onInitializeDataGenerator(fabricDataGenerator);
        fabricDataGenerator.addProvider(new OfferProvider(fabricDataGenerator) {
            @Override
            protected void generateOffers() {
                addSellOffer(sandstorm_in_a_bottle, new Value(0, 3, 0, 0));
                addSellOffer(cloud_in_a_bottle, new Value(0, 1, 0, 0));
                addSellOffer(tsunami_in_a_bottle, new Value(0, 1, 0, 0));
                addBuyOffer(Items.BEDROCK, 214748364700L);
                addBuyOffer(Items.OBSIDIAN, 11500);
                addBuyOffer(sandstorm_in_a_bottle, new Value(76, 10, 29, 20));
            }
        });
//        fabricDataGenerator.addProvider(new FabricModelProvider(fabricDataGenerator) {
//            @Override
//            public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//
//            }
//
//            @Override
//            public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//
//            }
//        });
//
        fabricDataGenerator.addProvider(new FabricRecipeProvider(fabricDataGenerator) {
            @Override
            protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
                try {
                    var field = BlockRegistry.class.getDeclaredField("data");
                    field.setAccessible(true);
                    @SuppressWarnings("unchecked")
                    ArrayList<BlockRegistry.TorchData> torchdata = (ArrayList<BlockRegistry.TorchData>)field.get(null);
                    for (BlockRegistry.TorchData torchData : torchdata) {
                        ShapedRecipeJsonBuilder.create(torchData.torch().getItem(), 4).input('X', Items.COAL).input('#', Items.STICK).input('S', torchData.resource()).pattern("X").pattern("#").pattern("S").criterion("has_" + Registry.ITEM.getId(torchData.torch().getItem().asItem()).getPath(), conditionsFromItem(torchData.torch().getItem())).offerTo(exporter);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
//                offerAxeRecipe(exporter, ItemRegistry.cactus_axe.getItem(), Items.CACTUS);
//                offerPickaxeRecipe(exporter, ItemRegistry.cactus_pickaxe.getItem(), Items.CACTUS);
//                offerShovelRecipe(exporter, ItemRegistry.cactus_shovel.getItem(), Items.CACTUS);
//                offerSwordRecipe(exporter, ItemRegistry.cactus_sword.getItem(), Items.CACTUS);
//                offerHoeRecipe(exporter, ItemRegistry.cactus_hoe.getItem(), Items.CACTUS);
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
