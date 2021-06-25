package io.github.simplycmd.terracraft.entities.grenade;

import io.github.simplycmd.terracraft.entities.util.BaseEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;

@Environment(EnvType.CLIENT)
public class GrenadeEntityRenderer extends BaseEntityRenderer {
    public GrenadeEntityRenderer(EntityRendererFactory.Context ctx) {
        super("textures/entity/grenade/grenade.png", new GrenadeEntityModel(), ctx);
    }
}
