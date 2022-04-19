package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.items.util.BaseItem;
import io.github.simplycmd.terracraft.items.util.Value;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CoinItem extends Item implements BaseItem {
    final Value value;
    final Item next;

    public CoinItem(Value value, Item next) {
        super(new FabricItemSettings().group(ItemGroup.MISC));
        this.value = value;
        this.next = next;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }

    @Override
    public Value getSellValue() {
        return value;
    }
}
