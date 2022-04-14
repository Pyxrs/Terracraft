package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.blocks.BlockRenderer;
import io.github.simplycmd.terracraft.packets.PacketHandler;
import io.github.simplycmd.terracraft.recipes.MoneyConversionRecipe;
import io.github.simplycmd.terracraft.registry.*;
import io.github.simplycmd.terracraft.util.ParticleUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.ArmorDyeRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer, ClientModInitializer {
	public static String MOD_ID = "terracraft";

	@Override
	public void onInitialize() {
		ParticleUtils.particleTypes();
		BlockRegistry.register();
		ItemRegistry.register();
		SoundRegistry.register();
		FeatureRegistry.register();
		TrinketsRegistry.register();
		RecipeRegistry.registerRecipeSerializers();
		PacketHandler.registerServerPackets();
		//Resources.RESOURCE_PACK.dump();
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.register();
		BlockRenderer.addBlocks();
		PacketHandler.registerClientPackets();
		ClientTickEvents.END_CLIENT_TICK.register(client-> {
			if(MinecraftClient.getInstance().options.jumpKey.isPressed()) {
				//PacketHandler.sendToServer();
			}
		});
	}

	public static Identifier ID(String id) {
        return new Identifier(Main.MOD_ID, id);
    }

	//Convert health/damage: T / 10 = M (rounded up)
}