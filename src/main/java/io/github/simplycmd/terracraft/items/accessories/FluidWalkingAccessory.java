package io.github.simplycmd.terracraft.items.accessories;

import be.florens.expandability.api.fabric.LivingFluidCollisionCallback;
import io.github.simplycmd.terracraft.util.AccessoryUtil;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;

public class FluidWalkingAccessory implements Accessory {
    @SuppressWarnings("all")
    public FluidWalkingAccessory(TagKey<Fluid> fluidTag) {
        LivingFluidCollisionCallback.EVENT.register((entity, fluidState) -> {
            return AccessoryUtil.isPowerEquipped(entity, this.getClass()) && fluidState.isIn(fluidTag) && !entity.isInSneakingPose();
        });
    }
}
