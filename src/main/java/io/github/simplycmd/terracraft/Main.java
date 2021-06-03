package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.registry.BlockRegistry;
import io.github.simplycmd.terracraft.entities.Entities;
import io.github.simplycmd.terracraft.features.Features;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static String MOD_ID = "terracraft";

	public static Identifier ID(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ItemRegistry.register();
		BlockRegistry.register();
		Entities.RegisterEntities();
		Sounds.RegisterSounds();
		Features.RegisterFeatures();
	}

	//Convert health: T / 10 = M
}
