package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.registry.BlockRegistry;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
	public static String MOD_ID = "terracraft";

	@Override
	public void onInitialize() {
		ItemRegistry.register();
		BlockRegistry.register();
		Sounds.RegisterSounds();
	}

	//Convert health: T / 10 = M
}
