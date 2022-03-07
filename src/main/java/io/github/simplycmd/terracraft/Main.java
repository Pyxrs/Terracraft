package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.blocks.BlockRenderer;
import io.github.simplycmd.terracraft.registry.BlockRegistry;
import io.github.simplycmd.terracraft.registry.EntityRegistry;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import io.github.simplycmd.terracraft.registry.SoundRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import java.util.ArrayList;

import com.simplycmd.featherlib.registry.Resources;

public class Main implements ModInitializer, ClientModInitializer {
	public static String MOD_ID = "terracraft";

	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
		SoundRegistry.register();
		Resources.RESOURCE_PACK.dump();
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.register();
		BlockRenderer.addBlocks();
	}

	//Convert health/damage: T / 10 = M (rounded up)

	private static ArrayList<Runnable> tasks = new ArrayList<>();

	public static void delay(Runnable task) {
		tasks.add(task);
	}

	public static void postInit() {
		// Runs after the normal fabric server is initialized
		for (Runnable task : tasks) {
			task.run();
		}
		tasks.clear();
	}
}