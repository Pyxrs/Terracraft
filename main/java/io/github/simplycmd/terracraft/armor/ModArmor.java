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
	Integer lines;
	Integer defenseArray;

	public ModArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings, Integer tooltip_lines, Integer defense) {
		super(material, slot, settings);
		lines = tooltip_lines;
		defenseArray = defense;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		ItemTooltips.Tooltipper(stack, tooltip, lines, defenseArray);
	}
}