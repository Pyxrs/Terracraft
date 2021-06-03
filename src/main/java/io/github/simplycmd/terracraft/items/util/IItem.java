package io.github.simplycmd.terracraft.items.util;

import io.github.simplycmd.simplylib.BetterEnchantment;

import java.util.List;

public interface IItem {
    List<BetterEnchantment> getEnchantments();
    default Value getBuyValue() {
        return new Value(0, 0, 0, 0);
    };
    Value getSellValue();
}
