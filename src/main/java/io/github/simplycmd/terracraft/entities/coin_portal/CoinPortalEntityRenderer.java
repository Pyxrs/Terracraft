package io.github.simplycmd.terracraft.entities.coin_portal;

import io.github.simplycmd.terracraft.entities.util.BaseEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;

@Environment(EnvType.CLIENT)
public class CoinPortalEntityRenderer extends BaseEntityRenderer {
    public CoinPortalEntityRenderer(EntityRendererFactory.Context ctx) {
        super("textures/entity/coin_portal/coin_portal.png", new CoinPortalEntityModel(), ctx);
    }
}
