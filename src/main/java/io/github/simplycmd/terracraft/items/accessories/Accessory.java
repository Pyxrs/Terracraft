package io.github.simplycmd.terracraft.items.accessories;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;

public interface Accessory {
    /**
     * This method returns the modifiers of the {@link Accessory}.<br/>
     * This method is not meant to be called, it is just meant to be overridden
     */
    default Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        return Multimaps.newMultimap(Maps.newLinkedHashMap(), ArrayList::new);
    }

    default void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
    }

    default void itemTick(ItemStack stack, World world, LivingEntity entity) {

    }

    default AccessoryItem create(FabricItemSettings settings) {
        return AccessoryItem.builder().addAccessory(this).settings(settings).build();
    }
}