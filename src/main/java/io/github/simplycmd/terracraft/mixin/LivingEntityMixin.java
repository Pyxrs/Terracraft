package io.github.simplycmd.terracraft.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.simplycmd.terracraft.AccessoryUtil;
import io.github.simplycmd.terracraft.items.util.accessories.AccessoryItem;
import io.github.simplycmd.terracraft.registry.ItemRegistry;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
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

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    private float getSlipperiness(Block self) {
        if (AccessoryUtil.isItemEquipped(((LivingEntity) (Object) this), (AccessoryItem)ItemRegistry.ice_skates.getItem())) {
            return 0.6F;
        }
        return self.getSlipperiness();
    }
}