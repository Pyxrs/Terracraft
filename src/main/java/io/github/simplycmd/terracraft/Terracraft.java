package io.github.simplycmd.terracraft;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.simplycmd.terracraft.armor.ArmorMaterials;
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
import net.minecraft.item.ItemGroup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class Terracraft extends Registers implements ModInitializer {
	public static final String MOD_ID = "terracraft";
    public static final String MOD_NAME = "Terracraft";

    private HashMap wooden_armor;
	private HashMap mining_armor;
	
	@Override
	public void onInitialize() {
		ServerTickCallback.EVENT.register(this::onServerTick);

		// Tools

        // Weapons

        // Ammunition
        registerAmmo("musket_ball", false);
        registerAmmo("silver_bullet", false);
        registerAmmo("tungsten_bullet", false);
        registerAmmo("meteor_shot", false);

        registerAmmo("flaming_arrow", false);
        registerAmmo("frostburn_arrow", false);
        registerAmmo("bone_arrow", false);
        registerAmmo("unholy_arrow", false);
        registerAmmo("jesters_arrow", false);
        registerAmmo("hellfire_arrow", false);

        registerAmmo("poison_dart", true);

        registerAmmo("flare", false);
        registerAmmo("blue_flare", false);

        registerAmmo("seed", true);
        // Armor
        mining_armor = registerArmor(ArmorMaterials.MINE, false, false, "+20% Mining Speed"); //Not 30% because haste goes in steps of 20%.
        wooden_armor = registerArmor(ArmorMaterials.WOOD, false, false, "+1 Defense");
        // Furniture

        // Crafting Stations

        // Coins
        registerItem("copper_coin", ItemGroup.MISC, 64, false);
        registerItem("silver_coin", ItemGroup.MISC, 64, false);
        registerItem("gold_coin", ItemGroup.MISC, 64, false);
        registerItem("platinum_coin", ItemGroup.MISC, 64, false);
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
            setBonus(player, "wooden_armor", wooden_armor, EntityAttributes.GENERIC_ARMOR, 1.0D, Operation.ADDITION, UUIDs.GENERIC_ARMOR_UUID);
            setBonus(player, mining_armor, StatusEffects.HASTE, 209, 1);
        }
    }

    // Set Bonuses

    private void setBonus(ServerPlayerEntity player, String name, HashMap armor_set, EntityAttribute attribute, Double value, Operation operation, UUID uuid) {
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
