package io.github.simplycmd.terracraft.registry;

import com.simplycmd.featherlib.registry.Resources;
import com.simplycmd.featherlib.registry.SimpleBlock;
import com.simplycmd.featherlib.registry.SimpleItem;
import com.simplycmd.featherlib.registry.SimpleBlock.ItemModel;

import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.blocks.*;
import io.github.simplycmd.terracraft.blocks.items.LifeCrystalBlockItem;
import lombok.Getter;
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
import net.minecraft.util.Identifier;

public class BlockRegistry {
    public static SimpleBlock hellstone_block, grass_bale, dart_trap;
    public static SimpleBlock blue_berry_bush, daybloom;
    public static SimpleBlock life_crystal;
    public static SimpleBlock forest_pot, tundra_pot, jungle_pot, dungeon_pot, underworld_pot, corrupt_pot, crimson_pot, hallowed_pot, spider_pot, pyramid_pot, lihzahrd_pot, marble_pot, desert_pot;
    public static Torch ice_torch, bone_torch, ultrabright_torch, demon_torch, cursed_torch, ichor_torch, rainbow_torch, desert_torch, coral_torch, corrupt_torch, crimson_torch, hallowed_torch, jungle_torch;

    public static void register() {
        // Blocks
        hellstone_block = new SimpleBlock(ID("hellstone_block"), new Block(FabricBlockSettings.of(Material.METAL).strength(1.5f, 6.0f).requiresTool()))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        grass_bale = new SimpleBlock(ID("grass_bale"), new HayBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.PALE_GREEN).strength(0.5F).sounds(BlockSoundGroup.GRASS)))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        dart_trap = new SimpleBlock(ID("dart_trap"), new DartTrapBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).requiresTool()))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings().group(ItemGroup.REDSTONE)));

        blue_berry_bush = new SimpleBlock(ID("blue_berry_bush"), new BlueBerryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));
        daybloom = new SimpleBlock(ID("daybloom"), new DaybloomBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.CROP)));

        life_crystal = new SimpleBlock(ID("life_crystal"), new Block(FabricBlockSettings.of(Material.AMETHYST, MapColor.BRIGHT_RED).sounds(BlockSoundGroup.LARGE_AMETHYST_BUD))).defaultBlockstate()
            .withItem(ItemModel.ITEM, (block) -> new LifeCrystalBlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

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

        forest_pot = new SimpleBlock(ID("forest_pot"), new PotBlock(4, 1/500.0, Items.TORCH, 1.0F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        tundra_pot = new SimpleBlock(ID("tundra_pot"), new PotBlock(3, 1/461.0, BlockRegistry.ice_torch.getItem().getItem(), 1.25F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        jungle_pot = new SimpleBlock(ID("jungle_pot"), new PotBlock(3, 1/400.0, BlockRegistry.jungle_torch.getItem().getItem(), 1.75F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        dungeon_pot = new SimpleBlock(ID("dungeon_pot"), new PotBlock(3, 1/384.0, Items.TORCH, 1.9F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        underworld_pot = new SimpleBlock(ID("underworld_pot"), new PotBlock(3, 1/365.0, Items.TORCH, 2.1F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        corrupt_pot = new SimpleBlock(ID("corrupt_pot"), new PotBlock(3, 1/365.0, BlockRegistry.corrupt_torch.getItem().getItem(), 1.6F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        crimson_pot = new SimpleBlock(ID("crimson_pot"), new PotBlock(3, 1/365.0, BlockRegistry.crimson_torch.getItem().getItem(), 1.6F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        hallowed_pot = new SimpleBlock(ID("hallowed_pot"), new PotBlock(3, 1/365.0, BlockRegistry.hallowed_torch.getItem().getItem(), 1.6F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        spider_pot = new SimpleBlock(ID("spider_pot"), new PotBlock(3, 1/272.0, Items.TORCH, 3.5F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        pyramid_pot = new SimpleBlock(ID("pyramid_pot"), new PotBlock(3, 1/125.0, Items.TORCH, 10.0F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        lihzahrd_pot = new SimpleBlock(ID("lihzahrd_pot"), new PotBlock(3, 1/250.0, Items.TORCH, 4.0F)) // TODO: Portal values are 1/250 for hm and 1/500 for pre-hm AND modifier is 4.0F hm and 1.0F pre-hm
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        marble_pot = new SimpleBlock(ID("marble_pot"), new PotBlock(3, 1/375.0, Items.TORCH, 2.0F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
        desert_pot = new SimpleBlock(ID("desert_pot"), new PotBlock(3, 1/461.0, BlockRegistry.desert_torch.getItem().getItem(), 1.25F))
            .withItem(ItemModel.BLOCK, (block) -> new BlockItem(block, new FabricItemSettings()));
    }

    private static JIngredient tr(Item item) {
        return JIngredient.ingredient().item(item);
    }
    private static JIngredient tr(String tag) {
        return JIngredient.ingredient().tag(tag);
    }
    
    private static Torch torch(String prefix, ParticleType<?> particle, JIngredient resource) {
        final String torch = prefix + "_torch";
        final String wallTorch = prefix + "_wall_torch";

        final SimpleBlock block1 = new SimpleBlock(ID(torch), new CustomTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        final SimpleBlock block2 = new SimpleBlock(ID(wallTorch), new CustomWallTorchBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
        final SimpleItem item1 = new SimpleItem(ID(torch), new WallStandingBlockItem(block1.getBlock(), block2.getBlock(), new FabricItemSettings().group(ItemGroup.DECORATIONS))).blockItemModel(block1.getBlock());

        // Delay this until after said items are registered
        Resources.RESOURCE_PACK.addRecipe(ID(torch), JRecipe.shaped(
            JPattern.pattern("X", "#", "S"),
            JKeys.keys()
                .key("X", JIngredient.ingredient().item(Items.COAL).item(Items.CHARCOAL))
                .key("#", JIngredient.ingredient().item(Items.STICK))
                .key("S", resource),
            JResult.itemStack(item1.getItem(), 4)
        ));

        return new Torch(block1, block2, item1);
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
