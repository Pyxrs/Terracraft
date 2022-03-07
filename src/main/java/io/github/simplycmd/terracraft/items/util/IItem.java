package io.github.simplycmd.terracraft.items.util;

import java.util.ArrayList;
import java.util.List;

import com.simplycmd.featherlib.util.SimpleEnchantment;

public interface IItem {
    default List<SimpleEnchantment> getEnchantments() {
        return new ArrayList<>();
    };
    default Value getBuyValue() {
        return new Value(0, 0, 0, 0);
    };
    Value getSellValue();
}
