package io.github.simplycmd.terracraft.ToolsArmor;
//Credit to KWPUGH (Owner of More Gems) for this simple design
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ModSword extends SwordItem
{
	public ModSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}
}