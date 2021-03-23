package io.github.simplycmd.terracraft;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Blocks {
    public static void RegisterBlocks() {
        //RegisterBlock("hellstone_block", new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    }

    public static Block RegisterBlock(String id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, id), block);
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, id), new BlockItem(block, new Item.Settings().group(group)));
        return block;
    }

    public static ConfiguredFeature<?, ?> RegisterOre(String id, ConfiguredFeature<?, ?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Main.MOD_ID, id), feature);
        return feature;
    }
}
