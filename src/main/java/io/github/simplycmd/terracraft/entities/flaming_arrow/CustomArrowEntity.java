package io.github.simplycmd.terracraft.entities.flaming_arrow;

import io.github.simplycmd.terracraft.registry.ItemReg;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CustomArrowEntity extends PersistentProjectileEntity {
    private static final int INTEGER_LIMIT = 2147483647;

    private final Item dropItem;

    public CustomArrowEntity(World world, LivingEntity owner, Item dropItem, boolean fire) {
        super(EntityType.ARROW, owner, world);
        if (fire) this.setOnFireFor(INTEGER_LIMIT);
        this.dropItem = dropItem;
    }

    @Override
    protected ItemStack asItemStack() {
        return dropItem.getDefaultStack();
    }
}
