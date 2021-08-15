package io.github.simplycmd.terracraft.entities.spark;

import io.github.simplycmd.terracraft.entities.util.BaseEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.HashMap;

@Environment(EnvType.CLIENT)
public class SparkEntityModel extends BaseEntityModel {
    public SparkEntityModel() {

    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.translate(0, 0, 0);
        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
