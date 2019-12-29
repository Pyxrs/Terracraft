package com.github.SimplyCmd.terracraft;

import com.github.SimplyCmd.terracraft.lists.BlockList;
import com.github.SimplyCmd.terracraft.lists.ItemList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MeleeItemGroup extends ItemGroup {
    public MeleeItemGroup() {
        super("melee");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Item.BLOCK_TO_ITEM.get(ItemList.iron_shortsword));
    }
}
