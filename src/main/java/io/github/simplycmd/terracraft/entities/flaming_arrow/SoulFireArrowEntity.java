package io.github.simplycmd.terracraft.entities.flaming_arrow;

import io.github.simplycmd.terracraft.registry.EntityRegistry;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class SoulFireArrowEntity extends CustomArrowEntity {
    public SoulFireArrowEntity(EntityType<? extends SoulFireArrowEntity> type, World world) {
        super(type, world, null, ItemRegistry.soul_fire_arrow.getItem(), true);
        this.setOnFire(true);
    }
    public SoulFireArrowEntity(World world, LivingEntity owner) {
        super(EntityRegistry.FLAMING_SOUL_ARROW, world, owner, ItemRegistry.soul_fire_arrow.getItem(), true);
        this.setOnFire(true);
    }
}
