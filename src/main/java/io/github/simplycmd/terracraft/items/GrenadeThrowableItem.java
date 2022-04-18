package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.items.util.BaseItem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.registry.EntityRegistry;
import io.github.simplycmd.terracraft.entities.grenade.GrenadeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GrenadeThrowableItem extends Item implements BaseItem {
    public GrenadeThrowableItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        GrenadeEntity grenade = new GrenadeEntity(EntityRegistry.GRENADE, world);
        grenade.setFuse((short) 100);
        grenade.updatePosition(playerEntity.getX(), playerEntity.getY() + 2, playerEntity.getZ());
        grenade.setProperties(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F);
        world.spawnEntity(grenade);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public Value getBuyValue() {
        return new Value(0, 0, 0, 75);
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 0, 0, 15);
    }
}
