package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class UmbrellaItem extends SwordItem implements IItem {
    public UmbrellaItem() {
        super(ToolMaterials.IRON, 1, -2.0F, new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            if (livingEntity.getStackInHand(Hand.MAIN_HAND).equals(stack) || livingEntity.getStackInHand(Hand.OFF_HAND).equals(stack) && !livingEntity.isSneaking()) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 9, 0, true, false, false));
            }
        }
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 0, 20, 0);
    }
}
