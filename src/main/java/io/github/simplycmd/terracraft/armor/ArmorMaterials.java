package io.github.simplycmd.terracraft.armor;
//Credit to KWPUGH (Owner of More Gems) for this simple design
import java.util.function.Supplier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

public enum ArmorMaterials implements ArmorMaterial
{
	WOOD("wooden", 1.2F, new int[]{0, 1, 2, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> {
		return Ingredient.ofItems(Items.OAK_LOG);
	}),

	MINE("mining", 1.0F, new int[]{0, 1, 2, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.ofItems(Items.LEATHER);
	});

	private static final int[] BASE_DURABILITY = new int[]{65, 75, 80, 55}; // Leather armor durability
	private final String name;
	private final float durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairIngredientSupplier;

	private ArmorMaterials(String name, float durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> supplier)
	{
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.protectionAmounts = protectionAmounts;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredientSupplier = new Lazy(supplier);
	}

	public int getDurability(EquipmentSlot slot)
	{
		return Math.round(BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier);
	}

	public int getProtectionAmount(EquipmentSlot slot)
	{
		return this.protectionAmounts[slot.getEntitySlotId()];
	}

	public int getEnchantability()
	{
		return this.enchantability;
	}

	public SoundEvent getEquipSound()
	{
		return this.equipSound;
	}

	public Ingredient getRepairIngredient()
	{
		return (Ingredient)this.repairIngredientSupplier.get();
	}

	@Environment(EnvType.CLIENT)
	public String getName()
	{
		return this.name;
	}

	public float getToughness()
	{
		return this.toughness;
	}

	public float getKnockbackResistance()
	{
		return this.knockbackResistance;
	}
}