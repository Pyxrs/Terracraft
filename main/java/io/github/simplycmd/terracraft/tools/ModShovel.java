package io.github.simplycmd.terracraft.tools;
//Credit to KWPUGH (Owner of More Gems) for this simple design
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class ModShovel extends ShovelItem
{
	public ModShovel(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings)
	{
		super(material, attackDamage, attackSpeed, settings);
	}
}