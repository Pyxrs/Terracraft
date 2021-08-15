package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.registry.BlockRegistry;
import io.github.simplycmd.terracraft.registry.EntityRegistry;
import io.github.simplycmd.terracraft.features.Features;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static String MOD_ID = "terracraft";
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(MOD_ID + ":resource_pack");

	public static Identifier ID(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		RRPCallback.EVENT.register(a -> a.add(RESOURCE_PACK));
		ItemRegistry.register();
		BlockRegistry.register();
		Sounds.RegisterSounds();
	}

	//Convert health: T / 10 = M
}
