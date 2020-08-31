package io.github.simplycmd.terracraft.tools;
//Credit to KWPUGH (Owner of More Gems) for this simple design
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class ModHoe extends HoeItem
{
	public ModHoe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings)
	{
		super(material, attackDamage, attackSpeed, settings);
	}
}