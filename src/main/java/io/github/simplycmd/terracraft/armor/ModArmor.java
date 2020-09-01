package io.github.simplycmd.terracraft.armor;

import java.util.List;

import io.github.simplycmd.terracraft.items.ItemTooltips;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ModArmor extends ArmorItem {
	Boolean tip;
	Integer defenseStat;
	String set_bonus;
	Boolean bonus_enabled;
	Boolean vanity_enabled;

	public ModArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings, Boolean tooltip_bool, Boolean vanity, Integer defense) {
		super(material, slot, settings);
		tip = tooltip_bool;
		defenseStat = defense;
		bonus_enabled = false;
		vanity_enabled = vanity;
	}

	public ModArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings, Boolean tooltip_bool, Boolean vanity, Integer defense, String bonus) {
		super(material, slot, settings);
		tip = tooltip_bool;
		defenseStat = defense;
		set_bonus = bonus;
		bonus_enabled = true;
		vanity_enabled = vanity;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		if (bonus_enabled) {
			ItemTooltips.TooltipperArmor(stack, tooltip, tip, vanity_enabled, defenseStat, set_bonus);
		} else {
			ItemTooltips.TooltipperArmor(stack, tooltip, tip, vanity_enabled, defenseStat);
		}
	}
}