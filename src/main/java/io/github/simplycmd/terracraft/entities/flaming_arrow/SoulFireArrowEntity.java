package io.github.simplycmd.terracraft.entities.flaming_arrow;

import io.github.simplycmd.terracraft.registry.ItemReg;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class SoulFireArrowEntity extends CustomArrowEntity {
    public SoulFireArrowEntity(EntityType<? extends SoulFireArrowEntity> type, World world) {
        super(type, world, null, ItemReg.get("soul_fire_arrow"), true);
    }
}
