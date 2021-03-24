package io.github.simplycmd.terracraft.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.DeepslateInterpolator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DeepslateInterpolator.class)
public class DeepslateInterpolatorMixin {
    @Inject(method = "sample", at = @At("HEAD"), cancellable = true)
    public void sample(int x, int y, int z, ChunkGeneratorSettings settings, CallbackInfoReturnable<BlockState> cir) {
        if (y < -64) {
            cir.setReturnValue(Blocks.MAGMA_BLOCK.getDefaultState());
            cir.cancel();
        }
    }
}
