package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.items.Items;
import io.github.simplycmd.terracraft.items.base.IThrowable;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;

public class Main implements ModInitializer {
	public static String MOD_ID = "terracraft";

	public static int throwTicks = 0;

	public static boolean isThrownItemThrown = false;

	@Override
	public void onInitialize() {
		Blocks.RegisterBlocks();
		Items.RegisterItems();
		Sounds.RegisterSounds();
		doListeners();
	}

	public void doListeners() {
		UseItemCallback.EVENT.register((player, world, hand) -> {
			if (player.getInventory().getMainHandStack().getItem() instanceof IThrowable) {
				tickThrowFrames();
				return TypedActionResult.pass(player.getInventory().getMainHandStack());
			} else {
				return TypedActionResult.pass(ItemStack.EMPTY);
			}
		});
	}

	public void tickThrowFrames() {
		WorldTickCallback.EVENT.register((world) -> {
			if (isThrownItemThrown) {
				throwTicks += 1;
			} else {
				return;
			}
		});
	}

	/*
	RECIPES:

	Convert use time: T / 3 = M
	Convert health: T / 10 = M
	 */
}
