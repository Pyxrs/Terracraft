package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.entities.Entities;
import io.github.simplycmd.terracraft.entities.GrenadeEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Entities.GRENADE, GrenadeEntityRenderer::new);
    }
}