package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.terracraft.gui.BuyScreenHandler;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

public class ScreenHandlerRegistry {
    public static final ScreenHandlerType<BuyScreenHandler> BUY_SCREEN_HANDLER = new ScreenHandlerType<>((ScreenHandlerRegistry::create));;
    static {
        Registry.register(Registry.SCREEN_HANDLER, "terracraft:buy_screen_handler", BUY_SCREEN_HANDLER);
    }

    public static void registerScreenHandlers() {

    }

    private static BuyScreenHandler create(int syncId, PlayerInventory playerInventory) {
        return new BuyScreenHandler(playerInventory, syncId);
    }
}
