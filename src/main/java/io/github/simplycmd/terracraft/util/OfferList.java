package io.github.simplycmd.terracraft.util;

import io.github.simplycmd.terracraft.items.util.Value;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;

import java.util.ArrayList;

public class OfferList extends ArrayList<Offer> {
    public OfferList(NbtCompound nbt) {
        NbtList nbtList = nbt.getList("Recipes", 10);

        for (int i = 0; i < nbtList.size(); ++i) {
            this.add(new Offer(nbtList.getCompound(i)));
        }

    }
    public OfferList(){}

    public void toPacket(PacketByteBuf buf) {
        buf.writeByte((byte)(this.size() & 255));

        for (Offer offer : this) {
            buf.writeItemStack(offer.getItem());
            buf.writeLong(offer.getValue().getValue());
        }
    }

    public static OfferList fromPacket(PacketByteBuf buf) {
        OfferList tradeOfferList = new OfferList();
        int i = buf.readByte() & 255;

        for(int j = 0; j < i; ++j) {
            ItemStack itemStack = buf.readItemStack();
            long price = buf.readLong();
            tradeOfferList.add(new Offer(itemStack, new Value(price)));
        }

        return tradeOfferList;
    }
}

