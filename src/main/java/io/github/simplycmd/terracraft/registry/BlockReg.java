package io.github.simplycmd.terracraft.registry;

import java.util.HashMap;

import io.github.simplycmd.simplylib.registry.BlockRegistry;
import io.github.simplycmd.simplylib.registry.BlockRegistrySettings;
import io.github.simplycmd.simplylib.registry.ID;
import io.github.simplycmd.simplylib.registry.RegisterModBlockCallback;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.blocks.*;
import io.github.simplycmd.terracraft.blocks.items.LifeCrystalBlockItem;
import io.github.simplycmd.terracraft.blocks.util.PotBlocks;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JKeys;
import net.devtech.arrp.json.recipe.JPattern;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class BlockReg {
    private static HashMap<BlockRegistrySettings, Block> regBlocks;
    private static HashMap<ID, BlockItem> regItems;

    public static void register() {
        RegisterModBlockCallback.EVENT.register((blocks, block_items) -> {
            regBlocks = blocks;
            regItems = block_items;

            // Blocks
            blocks.put(new BlockRegistrySettings(ID("hellstone_block")).blockstateType(BlockRegistrySettings.BlockstateType.RANDOM), new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()));
            blocks.put(new BlockRegistrySettings(ID("grass_bale")), new HayBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.PALE_GREEN).strength(0.5F).sounds(BlockSoundGroup.GRASS)));
            blocks.put(new BlockRegistrySettings(ID("dart_trap")), new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()));

            blocks.put(new BlockRegistrySettings(ID("blue_berry_bush")), new BlueBerryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
            blocks.put(new BlockRegistrySettings(ID("daybloom")), new DaybloomBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.CROP)));

            blocks.put(new BlockRegistrySettings(ID("life_crystal")).itemModelType(BlockRegistrySettings.ItemModelType.TEXTURE), new Block(FabricBlockSettings.of(Material.AMETHYST, MapColor.BRIGHT_RED).sounds(BlockSoundGroup.LARGE_AMETHYST_BUD)));

            blocks.put(new BlockRegistrySettings(ID("forest_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.FOREST));
            blocks.put(new BlockRegistrySettings(ID("tundra_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.TUNDRA));
            blocks.put(new BlockRegistrySettings(ID("jungle_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.JUNGLE));
            blocks.put(new BlockRegistrySettings(ID("dungeon_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.DUNGEON));
            blocks.put(new BlockRegistrySettings(ID("underworld_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.UNDERWORLD));
            blocks.put(new BlockRegistrySettings(ID("corrupt_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.CORRUPT));
            blocks.put(new BlockRegistrySettings(ID("crimson_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.CRIMSON));
            blocks.put(new BlockRegistrySettings(ID("hallowed_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.HALLOWED));
            blocks.put(new BlockRegistrySettings(ID("spider_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.SPIDER));
            blocks.put(new BlockRegistrySettings(ID("pyramid_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.PYRAMID));
            blocks.put(new BlockRegistrySettings(ID("lihzahrd_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.LIHZAHRD));
            blocks.put(new BlockRegistrySettings(ID("marble_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.MARBLE));
            blocks.put(new BlockRegistrySettings(ID("desert_pot")).blockstateType(BlockRegistrySettings.BlockstateType.NORMAL).itemModelType(BlockRegistrySettings.ItemModelType.NORMAL).lootType(BlockRegistrySettings.LootType.NONE), new PotBlock(PotBlocks.DESERT));

            torch("ice", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.ICE));
            torch("bone", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.BONE));
            torch("ultrabright", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.GLOW_BERRIES));
            torch("demon", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.GHAST_TEAR));
            torch("cursed", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.PHANTOM_MEMBRANE));
            torch("ichor", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.DANDELION));
            torch("rainbow", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.PRISMARINE_SHARD));
            torch("desert", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.SANDSTONE));
            torch("coral", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.SEA_PICKLE));
            torch("corrupt", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.BLACKSTONE));
            torch("crimson", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.NETHER_WART));
            torch("hallowed", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.SWEET_BERRIES));
            torch("jungle", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.BAMBOO));

            // Block Items
            block_items.put(ID("hellstone_block"), new BlockItem(BlockReg.get("hellstone_block"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
            block_items.put(ID("grass_bale"), new BlockItem(BlockReg.get("grass_bale"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
            block_items.put(ID("dart_trap"), new BlockItem(BlockReg.get("dart_trap"), new FabricItemSettings().group(ItemGroup.REDSTONE)));

            block_items.put(ID("daybloom_seeds"), new AliasedBlockItem(BlockReg.get("daybloom"), new FabricItemSettings().group(ItemGroup.MISC)));
            block_items.put(ID("blue_berries"), new AliasedBlockItem(BlockReg.get("blue_berry_bush"), new FabricItemSettings().group(ItemGroup.MISC)));

            block_items.put(ID("forest_pot"), new BlockItem(BlockReg.get("forest_pot"), new FabricItemSettings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("life_crystal"), new LifeCrystalBlockItem(BlockReg.get("life_crystal"), new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        });

        BlockRegistry.register();
    }

    private static JIngredient tr(Item item) {
        return JIngredient.ingredient().item(item);
    }
    private static JIngredient tr(String tag) {
        return JIngredient.ingredient().tag(tag);
    }
    
    private static void torch(String prefix, ParticleType<?> particle, JIngredient resource) {
        final String torch = prefix + "_torch";
        final String wallTorch = prefix + "_wall_torch";

        regBlocks.put(new BlockRegistrySettings(ID(torch)), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        regBlocks.put(new BlockRegistrySettings(ID(wallTorch)).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));

        regItems.put(ID(torch), new WallStandingBlockItem(BlockReg.get(torch), BlockReg.get(wallTorch), new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        io.github.simplycmd.simplylib.Main.RESOURCE_PACK.addRecipe(ID(torch).getIdentifier(), JRecipe.shaped(
            JPattern.pattern("X",
                             "#",
                             "S"),
            JKeys.keys()
                .key("X", JIngredient.ingredient().item(Items.COAL).item(Items.CHARCOAL))
                .key("#", JIngredient.ingredient().item(Items.STICK))
                .key("S", resource),
            JResult.itemStack(ItemReg.get(torch), 4)
        ));
    }

    public static Block get(String blockId) {
        try {
            return BlockRegistry.get(ID(blockId));
        } catch (IllegalArgumentException i) {
            return Blocks.AIR;
        }
    }

    private static ID ID(String id) {
        return new ID(Main.MOD_ID, id);
    }
}
