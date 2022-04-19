package io.github.simplycmd.terracraft.items.accessories;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class SpeedAccessory implements Accessory {
    private final float increasePercent;

    public SpeedAccessory(float increasePercent) {
        this.increasePercent = increasePercent;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = Accessory.super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "terracraft_speed_accessory" + "_speed", increasePercent, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;
    }
}
