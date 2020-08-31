package io.github.simplycmd.terracraft.tools;
//Credit to KWPUGH (Owner of More Gems) for this simple design
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class ModPickaxe extends PickaxeItem
{
	public ModPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings)
	{
		super(material, attackDamage, attackSpeed, settings);
	}
}