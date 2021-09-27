package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.simplylib.registry.BlockRegistrySettings;
import io.github.simplycmd.simplylib.registry.ID;
import io.github.simplycmd.simplylib.registry.RegisterModBlockCallback;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.blocks.*;
import io.github.simplycmd.terracraft.blocks.util.PotBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class BlockRegistry {
    public static void register() {
        RegisterModBlockCallback.EVENT.register((blocks, block_items) -> {
            // Blocks
            blocks.put(new BlockRegistrySettings(ID("hellstone_block")).blockstateType(BlockRegistrySettings.BlockstateType.RANDOM), new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()));
            blocks.put(new BlockRegistrySettings(ID("dart_trap")), new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()));

            blocks.put(new BlockRegistrySettings(ID("blue_berry_bush")), new BlueBerryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
            blocks.put(new BlockRegistrySettings(ID("daybloom")), new DaybloomBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.CROP)));

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

            blocks.put(new BlockRegistrySettings(ID("ice_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("ice_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("bone_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("bone_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("ultrabright_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("ultrabright_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("demon_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("demon_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("cursed_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("cursed_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("ichor_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("ichor_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("rainbow_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("rainbow_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("desert_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("desert_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("coral_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("coral_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("corrupt_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("corrupt_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("crimson_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("crimson_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("hallowed_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("hallowed_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("jungle_torch")), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            blocks.put(new BlockRegistrySettings(ID("jungle_wall_torch")).lootType(BlockRegistrySettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
            
            // Block Items
            block_items.put(ID("hellstone_block"), new BlockItem(BlockRegistry.get(ID("hellstone_block")), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
            block_items.put(ID("dart_trap"), new BlockItem(BlockRegistry.get(ID("dart_trap")), new FabricItemSettings().group(ItemGroup.REDSTONE)));

            block_items.put(ID("daybloom_seeds"), new AliasedBlockItem(BlockRegistry.get(ID("daybloom")), new FabricItemSettings().group(ItemGroup.MISC)));
            block_items.put(ID("blue_berries"), new AliasedBlockItem(BlockRegistry.get(ID("blue_berry_bush")), new FabricItemSettings().group(ItemGroup.MISC)));

            block_items.put(ID("forest_pot"), new BlockItem(BlockRegistry.get(ID("forest_pot")), new Item.Settings().group(ItemGroup.DECORATIONS)));

            block_items.put(ID("ice_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("ice_torch")), BlockRegistry.get(ID("ice_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("bone_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("bone_torch")), BlockRegistry.get(ID("bone_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("ultrabright_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("ultrabright_torch")), BlockRegistry.get(ID("ultrabright_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("demon_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("demon_torch")), BlockRegistry.get(ID("demon_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("cursed_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("cursed_torch")), BlockRegistry.get(ID("cursed_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("ichor_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("ichor_torch")), BlockRegistry.get(ID("ichor_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("rainbow_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("rainbow_torch")), BlockRegistry.get(ID("rainbow_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("desert_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("desert_torch")), BlockRegistry.get(ID("desert_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("coral_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("coral_torch")), BlockRegistry.get(ID("coral_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("corrupt_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("corrupt_torch")), BlockRegistry.get(ID("corrupt_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("crimson_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("crimson_torch")), BlockRegistry.get(ID("crimson_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("hallowed_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("hallowed_torch")), BlockRegistry.get(ID("hallowed_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
            block_items.put(ID("jungle_torch"), new WallStandingBlockItem(BlockRegistry.get(ID("jungle_torch")), BlockRegistry.get(ID("jungle_wall_torch")), new Item.Settings().group(ItemGroup.DECORATIONS)));
        });

        io.github.simplycmd.simplylib.registry.BlockRegistry.register();
    }

    public static Block get(ID blockId) {
        try {
            return io.github.simplycmd.simplylib.registry.BlockRegistry.get(blockId);
        } catch (IllegalArgumentException i) {
            return Blocks.AIR;
        }
    }

    public static BlockItem getBlockItem(ID itemId) {
        return io.github.simplycmd.simplylib.registry.BlockRegistry.getBlockItem(itemId);
    }
    
    public static ID ID(String id) {
        return new ID(Main.MOD_ID, id);
    }
}
