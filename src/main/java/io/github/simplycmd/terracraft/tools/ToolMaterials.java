package io.github.simplycmd.terracraft.tools;

//Credit to KWPUGH (Owner of More Gems) for this simple design
import java.util.function.Supplier;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

public enum ToolMaterials implements ToolMaterial
{
   /**Pickaxe Power Chart:
    * 2 - 35%-49% (Most pre-hardmode ores)
    * 3 - 50%-54% (Meteorite)
    * 4 - 55%-64% (Demonite+Crimtane)
    * 5 - 65%-99% (Hellstone, Ebon/Pearl/Crimstone)
    * 6 - 100%-109% (Cobalt/Palladium)
    * 7 - 110%-149% (Mythril/Orichalcum)
    * 8 - 150%-199% (Adamantite/Titanium)
    * 9 - 200%-209% (Chlorophyte)
    * 10 - 210%+ (Lihzahrd Brick)
    */
	CACTUS(2, 59, 4.0F, 0.0F, 15, () -> {
      return Ingredient.ofItems(Items.CACTUS);
   }),

   COPPER(2, 200, 5.0F, 1.0F, 14, () -> {
      return Ingredient.ofItems(Items.IRON_INGOT); // Replace repear item with correct one when added
   }),
   TIN(2, 200, 5.5F, 1.0F, 14, () -> {
      return Ingredient.ofItems(Items.IRON_INGOT); // Replace repear item with correct one when added
   }),

   LEAD(2, 250, 6.0F, 2.0F, 14, () -> {
      return Ingredient.ofItems(Items.IRON_INGOT); // Replace repear item with correct one when added
   }),

   SILVER(2, 950, 6.5F, 2.0F, 12, () -> {
      return Ingredient.ofItems(Items.IRON_INGOT); // Replace repear item with correct one when added
   }),
   TUNGSTEN(3, 1000, 7.0F, 2.0F, 12, () -> {
      return Ingredient.ofItems(Items.IRON_INGOT); // Replace repear item with correct one when added
   }),

   PLATINUM(4, 1500, 8.0F, 3.0F, 10, () -> {
      return Ingredient.ofItems(Items.IRON_INGOT); // Replace repear item with correct one when added
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