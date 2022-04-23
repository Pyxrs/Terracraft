package io.github.simplycmd.terracraft.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.util.Offer;
import io.github.simplycmd.terracraft.util.OfferList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public abstract class OfferProvider implements DataProvider {
    private final OfferList buyOffers = new OfferList();
    private final OfferList sellOffers = new OfferList();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    public final FabricDataGenerator dataGenerator;
    public final String modid;
    public OfferProvider(FabricDataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
        this.modid = dataGenerator.getModId();
    }

    @Override
    public void run(DataCache cache) throws IOException {
        generateOffers();
        var object = new JsonArray();
        var object2 = new JsonArray();
        for (Offer offer : buyOffers) {
            object.add(serializeOffer(offer));
        }
        for (Offer offer : sellOffers) {
            object2.add(serializeOffer(offer));
        }
        var trueObject = new JsonObject();
        var trueObject2 = new JsonObject();
        trueObject.addProperty("replace", false);
        trueObject.add("offers", object);
        trueObject2.addProperty("replace", false);
        trueObject2.add("offers", object2);
        String rawJson = JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(GSON.toJson(trueObject));
        String rawJson2 = JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(GSON.toJson(trueObject2));
        Path path = this.dataGenerator.getOutput().resolve("data/" + this.dataGenerator.getModId() + "/terracraft/" + "buy_offers" + ".json");
        Path path2 = this.dataGenerator.getOutput().resolve("data/" + this.dataGenerator.getModId() + "/terracraft/" + "sell_offers" + ".json");
        var debug = true;
        if (debug) {
            System.out.println("JSON(BUY): " + rawJson);
            System.out.println("Path(BUY): " + path);
            System.out.println("JSON(SELL): " + rawJson2);
            System.out.println("Path(SELL): " + path2);
        }
        String hash = SHA1.hashUnencodedChars(rawJson).toString();
        String hash2 = SHA1.hashUnencodedChars(rawJson2).toString();
        if (!Objects.equals(cache.getOldSha1(path), hash) || !Files.exists(path)) {
            Files.createDirectories(path.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(rawJson);
            }
        }
        if (!Objects.equals(cache.getOldSha1(path2), hash2) || !Files.exists(path2)) {
            Files.createDirectories(path2.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(path2)) {
                writer.write(rawJson2);
            }
        }
        cache.updateSha1(path, hash);
        cache.updateSha1(path2, hash2);
    }

    private JsonObject serializeOffer(Offer offer) {
        var object = new JsonObject();
        object.add("item", serializeItem(offer.getItem()));
        object.addProperty("price", offer.getValue().getValue());
        return object;
    }

    private JsonObject serializeItem(ItemStack item) {
        var object = new JsonObject();
        object.addProperty("id", Registry.ITEM.getId(item.getItem()).toString());
        object.addProperty("Count", item.getCount());
        if (item.getNbt() != null)
            object.addProperty("tag", item.getNbt().toString());
        return object;
    }

    @Override
    public String getName() {
        return "Offers: " + modid;
    }

    public void addBuyOffer(Offer offer) {
        buyOffers.add(offer);
    }

    public void addBuyOffer(ItemStack stack, Value value) {
        buyOffers.add(new Offer(stack, value));
    }

    public void addBuyOffer(ItemStack stack, long value) {
        buyOffers.add(new Offer(stack, new Value(value)));
    }

    public void addBuyOffer(ItemConvertible item, Value value) {
        buyOffers.add(new Offer(item.asItem().getDefaultStack(), value));
    }

    public void addBuyOffer(ItemConvertible item, long value) {
        buyOffers.add(new Offer(item.asItem().getDefaultStack(), new Value(value)));
    }

    public void addSellOffer(Offer offer) {
        sellOffers.add(offer);
    }

    public void addSellOffer(ItemStack stack, Value value) {
        sellOffers.add(new Offer(stack, value));
    }

    public void addSellOffer(ItemStack stack, long value) {
        sellOffers.add(new Offer(stack, new Value(value)));
    }

    public void addSellOffer(ItemConvertible item, Value value) {
        sellOffers.add(new Offer(item.asItem().getDefaultStack(), value));
    }

    public void addSellOffer(ItemConvertible item, long value) {
        sellOffers.add(new Offer(item.asItem().getDefaultStack(), new Value(value)));
    }

    protected abstract void generateOffers();
}
