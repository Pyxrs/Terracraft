package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.blocks.BlockRenderer;
import io.github.simplycmd.terracraft.registry.BlockReg;
import io.github.simplycmd.terracraft.registry.EntityReg;
import io.github.simplycmd.terracraft.registry.ItemReg;
import io.github.simplycmd.terracraft.registry.SoundReg;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer, ClientModInitializer {
	public static String MOD_ID = "terracraft";

	@Override
	public void onInitialize() {
		ItemReg.register();
		BlockReg.register();
		SoundReg.register();
	}

	@Override
	public void onInitializeClient() {
		EntityReg.register();
		BlockRenderer.addBlocks();
	}

	//Convert health: T / 10 = M
}