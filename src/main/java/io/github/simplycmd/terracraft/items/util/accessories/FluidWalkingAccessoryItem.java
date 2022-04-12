package io.github.simplycmd.terracraft.items.util.accessories;

import net.minecraft.tag.TagKey;
import be.florens.expandability.api.fabric.LivingFluidCollisionCallback;
import io.github.simplycmd.terracraft.TrinketsUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.fluid.Fluid;

public class FluidWalkingAccessoryItem extends AccessoryItem {
    public FluidWalkingAccessoryItem(TagKey<Fluid> fluidTag, FabricItemSettings settings) {
        super(settings);
        LivingFluidCollisionCallback.EVENT.register((entity, fluidState)
                -> {
                    return TrinketsUtil.isEquipped(entity, this) && fluidState.isIn(fluidTag) && !entity.isInSneakingPose();
                }
        );
    }
}
