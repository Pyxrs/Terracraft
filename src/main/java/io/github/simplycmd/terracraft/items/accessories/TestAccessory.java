package io.github.simplycmd.terracraft.items.accessories;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TestAccessory implements Accessory {
    private final AccessoryItem item;
    public TestAccessory(AccessoryItem item) {
        this.item = item;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        Accessory.super.inventoryTick(stack, world, entity, slot, selected);
        System.out.println(item.toString());
    }
}
