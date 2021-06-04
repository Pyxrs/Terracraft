package io.github.simplycmd.terracraft.items.util;

import io.github.simplycmd.simplylib.BetterEnchantment;
import net.minecraft.enchantment.Enchantments;

import java.util.ArrayList;
import java.util.List;

public interface IItem {
    default List<BetterEnchantment> getEnchantments() {
        return new ArrayList<BetterEnchantment>();
    };
    default Value getBuyValue() {
        return new Value(0, 0, 0, 0);
    };
    Value getSellValue();
}
