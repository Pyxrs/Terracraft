package io.github.simplycmd.terracraft.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import java.util.List;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import java.util.function.Predicate;
import com.simplycmd.featherlib.registry.SimpleBlock;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.Tuple;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.gen.feature.PlacedFeatures;

public class FeatureRegistry {
        // Daybloom
        public static ConfiguredFeature<RandomPatchFeatureConfig, ?> daybloomCF;
        public static RegistryKey<PlacedFeature> daybloomPF;

        // Blue Berry Bush
        public static ConfiguredFeature<RandomPatchFeatureConfig, ?> blueBerryBushCF;
        public static RegistryKey<PlacedFeature> blueBerryBushPF;

        public static void register() {
                final Tuple daybloom = registerSimplePlant(BlockRegistry.daybloom, 22, 64, 3, 2, BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST));
                daybloomCF = (ConfiguredFeature<RandomPatchFeatureConfig, ?>)daybloom.get(0);
                daybloomPF = (RegistryKey<PlacedFeature>)daybloom.get(1);
                final Tuple blueBerryBush = registerSimplePlant(BlockRegistry.blue_berry_bush, 32, 64, 1, 2, BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST));
                blueBerryBushCF = (ConfiguredFeature<RandomPatchFeatureConfig, ?>)blueBerryBush.get(0);
                blueBerryBushPF = (RegistryKey<PlacedFeature>)blueBerryBush.get(1);
        }

        public static Tuple registerSimplePlant(SimpleBlock block, int rarity, int tries, int xz_spread, int y_spread, Predicate<BiomeSelectionContext> biomeSelector) {
                final Identifier cf = Main.ID(block.getId().getPath() + "_cf");
                final Identifier pf = Main.ID(block.getId().getPath() + "_pf");
                ConfiguredFeature<?, ?> plantCF = new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchFeatureConfig(tries, xz_spread, y_spread, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(block.getBlock())))));
                Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, cf, plantCF);
        
                RegistryKey<PlacedFeature> plantPF = RegistryKey.of(Registry.PLACED_FEATURE_KEY, pf);
                Registry.register(BuiltinRegistries.PLACED_FEATURE, plantPF.getValue(), new PlacedFeature(RegistryEntry.of(plantCF), List.of(new PlacementModifier[]{RarityFilterPlacementModifier.of(rarity), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()})));
                
                BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, plantPF);
                return new Tuple(plantCF, plantPF);
        }
}
