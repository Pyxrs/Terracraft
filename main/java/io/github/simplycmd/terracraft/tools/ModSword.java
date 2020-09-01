package io.github.simplycmd.terracraft.tools;

import java.util.List;

import io.github.simplycmd.terracraft.items.ItemTooltips;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ModSword extends SwordItem {
	Integer lines;

	public ModSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, Integer tooltip_lines) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
		lines = tooltip_lines;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		ItemTooltips.Tooltipper(stack, tooltip, lines);
	}
}