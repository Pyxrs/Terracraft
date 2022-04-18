package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.blocks.BlockRenderer;
import io.github.simplycmd.terracraft.data.OfferManager;
import io.github.simplycmd.terracraft.gui.BuyScreen;
import io.github.simplycmd.terracraft.packets.PacketHandler;
import io.github.simplycmd.terracraft.recipes.MoneyConversionRecipe;
import io.github.simplycmd.terracraft.registry.*;
import io.github.simplycmd.terracraft.util.ParticleUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.recipe.ArmorDyeRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
		ScreenHandlerRegistry.registerScreenHandlers();
		PacketHandler.registerServerPackets();
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(OfferManager.instance());
		//Resources.RESOURCE_PACK.dump();
	}

	@Override
	public void onInitializeClient() {
		EntityRegistry.register();
		BlockRenderer.addBlocks();
		PacketHandler.registerClientPackets();
		ClientTickEvents.END_CLIENT_TICK.register(client-> {
			if(MinecraftClient.getInstance().options.jumpKey.isPressed()) {
			}
		});

		HandledScreens.register(ScreenHandlerRegistry.BUY_SCREEN_HANDLER, BuyScreen::new);
	}

	public static Identifier ID(String id) {
        return new Identifier(Main.MOD_ID, id);
    }

	//Convert health/damage: T / 10 = M (rounded up)
}