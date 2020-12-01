package io.github.simplycmd.terracraft.items;

import java.util.List;

import io.github.simplycmd.terracraft.Terracraft;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class ModItem extends Item {
    public ModItem(Settings settings) {
        super(settings);
    }
    
    @Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText(stack.getTranslationKey() + ".tooltip"));
    }

}