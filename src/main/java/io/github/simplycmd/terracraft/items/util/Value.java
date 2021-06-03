package io.github.simplycmd.terracraft.items.util;

import io.github.simplycmd.simplylib.BetterEnchantment;
import io.github.simplycmd.terracraft.items.util.IItem;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;

import java.util.ArrayList;
import java.util.List;

public class Value {
    @Getter
    int platinum;

    @Getter
    int gold;

    @Getter
    int silver;

    @Getter
    int copper;

    public Value(int platinum, int gold, int silver, int copper) {
        this.platinum = platinum;
        this.gold = gold;
        this.silver = silver;
        this.copper = copper;
    }
}
