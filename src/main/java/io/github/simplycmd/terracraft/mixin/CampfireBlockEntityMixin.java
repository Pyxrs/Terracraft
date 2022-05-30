package io.github.simplycmd.terracraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {
    private static final byte RADIUS = 20;

    @Inject(at = @At("TAIL"), method = "litServerTick")
    private static void litServerTick(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
        for (ServerPlayerEntity player : world.getNonSpectatingEntities(ServerPlayerEntity.class, new Box(pos, pos).expand(RADIUS, RADIUS, RADIUS))) {
            final boolean activeStatus = player.getStatusEffect(StatusEffects.REGENERATION) != null && player.getStatusEffect(StatusEffects.REGENERATION).getDuration() > 20;
            if (!activeStatus) { player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 10, 0, true, false, true)); }
        }
    }
}
