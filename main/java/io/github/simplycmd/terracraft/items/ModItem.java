package io.github.simplycmd.terracraft.items;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ModItem extends Item {
    Integer lines;

    public ModItem(Settings settings, Integer tooltip_lines) {
        super(settings);
        lines = tooltip_lines;
    }
 
    @Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		ItemTooltips.Tooltipper(stack, tooltip, lines);
	}
}