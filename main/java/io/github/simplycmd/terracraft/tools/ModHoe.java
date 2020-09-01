package io.github.simplycmd.terracraft.tools;

import java.util.List;

import io.github.simplycmd.terracraft.items.ItemTooltips;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ModHoe extends HoeItem {
	Boolean lines;

	public ModHoe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, Boolean tooltip_lines) {
		super(material, attackDamage, attackSpeed, settings);
		lines = tooltip_lines;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		ItemTooltips.Tooltipper(stack, tooltip, lines);
	}
}