package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.simplylib.SimplyLib;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.blocks.*;
import io.github.simplycmd.terracraft.blocks.util.CustomSettings;
import io.github.simplycmd.terracraft.blocks.util.PotBlocks;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JPosition;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.devtech.arrp.api.RuntimeResourcePack.id;
import static net.devtech.arrp.json.loot.JLootTable.*;

public class BlockRegistry {

    private static final HashMap<CustomSettings, Block> BLOCKS = new HashMap<>() {{
        put(new CustomSettings("hellstone_block", CustomSettings.BlockstateType.RANDOM), new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()));
        put(new CustomSettings("dart_trap"), new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()));

        put(new CustomSettings("blue_berry_bush"), new BlueBerryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
        put(new CustomSettings("daybloom"), new DaybloomBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.CROP)));

        put(new CustomSettings("forest_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.FOREST));
        put(new CustomSettings("tundra_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.TUNDRA));
        put(new CustomSettings("jungle_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.JUNGLE));
        put(new CustomSettings("dungeon_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.DUNGEON));
        put(new CustomSettings("underworld_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.UNDERWORLD));
        put(new CustomSettings("corrupt_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.CORRUPT));
        put(new CustomSettings("crimson_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.CRIMSON));
        put(new CustomSettings("hallowed_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.HALLOWED));
        put(new CustomSettings("spider_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.SPIDER));
        put(new CustomSettings("pyramid_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.PYRAMID));
        put(new CustomSettings("lihzahrd_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.LIHZAHRD));
        put(new CustomSettings("marble_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.MARBLE));
        put(new CustomSettings("desert_pot", CustomSettings.BlockstateType.NORMAL, CustomSettings.ItemModelType.NORMAL, CustomSettings.LootType.NONE), new PotBlock(PotBlocks.DESERT));

        put(new CustomSettings("ice_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("ice_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("bone_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("bone_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("ultrabright_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("ultrabright_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("demon_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("demon_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("cursed_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("cursed_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("ichor_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("ichor_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("rainbow_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("rainbow_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("desert_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("desert_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("coral_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("coral_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("corrupt_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("corrupt_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("crimson_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("crimson_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("hallowed_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("hallowed_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("jungle_torch"), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put(new CustomSettings("jungle_wall_torch", CustomSettings.LootType.NONE), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
    }};

    private static final HashMap<String, BlockItem> BLOCK_ITEMS = new HashMap<>() {{
        put("hellstone_block", new BlockItem(BlockRegistry.get("hellstone_block"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("dart_trap", new BlockItem(BlockRegistry.get("dart_trap"), new Item.Settings().group(ItemGroup.REDSTONE)));

        put("daybloom_seeds", new AliasedBlockItem(BlockRegistry.get("daybloom"), new FabricItemSettings().group(ItemGroup.MISC)));
        put("blue_berries", new AliasedBlockItem(BlockRegistry.get("blue_berry_bush"), new FabricItemSettings().group(ItemGroup.MISC)));

        put("forest_pot", new BlockItem(BlockRegistry.get("forest_pot"), new Item.Settings().group(ItemGroup.DECORATIONS)));

        put("ice_torch", new WallStandingBlockItem(BlockRegistry.get("ice_torch"), BlockRegistry.get("ice_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("bone_torch", new WallStandingBlockItem(BlockRegistry.get("bone_torch"), BlockRegistry.get("bone_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("ultrabright_torch", new WallStandingBlockItem(BlockRegistry.get("ultrabright_torch"), BlockRegistry.get("ultrabright_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("demon_torch", new WallStandingBlockItem(BlockRegistry.get("demon_torch"), BlockRegistry.get("demon_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("cursed_torch", new WallStandingBlockItem(BlockRegistry.get("cursed_torch"), BlockRegistry.get("cursed_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("ichor_torch", new WallStandingBlockItem(BlockRegistry.get("ichor_torch"), BlockRegistry.get("ichor_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("rainbow_torch", new WallStandingBlockItem(BlockRegistry.get("rainbow_torch"), BlockRegistry.get("rainbow_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("desert_torch", new WallStandingBlockItem(BlockRegistry.get("desert_torch"), BlockRegistry.get("desert_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("coral_torch", new WallStandingBlockItem(BlockRegistry.get("coral_torch"), BlockRegistry.get("coral_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("corrupt_torch", new WallStandingBlockItem(BlockRegistry.get("corrupt_torch"), BlockRegistry.get("corrupt_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("crimson_torch", new WallStandingBlockItem(BlockRegistry.get("crimson_torch"), BlockRegistry.get("crimson_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("hallowed_torch", new WallStandingBlockItem(BlockRegistry.get("hallowed_torch"), BlockRegistry.get("hallowed_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("jungle_torch", new WallStandingBlockItem(BlockRegistry.get("jungle_torch"), BlockRegistry.get("jungle_wall_torch"), new Item.Settings().group(ItemGroup.DECORATIONS)));
    }};


    public static void register() {
        // Scary code do not touch
        for (Map.Entry<CustomSettings, Block> block : BLOCKS.entrySet()) {
            Registry.register(Registry.BLOCK, Main.ID(block.getKey().getId()), block.getValue());
            Main.RESOURCE_PACK.addBlockState(blockstate(block.getKey().getId(), block.getKey().getBlockstateType()), Main.ID(block.getKey().getId()));
            Main.RESOURCE_PACK.addModel(model(block.getKey().getId(), block.getKey().getItemModelType()), Main.ID("item/" + block.getKey().getId()));
            lootTable(block.getKey().getId(), block.getKey().getLootType());
        }
        for (Map.Entry<String, BlockItem> item : BLOCK_ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, Main.ID(item.getKey()), item.getValue());
        }
        SimplyLib.registerOverworldOre(Main.MOD_ID, "hellstone_ore", get("hellstone_block"), YOffset.getBottom(), YOffset.getTop(), 9, 20);
    }

    public static Block get(String blockId) {
        if (BLOCKS != null) {
            for (Map.Entry<CustomSettings, Block> block : BLOCKS.entrySet()) {
                if (block.getKey().getId().matches(blockId)) {
                    return block.getValue();
                }
            }
            throw new IllegalArgumentException("Block not valid!");
        } else {
            return Blocks.AIR;
        }
    }

    public static BlockItem getBlockItem(String itemId) {
        if (BLOCK_ITEMS != null) {
            for (Map.Entry<String, BlockItem> item : BLOCK_ITEMS.entrySet()) {
                if (item.getKey().matches(itemId)) {
                    return item.getValue();
                }
            }
            throw new IllegalArgumentException("Block not valid!");
        } else {
            return (BlockItem) Blocks.AIR.asItem();
        }
    }

    public static void lootTable(String blockId, CustomSettings.LootType type) {
        switch (type) {
            case NORMAL: {
                Main.RESOURCE_PACK.addLootTable(id(Main.MOD_ID + ":blocks/" + blockId),
                        loot("minecraft:block")
                                .pool(pool()
                                        .rolls(1)
                                        .entry(entry()
                                                .type("minecraft:item")
                                                .name(Main.MOD_ID + ":" + blockId))
                                        .condition(predicate("minecraft:survives_explosion")))
                );
            }
            case NONE: {

            }
        }
    }

    private static JState blockstate(String blockId, CustomSettings.BlockstateType type) {
        JBlockModel model = JState.model(Main.MOD_ID + ":block/" + blockId);
        return switch (type) {
            case RANDOM_X -> JState.state(JState.multipart(
                    model, model.x(90), model.x(180), model.x(270)
            ));
            case RANDOM_Y -> JState.state(JState.multipart(
                    model, model.y(90), model.y(180), model.y(270)
            ));
            case RANDOM -> JState.state(JState.multipart(
                    model, model.x(90), model.x(180), model.x(270), model.y(90), model.y(180), model.y(270)
            ));
            default -> JState.state(JState.variant(model));
        };
    }

    private static JModel model(String blockId, CustomSettings.ItemModelType type) {
        switch (type) {
            case TEXTURE:
                return JModel.model().parent("minecraft:item/generated").textures(JModel.textures().layer0(Main.MOD_ID + ":item/" + blockId));
            default:
                return JModel.model().parent(Main.MOD_ID + ":block/" + blockId).display(JModel.display()
                        .setGui(new JPosition().rotation(30, 45, 0).scale(0.625f, 0.625f, 0.625f))
                        .setGround(new JPosition().translation(0, 3, 0).scale(0.25f, 0.25f, 0.25f))
                        .setHead(new JPosition().rotation(0, 180, 0).scale(1, 1, 1))
                        .setFixed(new JPosition().rotation(0, 180, 0).scale(0.5f, 0.5f, 0.5f))
                        .setThirdperson_righthand(new JPosition().rotation(75, 315, 0).translation(0, 2.5f, 0).scale(0.375f, 0.375f, 0.375f))
                        .setFirstperson_righthand(new JPosition().rotation(0, 315, 0).scale(0.4f, 0.4f, 0.4f))
                );
        }
    }
}
