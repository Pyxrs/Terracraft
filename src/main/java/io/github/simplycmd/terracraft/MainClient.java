package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.registry.BlockRegistry;
import io.github.simplycmd.terracraft.entities.Entities;
import io.github.simplycmd.terracraft.entities.GrenadeEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Entities.GRENADE, GrenadeEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("blue_berry_bush"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("forest_pot"), RenderLayer.getCutout());
    }
}