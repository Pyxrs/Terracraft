package io.github.simplycmd.terracraft.packets;

import io.github.simplycmd.terracraft.items.accessories.DoubleJumpAccessory;
import io.github.simplycmd.terracraft.util.AccessoryUtil;
import io.github.simplycmd.terracraft.util.ParticleUtil;
import io.github.simplycmd.terracraft.gui.BuyScreenHandler;
import io.github.simplycmd.terracraft.util.OfferList;
import io.github.simplycmd.terracraft.util.OfferUtils;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.UUID;

import static io.github.simplycmd.terracraft.Main.MOD_ID;

public class PacketHandler {
    private static final Identifier DOUBLE_DUMP = new Identifier(MOD_ID, "double_jump");
    private static final Identifier DOUBLE_DUMP_EFFECT = new Identifier(MOD_ID, "double_jump_effect");
    public static final Identifier CHANGE_OFFER = new Identifier(MOD_ID, "change_offer");
    public static final Identifier OFFER_CHANGED = new Identifier(MOD_ID, "offer_changed");
    public static final Identifier OPEN_SELL_SCREEN = new Identifier(MOD_ID, "open_sell_screen");
    public static void sendToServer(int power) {
        System.out.println("SEND");
        var buf = PacketByteBufs.create();
        buf.writeByte(power);
        ClientPlayNetworking.send(DOUBLE_DUMP, buf);
    }

    public static void recieveFromServer(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        UUID effectPlayerUuid = buf.readUuid();
        byte effect = buf.readByte();
        int amount = buf.readInt();
        System.out.println(effect);
        client.execute(() -> {
            PlayerEntity effectPlayer = client.player.getEntityWorld().getPlayerByUuid(effectPlayerUuid);
            if (effectPlayer != null) {
                ParticleUtil.jumpEffect(client.player, effectPlayer, effect, amount);
            }
        });
    }

    public static void receiveFromClient(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        var d = buf.readByte();
        if(Arrays.stream(AccessoryUtil.getDJList(player)).toList().contains(DoubleJumpAccessory.getFromPower(d))) {
            server.execute(()->{
                //player.jump();
                //player.getItemCooldownManager().set(DoubleJumpAccessory.getFromPower(d), 5);
                PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                OfferUtils.initializeBuyOffer(player);
                passedData.writeUuid(player.getUuid());
                passedData.writeByte(DoubleJumpAccessory.getFromPower(d).particleId());
                passedData.writeInt(DoubleJumpAccessory.getFromPower(d).particleAmount());
                PlayerLookup.tracking(player).forEach(p -> {
                    if (p != player) {
                        ServerPlayNetworking.send(p, DOUBLE_DUMP_EFFECT, passedData);
                    }
                });
            });
        }
    }

    public static void processOfferChange(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        var d = buf.readInt();
        server.execute(()-> {
            //NetworkThreadUtils.forceMainThread(handler, handler);
            ScreenHandler screenHandler = player.currentScreenHandler;
            if (screenHandler instanceof BuyScreenHandler buyScreenHandler) {
                buyScreenHandler.setRecipeIndex(d);
                buyScreenHandler.switchTo(d);
            }
        });
    }


    public static void processOfferChange(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        var syncId = buf.readInt();
        var offers = OfferList.fromPacket(buf);
        client.execute(() -> {
            ScreenHandler screenHandler = client.player.currentScreenHandler;
            if (syncId == client.player.currentScreenHandler.syncId && screenHandler instanceof BuyScreenHandler handler1) {
                handler1.setOffers(offers);
            }
        });
    }

    public static void openSellScreen() {
        var buf = PacketByteBufs.create();
        ClientPlayNetworking.send(OPEN_SELL_SCREEN, buf);
    }

    private static void openSellScreen(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            player.closeHandledScreen();
            OfferUtils.initalizeSellOffer(player);
        });
    }

    public static void registerServerPackets(){
        ServerPlayNetworking.registerGlobalReceiver(DOUBLE_DUMP, PacketHandler::receiveFromClient);
        ServerPlayNetworking.registerGlobalReceiver(CHANGE_OFFER, PacketHandler::processOfferChange);
        ServerPlayNetworking.registerGlobalReceiver(OPEN_SELL_SCREEN, PacketHandler::openSellScreen);
    }
    public static void registerClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(DOUBLE_DUMP_EFFECT, PacketHandler::recieveFromServer);
        ClientPlayNetworking.registerGlobalReceiver(OFFER_CHANGED, PacketHandler::processOfferChange);
    }

    public static void sendOffers(ServerPlayerEntity player, int syncId, OfferList offers) {
        var buf = PacketByteBufs.create();
        buf.writeInt(syncId);
        offers.toPacket(buf);
        ServerPlayNetworking.send(player, OFFER_CHANGED, buf);
    }
}
