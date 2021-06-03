package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.simplylib.SimplyLib;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.items.*;
import io.github.simplycmd.terracraft.items.util.Value;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private static final HashMap<String, Item> ITEMS = new HashMap<String, Item>() {{
        put("magic_mirror", new MirrorItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
        put("grenade", new GrenadeThrowableItem(new FabricItemSettings().group(ItemGroup.COMBAT)));
        put("flaming_arrow", new FlamingArrowItem());
        put("soul_fire_arrow", new FlamingArrowItem());
        put("slap_hand", new SlapHandItem());
        put("copper_coin", new CoinItem(new Value(0, 0, 0, 1)));
        put("silver_coin", new CoinItem(new Value(0, 0, 1, 0)));
        put("gold_coin", new CoinItem(new Value(0, 1, 0, 0)));
        put("platinum_coin", new CoinItem(new Value(1, 0, 0, 0)));
        put("heart", new HeartItem());
    }};

    public static void register() {
        for (Map.Entry<String, Item> item : ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, Main.ID(item.getKey()), item.getValue());
        }
    }

    public static Item get(String itemId) {
        return ITEMS.getOrDefault(itemId, Items.AIR);
    }
}
