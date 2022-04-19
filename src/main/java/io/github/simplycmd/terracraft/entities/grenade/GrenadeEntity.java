package io.github.simplycmd.terracraft.entities.grenade;

import io.github.simplycmd.terracraft.entities.util.BaseEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class GrenadeEntity extends BaseEntity {
    private static final TrackedData<Integer> FUSE = DataTracker.registerData(GrenadeEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int DEFAULT_FUSE = 100;
    private static final float VEL_CONST = 0.017453292F;

    double y_vel = 0.5;

    public GrenadeEntity(EntityType<? extends GrenadeEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FUSE, 100);
    }

    public short getFuse() {
        return (short)(int)this.dataTracker.get(FUSE);
    }
    public void setFuse(short val) {
        this.dataTracker.set(FUSE, (int)val);
    }

    @Override
    public void tick() {
        setFuse((short) (getFuse()-1));
        if (getFuse() <= 0) explode();
        if (this.onGround) {
            this.setVelocity(this.getVelocity().multiply(0.6, 1, 0.6));
            this.addVelocity(0, y_vel, 0);
            y_vel /= 1.5;
            this.onGround = false;
        }
        this.updateVelocity(0.05F, new Vec3d(0, -1, 0));
        this.move(MovementType.SELF, this.getVelocity());
        this.baseTick();
    }

    private void explode() {
        this.kill();
        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 3.0F, Explosion.DestructionType.NONE);
    };

    public void setProperties(Entity user, float pitch, float yaw, float roll) {
        final float cos = MathHelper.cos(pitch * VEL_CONST);
        float x = -MathHelper.sin(yaw * VEL_CONST) * cos;
        float y = -MathHelper.sin((pitch + roll) * VEL_CONST);
        float z = MathHelper.cos(yaw * VEL_CONST) * cos;
        this.setVelocity(x, y, z);
        Vec3d vec3d = user.getVelocity();
        this.setVelocity(this.getVelocity().add(vec3d.x, user.isOnGround() ? 0.0D : vec3d.y, vec3d.z));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putShort("Fuse", (short)(int)this.dataTracker.get(FUSE));
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(FUSE, (int)nbt.getShort("Fuse"));
    }
}
