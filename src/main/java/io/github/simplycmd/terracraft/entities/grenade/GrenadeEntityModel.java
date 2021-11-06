package io.github.simplycmd.terracraft.entities.grenade;

import java.util.HashMap;

import io.github.simplycmd.terracraft.entities.util.BaseEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class GrenadeEntityModel extends BaseEntityModel {
    public GrenadeEntityModel() {
        cubes.add(new ModelPart.Cuboid(0, 0, -2, -3, -2, 4, 6, 4, 0, 0, 0, false, 32, 16));
        cubes.add(new ModelPart.Cuboid(16, 0, -4, -5, 0, 6, 6, 0, 0, 0, 0, false, 32, 16));
        base = new ModelPart(cubes, new HashMap<>());
    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.translate(0, -0.1875, 0);
        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
