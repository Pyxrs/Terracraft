package io.github.simplycmd.terracraft.entities.flaming_arrow;

import io.github.simplycmd.terracraft.registry.ItemReg;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class FlamingArrowEntity extends CustomArrowEntity {
    public FlamingArrowEntity(World world, LivingEntity owner) {
        super(world, owner, ItemReg.get("flaming_arrow"), true);
    }
}
