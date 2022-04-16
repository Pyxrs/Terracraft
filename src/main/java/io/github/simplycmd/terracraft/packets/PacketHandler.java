package io.github.simplycmd.terracraft.packets;

import io.github.simplycmd.terracraft.JumpingEffect;
import io.github.simplycmd.terracraft.gui.BuyScreenHandler;
import io.github.simplycmd.terracraft.items.accessories.DoubleJumpAccessoryItem;
import io.github.simplycmd.terracraft.registry.ScreenHandlerRegistry;
import io.github.simplycmd.terracraft.util.TrinketsUtil;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.UUID;

import static io.github.simplycmd.terracraft.Main.MOD_ID;

public class PacketHandler {
    private static final Identifier DOUBLE_DUMP = new Identifier(MOD_ID, "double_jump");
    private static final Identifier DOUBLE_DUMP_EFFECT = new Identifier(MOD_ID, "double_jump_effect");
    public static final Identifier CHANGE_OFFER = new Identifier(MOD_ID, "change_offer");
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
                JumpingEffect.play(client.player, effectPlayer, effect, amount);
            }
        });
    }

    public static void receiveFromClient(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        var d = buf.readByte();
        if(Arrays.stream(TrinketsUtil.getDJList(player)).toList().contains(DoubleJumpAccessoryItem.getFromPower(d))) {
            server.execute(()->{
                //player.jump();
                player.getItemCooldownManager().set(DoubleJumpAccessoryItem.getFromPower(d), 5);
                player.openHandledScreen(new SimpleNamedScreenHandlerFactory(((syncId, inv, player1) -> new BuyScreenHandler(inv, syncId)), new LiteralText("NPC")));
                PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                passedData.writeUuid(player.getUuid());
                passedData.writeByte(DoubleJumpAccessoryItem.getFromPower(d).particleId());
                passedData.writeInt(DoubleJumpAccessoryItem.getFromPower(d).particleAmount());
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

    public static void registerServerPackets(){
        ServerPlayNetworking.registerGlobalReceiver(DOUBLE_DUMP, PacketHandler::receiveFromClient);
        ServerPlayNetworking.registerGlobalReceiver(CHANGE_OFFER, PacketHandler::processOfferChange);
    }
    public static void registerClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(DOUBLE_DUMP_EFFECT, PacketHandler::recieveFromServer);
    }

}
