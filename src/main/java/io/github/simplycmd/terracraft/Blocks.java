package io.github.simplycmd.terracraft;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Blocks {
    public static void RegisterBlocks() {

    }

    public static Block RegisterBlock(String id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, id), block);
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, id), new BlockItem(block, new Item.Settings().group(group)));
        return block;
    }
}
