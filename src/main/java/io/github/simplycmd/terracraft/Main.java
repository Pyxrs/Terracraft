package io.github.simplycmd.terracraft;

import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.simplycmd.terracraft.blocks.BlockRenderer;
import io.github.simplycmd.terracraft.data.OfferManager;
import io.github.simplycmd.terracraft.gui.BuyScreen;
import io.github.simplycmd.terracraft.packets.PacketHandler;
import io.github.simplycmd.terracraft.registry.*;
import io.github.simplycmd.terracraft.util.LivingEntityExtension;
import io.github.simplycmd.terracraft.util.ParticleUtil;
import io.github.simplycmd.terracraft.util.PlayerEntityExtension;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class Main implements ModInitializer, ClientModInitializer {
	public static String MOD_ID = "terracraft";
	public static final TrackedDataHandler<Long> LONG_HANDLER = new TrackedDataHandler<Long>() {
		@Override
		public void write(PacketByteBuf buf, Long value) {
			buf.writeLong(value);
		}

		@Override
		public Long read(PacketByteBuf buf) {
			return buf.readLong();
		}

		@Override
		public Long copy(Long value) {
			return value;
		}
	};

	@Override
	public void onInitialize() {
		// dev testing command
		CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
			LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder = CommandManager.literal("money").requires((serverCommandSource -> serverCommandSource.getEntity() instanceof PlayerEntity && FabricLoader.getInstance().isDevelopmentEnvironment()))
			    .then(CommandManager.argument("amount", LongArgumentType.longArg()).executes(
				    (context) -> {
					        ((PlayerEntityExtension)(PlayerEntity)context.getSource().getEntity()).setTemporaryMoney(context.getArgument("amount", Long.class));
						    return 1;
					    }
					));
			dispatcher.register(literalArgumentBuilder);
		}));
		TrackedDataHandlerRegistry.register(LONG_HANDLER);
		ParticleUtil.particleTypes();
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