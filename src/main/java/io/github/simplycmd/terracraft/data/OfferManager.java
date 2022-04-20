package io.github.simplycmd.terracraft.data;

import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.util.Offer;
import io.github.simplycmd.terracraft.util.OfferList;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static io.github.simplycmd.terracraft.Main.MOD_ID;

public class OfferManager implements IdentifiableResourceReloadListener {
    private static final int FILE_TYPE_LENGTH_VALUE = ".json".length();
    private static final Gson GSON = new GsonBuilder().create();
    private static OfferManager instance;
    private OfferList offers = new OfferList();

    public static OfferManager instance() {
        if (instance == null) {
            instance = new OfferManager();
        }
        return instance;
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier(MOD_ID, "offer_manager");
    }

    public OfferList getOffers() {
        return offers;
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        return CompletableFuture.allOf(CompletableFuture.runAsync(() -> {
            String folder = "terracraft";
            List<Identifier> resources = new ArrayList<>(manager.findResources(folder, (filename)-> Objects.equals(filename, "buy_offers.json")));
            resources.forEach((identifier -> System.out.println("Identifier: " + identifier)));
            resources.sort((r1, r2) -> {
                if(r1.getNamespace().equals(r2.getNamespace())) return 0;
                return r2.getNamespace().equals(MOD_ID) ? 1 : -1;
            });
            this.deserializeOffers(manager, resources);
        })).thenCompose(synchronizer::whenPrepared);
    }

    private void deserializeOffers(ResourceManager manager, List<Identifier> resources) {
        for (Identifier resource : resources) {
            System.out.println("A");
            try (InputStream inputStream = manager.getResource(resource).getInputStream(); Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                JsonObject object = JsonHelper.deserialize(GSON, reader, JsonObject.class);
                Objects.requireNonNull(object, "how");
                JsonArray array = object.getAsJsonArray("offers");
                for (JsonElement jsonElement : array) {
                    System.out.println("B");
                    deserializeOffer(jsonElement.getAsJsonObject());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void deserializeOffer(JsonObject object) {
        try {
            System.out.println("B");
            System.out.println(object.toString());
            var stack = deserializeItemStack(object.getAsJsonObject("item"));
            var price = object.get("price").getAsLong();
            offers.add(new Offer(stack, new Value(price)));
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static ItemStack deserializeItemStack(JsonObject object) throws CommandSyntaxException {
        var count = object.get("Count").getAsInt();
        Item item = Registry.ITEM.get(Identifier.tryParse(object.get("id").getAsString()));
        var itemstack = new ItemStack(item);
        if (object.has("tag")) {
            var nbt = StringNbtReader.parse(object.get("tag").getAsString());
            itemstack.setNbt(nbt);
        }
        itemstack.setCount(count);
        //System.out.println(itemstack.toString() + " " + itemstack.getNbt().toString());
        return itemstack;
    }
}
