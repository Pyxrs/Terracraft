package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.entities.Entities;
import io.github.simplycmd.terracraft.entities.GrenadeEntity;
import io.github.simplycmd.terracraft.items.base.IThrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GrenadeThrowableItem extends Item implements IThrowable {
    World world;

    public GrenadeThrowableItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        this.world = world;
        GrenadeEntity grenade = new GrenadeEntity(Entities.GRENADE, world);
        grenade.updatePosition(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
        grenade.setVelocity(1, 0.5, 0);
        world.spawnEntity(grenade);
        //throwItem();
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public Entity thrownEntity() {
        return new GrenadeEntity(Entities.GRENADE, world);
    }

    @Override
    public ThowableType setThrowType() {
        return ThowableType.EXPLOSIVE;
    }

    @Override
    public int framesUntilExplosion() {
        return 100;
    }

    @Override
    public int explosionRadius() {
        return 10;
    }
}
