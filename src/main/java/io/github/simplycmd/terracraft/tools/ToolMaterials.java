package io.github.simplycmd.terracraft.tools;
//Credit to KWPUGH (Owner of More Gems) for this simple design
import java.util.function.Supplier;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

public enum ToolMaterials implements ToolMaterial
{
	/*TOPAZ(2, 1248, 6.0F, 2.0F, 14, () -> {
        return Ingredient.ofItems(Terracraft.TOPAZ);
		}),*/
    EMERALD(2, 1170, 6.0F, 2.0F, 14, () -> {
        return Ingredient.ofItems(Items.EMERALD);
	   });

   private final int miningLevel;
   private final int itemDurability;
   private final float miningSpeed;
   private final float attackDamage;
   private final int enchantability;
   private final Lazy<Ingredient> repairIngredient;

   private ToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantibility, Supplier<Ingredient> repairIngredient)
   {
      this.miningLevel = miningLevel;
      this.itemDurability = itemDurability;
      this.miningSpeed = miningSpeed;
      this.attackDamage = attackDamage;
      this.enchantability = enchantibility;
      this.repairIngredient = new Lazy(repairIngredient);
   }

   public int getDurability()
   {
      return this.itemDurability;
   }

   public float getMiningSpeedMultiplier()
   {
      return this.miningSpeed;
   }

   public float getAttackDamage()
   {
      return this.attackDamage;
   }

   public int getMiningLevel()
   {
      return this.miningLevel;
   }

   public int getEnchantability()
   {
      return this.enchantability;
   }

   public Ingredient getRepairIngredient()
   {
      return (Ingredient)this.repairIngredient.get();
   }
}