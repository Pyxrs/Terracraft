package io.github.simplycmd.terracraft.registry;

import com.simplycmd.featherlib.registry.SimpleItem;

import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.items.*;
import io.github.simplycmd.terracraft.items.accessories.v2.*;
import io.github.simplycmd.terracraft.items.util.*;
import io.github.simplycmd.terracraft.items.util.tools.*;
import io.github.simplycmd.terracraft.util.ParticleUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;

public class ItemRegistry {
    public static SimpleItem magic_mirror, grenade, flaming_arrow, soul_fire_arrow, slap_hand, wand_of_sparking, heart, umbrella, sickle;
    public static SimpleItem daybloom, daybloom_seeds, blue_berries;
    public static SimpleItem copper_coin, silver_coin, gold_coin, platinum_coin;
    public static SimpleItem cactus_helmet, cactus_chestplate, cactus_leggings, cactus_boots, cactus_sword, cactus_shovel, cactus_pickaxe, cactus_axe, cactus_hoe;

    // Movement accessories
    public static SimpleItem aglet, anklet_of_the_wind, balloon_pufferfish, climbing_claws, cloud_in_a_bottle, dunerider_boots, flipper, flurry_boots, flying_carpet,
                            hermes_boots, ice_skates, inner_tube, lucky_horseshoe, magiluminescence, rocket_boots, sailfish_boots, sandstorm_in_a_bottle, shiny_red_balloon,
                            shoe_spikes, step_stool, tsunami_in_a_bottle, valentine_ring, water_walking_boots, fledgling_wings;
    // Combat accessories
    public static SimpleItem feral_claws;

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

        aglet = new SimpleItem(ID("aglet"), AccessoryItem.builder().addAccessory(new SpeedAccessory(0.05F)).settings(new FabricItemSettings().group(ItemGroup.TOOLS)).build()).defaultItemModel();
        anklet_of_the_wind = new SimpleItem(ID("anklet_of_the_wind"), AccessoryItem.builder().addAccessory(new SpeedAccessory(0.1F)).settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/balloon_pufferfish = new SimpleItem(ID("balloon_pufferfish"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/climbing_claws = new SimpleItem(ID("climbing_claws"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        cloud_in_a_bottle = new SimpleItem(ID("cloud_in_a_bottle"), AccessoryItem.builder().addAccessory(DoubleJumpAccessory.create(ParticleUtil.getParticleId(ParticleTypes.CAMPFIRE_COSY_SMOKE), 50, 0.4, 1)).settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/dunerider_boots = new SimpleItem(ID("dunerider_boots"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        flipper = new SimpleItem(ID("flipper"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/flurry_boots = new SimpleItem(ID("flurry_boots"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/flying_carpet = new SimpleItem(ID("flying_carpet"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/hermes_boots = new SimpleItem(ID("hermes_boots"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        ice_skates = new SimpleItem(ID("ice_skates"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        inner_tube = new SimpleItem(ID("inner_tube"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/lucky_horseshoe = new SimpleItem(ID("lucky_horseshoe"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/magiluminescence = new SimpleItem(ID("magiluminescence"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/rocket_boots = new SimpleItem(ID("rocket_boots"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/sailfish_boots = new SimpleItem(ID("sailfish_boots"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        sandstorm_in_a_bottle = new SimpleItem(ID("sandstorm_in_a_bottle"), AccessoryItem.builder().addAccessory(DoubleJumpAccessory.create(ParticleUtil.getParticleId(ParticleTypes.CLOUD), 50, 1.6, 1)).settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/shiny_red_balloon = new SimpleItem(ID("shiny_red_balloon"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/shoe_spikes = new SimpleItem(ID("shoe_spikes"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        step_stool = new SimpleItem(ID("step_stool"), AccessoryItem.builder().addAccessory(new StepAccessory(Blocks.AIR)).settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        tsunami_in_a_bottle = new SimpleItem(ID("tsunami_in_a_bottle"), AccessoryItem.builder().addAccessory(DoubleJumpAccessory.create(ParticleUtil.getParticleId(ParticleTypes.RAIN), 300, 0.8, 1)).settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/valentine_ring = new SimpleItem(ID("valentine_ring"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        water_walking_boots = new SimpleItem(ID("water_walking_boots"), AccessoryItem.builder().addAccessory(new FluidWalkingAccessory(FluidTags.WATER)).settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        /*TODO*/fledgling_wings = new SimpleItem(ID("fledgling_wings"), AccessoryItem.builder().settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
        feral_claws = new SimpleItem(ID("feral_claws"), AccessoryItem.builder().addAccessory(new AttackSpeedAccessory(0.12F)).addAccessory(new AutoSwingAccessory()).settings(new FabricItemSettings().group(ItemGroup.MISC)).build()).defaultItemModel();
    }

    private static Identifier ID(String id) {
        return new Identifier(Main.MOD_ID, id);
    }
}
