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
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public abstract class OfferProvider implements DataProvider {
    private final OfferList offers = new OfferList();
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
        for (Offer offer : offers) {
            object.add(serializeOffer(offer));
        }
        var trueObject = new JsonObject();
        trueObject.addProperty("replace", false);
        trueObject.add("offers", object);
        String rawJson = JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(GSON.toJson(trueObject));
        Path path = this.dataGenerator.getOutput().resolve("data/" + this.dataGenerator.getModId() + "/terracraft/" + "offers" + ".json");
        var debug = true;
        if (debug) {
            System.out.println("JSON: " + rawJson);
            System.out.println("Path: " + path);
        }
        String hash = SHA1.hashUnencodedChars(rawJson).toString();
        if (!Objects.equals(cache.getOldSha1(path), hash) || !Files.exists(path)) {
            Files.createDirectories(path.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(rawJson);
            }
        }
        cache.updateSha1(path, hash);
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

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public void addOffer(ItemStack stack, Value value) {
        offers.add(new Offer(stack, value));
    }

    public void addOffer(ItemStack stack, long value) {
        offers.add(new Offer(stack, new Value(value)));
    }

    public void addOffer(Item item, Value value) {
        offers.add(new Offer(item.getDefaultStack(), value));
    }

    public void addOffer(Item item, long value) {
        offers.add(new Offer(item.getDefaultStack(), new Value(value)));
    }

    protected abstract void generateOffers();
}
