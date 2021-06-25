package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.registry.BlockRegistry;
import io.github.simplycmd.terracraft.registry.EntityRegistry;
import io.github.simplycmd.terracraft.entities.grenade.GrenadeEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {

    private static final ArrayList<String> CUTOUT = new ArrayList<>() {{
        add("blue_berry_bush");
        add("forest_pot");
        add("ice_torch");
        add("ice_wall_torch");
        add("bone_torch");
        add("bone_wall_torch");
        add("ultrabright_torch");
        add("ultrabright_wall_torch");
        add("demon_torch");
        add("demon_wall_torch");
        add("cursed_torch");
        add("cursed_wall_torch");
        add("ichor_torch");
        add("ichor_wall_torch");
        add("rainbow_torch");
        add("rainbow_wall_torch");
        add("desert_torch");
        add("desert_wall_torch");
        add("coral_torch");
        add("coral_wall_torch");
        add("corrupt_torch");
        add("corrupt_wall_torch");
        add("crimson_torch");
        add("crimson_wall_torch");
        add("hallowed_torch");
        add("hallowed_wall_torch");
        add("jungle_torch");
        add("jungle_wall_torch");
    }};

    @Override
    public void onInitializeClient() {
        for (String id : CUTOUT) {
            BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get(id), RenderLayer.getCutout());
        }
        EntityRegistry.register();
    }
}