package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.entities.coin_portal.CoinPortalEntity;
import io.github.simplycmd.terracraft.entities.coin_portal.CoinPortalEntityRenderer;
import io.github.simplycmd.terracraft.entities.flaming_arrow.FlamingArrowEntity;
import io.github.simplycmd.terracraft.entities.flaming_arrow.FlamingArrowEntityRenderer;
import io.github.simplycmd.terracraft.entities.flaming_arrow.SoulFireArrowEntity;
import io.github.simplycmd.terracraft.entities.flaming_arrow.SoulFireArrowEntityRenderer;
import io.github.simplycmd.terracraft.entities.grenade.GrenadeEntity;
import io.github.simplycmd.terracraft.entities.grenade.GrenadeEntityRenderer;
import io.github.simplycmd.terracraft.entities.spark.SparkEntity;
import io.github.simplycmd.terracraft.entities.spark.SparkEntityRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityReg {
    public static EntityType<GrenadeEntity> GRENADE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "grenade"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, GrenadeEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build()
    );
    public static EntityType<SparkEntity> SPARK = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "spark"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, SparkEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build()
    );
    public static EntityType<CoinPortalEntity> COIN_PORTAL = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "coin_portal"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, CoinPortalEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build()
    );
    public static EntityType<FlamingArrowEntity> FLAMING_ARROW = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "flaming_arrow"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, FlamingArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );
    public static EntityType<SoulFireArrowEntity> FLAMING_SOUL_ARROW = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "flaming_soul_arrow"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, SoulFireArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );
    public static void register() {
        EntityRendererRegistry.INSTANCE.register(GRENADE, GrenadeEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SPARK, SparkEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(COIN_PORTAL, CoinPortalEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(FLAMING_ARROW, FlamingArrowEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(FLAMING_SOUL_ARROW, SoulFireArrowEntityRenderer::new);
    }
}
