package io.github.simplycmd.terracraft.entities.coin_portal;

import io.github.simplycmd.terracraft.entities.util.BaseEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;

import java.util.HashMap;

@Environment(EnvType.CLIENT)
public class CoinPortalEntityModel extends BaseEntityModel {
    public CoinPortalEntityModel() {
        cubes.add(new ModelPart.Cuboid(0, 0, -6, -6, -6, 12, 12, 12, 0, 0, 0, false, 32, 16));
        base = new ModelPart(cubes, new HashMap<>());
    }
}
