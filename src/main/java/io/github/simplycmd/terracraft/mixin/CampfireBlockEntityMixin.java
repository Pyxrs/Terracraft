package io.github.simplycmd.terracraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {
    private static final byte RADIUS = 20;

    @Inject(at = @At("TAIL"), method = "litServerTick")
    private static void litServerTick(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
        for (PlayerEntity player : world.getPlayers()) {
            BlockPos player_pos = player.getBlockPos();
            if (dist(pos, player_pos))
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 10, 0, true, false, false));
        }
    }

    private static boolean dist(BlockPos pos1, BlockPos pos2) {
        // Avoids sqrt since this will be called every tick
        double mag1 = Math.sqrt(Math.pow(pos1.getX(), 2) + Math.pow(pos1.getY(), 2) + Math.pow(pos1.getZ(), 2));
        double mag2 = Math.sqrt(Math.pow(pos2.getX(), 2) + Math.pow(pos2.getY(), 2) + Math.pow(pos2.getZ(), 2));
        return Math.abs(mag1 - mag2) < Math.pow(RADIUS, 2);
    }
}
