package io.github.simplycmd.terracraft.tools;

import java.util.List;

import io.github.simplycmd.terracraft.items.ItemTooltips;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ModPickaxe extends PickaxeItem {
	Boolean lines;

	public ModPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, Boolean tooltip_lines) {
		super(material, attackDamage, attackSpeed, settings);
		lines = tooltip_lines;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		ItemTooltips.Tooltipper(stack, tooltip, lines);
	}
}