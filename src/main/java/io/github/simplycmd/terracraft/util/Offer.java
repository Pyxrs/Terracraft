package io.github.simplycmd.terracraft.util;

import io.github.simplycmd.terracraft.items.util.Value;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.village.TradeOffer;

public class Offer {
    private final Item item;
    private final Value value;
    public Offer(Item item, Value value) {
        this.item = item;
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public boolean isDisabled() {
        return false;
    }

    public Value getValue() {
        return value;
    }
}
