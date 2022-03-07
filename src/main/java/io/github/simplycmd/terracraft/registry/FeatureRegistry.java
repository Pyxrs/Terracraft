package io.github.simplycmd.terracraft.registry;

import java.util.List;

import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.blocks.BlueBerryBushBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeatures;

public class FeatureRegistry {
    //VegetationConfiguredFeatures
    public static final ConfiguredFeature<?, ?> PATCH_BLUE_BERRY_BUSH = ConfiguredFeatures.register("patch_blue_berry_bush", Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of((BlockRegistry.blue_berry_bush.getBlock().getDefaultState().with(BlueBerryBushBlock.AGE, 3)))), List.of(Blocks.GRASS_BLOCK)))));
    //public static ConfiguredFeature<?, ?> DAYBLOOM_FEATURE = ConfiguredFeatures.register("daybloom", Feature.FLOWER.configure(new RandomPatchFeatureConfig(6, 5, 2, () -> Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of(BlockusBlocks.RAINBOW_ROSE))).withBlockPredicateFilter(BlockPredicate.matchingBlocks(List.of(Blocks.AIR, Blocks.GRASS, Blocks.TALL_GRASS, Blocks.FERN, Blocks.LARGE_FERN), BlockPos.ORIGIN)))));

    RegistryKey<PlacedFeature> blueBerryBush = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Main.MOD_ID, "blue_berry_bush"));

    Registry.register(BuiltinRegistries.PLACED_FEATURE, blueBerryBush.getValue(), BlockusVegetationFeatures.RAINBOW_ROSE.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, rainbowRose);

}
