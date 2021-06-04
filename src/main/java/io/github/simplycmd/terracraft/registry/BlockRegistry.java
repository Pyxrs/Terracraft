package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.simplylib.SimplyLib;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.blocks.*;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JPosition;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
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

import static net.devtech.arrp.api.RuntimeResourcePack.id;
import static net.devtech.arrp.json.loot.JLootTable.*;

public class BlockRegistry {
    enum BlockstateType {
        NORMAL,
        RANDOM_X,
        RANDOM_Y,
    }
    enum ItemModelType {
        NORMAL,
        TEXTURE
    }

    private static final HashMap<String, Block> BLOCKS = new HashMap<String, Block>() {{
        put("hellstone_block", new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()));
        put("dart_trap", new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()));
        put("forest_pot", new PotBlock(PotBlocks.FOREST));
        put("blue_berry_bush", new BlueBerryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));

        put("ice_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("ice_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("bone_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("bone_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("ultrabright_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("ultrabright_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("demon_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("demon_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("cursed_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("cursed_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("ichor_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("ichor_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("rainbow_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("rainbow_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("desert_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("desert_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("coral_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("coral_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("corrupt_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("corrupt_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("crimson_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("crimson_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("hallowed_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("hallowed_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("jungle_torch", new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        put("jungle_wall_torch", new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
    }};

    private static final HashMap<String, BlockItem> BLOCK_ITEMS = new HashMap<String, BlockItem>() {{
        put("hellstone_block", new BlockItem(BlockRegistry.get("hellstone_block"), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        put("dart_trap", new BlockItem(BlockRegistry.get("dart_trap"), new Item.Settings().group(ItemGroup.REDSTONE)));
        put("forest_pot", new BlockItem(BlockRegistry.get("forest_pot"), new Item.Settings().group(ItemGroup.DECORATIONS)));
        put("blue_berry_bush", new BlockItem(BlockRegistry.get("blue_berry_bush"), new Item.Settings().group(ItemGroup.DECORATIONS)));

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
        for (Map.Entry<String, Block> block : BLOCKS.entrySet()) {
            Registry.register(Registry.BLOCK, Main.ID(block.getKey()), block.getValue());
            Main.RESOURCE_PACK.addBlockState(blockstate(block.getKey(), BlockstateType.NORMAL), Main.ID(block.getKey()));
            Main.RESOURCE_PACK.addModel(model(block.getKey(), ItemModelType.NORMAL), Main.ID("item/" + block.getKey()));
            standardLootTable(block.getKey());
        }
        for (Map.Entry<String, BlockItem> item : BLOCK_ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, Main.ID(item.getKey()), item.getValue());
        }
        SimplyLib.registerOverworldOre(Main.MOD_ID, "hellstone_ore", get("hellstone_block"), YOffset.getBottom(), YOffset.getTop(), 9, 20);
    }

    public static Block get(String blockId) {
        return BLOCKS.getOrDefault(blockId, Blocks.AIR);
    }

    public static void standardLootTable(String blockId) {
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

    private static JState blockstate(String blockId, BlockstateType type) {
        JBlockModel model = JState.model(Main.MOD_ID + ":block/" + blockId);
        switch (type) {
            case RANDOM_X:
                return JState.state(JState.multipart(
                        model, model.x(90), model.x(180), model.x(270)
                ));
            case RANDOM_Y:
                return JState.state(JState.multipart(
                        model, model.y(90), model.y(180), model.y(270)
                ));
            default:
                return JState.state(JState.variant(model));
        }
    }

    private static JModel model(String blockId, ItemModelType type) {
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
