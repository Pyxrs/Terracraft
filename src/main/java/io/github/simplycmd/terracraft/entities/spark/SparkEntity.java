package io.github.simplycmd.terracraft.entities.spark;

import java.util.Optional;

import io.github.simplycmd.terracraft.entities.util.BaseEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SparkEntity extends BaseEntity {
    private static final float VEL_CONST = 0.017453292F;
    int tick = 0;
    private final Optional<LivingEntity> shooter;

    public SparkEntity(EntityType<? extends SparkEntity> type, World world) {
        super(type, world);
        this.shooter = Optional.empty();
    }
    public SparkEntity(EntityType<? extends SparkEntity> type, World world, LivingEntity owner) {
        super(type, world);
        this.shooter = Optional.of(owner);
    }

    public void tick() {
        // Ticking
        tick++;
        if (horizontalCollision || verticalCollision) tick += 10;
        if (tick % 3 == 0) world.addParticle(ParticleTypes.LAVA, getPos().getX(), getPos().getY(), getPos().getZ(), 0.0D, 0.0D, 0.0D);
        if (tick >= 50) {
            world.addParticle(ParticleTypes.POOF, getPos().getX(), getPos().getY(), getPos().getZ(), 0.0D, 0.0D, 0.0D);
            discard();
        }

        // Attacking
        if (shooter.isPresent())
            for (Entity entity : world.getOtherEntities(shooter.get(), getBoundingBox())) {
                entity.damage(DamageSource.mobProjectile(this, shooter.get()), 2);
                entity.setOnFireFor(3);
            }

        // Movement
        updateVelocity(0.05F, new Vec3d(0, -1, 0));
        super.tick();
    }

    public void setProperties(Entity user, float pitch, float yaw, float roll) {
        final float cos = MathHelper.cos(pitch * VEL_CONST);
        float x = -MathHelper.sin(yaw * VEL_CONST) * cos;
        float y = -MathHelper.sin((pitch + roll) * VEL_CONST);
        float z = MathHelper.cos(yaw * VEL_CONST) * cos;
        setVelocity(x, y * 2, z);
        Vec3d vec3d = user.getVelocity();
        setVelocity(getVelocity().add(vec3d.x, user.isOnGround() ? 0.0D : vec3d.y, vec3d.z));
    }
}
