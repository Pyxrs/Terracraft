package io.github.simplycmd.terracraft.util;

import io.github.simplycmd.terracraft.gui.BuyScreenHandler;
import io.github.simplycmd.terracraft.items.util.BaseItem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.packets.PacketHandler;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.village.TradeOffer;

public class Offer {
    private final ItemStack item;
    private final Value value;
    public Offer(ItemStack item, Value value) {
        this.item = item;
        this.value = value;
    }

    public Offer(BaseItem item) {
        this.item = ((Item)item).getDefaultStack();
        this.value = item.getBuyValue();
    }

    public Offer(NbtCompound compound) {
        this.item = ItemStack.fromNbt(compound.getCompound("item"));
        this.value = new Value(compound.getInt("value"));
    }

    public ItemStack getItem() {
        return item;
    }

    public boolean isDisabled() {
        return false;
    }

    public Value getValue() {
        return value;
    }

    public static void initializeOffer(ServerPlayerEntity player) {
        var d = new OfferList();

        d.add(new Offer((BaseItem) ItemRegistry.sickle.getItem()));
        d.add(new Offer((BaseItem) ItemRegistry.grenade.getItem()));
        d.add(new Offer((BaseItem) ItemRegistry.slap_hand.getItem()));
        //d.add(new Offer(Items.CACTUS.getDefaultStack(), new Value(1)));
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory(((syncId, inv, player1) -> new BuyScreenHandler(d, inv, syncId)), new LiteralText("NPC")));
        PacketHandler.sendOffers(player, player.currentScreenHandler.syncId, d);
    }
}
