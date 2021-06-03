package io.github.simplycmd.terracraft.entities;

import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlamingArrowEntity extends PersistentProjectileEntity {
    private static final int CRAZY_HIGH_NUMBER = 10000000;

    protected FlamingArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.setOnFireFor(CRAZY_HIGH_NUMBER);
    }

    public FlamingArrowEntity(World world, LivingEntity owner) {
        super(EntityType.ARROW, owner, world);
        this.setOnFireFor(CRAZY_HIGH_NUMBER);
    }

    public FlamingArrowEntity(World world, double x, double y, double z) {
        super(EntityType.ARROW, x, y, z, world);
        this.setOnFireFor(CRAZY_HIGH_NUMBER);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ItemRegistry.get("flaming_arrow"));
    }
}
