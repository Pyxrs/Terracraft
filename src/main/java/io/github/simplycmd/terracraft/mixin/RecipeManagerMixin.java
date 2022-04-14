package io.github.simplycmd.terracraft.mixin;

import io.github.simplycmd.terracraft.recipes.MoneyConversionRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Optional;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @Shadow protected abstract <C extends Inventory, T extends Recipe<C>> Map<Identifier, Recipe<C>> getAllOfType(RecipeType<T> type);

    @Inject(method = "getFirstMatch", at = @At("HEAD"), cancellable = true)
    public <C extends Inventory, T extends Recipe<C>> void terracraft$getFirstMatch(RecipeType<T> type, C inventory, World world, CallbackInfoReturnable<Optional<T>> cir) {
        var d = this.getAllOfType(type).values().stream().flatMap((recipe) -> type.match(recipe, world, inventory).stream()).filter((e)-> e instanceof MoneyConversionRecipe).findFirst();
        if (d.isPresent()) {
            cir.setReturnValue(d);
        }
    }
}
