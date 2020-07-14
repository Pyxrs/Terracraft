package io.github.simplycmd.terracraft;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.simplycmd.terracraft.ToolsArmor.ModArmor;
import io.github.simplycmd.terracraft.ToolsArmor.ModAxe;
import io.github.simplycmd.terracraft.ToolsArmor.ModHoe;
import io.github.simplycmd.terracraft.ToolsArmor.ModPickaxe;
import io.github.simplycmd.terracraft.ToolsArmor.ModShovel;
import io.github.simplycmd.terracraft.ToolsArmor.ModSword;

public class Terracraft implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "terracraft";
    public static final String MOD_NAME = "Terracraft";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing Items");
        //Tools
    
        //Weapons

        //Ammunition
        registerItem("musket_ball", ItemGroup.COMBAT, 64);
        registerItem("silver_bullet", ItemGroup.COMBAT, 64);
        registerItem("tungsten_bullet", ItemGroup.COMBAT, 64);
        registerItem("meteor_shot", ItemGroup.COMBAT, 64);

        registerItem("wooden_arrow", ItemGroup.COMBAT, 64);
        registerItem("flaming_arrow", ItemGroup.COMBAT, 64);
        registerItem("frostburn_arrow", ItemGroup.COMBAT, 64);
        registerItem("bone_arrow", ItemGroup.COMBAT, 64);
        registerItem("heart_arrow", ItemGroup.COMBAT, 64);
        registerItem("unholy_arrow", ItemGroup.COMBAT, 64);
        registerItem("jesters_arrow", ItemGroup.COMBAT, 64);
        registerItem("hellfire_arrow", ItemGroup.COMBAT, 64);

        registerItem("poison_dart", ItemGroup.COMBAT, 64);
        //Armor

        //Furniture

        //Crafting Stations

        //Coins
        registerItem("copper_coin", ItemGroup.MISC, 64);
        registerItem("silver_coin", ItemGroup.MISC, 64);
        registerItem("gold_coin", ItemGroup.MISC, 64);
        registerItem("platinum_coin", ItemGroup.MISC, 64);
        //Ores

        //Bars

        //Accessories

        //Blocks

        //Walls
                
        //Paint

        //Gems

        //Vanity Items

        //Dyes

        //Potions

        //Statues

        //Wire

        //Pets

        //Mounts

        //Minions

        //Wings

        //Miscellaneous
    }

    public static void registerArmor(String name, ItemGroup group, ArmorMaterial material, EquipmentSlot slot) {
        Registry.register(Registry.ITEM, new Identifier("terracraft", name), new ModArmor(material, slot, (new Item.Settings()).group(group)));
    }

    public static void registerAxe(String name, ItemGroup group, ToolMaterial material, float damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier("terracraft", name), new ModAxe(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerHoe(String name, ItemGroup group, ToolMaterial material, int damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier("terracraft", name), new ModHoe(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerPickaxe(String name, ItemGroup group, ToolMaterial material, int damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier("terracraft", name), new ModPickaxe(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerShovel(String name, ItemGroup group, ToolMaterial material, int damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier("terracraft", name), new ModShovel(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerSword(String name, ItemGroup group, ToolMaterial material, int damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier("terracraft", name), new ModSword(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerItem(String name, ItemGroup group, int stack) {
        Registry.register(Registry.ITEM, new Identifier("terracraft", name), new Item(new Item.Settings().group(group).maxCount(stack)));
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}