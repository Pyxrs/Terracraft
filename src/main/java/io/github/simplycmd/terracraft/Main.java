package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.items.Items;
import io.github.simplycmd.terracraft.items.MirrorItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static String MOD_ID = "terracraft";

	public static final Identifier ITEM_MAGIC_MIRROR_USE_ID = new Identifier("terracraft:magic_mirror_sound");
	public static SoundEvent ITEM_MAGIC_MIRROR_USE = new SoundEvent(ITEM_MAGIC_MIRROR_USE_ID);

	@Override
	public void onInitialize() {
		Blocks.RegisterBlocks();
		Items.RegisterItems();
		Registry.register(Registry.SOUND_EVENT, Main.ITEM_MAGIC_MIRROR_USE_ID, ITEM_MAGIC_MIRROR_USE);
	}

	/*
	RECIPES:

	Convert use time: T / 3 = M
	Convert health: T / 10 = M
	 */
}
