package io.github.simplycmd.terracraft.items.util;

import io.github.simplycmd.simplylib.util.EnchantmentWithLevel;
import net.minecraft.enchantment.Enchantments;

import java.util.ArrayList;
import java.util.List;

public interface IItem {
    default List<EnchantmentWithLevel> getEnchantments() {
        return new ArrayList<>();
    };
    default Value getBuyValue() {
        return new Value(0, 0, 0, 0);
    };
    Value getSellValue();
}
