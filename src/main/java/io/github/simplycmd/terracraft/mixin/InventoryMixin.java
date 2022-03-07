package io.github.simplycmd.terracraft.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.simplycmd.terracraft.data.PlayerData;

@Mixin(Inventory.class)
public class InventoryMixin {
    @Redirect(method = "onOpen(Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Inventory;onOpen(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    public void onOpen(Inventory inventory, PlayerEntity player) {
        PlayerData.inventoryOpen.put(player, true);
    }

    @Redirect(method = "onClose(Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Inventory;onClose(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    public void onClose(Inventory inventory, PlayerEntity player) {
        PlayerData.inventoryOpen.put(player, false);
    }
}
