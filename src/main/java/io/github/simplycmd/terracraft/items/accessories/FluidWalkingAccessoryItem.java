package io.github.simplycmd.terracraft.items.accessories;

import net.minecraft.tag.TagKey;
import be.florens.expandability.api.fabric.LivingFluidCollisionCallback;
import io.github.simplycmd.terracraft.util.AccessoryUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.fluid.Fluid;

public class FluidWalkingAccessoryItem extends AccessoryItem {
    public FluidWalkingAccessoryItem(TagKey<Fluid> fluidTag, FabricItemSettings settings) {
        super(settings);
        LivingFluidCollisionCallback.EVENT.register((entity, fluidState) -> {
            return AccessoryUtil.isPowerEquipped(entity, this.getClass()) && fluidState.isIn(fluidTag) && !entity.isInSneakingPose();
        });
    }
}
