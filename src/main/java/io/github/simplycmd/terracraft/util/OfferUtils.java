package io.github.simplycmd.terracraft.util;

import io.github.simplycmd.terracraft.data.OfferManager;
import io.github.simplycmd.terracraft.gui.BuyScreenHandler;
import io.github.simplycmd.terracraft.items.util.BaseItem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.packets.PacketHandler;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.minecraft.item.Items;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

public class OfferUtils {

    public static OfferList getOffers() {
        var d = new OfferList();
        d.addAll(getDataDrivenOffers());
        d.add(new Offer((BaseItem) ItemRegistry.sickle.getItem()));
        d.add(new Offer((BaseItem) ItemRegistry.grenade.getItem()));
        d.add(new Offer((BaseItem) ItemRegistry.slap_hand.getItem()));
        d.add(new Offer(Items.BEDROCK.getDefaultStack(), new Value(100, 0, 0, 0)));
        return d;
    }

    private static OfferList getDataDrivenOffers() {
        return OfferManager.instance().getOffers();
    }
    public static void initializeBuyOffer(ServerPlayerEntity player) {
        var d = new OfferList();
        d.addAll(getOffers());
        //d.add(new Offer(Items.CACTUS.getDefaultStack(), new Value(1)));
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory(((syncId, inv, player1) -> new BuyScreenHandler(d, inv, syncId)), new LiteralText("NPC")));
        PacketHandler.sendOffers(player, player.currentScreenHandler.syncId, d);
    }

    public static void initalizeSellOffer(ServerPlayerEntity player) {
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory(((syncId, inv, player1) -> {
            return new BuyScreenHandler(new OfferList(), inv, syncId);
        }), new LiteralText("NPC")));
    }
}
