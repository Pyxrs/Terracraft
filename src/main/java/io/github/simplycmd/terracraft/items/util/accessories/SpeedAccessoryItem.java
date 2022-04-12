package io.github.simplycmd.terracraft.items.util.accessories;

import java.util.UUID;

import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

public class SpeedAccessoryItem extends AccessoryItem {
    private final float increasePercent;

    public SpeedAccessoryItem(float increasePercent, FabricItemSettings settings) {
        super(settings);
        this.increasePercent = increasePercent;
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, this.getTranslationKey() + "_speed", increasePercent, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;
    }
}
