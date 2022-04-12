package io.github.simplycmd.terracraft.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.simplycmd.terracraft.TrinketsUtil;
import io.github.simplycmd.terracraft.registry.ItemRegistry;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @ModifyArg(
            method = "travel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"),
            index = 0
    )
    private float updateVelocity(float original) {
        if (TrinketsUtil.isEquipped(((LivingEntity) (Object) this), ItemRegistry.flipper.getItem())) {
            return original * 2;
        }
        return original;
    }
}
