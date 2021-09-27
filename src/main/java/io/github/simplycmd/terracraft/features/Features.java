package io.github.simplycmd.terracraft.features;

import io.github.simplycmd.simplylib.util.Util;
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
    public static ConfiguredFeature<?, ?> BLUE_BERRY_BUSH_FEATURE = Util.registerFeature(Main.MOD_ID, "berry_bush_feature", 5, new BlueBerryBushFeature(DefaultFeatureConfig.CODEC));
    public static ConfiguredFeature<?, ?> DAYBLOOM_FEATURE = Util.registerFeature(Main.MOD_ID, "daybloom_feature", 20, new DaybloomFeature(DefaultFeatureConfig.CODEC));
}
