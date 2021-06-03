package io.github.simplycmd.terracraft.features;

import io.github.simplycmd.simplylib.SimplyLib;
import io.github.simplycmd.terracraft.Main;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class Features {
    public static ConfiguredFeature<?, ?> BLUE_BERRY_BUSH_FEATURE = SimplyLib.registerFeature(Main.MOD_ID, "berry_bush_feature", 5, new BlueBerryBushFeature(DefaultFeatureConfig.CODEC));
    //private static final Feature<DefaultFeatureConfig> STONE_SPIRAL = new BlueBerryBushFeature(DefaultFeatureConfig.CODEC);
    //public static final ConfiguredFeature<?, ?> STONE_SPIRAL_CONFIGURED = STONE_SPIRAL.configure(FeatureConfig.DEFAULT).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(5)));

    public static void RegisterFeatures() {
        //Registry.register(Registry.FEATURE, new Identifier(Main.MOD_ID, "stone_spiral"), STONE_SPIRAL);
        //Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Main.MOD_ID, "stone_spiral"), STONE_SPIRAL_CONFIGURED);
    }
}
