package io.github.simplycmd.terracraft.entities.flaming_arrow;

import io.github.simplycmd.terracraft.registry.ItemReg;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class FlamingArrowEntity extends CustomArrowEntity {
    public FlamingArrowEntity(EntityType<? extends FlamingArrowEntity> type, World world) {
        super(type, world, null, ItemReg.get("flaming_arrow"), true);
    }
}
