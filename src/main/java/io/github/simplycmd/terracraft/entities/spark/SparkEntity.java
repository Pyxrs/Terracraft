package io.github.simplycmd.terracraft.entities.spark;

import io.github.simplycmd.terracraft.entities.util.BaseEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class SparkEntity extends BaseEntity {
    int tick = 0;

    public SparkEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        world.addParticle(ParticleTypes.LAVA, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 0.0D, 0.0D, 0.0D);
        tick++;
        if (tick >= 100) this.kill();
        this.updateVelocity(0.05F, new Vec3d(0, -1, 0));
        this.move(MovementType.SELF, this.getVelocity());
        this.baseTick();
    }

    public void setProperties(Entity user, float pitch, float yaw, float roll) {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float g = -MathHelper.sin((pitch + roll) * 0.017453292F);
        float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.setVelocity(f, g, h);
        Vec3d vec3d = user.getVelocity();
        this.setVelocity(this.getVelocity().add(vec3d.x, user.isOnGround() ? 0.0D : vec3d.y, vec3d.z));
    }
}
