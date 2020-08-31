package io.github.simplycmd.terracraft.armor;
//Credit to KWPUGH (Owner of More Gems) for this simple design
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class ModArmor extends ArmorItem
{
	public ModArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
	{
		super(material, slot, settings);
	}
}