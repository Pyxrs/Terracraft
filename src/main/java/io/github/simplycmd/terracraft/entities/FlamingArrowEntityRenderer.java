package io.github.simplycmd.terracraft.entities;

import io.github.simplycmd.terracraft.Main;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class FlamingArrowEntityRenderer extends ProjectileEntityRenderer<FlamingArrowEntity> {
    public FlamingArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(FlamingArrowEntity entity) {
        return new Identifier(Main.MOD_ID, "textures/entity/flaming_arrow/flaming_arrow.png");
    }
}
