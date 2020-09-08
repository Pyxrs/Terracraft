package io.github.simplycmd.terracraft;

import java.util.HashMap;
import java.util.List;

import io.github.simplycmd.terracraft.armor.ModArmor;
import io.github.simplycmd.terracraft.items.ItemTooltips;
import io.github.simplycmd.terracraft.items.ModItem;
import io.github.simplycmd.terracraft.tools.ModAxe;
import io.github.simplycmd.terracraft.tools.ModHoe;
import io.github.simplycmd.terracraft.tools.ModPickaxe;
import io.github.simplycmd.terracraft.tools.ModShovel;
import io.github.simplycmd.terracraft.tools.ModSword;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Registers {
    static HashMap<Item, List<Text>> tooltip_list = new HashMap<Item, List<Text>>();

    /*
     * public HashMap registerArmor(ArmorMaterial material, Boolean tip, Boolean
     * vanity) { ItemGroup group = ItemGroup.COMBAT; EquipmentSlot[] slot =
     * {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS,
     * EquipmentSlot.FEET}; HashMap<EquipmentSlot, Item> armor = new
     * HashMap<EquipmentSlot, Item>();
     * 
     * for (int i = 0; i < 4; i++) { String equipment = ""; String name; Integer
     * defense = material.getProtectionAmount(slot[i]);
     * 
     * if(i == 0) {equipment = "_helmet";} else if(i == 1) {equipment =
     * "_chestplate";} else if(i == 2) {equipment = "_leggings";} else if(i == 3)
     * {equipment = "_boots";}
     * 
     * name = material.getName() + equipment; armor.put(slot[i], new
     * ModArmor(material, slot[i], (new Item.Settings()).group(group), tip, vanity,
     * defense)); Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID,
     * name), armor.get(slot[i])); }
     * 
     * return armor; }
     * 
     * public HashMap registerArmor(ArmorMaterial material, Boolean tip, Boolean
     * vanity, String set_bonus) { ItemGroup group = ItemGroup.COMBAT;
     * EquipmentSlot[] slot = {EquipmentSlot.HEAD, EquipmentSlot.CHEST,
     * EquipmentSlot.LEGS, EquipmentSlot.FEET}; HashMap<EquipmentSlot, Item> armor =
     * new HashMap<EquipmentSlot, Item>();
     * 
     * for (int i = 0; i < 4; i++) { String equipment = ""; String name; Integer
     * defense = material.getProtectionAmount(slot[i]);
     * 
     * if(i == 0) {equipment = "_helmet";} else if(i == 1) {equipment =
     * "_chestplate";} else if(i == 2) {equipment = "_leggings";} else if(i == 3)
     * {equipment = "_boots";}
     * 
     * name = material.getName() + equipment; armor.put(slot[i], new
     * ModArmor(material, slot[i], (new Item.Settings()).group(group), tip, vanity,
     * defense, set_bonus)); Registry.register(Registry.ITEM, new
     * Identifier(Terracraft.MOD_ID, name), armor.get(slot[i])); }
     * 
     * return armor; }
     */

    public Item registerNew(Float type, String name, String tooltip) {
        // If you are registering an item
        return registerNew(type, name, tooltip, null, null, null, null);
    }

    public Item registerNew(Float type, String name, String tooltip, ToolMaterial tool_material, Integer attack_damage,
            Float attack_speed) {
        // If you are registering a tool
        return registerNew(type, name, tooltip, tool_material, null, attack_damage, attack_speed);
    }

    public Item registerNew(Float type, String name, String tooltip, ArmorMaterial armor_material) {
        // If you are registering a piece of armor
        return registerNew(type, name, tooltip, null, armor_material, null, null);
    }

    public Item registerNew(Float type, String name, String tooltip, ToolMaterial tool_material,
            ArmorMaterial armor_material, Integer attack_damage, Float attack_speed) {

        ItemGroup group = ItemGroup.MISC;
        Integer stack = 64;
        EquipmentSlot slot = EquipmentSlot.CHEST;

        // Determine stack size
        /*
         * if ((type <= Types.WEAPON_WHIP) || (type >= Types.ARMOR_HELMET && type <=
         * Types.DYE && type != Types.COIN && type != Types.GEM) || (type >= 12.0F &&
         * type < 15.0F)) { stack = 1; }
         */

        // Determine item group and slot (if armor)
        if (type <= Types.TOOL_SHOVEL) {
            group = ItemGroup.TOOLS;
        }
        if (type >= Types.WEAPON_SWORD && type <= Types.ARMOR_BOOTS) {
            group = ItemGroup.COMBAT;
        }
        if ((type >= Types.COIN && type <= Types.DYE) || (type >= Types.PET)) {
            group = ItemGroup.MISC;
        }
        if (type >= Types.POTION_RECOVERY && type <= Types.POTION_OTHER) {
            group = ItemGroup.BREWING;
        }
        if (type == Types.ARMOR_HELMET) {
            slot = EquipmentSlot.HEAD;
        } else if (type == Types.ARMOR_CHESTPLATE) {
            slot = EquipmentSlot.CHEST;
        } else if (type == Types.ARMOR_LEGGINGS) {
            slot = EquipmentSlot.LEGS;
        } else if (type == Types.ARMOR_BOOTS) {
            slot = EquipmentSlot.FEET;
        }

        // Determine type and create item
        if (type == Types.TOOL_AXE) {
            final ModAxe item = new ModAxe(tool_material, attack_damage, attack_speed,
                    (new Item.Settings()).group(group));
            Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name), item);
            ItemTooltips.Tooltipper(type, GetTooltip(item), tooltip);
            return item;
        } else if (type == Types.TOOL_HOE) {
            final ModHoe item = new ModHoe(tool_material, attack_damage, attack_speed,
                    (new Item.Settings()).group(group));
            Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name), item);
            ItemTooltips.Tooltipper(type, GetTooltip(item), tooltip);
            return item;
        } else if (type == Types.TOOL_PICKAXE) {
            final ModPickaxe item = new ModPickaxe(tool_material, attack_damage, attack_speed,
                    (new Item.Settings()).group(group));
            Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name), item);
            ItemTooltips.Tooltipper(type, GetTooltip(item), tooltip);
            return item;
        } else if (type == Types.TOOL_SHOVEL) {
            final ModShovel item = new ModShovel(tool_material, attack_damage, attack_speed,
                    (new Item.Settings()).group(group));
            Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name), item);
            ItemTooltips.Tooltipper(type, GetTooltip(item), tooltip);
            return item;
        } else if (type == Types.WEAPON_SWORD) {
            final ModSword item = new ModSword(tool_material, attack_damage, attack_speed,
                    (new Item.Settings()).group(group));
            Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name), item);
            ItemTooltips.Tooltipper(type, GetTooltip(item), tooltip);
            return item;
        } else if (type >= Types.ARMOR_HELMET && type <= Types.ARMOR_BOOTS) {
            final ModArmor item = new ModArmor(armor_material, slot, (new Item.Settings()).group(group));
            Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name), item);
            ItemTooltips.Tooltipper(type, GetTooltip(item), tooltip);
            return item;
        } else {
            final ModItem item = new ModItem(new Item.Settings().group(group).maxCount(stack));
            Registry.register(Registry.ITEM, new Identifier(Terracraft.MOD_ID, name), item);
            ItemTooltips.Tooltipper(type, GetTooltip(item), tooltip);
            return item;
        }
    }

    public static void PutTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip_list.put(stack.getItem(), tooltip);
    }

    public List<Text> GetTooltip(Item item) {
        return tooltip_list.get(item);
    }
}