package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.entities.coin_portal.CoinPortalEntity;
import io.github.simplycmd.terracraft.entities.coin_portal.CoinPortalEntityRenderer;
import io.github.simplycmd.terracraft.entities.grenade.GrenadeEntity;
import io.github.simplycmd.terracraft.entities.grenade.GrenadeEntityRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    public static EntityType<GrenadeEntity> GRENADE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "grenade"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, GrenadeEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build()
    );
    public static EntityType<CoinPortalEntity> COIN_PORTAL = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "coin_portal"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, CoinPortalEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build()
    );

    public static void register() {
        EntityRendererRegistry.INSTANCE.register(GRENADE, GrenadeEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(COIN_PORTAL, CoinPortalEntityRenderer::new);
    }
}
