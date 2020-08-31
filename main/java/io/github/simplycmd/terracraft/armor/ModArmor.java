package io.github.simplycmd.terracraft.armor;

import io.github.simplycmd.terracraft.items.ItemTooltips;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class ModArmor extends ArmorItem
{
	public ModArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
	{
		super(material, slot, settings);
	}

	@Override
	public void appendTooltip(net.minecraft.item.ItemStack stack, net.minecraft.world.World world, java.util.List<net.minecraft.text.Text> tooltip, net.minecraft.client.item.TooltipContext context) {
		ItemTooltips.Tooltipper(stack, tooltip);
	}
}