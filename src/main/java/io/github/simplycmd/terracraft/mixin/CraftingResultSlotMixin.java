package io.github.simplycmd.terracraft.mixin;

import io.github.simplycmd.terracraft.recipes.MoneyConversionRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin extends Slot {
    @Shadow @Final private CraftingInventory input;

    public CraftingResultSlotMixin(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    Optional<CraftingRecipe> o = Optional.empty();
    @Inject(method = "onTakeItem", at = @At("HEAD"))
    private void terracraft$onTakeItem(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        o = player.world.getRecipeManager().getFirstMatch(RecipeType.CRAFTING, this.input, player.world);
    }

    @Inject(method = "onTakeItem", at = @At("TAIL"))
    private void terracraft$onTakeItem2(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (o.isPresent()) {
            var d = 64;
            if (o.get() instanceof MoneyConversionRecipe) {
                for (int i = 0; i < this.input.size(); i++) {
                    if (d <= 0) break;
                    var e = Math.min(d, this.input.getStack(i).getCount());
                    this.input.removeStack(i, e);
                    d -= e;
                }
            }
        }
    }

}
