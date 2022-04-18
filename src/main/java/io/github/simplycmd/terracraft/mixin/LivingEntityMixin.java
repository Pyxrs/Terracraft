package io.github.simplycmd.terracraft.mixin;

import io.github.simplycmd.terracraft.items.accessories.DoubleJumpAccessoryItem;
import io.github.simplycmd.terracraft.util.LivingEntityExtension;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.simplycmd.terracraft.AccessoryUtil;
import io.github.simplycmd.terracraft.items.accessories.AccessoryItem;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityExtension {
    @Unique
    private HashMap<DoubleJumpAccessoryItem, Integer> jumpItemCounter = null;
    @Shadow protected abstract void jump();

    @Override
    public HashMap<DoubleJumpAccessoryItem, Integer> terracraft$getJumpCounter() {
        if (jumpItemCounter == null) {
            jumpItemCounter = new HashMap<>();
            DoubleJumpAccessoryItem.power().forEach(((doubleJumpAccessoryItem, integer) -> {
                jumpItemCounter.put(doubleJumpAccessoryItem, doubleJumpAccessoryItem.doubleJumps());
            }));
        }
        return jumpItemCounter;
    }

    @Override
    public void terracraft$resetJumpCounter() {
        jumpItemCounter = null;
        terracraft$getJumpCounter();
    }

    @ModifyArg(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"), index = 0)
    private float updateVelocity(float original) {
        if (AccessoryUtil.isItemEquipped(((LivingEntity) (Object) this), (AccessoryItem)ItemRegistry.flipper.getItem())) {
            return original * 2;
        }
        return original;
    }

    @ModifyArg(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"), index = 0)
    private Vec3d setVelocity(Vec3d velocity) {
        if (AccessoryUtil.isItemEquipped(((LivingEntity) (Object) this), (AccessoryItem)ItemRegistry.inner_tube.getItem())
                && !((LivingEntity) (Object) this).isInSneakingPose()) {
            return velocity.add(0, 0.1, 0);
        }
        return velocity;
    }

    @Inject(method = "computeFallDamage", at = @At("RETURN"), cancellable = true)
    private void computeFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Integer> cir) {
        if ((LivingEntity)(Object)this instanceof PlayerEntity playerEntity && AccessoryUtil.getDJ(playerEntity) != null) {
            double l = 1;
            for (var d : AccessoryUtil.getDJList(playerEntity)) {
                l = l + 10*d.jumpPower()*d.doubleJumps();
            }
            cir.setReturnValue(MathHelper.ceil((cir.getReturnValue()/damageMultiplier - l)*damageMultiplier));
        }
        //return MathHelper.ceil((fallDistance - 3.0F - f) * damageMultiplier);
    }


    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    private float getSlipperiness(Block self) {
        if (AccessoryUtil.isItemEquipped(((LivingEntity) (Object) this), (AccessoryItem)ItemRegistry.ice_skates.getItem())) {
            return 0.6F;
        }
        return self.getSlipperiness();
    }
}