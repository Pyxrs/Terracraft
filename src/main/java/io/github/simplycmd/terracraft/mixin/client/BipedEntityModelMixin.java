package io.github.simplycmd.terracraft.mixin.client;

import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> {
    @Shadow public BipedEntityModel.ArmPose leftArmPose;

    @Shadow @Final public ModelPart leftArm;

    @Shadow protected abstract Arm getPreferredArm(T entity);

    @Shadow @Final public ModelPart rightArm;

    @Inject(method = "positionLeftArm", at = @At("HEAD"), cancellable = true)
    private void terracraft$positionLeftArm(T entity, CallbackInfo ci) {
        if (entity.getStackInHand(this.getPreferredArm(entity) == Arm.RIGHT ? Hand.MAIN_HAND : Hand.OFF_HAND).isOf(ItemRegistry.umbrella.getItem())) {
            this.leftArm.pitch = this.leftArm.pitch * 0.5F - 3.1415927F;
            this.leftArm.yaw = 0.0F;
            ci.cancel();
        }
    }

    @Inject(method = "positionRightArm", at = @At("HEAD"), cancellable = true)
    private void terracraft$positionRightArm(T entity, CallbackInfo ci) {
        if (entity.getStackInHand(this.getPreferredArm(entity) == Arm.LEFT ? Hand.MAIN_HAND : Hand.OFF_HAND).isOf(ItemRegistry.umbrella.getItem())) {
            this.rightArm.pitch = this.rightArm.pitch * 0.5F - 3.1415927F;
            this.rightArm.yaw = 0.0F;
            ci.cancel();
        }
    }
}
