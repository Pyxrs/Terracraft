package io.github.simplycmd.terracraft.registry;

import com.simplycmd.featherlib.registry.SimpleBlock;
import com.simplycmd.featherlib.registry.SimpleItem;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.blocks.*;
import io.github.simplycmd.terracraft.blocks.items.LifeCrystalBlockItem;
import lombok.Getter;
import net.devtech.arrp.json.recipe.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.HayBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.data.client.Models;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockRegistry {
    public static SimpleBlock step_stool;
    public static SimpleBlock hellstone_block, grass_bale, dart_trap;
    public static SimpleBlock blue_berry_bush, daybloom;
    public static SimpleBlock life_crystal;
    public static SimpleBlock forest_pot, tundra_pot, jungle_pot, dungeon_pot, underworld_pot, corrupt_pot, crimson_pot, hallowed_pot, spider_pot, pyramid_pot, lihzahrd_pot, marble_pot, desert_pot;
    public static Torch ice_torch, bone_torch, ultrabright_torch, demon_torch, cursed_torch, ichor_torch, rainbow_torch, desert_torch, coral_torch, corrupt_torch, crimson_torch, hallowed_torch, jungle_torch;

    public static void register() {
        // Blocks
        hellstone_block = new SimpleBlock(ID("hellstone_block"), new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()));
        //    .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        grass_bale = new SimpleBlock(ID("grass_bale"), new HayBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.PALE_GREEN).strength(0.5F).sounds(BlockSoundGroup.GRASS)));
        //    .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        dart_trap = new SimpleBlock(ID("dart_trap"), new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()));
        //    .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings().group(ItemGroup.REDSTONE)));

        blue_berry_bush = new SimpleBlock(ID("blue_berry_bush"), new BlueBerryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
        daybloom = new SimpleBlock(ID("daybloom"), new DaybloomBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.CROP)));

        life_crystal = new SimpleBlock(ID("life_crystal"), new Block(FabricBlockSettings.of(Material.AMETHYST, MapColor.BRIGHT_RED).sounds(BlockSoundGroup.LARGE_AMETHYST_BUD)));//.defaultBlockstate()
        //    .withItem(ItemModel.ITEM, (block) -> new LifeCrystalBlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        step_stool = new SimpleBlock(ID("step_stool"), new Block(FabricBlockSettings.of(Material.PISTON).sounds(BlockSoundGroup.WOOD).strength(-1.0f, 3600000.0f)));

        ice_torch = torch("ice", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.ICE));
        bone_torch = torch("bone", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.BONE));
        ultrabright_torch = torch("ultrabright", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.GLOW_BERRIES));
        demon_torch = torch("demon", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.GHAST_TEAR));
        cursed_torch = torch("cursed", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.PHANTOM_MEMBRANE));
        ichor_torch = torch("ichor", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.DANDELION));
        rainbow_torch = torch("rainbow", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.PRISMARINE_SHARD));
        desert_torch = torch("desert", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.SANDSTONE));
        coral_torch = torch("coral", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.SEA_PICKLE));
        corrupt_torch = torch("corrupt", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.BLACKSTONE));
        crimson_torch = torch("crimson", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.NETHER_WART));
        hallowed_torch = torch("hallowed", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.SWEET_BERRIES));
        jungle_torch = torch("jungle", ParticleTypes.SOUL_FIRE_FLAME, tr(Items.BAMBOO));

        forest_pot = new SimpleBlock(ID("forest_pot"), new PotBlock(4, 1/500.0, Items.TORCH, 1.0F), (block) -> {
            return new BlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS));
        }, (block, generator) -> {
            generator.registerSimpleCubeAll(block);
        });
        //    .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        tundra_pot = createPot("tundra_pot", 3, 1/461.0, BlockRegistry.ice_torch.getItem().asItem(), 1.25F);

        jungle_pot = createPot("jungle_pot", 3, 1/400.0, BlockRegistry.jungle_torch.getItem().asItem(), 1.75F);

        dungeon_pot = createPot("dungeon_pot", 3, 1/384.0, Items.TORCH, 1.9F);

        underworld_pot = createPot("underworld_pot", 3, 1/365.0, Items.TORCH, 2.1F);

        corrupt_pot = createPot("corrupt_pot", 3, 1/365.0, BlockRegistry.corrupt_torch.getItem().getItem(), 1.6F);

        crimson_pot = createPot("crimson_pot", 3, 1/365.0, BlockRegistry.crimson_torch.getItem().asItem(), 1.6F);

        hallowed_pot = createPot("hallowed_pot", 3, 1/365.0, BlockRegistry.hallowed_torch.getItem().asItem(), 1.6F);

        spider_pot = createPot("spider_pot", 3, 1/272.0, Items.TORCH, 3.5F);

        pyramid_pot = createPot("pyramid_pot", 3, 1/125.0, Items.TORCH, 10.0F);

        lihzahrd_pot = createPot("lihzahrd_pot", 3, 1/250.0, Items.TORCH, 4.0F); // TODO: Portal values are 1/250 for hm and 1/500 for pre-hm AND modifier is 4.0F hm and 1.0F pre-hm

        marble_pot = createPot("marble_pot", 3, 1/375.0, Items.TORCH, 2.0F);

        desert_pot = createPot("desert_pot", 3, 1/461.0, BlockRegistry.desert_torch.getItem().getItem(), 1.25F);

    }

    private static Item tr(Item item) {
        return item;
    }

    public static void reflectRegistration() {
        try {
            var register = SimpleBlock.class.getDeclaredMethod("register");
            register.setAccessible(true);
            for (Field field : BlockRegistry.class.getFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.get(null) instanceof SimpleBlock block) {
                    register.invoke(block);
                }
            }
        } catch (Exception e) {throw new RuntimeException(e);}
    }

    public static SimpleBlock[] getAllBlocks() {
        try {
            ArrayList<SimpleBlock> d = new ArrayList<>();
            for (Field field : BlockRegistry.class.getFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.get(null) instanceof SimpleBlock block) {
                    d.add(block);
                }
            }
            return d.toArray(new SimpleBlock[0]);
        } catch (Exception e) {throw new RuntimeException(e);}
    }

    @Deprecated //TODO: Remove completely
    @SuppressWarnings("unused")
    private static JIngredient tr(String tag) {
        return JIngredient.ingredient().tag(tag);
    }

    private static final ArrayList<TorchData> data = new ArrayList<>();
    // for data generation
    public static record TorchData(Torch torch, Item resource){};

    private static Torch torch(String prefix, ParticleType<?> particle, Item resource) {
        final String torch = prefix + "_torch";
        final String wallTorch = prefix + "_wall_torch";

        final SimpleBlock block1 = new SimpleBlock(ID(torch), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        final SimpleBlock block2 = new SimpleBlock(ID(wallTorch), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        Registry.register(Registry.BLOCK, block1.getId(), block1.getBlock());
        Registry.register(Registry.BLOCK, block2.getId(), block2.getBlock());
        final SimpleItem item1 = new SimpleItem(ID(torch), new WallStandingBlockItem(block1.getBlock(), block2.getBlock(), new FabricItemSettings().group(ItemGroup.DECORATIONS)), (item, generator) -> {
            generator.register(item, Models.TEMPLATE_TORCH);
        });
        Registry.register(Registry.ITEM, item1.getId(), item1.asItem());
        var d = new Torch(block1, block2, item1);
        data.add(new TorchData(d, resource));

        return d;
    }

    private static SimpleBlock createPot(String id, int variants, double coin_portal_chance, Item torch_type, float base_money_modifier) {
        return new SimpleBlock(ID(id), new PotBlock(variants, coin_portal_chance, torch_type, base_money_modifier), (block) -> {
            return new BlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS));
        }, (block, generator) -> {
            generator.registerSimpleCubeAll(block);
        });
    }

    public static class Torch {
        @Getter private final SimpleBlock block1;
        @Getter private final SimpleBlock block2;
        @Getter private final SimpleItem item;
        private Torch(SimpleBlock block1, SimpleBlock block2, SimpleItem item) {
            this.block1 = block1;
            this.block2 = block2;
            this.item = item;
        }
    }

    private static Identifier ID(String id) {
        return new Identifier(Main.MOD_ID, id);
    }
}
