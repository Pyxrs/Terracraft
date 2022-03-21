package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.blocks.BlockRenderer;
import io.github.simplycmd.terracraft.registry.BlockRegistry;
import io.github.simplycmd.terracraft.registry.EntityRegistry;
import io.github.simplycmd.terracraft.registry.FeatureRegistry;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import io.github.simplycmd.terracraft.registry.SoundRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer, ClientModInitializer {
	public static String MOD_ID = "terracraft";

	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
		SoundRegistry.register();
		FeatureRegistry.register();
		//Resources.RESOURCE_PACK.dump();
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.register();
		BlockRenderer.addBlocks();
	}

	public static Identifier ID(String id) {
        return new Identifier(Main.MOD_ID, id);
    }

	//Convert health/damage: T / 10 = M (rounded up)
}