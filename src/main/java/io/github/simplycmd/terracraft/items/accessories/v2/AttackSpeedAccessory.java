package io.github.simplycmd.terracraft.items.accessories.v2;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class AttackSpeedAccessory implements Accessory {
    private final float speed;
    public AttackSpeedAccessory(float speed) {
        this.speed = speed;
    }
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = Accessory.super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(uuid, "terracraft:attack_speed", speed, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;
    }
}
