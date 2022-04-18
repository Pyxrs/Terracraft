package io.github.simplycmd.terracraft.mixin;

import com.mojang.authlib.GameProfile;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.util.PlayerEntityExtension;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExtension {
    @Shadow protected abstract void attackLivingEntity(LivingEntity target);

    @Unique
    private static final UUID uuid = UUID.fromString("9f8b0b75-700f-431f-a07a-8d7094faf79a");

    @Unique
    private static final TrackedData<Long> TEMPORARY_MONEY = DataTracker.registerData(PlayerEntity.class, Main.LONG_HANDLER);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(World world, BlockPos pos, float yaw, GameProfile profile, CallbackInfo ci) {
        Objects.requireNonNull(((PlayerEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addTemporaryModifier(new EntityAttributeModifier(uuid, "life", -10, EntityAttributeModifier.Operation.ADDITION));
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    public void initDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(TEMPORARY_MONEY, 0L);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putLong("terracraft:temporary_money", this.dataTracker.get(TEMPORARY_MONEY));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("terracraft:temporary_money", NbtElement.LONG_TYPE)) {
            this.dataTracker.set(TEMPORARY_MONEY, nbt.getLong("terracraft:temporary_money"));
        }
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
    }

    @Inject(method = "jump", at = @At("TAIL"))
    public void jump(CallbackInfo ci) {
        //Objects.requireNonNull(((PlayerEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addTemporaryModifier(new EntityAttributeModifier(uuid, "life", -10, EntityAttributeModifier.Operation.ADDITION));
        //throw new RuntimeException();
    }

    @Override
    public long getTemporaryMoney() {
        return this.dataTracker.get(TEMPORARY_MONEY);
    }

    @Override
    public void setTemporaryMoney(long value) {
        this.dataTracker.set(TEMPORARY_MONEY, value);
    }
}
