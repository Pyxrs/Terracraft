package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.items.Items;
import io.github.simplycmd.terracraft.items.base.IThowable;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static String MOD_ID = "terracraft";

	public static final Identifier ITEM_MAGIC_MIRROR_USE_ID = new Identifier("terracraft:magic_mirror_sound");
	public static SoundEvent ITEM_MAGIC_MIRROR_USE = new SoundEvent(ITEM_MAGIC_MIRROR_USE_ID);

	public static int throwTicks = 0;

	public static boolean isThrownItemThrown = false;

	@Override
	public void onInitialize() {
		Blocks.RegisterBlocks();
		Items.RegisterItems();
		Registry.register(Registry.SOUND_EVENT, Main.ITEM_MAGIC_MIRROR_USE_ID, ITEM_MAGIC_MIRROR_USE);

		doListeners();
	}

	public void doListeners() {
		UseItemCallback.EVENT.register((player, world, hand) -> {
			if (player.getInventory().getMainHandStack().getItem() instanceof IThowable) {
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
