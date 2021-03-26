package io.github.simplycmd.terracraft.entities;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrenadeEntityModel extends EntityModel<GrenadeEntity> {

    private ModelPart base;
    private List<ModelPart.Cuboid> cubes = new ArrayList<ModelPart.Cuboid>();

    public GrenadeEntityModel() {
        cubes.add(new ModelPart.Cuboid(0, 0, -2, -3, -2, 4, 6, 4, 0, 0, 0, false, 32, 16));
        cubes.add(new ModelPart.Cuboid(16, 0, -4, -5, 0, 6, 6, 0, 0, 0, 0, false, 32, 16));
        base = new ModelPart(cubes, new HashMap<>());
    }

    @Override
    public void setAngles(GrenadeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        // translate model down
        matrices.translate(0, 1.32, 0);

        // render cube
        base.render(matrices, vertices, light, overlay);
    }
}