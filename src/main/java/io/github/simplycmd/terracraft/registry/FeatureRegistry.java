package io.github.simplycmd.terracraft.registry;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.function.Predicate;

import com.simplycmd.featherlib.registry.SimpleBlock;

import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.Tuple;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeatures;

public class FeatureRegistry {
        // Daybloom
        public static ConfiguredFeature<RandomPatchFeatureConfig, ?> daybloomCF;
        public static PlacedFeature daybloomPF;
        public static RegistryKey<PlacedFeature> daybloomK;

        // Blue Berry Bush
        public static ConfiguredFeature<RandomPatchFeatureConfig, ?> blueBerryBushCF;
        public static PlacedFeature blueBerryBushPF;
        public static RegistryKey<PlacedFeature> blueBerryBushK;

        public static void register() {
                final Tuple daybloom = registerSimplePlant(BlockRegistry.daybloom, 22, 64, 3, 2, BiomeSelectors.all());
                daybloomCF = (ConfiguredFeature<RandomPatchFeatureConfig, ?>)daybloom.get(0);
                daybloomPF = (PlacedFeature)daybloom.get(1);
                daybloomK = (RegistryKey<PlacedFeature>)daybloom.get(2);
                final Tuple blueBerryBush = registerSimplePlant(BlockRegistry.blue_berry_bush, 32, 64, 1, 2, BiomeSelectors.all());
                blueBerryBushCF = (ConfiguredFeature<RandomPatchFeatureConfig, ?>)blueBerryBush.get(0);
                blueBerryBushPF = (PlacedFeature)blueBerryBush.get(1);
                blueBerryBushK = (RegistryKey<PlacedFeature>)blueBerryBush.get(2);
        }

        public static Tuple registerSimplePlant(SimpleBlock block, int rarity, int tries, int xz_spread, int y_spread, Predicate<BiomeSelectionContext> biomeSelector) {
                final String id = block.getId().getPath();
                final ConfiguredFeature<RandomPatchFeatureConfig, ?> plantCF = registerCF(id + "_cf", Feature.FLOWER.configure(new RandomPatchFeatureConfig(tries, xz_spread, y_spread, () -> {
                        return Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider
                                .of(block.getBlock().getDefaultState())))
                                .withInAirFilter();
                })));
                final PlacedFeature plantPF = registerPF(id + "_pf", plantCF.withPlacement(
                        new PlacementModifier[] {
                                RarityFilterPlacementModifier.of(rarity), SquarePlacementModifier.of(),
                                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                                BiomePlacementModifier.of()
                }));
                final RegistryKey<PlacedFeature> plantK = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Main.MOD_ID, id + "_pf"));
                BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.VEGETAL_DECORATION, plantK);
                return new Tuple(plantCF, plantPF, plantK);
        }

        public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerCF(String id, ConfiguredFeature<FC, ?> configuredFeature) {
                return (ConfiguredFeature<FC, ?>) Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Main.MOD_ID, id), configuredFeature);
        }

        public static PlacedFeature registerPF(String id, PlacedFeature feature) {
                return (PlacedFeature) Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Main.MOD_ID, id), feature);
        }
}
