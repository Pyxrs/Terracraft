package io.github.simplycmd.terracraft.items.accessories;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;

public class SpeedOnBlockAccessory implements Accessory {

    private final UUID specilUUID = UUID.fromString("d70657a0-c05d-11ec-9d64-0242ac120002");
    private final float increasePercent;
    private final TagKey<Block> tag;

    public SpeedOnBlockAccessory(TagKey<Block> tag, float increasePercent) {
        this.increasePercent = increasePercent;
        this.tag = tag;
    }

//    @Override
//    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
//        var modifiers = Accessory.super.getModifiers(stack, slot, entity, uuid);
//        System.out.println("called" + Math.random()*Math.random()*Math.random()*Math.random());
//        if (entity.isOnGround() && entity.getWorld().getBlockState(entity.getBlockPos().down()).isIn(tag)) {
//            System.out.println("PUT");
//            modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "terracraft_speed_accessory" + "_speed", increasePercent, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
//        }
//        return modifiers;
//    }

    @Override
    public void itemTick(ItemStack stack, World world, LivingEntity entity) {
        Accessory.super.itemTick(stack, world, entity);
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = Multimaps.newMultimap(Maps.newLinkedHashMap(), ArrayList::new);
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(specilUUID, "terracraft_speed_accessory" + "_speed", increasePercent, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        if (entity.isOnGround() && entity.world.getBlockState(entity.getBlockPos().down()).isIn(this.tag))
        entity.getAttributes().addTemporaryModifiers(modifiers);
        else
        entity.getAttributes().removeModifiers(modifiers);
    }
}
