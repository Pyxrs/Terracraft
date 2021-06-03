package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.simplylib.BetterEnchantment;
import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;

import java.util.ArrayList;
import java.util.List;

public class CoinItem extends SwordItem implements IItem {
    Value value;

    public CoinItem(Value value) {
        super(ToolMaterials.IRON, 4, -2.0F, new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
        this.value = value;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }

    @Override
    public List<BetterEnchantment> getEnchantments() {
        List<BetterEnchantment> list = new ArrayList<BetterEnchantment>();
        list.add(new BetterEnchantment(Enchantments.KNOCKBACK, 10));
        return list;
    }

    @Override
    public Value getSellValue() {
        return value;
    }
}
