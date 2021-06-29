package io.github.simplycmd.terracraft.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {
    @Inject(at = @At("TAIL"), method = "litServerTick")
    private static void litServerTick(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
        for (PlayerEntity player : world.getPlayers()) {
            BlockPos player_pos = player.getBlockPos();
            if (Math.abs(pos.getX() - player_pos.getX()) < 20)
                if (Math.abs(pos.getY() - player_pos.getY()) < 20)
                    if (Math.abs(pos.getZ() - player_pos.getZ()) < 20)
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 10, 0, false, false, true));
        }
    }
}
