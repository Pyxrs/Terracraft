package io.github.simplycmd.terracraft;

import java.util.HashMap;

import io.github.simplycmd.terracraft.armor.ModArmor;
import io.github.simplycmd.terracraft.tools.ModAxe;
import io.github.simplycmd.terracraft.tools.ModHoe;
import io.github.simplycmd.terracraft.tools.ModPickaxe;
import io.github.simplycmd.terracraft.tools.ModShovel;
import io.github.simplycmd.terracraft.tools.ModSword;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Registers {
    public static HashMap registerArmor(ArmorMaterial material, Boolean tooltip) {
        ItemGroup group = ItemGroup.COMBAT;
        EquipmentSlot[] slot = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        HashMap<EquipmentSlot, Item> armor = new HashMap<EquipmentSlot, Item>();

        for (int i = 0; i < 4; i++) {
            String equipment = "";
            String name;

            if(i == 0) {equipment = "_helmet";}
            else if(i == 1) {equipment = "_chestplate";}
            else if(i == 2) {equipment = "_leggings";}
            else if(i == 3) {equipment = "_boots";}

            name = material.getName() + equipment;
            armor.put(slot[i], new ModArmor(material, slot[i], (new Item.Settings()).group(group)));
            Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name), armor.get(slot[i]));
        }

        return armor;
    }

    public static void registerAxe(String name, ItemGroup group, ToolMaterial material, float damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name),
                new ModAxe(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerHoe(String name, ItemGroup group, ToolMaterial material, int damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name),
                new ModHoe(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerPickaxe(String name, ItemGroup group, ToolMaterial material, int damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name),
                new ModPickaxe(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerShovel(String name, ItemGroup group, ToolMaterial material, int damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name),
                new ModShovel(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerSword(String name, ItemGroup group, ToolMaterial material, int damage, float speed) {
        Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name),
                new ModSword(material, damage, speed, (new Item.Settings()).group(group)));
    }

    public static void registerItem(String name, ItemGroup group, int stack) {
        Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name),
                new Item(new Item.Settings().group(group).maxCount(stack)));
    }

    public static void registerAmmo(String name) {
        Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name),
                new Item(new Item.Settings().group(ItemGroup.COMBAT).maxCount(64)));
    }
}