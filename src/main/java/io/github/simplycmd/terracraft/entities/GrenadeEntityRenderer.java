package io.github.simplycmd.terracraft.entities;

import io.github.simplycmd.terracraft.Main;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GrenadeEntityRenderer extends MobEntityRenderer<GrenadeEntity, GrenadeEntityModel> {
    public GrenadeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GrenadeEntityModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(GrenadeEntity entity) {
        return new Identifier(Main.MOD_ID, "textures/entity/grenade/grenade.png");
    }
}
