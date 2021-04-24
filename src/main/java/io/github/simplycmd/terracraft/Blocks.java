package io.github.simplycmd.terracraft;

import io.github.simplycmd.simplylib.SimplyLib;
import io.github.simplycmd.terracraft.blocks.DartTrapBlock;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class Blocks {
    public static Block HELLSTONE_BLOCK;

    public static void RegisterBlocks() {
        HELLSTONE_BLOCK = SimplyLib.registerBlock(Main.MOD_ID, "hellstone_block", new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()), ItemGroup.BUILDING_BLOCKS);
        SimplyLib.registerOverworldOre(Main.MOD_ID, "hellstone_ore", HELLSTONE_BLOCK, YOffset.getBottom(), YOffset.getTop(), 9, 20);

        SimplyLib.registerBlock(Main.MOD_ID, "dart_trap", new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()), ItemGroup.DECORATIONS);
    }
}
