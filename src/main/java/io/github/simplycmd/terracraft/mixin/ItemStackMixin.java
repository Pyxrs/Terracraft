package io.github.simplycmd.terracraft.mixin;

import io.github.simplycmd.terracraft.items.util.BaseItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

import com.simplycmd.featherlib.util.SimpleEnchantment;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/item/ItemConvertible;I)V")
    private void init(ItemConvertible item, int count, CallbackInfo ci) {
        if (item instanceof BaseItem) {
            for (SimpleEnchantment enchantment : ((BaseItem) item).getEnchantments()) {
                ((ItemStack) (Object) this).addEnchantment(enchantment.getEnchantment(), enchantment.getLevel());
            }
        }
    }
}
