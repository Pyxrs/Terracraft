package io.github.simplycmd.terracraft.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    private static final UUID uuid = UUID.fromString("9f8b0b75-700f-431f-a07a-8d7094faf79a");

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(World world, BlockPos pos, float yaw, GameProfile profile, CallbackInfo ci) {
        Objects.requireNonNull(((PlayerEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addTemporaryModifier(new EntityAttributeModifier(uuid, "life", -10, EntityAttributeModifier.Operation.ADDITION));
    }

    @Inject(method = "jump", at = @At("TAIL"))
    public void jump(CallbackInfo ci) {
        //Objects.requireNonNull(((PlayerEntity) (Object) this).getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addTemporaryModifier(new EntityAttributeModifier(uuid, "life", -10, EntityAttributeModifier.Operation.ADDITION));
        //throw new RuntimeException();
    }
}
