package io.github.simplycmd.terracraft.gui;

import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.packets.PacketHandler;
import io.github.simplycmd.terracraft.registry.ScreenHandlerRegistry;
import io.github.simplycmd.terracraft.util.Offer;
import io.github.simplycmd.terracraft.util.OfferList;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.TradeOutputSlot;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.MerchantInventory;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

import static io.github.simplycmd.terracraft.items.util.Value.trySpend;
import static io.github.simplycmd.terracraft.items.util.Value.trySpend2;

public class BuyScreenHandler extends ScreenHandler {
    private int recipeIndex;
    private final SimpleInventory simpleInventory;
    private final PlayerInventory playerInventory;
    private final CraftingInventory craftingInventory;
    private final CraftingResultInventory craftingResult;
    public BuyScreenHandler(OfferList offers, PlayerInventory playerInventory, int syncId) {
        super(ScreenHandlerRegistry.BUY_SCREEN_HANDLER, syncId);
        this.simpleInventory = new SimpleInventory(1);
        this.playerInventory = playerInventory;
        this.craftingInventory = new CraftingInventory(this, 1, 1);
        this.craftingResult = new CraftingResultInventory();
        this.oferu = offers;
        this.addSlot(new Slot(this.simpleInventory, 0, -40+220, 37){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                this.onCrafted(stack);
                super.onTakeItem(player, stack);
                take();
            }
        });

        this.addSlot(new Slot(this.craftingInventory, 0, 108, 18));
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.craftingInventory, this.craftingResult, 0, 108, 48){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        int i;
        int j;

        for(i = 0; i < 3; ++i) {
            for(j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 108 + j * 18, 84 + i * 18){
                    @Override
                    public void onTakeItem(PlayerEntity player, ItemStack stack) {
                        super.onTakeItem(player, stack);
                        BuyScreenHandler.this.update();
                    }
                });
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 108 + i * 18, 142) {
                @Override
                public void onTakeItem(PlayerEntity player, ItemStack stack) {
                    super.onTakeItem(player, stack);
                    BuyScreenHandler.this.update();
                }
            });
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public void setRecipeIndex(int selectedIndex) {
        recipeIndex = selectedIndex;
        update();
    }

    public void switchTo(int selectedIndex) {
        recipeIndex = selectedIndex;
    }

    private OfferList oferu = null;
    public OfferList getRecipes() {
        if (oferu != null) return oferu;
        this.oferu = new OfferList();
        return oferu;
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 0) {
                if (!this.insertItem(itemStack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(itemStack2, itemStack);
            } else if (index != 1 && index != 2) {
                if (index >= 3 && index < 30) {
                    if (!this.insertItem(itemStack2, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.insertItem(itemStack2, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 3, 39, false)) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    public boolean update() {
        if (this.oferu.size() <= 0) return false;
        if (trySpend2(this.playerInventory, getRecipes().get(this.recipeIndex).getValue().getValue()))
        this.simpleInventory.setStack(0, getRecipes().get(this.recipeIndex).getItem().copy());
        else this.simpleInventory.setStack(0, ItemStack.EMPTY);
        return trySpend2(this.playerInventory, getRecipes().get(this.recipeIndex).getValue().getValue());
    }

    public void take() {
        if (this.oferu.size() <= 0) return;
        trySpend(this.playerInventory, getRecipes().get(this.recipeIndex).getValue().getValue());
        this.playerInventory.markDirty();
    }

    public void setOffers(OfferList list) {
        oferu = list;
    }


    protected static void updateResult(ScreenHandler handler, World world, PlayerEntity player, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(RecipeType.CRAFTING, craftingInventory, world);
            if (optional.isPresent()) {
                CraftingRecipe craftingRecipe = optional.get();
                if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, craftingRecipe)) {
                    itemStack = craftingRecipe.craft(craftingInventory);
                }
            }

            resultInventory.setStack(2, itemStack);
            handler.setPreviousTrackedSlot(2, itemStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 2, itemStack));
        }
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        updateResult(this, this.playerInventory.player.world, this.playerInventory.player, this.craftingInventory, this.craftingResult);
        super.onContentChanged(inventory);
    }

    public boolean matches(Recipe<? super CraftingInventory> recipe) {
        return recipe.matches(this.craftingInventory, this.playerInventory.player.world);
    }

    public void clearCraftingSlots() {
        this.craftingInventory.clear();
        this.craftingResult.clear();
    }

    public void switchToSellScreen() {
        PacketHandler.openSellScreen();
    }
}
