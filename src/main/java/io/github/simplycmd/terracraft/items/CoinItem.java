package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.simplylib.BetterEnchantment;
import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;

import java.util.ArrayList;
import java.util.List;

public class CoinItem extends Item implements IItem {
    Value value;

    public CoinItem(Value value) {
        super(new FabricItemSettings().group(ItemGroup.MISC));
        this.value = value;
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
