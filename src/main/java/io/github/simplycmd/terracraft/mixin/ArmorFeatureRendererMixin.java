package io.github.simplycmd.terracraft.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import dev.emi.trinkets.api.SlotType;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin<T extends LivingEntity> {
    @Redirect(method = "renderArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
    public ItemStack getOverlayArmor(T entity, EquipmentSlot slot) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);
        if (entity instanceof ClientPlayerEntity && component.isPresent()) {
            Optional<ItemStack> head = Optional.empty();
            Optional<ItemStack> chest = Optional.empty();
            Optional<ItemStack> legs = Optional.empty();
            Optional<ItemStack> feet = Optional.empty();
            for (var equipped : component.get().getAllEquipped()) {
                SlotType slotType = equipped.getLeft().inventory().getSlotType();
                if (slotType.getGroup().equals("head")) { head = Optional.of(equipped.getRight()); }
                if (slotType.getGroup().equals("chest")) { chest = Optional.of(equipped.getRight()); }
                if (slotType.getGroup().equals("legs")) { legs = Optional.of(equipped.getRight()); }
                if (slotType.getGroup().equals("feet")) { feet = Optional.of(equipped.getRight()); }
            }
            if (slot == EquipmentSlot.HEAD && head.isPresent()) {
                return head.get();
            }
            if (slot == EquipmentSlot.CHEST && chest.isPresent()) {
                return chest.get();
            }
            if (slot == EquipmentSlot.LEGS && legs.isPresent()) {
                return legs.get();
            }
            if (slot == EquipmentSlot.FEET && feet.isPresent()) {
                return feet.get();
            }
        }
        return entity.getEquippedStack(slot);
    }
}