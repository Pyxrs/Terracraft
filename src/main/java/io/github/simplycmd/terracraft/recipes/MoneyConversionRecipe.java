package io.github.simplycmd.terracraft.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static io.github.simplycmd.terracraft.Main.MOD_ID;

public abstract class MoneyConversionRecipe extends SpecialCraftingRecipe {

    public MoneyConversionRecipe(Identifier identifier) {
        super(identifier);
    }

    public static SpecialRecipeSerializer<MoneyConversionRecipe> register(ItemConvertible result, ItemConvertible item) {
        var id = "to_" + Registry.ITEM.getId(result.asItem()).getPath();
        var serializer = new SpecialRecipeSerializer<MoneyConversionRecipe>((s)->{
            return new MoneyConversionRecipe(s) {
                @Override
                protected ItemConvertible item() {
                    return item;
                }

                @Override
                protected ItemStack result() {
                    return new ItemStack(result, 1);
                }
            };
        });
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, id), serializer);
    }

    protected abstract ItemConvertible item();
    protected abstract ItemStack result();

    @Override
    public ItemStack getOutput() {
        return result();
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int amount = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (!(inventory.getStack(i).isEmpty() || inventory.getStack(i).isOf(item().asItem()))) {
                return false;
            }
            if (!inventory.getStack(i).isEmpty())
            amount+=inventory.getStack(i).getCount();
        }
        return amount>=64;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
//        var e = 64;
//        for (int i = 0; i < inventory.size(); i++) {
//            if (inventory.getStack(i).isOf(item().asItem())) {
//                var copy = inventory.getStack(i).copy();
//                var q = Math.min(copy.getCount(), e);
//                //copy.decrement(q);
//                //inventory.setStack(i, copy);
//                e -= q;
//            }
//            break;
//        }
        return result();
    }

    @Override
    public boolean fits(int width, int height) {
        return width*height>=1;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        var d = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
        for(int i = 0; i < d.size(); ++i) {
            d.set(i, new ItemStack(inventory.getStack(i).getItem()));
        }
        return d;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }
}
