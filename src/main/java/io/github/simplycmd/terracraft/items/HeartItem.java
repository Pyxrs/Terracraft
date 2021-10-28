package io.github.simplycmd.terracraft.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HeartItem extends Item {
    public HeartItem() {
        super(new FabricItemSettings());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        int count = stack.getCount();
        stack.decrement(count);
        if (entity instanceof LivingEntity) ((LivingEntity) entity).heal(count * 2);
    }
}