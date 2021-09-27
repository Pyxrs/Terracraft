package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.entities.grenade.GrenadeEntity;
import io.github.simplycmd.terracraft.entities.spark.SparkEntity;
import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.registry.EntityRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WandOfSparkingItem extends Item implements IItem {
    public WandOfSparkingItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        SparkEntity spark = new SparkEntity(EntityRegistry.SPARK, world);
        spark.updatePosition(playerEntity.getX(), playerEntity.getY() + 1, playerEntity.getZ());
        spark.setProperties(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F);
        world.spawnEntity(spark);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 0, 10, 0);
    }
}
