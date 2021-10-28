package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.simplylib.util.Util;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.features.BlueBerryBushFeature;
import io.github.simplycmd.terracraft.features.DaybloomFeature;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class FeatureReg {
    public static ConfiguredFeature<?, ?> BLUE_BERRY_BUSH_FEATURE = Util.registerFeature(Main.MOD_ID, "berry_bush_feature", 11, new BlueBerryBushFeature(DefaultFeatureConfig.CODEC));
    public static ConfiguredFeature<?, ?> DAYBLOOM_FEATURE = Util.registerFeature(Main.MOD_ID, "daybloom_feature", 4, new DaybloomFeature(DefaultFeatureConfig.CODEC));
}
