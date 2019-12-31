package com.github.SimplyCmd.terracraft;

import com.github.SimplyCmd.terracraft.lists.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TerracraftItemGroup extends ItemGroup {
    public TerracraftItemGroup() {
        super("TERRACRAFT");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemList.angel_statue);
        //return new ItemStack(Item.BLOCK_TO_ITEM.get(ItemList.BLOCK)); for block icon
    }
}
