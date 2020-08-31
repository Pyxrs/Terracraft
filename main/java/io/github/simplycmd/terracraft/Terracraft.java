package io.github.simplycmd.terracraft;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.UUIDs;
import io.github.simplycmd.terracraft.armor.ArmorMaterials;
import io.github.simplycmd.terracraft.armor.ModArmor;
import io.github.simplycmd.terracraft.items.WoodenArmor;
import io.github.simplycmd.terracraft.tools.ModAxe;
import io.github.simplycmd.terracraft.tools.ModHoe;
import io.github.simplycmd.terracraft.tools.ModPickaxe;
import io.github.simplycmd.terracraft.tools.ModShovel;
import io.github.simplycmd.terracraft.tools.ModSword;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Terracraft extends Registers implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "terracraft";
    public static final String MOD_NAME = "Terracraft";

    private HashMap wooden_armor;

    @Override
    public void onInitialize() {
        ServerTickCallback.EVENT.register(this::onServerTick);

        log(Level.INFO, "Initializing");

        // Tools

        // Weapons

        // Ammunition
        registerAmmo("musket_ball");
        registerAmmo("silver_bullet");
        registerAmmo("tungsten_bullet");
        registerAmmo("meteor_shot");

        registerAmmo("flaming_arrow");
        registerAmmo("frostburn_arrow");
        registerAmmo("bone_arrow");
        registerAmmo("unholy_arrow");
        registerAmmo("jesters_arrow");
        registerAmmo("hellfire_arrow");

        registerAmmo("poison_dart");

        registerAmmo("flare");
        registerAmmo("blue_flare");

        registerAmmo("seed");
        // Armor
        wooden_armor = registerArmor(ArmorMaterials.WOOD);
        // Furniture

        // Crafting Stations

        // Coins
        registerItem("copper_coin", ItemGroup.MISC, 64);
        registerItem("silver_coin", ItemGroup.MISC, 64);
        registerItem("gold_coin", ItemGroup.MISC, 64);
        registerItem("platinum_coin", ItemGroup.MISC, 64);
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

    // Tooltips

    /*@Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.tutorial.fabric_item.tooltip"));
    }*/

    // Logger

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    // Listener
    
    private void onServerTick(MinecraftServer server) {
        Iterator<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList().iterator();
        while(players.hasNext()) {
            ServerPlayerEntity player = players.next();
            setBonus(player, "wooden_armor", wooden_armor, EntityAttributes.GENERIC_ARMOR, 1.0D, Operation.ADDITION, UUIDs.GENERIC_ARMOR_UUID);
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