package io.github.simplycmd.terracraft.entities;

import io.github.simplycmd.terracraft.Main;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Entities {
    public static EntityType<GrenadeEntity> GRENADE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "grenade"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, GrenadeEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build()
    );

    public static void RegisterEntities() {
        FabricDefaultAttributeRegistry.register(GRENADE, GrenadeEntity.createMobAttributes());
    }
}
