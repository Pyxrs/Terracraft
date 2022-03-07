package io.github.simplycmd.terracraft.mixin;

import io.github.simplycmd.terracraft.Main;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftDedicatedServer.class)
public class LateServerRegistryMixin {

    @Inject(method = "setupServer", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;info(Ljava/lang/String;)V", ordinal = 0, remap = false))
    public void runPostInitialize(CallbackInfoReturnable<?> cir) {
        Main.postInit();
    }
}