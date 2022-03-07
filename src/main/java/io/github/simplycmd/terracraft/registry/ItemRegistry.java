package io.github.simplycmd.terracraft.registry;

import com.simplycmd.featherlib.registry.SimpleItem;

import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.items.*;
import io.github.simplycmd.terracraft.items.util.*;
import io.github.simplycmd.terracraft.items.util.tools.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;

public class ItemRegistry {
    public static SimpleItem magic_mirror, grenade, flaming_arrow, soul_fire_arrow, slap_hand, wand_of_sparking, heart, umbrella, sickle;
    public static SimpleItem daybloom, daybloom_seeds, blue_berries;
    public static SimpleItem copper_coin, silver_coin, gold_coin, platinum_coin;
    public static SimpleItem cactus_helmet, cactus_chestplate, cactus_leggings, cactus_boots, cactus_sword, cactus_shovel, cactus_pickaxe, cactus_axe, cactus_hoe;

    public static void register() {
        magic_mirror = new SimpleItem(ID("magic_mirror"), new MirrorItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1))).defaultItemModel();
        grenade = new SimpleItem(ID("grenade"), new GrenadeThrowableItem(new FabricItemSettings().group(ItemGroup.COMBAT))).defaultItemModel();
        flaming_arrow = new SimpleItem(ID("flaming_arrow"), new FlamingArrowItem()).defaultItemModel();
        soul_fire_arrow = new SimpleItem(ID("soul_fire_arrow"), new FlamingArrowItem()).defaultItemModel();
        slap_hand = new SimpleItem(ID("slap_hand"), new SlapHandItem()).defaultItemModel();
        wand_of_sparking = new SimpleItem(ID("wand_of_sparking"), new WandOfSparkingItem(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(250))).defaultItemModel();
        heart = new SimpleItem(ID("heart"), new HeartItem()).defaultItemModel();
        umbrella = new SimpleItem(ID("umbrella"), new UmbrellaItem());
        sickle = new SimpleItem(ID("sickle"), new SickleItem()).defaultItemModel();

        daybloom = new SimpleItem(ID("daybloom"), new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));
        daybloom_seeds = new SimpleItem(ID("daybloom_seeds"), new AliasedBlockItem(BlockRegistry.daybloom.getBlock(), new FabricItemSettings().group(ItemGroup.MISC)));
        blue_berries = new SimpleItem(ID("blue_berries"), new AliasedBlockItem(BlockRegistry.blue_berry_bush.getBlock(), new FabricItemSettings().group(ItemGroup.MISC)));

        platinum_coin = new SimpleItem(ID("platinum_coin"), new CoinItem(new Value(1, 0, 0, 0), null)).defaultItemModel();
        gold_coin = new SimpleItem(ID("gold_coin"), new CoinItem(new Value(0, 1, 0, 0), ItemRegistry.platinum_coin.getItem())).defaultItemModel();
        silver_coin = new SimpleItem(ID("silver_coin"), new CoinItem(new Value(0, 0, 1, 0), ItemRegistry.gold_coin.getItem())).defaultItemModel();
        copper_coin = new SimpleItem(ID("copper_coin"), new CoinItem(new Value(0, 0, 0, 1), ItemRegistry.silver_coin.getItem())).defaultItemModel();

        cactus_helmet = new SimpleItem(ID("cactus_helmet"), new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT))).defaultItemModel();
        cactus_chestplate = new SimpleItem(ID("cactus_chestplate"), new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT))).defaultItemModel();
        cactus_leggings = new SimpleItem(ID("cactus_leggings"), new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT))).defaultItemModel();
        cactus_boots = new SimpleItem(ID("cactus_boots"), new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.FEET, new FabricItemSettings().group(ItemGroup.COMBAT))).defaultItemModel();
        cactus_sword = new SimpleItem(ID("cactus_sword"), new SwordItem(ToolMaterials.CACTUS, 3, -2.4F, new FabricItemSettings().group(ItemGroup.COMBAT))).defaultItemModel();
        cactus_shovel = new SimpleItem(ID("cactus_shovel"), new ShovelItem(ToolMaterials.CACTUS, 1.5F, -3.0F, new FabricItemSettings().group(ItemGroup.TOOLS))).defaultItemModel();
        cactus_pickaxe = new SimpleItem(ID("cactus_pickaxe"), new PickaxeItem(ToolMaterials.CACTUS, 1, -2.8F, new FabricItemSettings().group(ItemGroup.TOOLS))).defaultItemModel();
        cactus_axe = new SimpleItem(ID("cactus_axe"), new AxeItem(ToolMaterials.CACTUS, 6.0F, -3.2F, new FabricItemSettings().group(ItemGroup.TOOLS))).defaultItemModel();
        cactus_hoe = new SimpleItem(ID("cactus_hoe"), new HoeItem(ToolMaterials.CACTUS, 0, -3.0F, new FabricItemSettings().group(ItemGroup.TOOLS))).defaultItemModel();
    }

    private static Identifier ID(String id) {
        return new Identifier(Main.MOD_ID, id);
    }
}
