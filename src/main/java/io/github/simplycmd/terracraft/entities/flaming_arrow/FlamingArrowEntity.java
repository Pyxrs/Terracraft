package io.github.simplycmd.terracraft.entities.flaming_arrow;

import io.github.simplycmd.terracraft.registry.EntityRegistry;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class FlamingArrowEntity extends CustomArrowEntity {
    public FlamingArrowEntity(EntityType<? extends FlamingArrowEntity> type, World world) {
        super(type, world, null, ItemRegistry.flaming_arrow.getItem(), true);
        this.setOnFire(true);
    }
    public FlamingArrowEntity(World world, LivingEntity owner) {
        super(EntityRegistry.FLAMING_ARROW, world, owner, ItemRegistry.flaming_arrow.getItem(), true);
        this.setOnFire(true);
    }
}
