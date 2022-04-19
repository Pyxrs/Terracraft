package io.github.simplycmd.terracraft.items.util;

import com.simplycmd.featherlib.util.SimpleEnchantment;

import java.util.ArrayList;
import java.util.List;

public interface BaseItem {
    default List<SimpleEnchantment> getEnchantments() {
        return new ArrayList<>();
    };
    default Value getBuyValue() {
        return new Value(0, 0, 0, 0);
    };
    Value getSellValue();
}
