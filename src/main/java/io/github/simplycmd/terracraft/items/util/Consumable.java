package io.github.simplycmd.terracraft.items.util;

import net.minecraft.item.ItemStack;

public interface Consumable {
	public abstract int removeAmount();
	
	public default void onUse(ItemStack stack) {
		stack.decrement(removeAmount());
	}
}
