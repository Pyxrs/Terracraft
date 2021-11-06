package io.github.simplycmd.terracraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.simplycmd.terracraft.registry.FeatureReg;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(method = "addPlainsFeatures(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
    private static void addPlainsFeatures(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, FeatureReg.DAYBLOOM_FEATURE);
    }

    @Inject(method = "addForestFlowers(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
    private static void addForestFlowers(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, FeatureReg.BLUE_BERRY_BUSH_FEATURE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, FeatureReg.DAYBLOOM_FEATURE);
    }
}