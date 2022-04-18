package io.github.simplycmd.terracraft.items.accessories;

import java.util.UUID;

import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class StepAccessoryItem extends AccessoryItem {
    //private final Block stepBlock;

    public StepAccessoryItem(/*Block stepBlock, */FabricItemSettings settings) {
        super(settings);
        //this.stepBlock = stepBlock;
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        //modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, this.getTranslationKey() + "_speed", increasePercent, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        //a
    }
}
