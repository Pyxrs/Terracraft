package io.github.simplycmd.terracraft;

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
        HELLSTONE_BLOCK = RegisterBlock("hellstone_block", new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()), ItemGroup.BUILDING_BLOCKS);
        RegisterOverworldOre("hellstone_ore", HELLSTONE_BLOCK, YOffset.getBottom(), YOffset.getTop(), 9, 20);

        RegisterBlock("dart_trap", new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()), ItemGroup.DECORATIONS);
    }

    public static Block RegisterBlock(String id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, id), block);
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, id), new BlockItem(block, new Item.Settings().group(group)));
        return block;
    }

    public static ConfiguredFeature<?, ?> RegisterOverworldOre(String id, Block block, YOffset lowest, YOffset highest, Integer vein_size, Integer veins_per_chunk) {
        ConfiguredFeature<?, ?> ORE = Feature.ORE
                .configure(new OreFeatureConfig(
                        OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                        block.getDefaultState(),
                        vein_size))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(lowest, highest))))
                .spreadHorizontally()
                .repeat(veins_per_chunk);

        RegistryKey<ConfiguredFeature<?, ?>> oreWoolOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("tutorial", "ore_wool_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreWoolOverworld.getValue(), ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreWoolOverworld);

        return ORE;
    }


}
