package io.github.simplycmd.terracraft.entities.coin_portal;

import io.github.simplycmd.terracraft.entities.util.BaseEntity;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class CoinPortalEntity extends BaseEntity {
    int tick = 0;

    public CoinPortalEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        tick++;
        if (tick % 10 == 0) {
            ItemEntity coin = new ItemEntity(world, this.getX(), this.getY(), this.getZ(), ItemRegistry.get("gold_coin").getDefaultStack());
            world.spawnEntity(coin);
        }
        if (tick >= 100) kill();
        this.move(MovementType.SELF, this.getVelocity());
        this.baseTick();
    }
}