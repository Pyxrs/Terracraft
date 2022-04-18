package io.github.simplycmd.terracraft.util;

import io.github.simplycmd.terracraft.items.util.BaseItem;
import io.github.simplycmd.terracraft.items.util.Value;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class Offer {
    private final ItemStack item;
    private final Value value;
    public Offer(ItemStack item, Value value) {
        this.item = item;
        this.value = value;
    }

    public Offer(BaseItem item) {
        this.item = ((Item)item).getDefaultStack();
        this.value = item.getBuyValue();
    }

    public Offer(NbtCompound compound) {
        this.item = ItemStack.fromNbt(compound.getCompound("item"));
        this.value = new Value(compound.getInt("value"));
    }

    public ItemStack getItem() {
        return item;
    }

    public boolean isDisabled() {
        return false;
    }

    public Value getValue() {
        return value;
    }

}
