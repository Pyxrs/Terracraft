package io.github.simplycmd.terracraft.items;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ModItem extends Item {
    Boolean tip;

    public ModItem(Settings settings, Boolean tooltip) {
        super(settings);
        tip = tooltip;
    }
 
    @Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		ItemTooltips.Tooltipper(stack, tooltip, tip);
	}
}