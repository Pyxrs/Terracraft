package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.entities.spark.SparkEntity;
import io.github.simplycmd.terracraft.items.util.BaseItem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.registry.EntityRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WandOfSparkingItem extends Item implements BaseItem {
    public WandOfSparkingItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        SparkEntity spark = new SparkEntity(EntityRegistry.SPARK, world, playerEntity);
        spark.updatePosition(playerEntity.getX(), playerEntity.getY() + 2, playerEntity.getZ());
        spark.setProperties(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F);
        world.spawnEntity(spark);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 0, 10, 0);
    }
}
