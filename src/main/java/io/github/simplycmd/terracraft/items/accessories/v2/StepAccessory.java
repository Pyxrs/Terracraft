package io.github.simplycmd.terracraft.items.accessories.v2;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.UUID;

public class StepAccessory implements Accessory {
    private final Block stepBlock;

    public StepAccessory(Block stepBlock) {
        this.stepBlock = stepBlock;
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = Accessory.super.getModifiers(stack, slot, entity, uuid);
        var increasePercent = 0;
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "terracraft_step_accessory" + "_speed", increasePercent, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity.isInSneakingPose() && !entity.isOnGround()) {
            if (world.getBlockState(entity.getBlockPos().down(1)).getBlock() != Blocks.AIR) {
                world.setBlockState(entity.getBlockPos(), stepBlock.getDefaultState());
                entity.teleport(entity.getX(), entity.getY() + 1, entity.getZ());
            }
            else if (world.getBlockState(entity.getBlockPos().down(2)).getBlock() != Blocks.AIR) {
                world.setBlockState(entity.getBlockPos().down(1), stepBlock.getDefaultState());
            }
        }
    }
}
