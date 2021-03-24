package io.github.simplycmd.terracraft.items.base;

import net.minecraft.item.ItemStack;

public abstract interface IConsumable {

	public abstract int removeAmount();
	
	public default void onUse(ItemStack stack) {
		stack.decrement(removeAmount());
	}
}
