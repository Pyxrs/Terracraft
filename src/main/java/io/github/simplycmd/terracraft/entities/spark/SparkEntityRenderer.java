package io.github.simplycmd.terracraft.entities.spark;

import io.github.simplycmd.terracraft.entities.util.BaseEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;

@Environment(EnvType.CLIENT)
public class SparkEntityRenderer extends BaseEntityRenderer {
    public SparkEntityRenderer(EntityRendererFactory.Context ctx) {
        super("textures/entity/grenade/grenade.png", new SparkEntityModel(), ctx);
    }
}
