package io.github.simplycmd.terracraft.items.accessories;

import java.util.ArrayList;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import lombok.Getter;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CombinationAccessoryItem extends AccessoryItem {
    @Getter
    public final ArrayList<AccessoryItem> ingredients;

    public CombinationAccessoryItem(ArrayList<AccessoryItem> ingredients, FabricItemSettings settings) {
        super(settings);
        this.ingredients = ingredients;
    }
    
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        for (AccessoryItem ingredient : ingredients) ingredient.inventoryTick(stack, world, entity, slot, selected);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        HashMultimap<EntityAttribute, EntityAttributeModifier> map = HashMultimap.create();
        for (AccessoryItem ingredient : ingredients) map.putAll(ingredient.getModifiers(stack, slot, entity, uuid));
        return map;
    }
}
