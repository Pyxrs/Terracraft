package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.simplylib.SimplyLib;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {
    private static final HashMap<String, Block> BLOCKS = new HashMap<String, Block>() {{
        put("hellstone_block", new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()));
        put("dart_trap", new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()));
        put("forest_pot", new PotBlock(PotBlocks.FOREST));
        put("blue_berry_bush", new BlueBerryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
        put("ice_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("ice_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD).dropsLike(BlockRegistry.get("ice_torch")), ParticleTypes.SOUL_FIRE_FLAME));
    }};

    private static final HashMap<String, BlockItem> BLOCK_ITEMS = new HashMap<String, BlockItem>() {{
        put("hellstone_block", new BlockItem(BlockRegistry.get("hellstone_block"), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        put("dart_trap", new BlockItem(BlockRegistry.get("dart_trap"), new Item.Settings().group(ItemGroup.REDSTONE)));
        put("forest_pot", new BlockItem(BlockRegistry.get("forest_pot"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("blue_berry_bush", new BlockItem(BlockRegistry.get("blue_berry_bush"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("ice_torch", new WallStandingBlockItem(BlockRegistry.get("ice_torch"), BlockRegistry.get("ice_wall_torch"), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }};


    public static void register() {
        for (Map.Entry<String, Block> block : BLOCKS.entrySet()) {
            Registry.register(Registry.BLOCK, Main.ID(block.getKey()), block.getValue());
        }
        for (Map.Entry<String, BlockItem> item : BLOCK_ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, Main.ID(item.getKey()), item.getValue());
        }
        SimplyLib.registerOverworldOre(Main.MOD_ID, "hellstone_ore", get("hellstone_block"), YOffset.getBottom(), YOffset.getTop(), 9, 20);
    }

    public static Block get(String blockId) {
        return BLOCKS.getOrDefault(blockId, net.minecraft.block.Blocks.AIR);
    }
}
