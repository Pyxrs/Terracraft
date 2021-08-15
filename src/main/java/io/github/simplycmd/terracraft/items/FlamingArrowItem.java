package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.entities.flaming_arrow.FlamingArrowEntity;
import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlamingArrowItem extends ArrowItem implements IItem {
    public FlamingArrowItem() {
        super(new FabricItemSettings().group(ItemGroup.COMBAT));
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new FlamingArrowEntity(world, shooter);
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 0, 0, 2);
    }
}
