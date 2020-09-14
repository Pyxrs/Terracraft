package io.github.simplycmd.terracraft;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import io.github.simplycmd.terracraft.armor.ArmorMaterials;
import io.github.simplycmd.terracraft.tools.ToolMaterials;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class Terracraft extends Registers implements ModInitializer {
	public static final String MOD_ID = "terracraft";
    public static final String MOD_NAME = "Terracraft";

    private HashMap<EquipmentSlot, Item> wooden_armor = new HashMap<EquipmentSlot, Item>();
	private HashMap<EquipmentSlot, Item> mining_armor = new HashMap<EquipmentSlot, Item>();
	
	@Override
	public void onInitialize() {
		ServerTickCallback.EVENT.register(this::onServerTick);

        // Tools
        registerNew(Types.WEAPON_SWORD, "cactus_sword", null, ToolMaterials.CACTUS, 4, 1.6F);
        registerNew(Types.TOOL_PICKAXE, "cactus_pickaxe", null, ToolMaterials.CACTUS, 2, 1.2F);
        registerNew(Types.TOOL_AXE, "cactus_axe", null, ToolMaterials.CACTUS, 7, 0.8F);

        registerNew(Types.WEAPON_SWORD, "copper_broadsword", null, ToolMaterials.COPPER, 5, 1.6F);
        registerNew(Types.TOOL_PICKAXE, "copper_pickaxe", null, ToolMaterials.COPPER, 3, 1.2F);
        registerNew(Types.TOOL_AXE, "copper_axe", null, ToolMaterials.COPPER, 9, 0.8F);

        registerNew(Types.WEAPON_SWORD, "tin_broadsword", null, ToolMaterials.TIN, 5, 1.6F);
        registerNew(Types.TOOL_PICKAXE, "tin_pickaxe", null, ToolMaterials.TIN, 3, 1.2F);
        registerNew(Types.TOOL_AXE, "tin_axe", null, ToolMaterials.TIN, 9, 0.8F);

        registerNew(Types.WEAPON_SWORD, "lead_broadsword", null, ToolMaterials.LEAD, 6, 1.6F);
        registerNew(Types.TOOL_PICKAXE, "lead_pickaxe", null, ToolMaterials.LEAD, 4, 1.2F);
        registerNew(Types.TOOL_AXE, "lead_axe", null, ToolMaterials.LEAD, 9, 0.9F);

        registerNew(Types.WEAPON_SWORD, "silver_broadsword", null, ToolMaterials.SILVER, 7, 1.6F);
        registerNew(Types.TOOL_PICKAXE, "silver_pickaxe", null, ToolMaterials.SILVER, 5, 1.2F);
        registerNew(Types.TOOL_AXE, "silver_axe", null, ToolMaterials.SILVER, 9, 1.0F);

        registerNew(Types.WEAPON_SWORD, "tungsten_broadsword", null, ToolMaterials.TUNGSTEN, 7, 1.6F);
        registerNew(Types.TOOL_PICKAXE, "tungsten_pickaxe", null, ToolMaterials.TUNGSTEN, 5, 1.2F);
        registerNew(Types.TOOL_AXE, "tungsten_axe", null, ToolMaterials.TUNGSTEN, 9, 1.0F);

        registerNew(Types.WEAPON_SWORD, "platinum_broadsword", null, ToolMaterials.PLATINUM, 8, 1.6F);
        registerNew(Types.TOOL_PICKAXE, "platinum_pickaxe", null, ToolMaterials.PLATINUM, 6, 1.2F);
        registerNew(Types.TOOL_AXE, "platinum_axe", null, ToolMaterials.PLATINUM, 10, 1.0F);
        // Weapons

        // Ammunition
        registerNew(Types.AMMUNITION_BULLET, "musket_ball", null);
        registerNew(Types.AMMUNITION_BULLET, "silver_bullet", null);
        registerNew(Types.AMMUNITION_BULLET, "tungsten_bullet", null);
        registerNew(Types.AMMUNITION_BULLET, "meteor_shot", null);

        registerNew(Types.AMMUNITION_ARROW, "flaming_arrow", null);
        registerNew(Types.AMMUNITION_ARROW, "frostburn_arrow", null);
        registerNew(Types.AMMUNITION_ARROW, "bone_arrow", null);
        registerNew(Types.AMMUNITION_ARROW, "unholy_arrow", null);
        registerNew(Types.AMMUNITION_ARROW, "jesters_arrow", null);
        registerNew(Types.AMMUNITION_ARROW, "hellfire_arrow", null);

        registerNew(Types.AMMUNITION_DART, "poison_dart", null);

        registerNew(Types.AMMUNITION_SPECIAL, "flare", null);
        registerNew(Types.AMMUNITION_SPECIAL, "blue_flare", null);

        registerNew(Types.AMMUNITION_SPECIAL, "seed", null);
        // Armor
        wooden_armor.put(EquipmentSlot.HEAD, registerNew(Types.ARMOR_HELMET, "wooden_helmet", null, ArmorMaterials.WOOD, "+1 Defense"));
        wooden_armor.put(EquipmentSlot.CHEST, registerNew(Types.ARMOR_CHESTPLATE, "wooden_chestplate", null, ArmorMaterials.WOOD, "+1 Defense"));
        wooden_armor.put(EquipmentSlot.LEGS, registerNew(Types.ARMOR_LEGGINGS, "wooden_leggings", null, ArmorMaterials.WOOD, "+1 Defense"));
        wooden_armor.put(EquipmentSlot.FEET, registerNew(Types.ARMOR_BOOTS, "wooden_boots", null, ArmorMaterials.WOOD, "+1 Defense"));

        mining_armor.put(EquipmentSlot.HEAD, registerNew(Types.ARMOR_HELMET, "mining_helmet", null, ArmorMaterials.MINE, "+20% Mining Speed")); //Not 30% because haste goes in steps of 20%.
        mining_armor.put(EquipmentSlot.CHEST, registerNew(Types.ARMOR_CHESTPLATE, "mining_chestplate", null, ArmorMaterials.MINE, "+20% Mining Speed"));
        mining_armor.put(EquipmentSlot.LEGS, registerNew(Types.ARMOR_LEGGINGS, "mining_leggings", null, ArmorMaterials.MINE, "+20% Mining Speed"));
        mining_armor.put(EquipmentSlot.FEET, registerNew(Types.ARMOR_BOOTS, "mining_boots", null, ArmorMaterials.MINE, "+20% Mining Speed"));
        // Furniture

        // Crafting Stations

        // Coins
        registerNew(Types.COIN, "copper_coin", null);
        registerNew(Types.COIN, "silver_coin", null);
        registerNew(Types.COIN, "gold_coin", null);
        registerNew(Types.COIN, "platinum_coin", null);
        // Ores

        // Bars

        // Accessories

        // Blocks

        // Walls

        // Paint

        // Gems

        // Vanity Items

        // Dyes

        // Potions

        // Statues

        // Wire

        // Pets

        // Mounts

        // Minions

        // Wings

        // Miscellaneous

	}

	// Listener
    private void onServerTick(MinecraftServer server) {
        Iterator<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList().iterator();
        while(players.hasNext()) {
            ServerPlayerEntity player = players.next();
            setBonus(player, wooden_armor, EntityAttributes.GENERIC_ARMOR, 1.0D, Operation.ADDITION, Types.GENERIC_ARMOR_UUID, "wooden_armor");
            setBonus(player, mining_armor, StatusEffects.HASTE, 209, 0);
        }
    }

    // Set Bonuses
    private void setBonus(ServerPlayerEntity player, HashMap armor_set, EntityAttribute attribute, Double value, Operation operation, UUID uuid, String name) {
        Item[] slots = {player.getEquippedStack(EquipmentSlot.HEAD).getItem(),player.getEquippedStack(EquipmentSlot.CHEST).getItem(),player.getEquippedStack(EquipmentSlot.LEGS).getItem(),player.getEquippedStack(EquipmentSlot.FEET).getItem()};
        if (slots[0] == armor_set.get(EquipmentSlot.HEAD) && slots[1] == armor_set.get(EquipmentSlot.CHEST) && slots[2] == armor_set.get(EquipmentSlot.LEGS) && slots[3] == armor_set.get(EquipmentSlot.FEET)) {
            if(player.getAttributeInstance(attribute).getModifier(uuid) == null) {
                player.getAttributeInstance(attribute).addTemporaryModifier(new EntityAttributeModifier(uuid, name, value, operation));
            }
        } else {
            if(player.getAttributeInstance(attribute).getModifier(uuid) != null) {
                player.getAttributeInstance(attribute).removeModifier(new EntityAttributeModifier(uuid, name, value, operation));
            }
        }
    }
    private void setBonus(ServerPlayerEntity player, HashMap armor_set, StatusEffect effect, Integer duration, Integer amplifier) {
        Item[] slots = {player.getEquippedStack(EquipmentSlot.HEAD).getItem(),player.getEquippedStack(EquipmentSlot.CHEST).getItem(),player.getEquippedStack(EquipmentSlot.LEGS).getItem(),player.getEquippedStack(EquipmentSlot.FEET).getItem()};
        if (slots[0] == armor_set.get(EquipmentSlot.HEAD) && slots[1] == armor_set.get(EquipmentSlot.CHEST) && slots[2] == armor_set.get(EquipmentSlot.LEGS) && slots[3] == armor_set.get(EquipmentSlot.FEET)) {
            player.applyStatusEffect(new StatusEffectInstance(effect, duration, amplifier, false, false));
        }
    }
}
