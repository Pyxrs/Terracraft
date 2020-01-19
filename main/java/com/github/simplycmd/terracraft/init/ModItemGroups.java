package com.github.simplycmd.terracraft.init;

import com.github.simplycmd.terracraft.Main;
import com.github.simplycmd.terracraft.ModEventSubscriber;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class ModItemGroups {

    public static final ItemGroup TERRACRAFT = new ModItemGroup(Main.MODID, () -> new ItemStack(Item.BLOCK_TO_ITEM.get(ModBlocks.ANGEL_STATUE))); //ItemStack(ModItems.TESTING_DUST) for item

    public static final class ModItemGroup extends ItemGroup {

        @Nonnull
        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        @Nonnull
        public ItemStack createIcon() {
            return iconSupplier.get();
        }
    }
}