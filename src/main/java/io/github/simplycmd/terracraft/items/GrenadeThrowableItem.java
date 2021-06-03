package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.entities.Entities;
import io.github.simplycmd.terracraft.entities.GrenadeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GrenadeThrowableItem extends Item {
    World world;

    public GrenadeThrowableItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        this.world = world;
        GrenadeEntity grenade = new GrenadeEntity(Entities.GRENADE, world);
        grenade.updatePosition(playerEntity.getX(), playerEntity.getY() + 2, playerEntity.getZ());
        grenade.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F);
        world.spawnEntity(grenade);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
