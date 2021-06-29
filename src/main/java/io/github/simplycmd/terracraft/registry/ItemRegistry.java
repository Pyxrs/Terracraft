package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.items.*;
import io.github.simplycmd.terracraft.items.util.ArmorMaterials;
import io.github.simplycmd.terracraft.items.util.tools.AxeItem;
import io.github.simplycmd.terracraft.items.util.tools.HoeItem;
import io.github.simplycmd.terracraft.items.util.tools.PickaxeItem;
import io.github.simplycmd.terracraft.items.util.tools.ToolMaterials;
import net.devtech.arrp.json.models.JModel;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

import static net.devtech.arrp.json.models.JModel.model;

public class ItemRegistry {
    private static final HashMap<String, Item> ITEMS = new HashMap<>() {{
        put("magic_mirror", new MirrorItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
        put("grenade", new GrenadeThrowableItem(new FabricItemSettings().group(ItemGroup.COMBAT)));
        put("flaming_arrow", new FlamingArrowItem());
        put("soul_fire_arrow", new FlamingArrowItem());
        put("slap_hand", new SlapHandItem());
        put("heart", new HeartItem());

        put("copper_coin", new CoinItem(CoinItem.Coin.COPPER));
        put("silver_coin", new CoinItem(CoinItem.Coin.SILVER));
        put("gold_coin", new CoinItem(CoinItem.Coin.GOLD));
        put("platinum_coin", new CoinItem(CoinItem.Coin.PLATINUM));

        put("cactus_helmet", new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT)));
        put("cactus_chestplate", new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT)));
        put("cactus_leggings", new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT)));
        put("cactus_boots", new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.FEET, new FabricItemSettings().group(ItemGroup.COMBAT)));
        put("cactus_sword", new SwordItem(ToolMaterials.CACTUS, 3, -2.4F, new FabricItemSettings().group(ItemGroup.COMBAT)));
        put("cactus_shovel", new ShovelItem(ToolMaterials.CACTUS, 1.5F, -3.0F, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("cactus_pickaxe", new PickaxeItem(ToolMaterials.CACTUS, 1, -2.8F, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("cactus_axe", new AxeItem(ToolMaterials.CACTUS, 6.0F, -3.2F, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("cactus_hoe", new HoeItem(ToolMaterials.CACTUS, 0, -3.0F, new FabricItemSettings().group(ItemGroup.TOOLS)));
    }};

    public static void register() {
        for (Map.Entry<String, Item> item : ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, Main.ID(item.getKey()), item.getValue());
            Main.RESOURCE_PACK.addModel(model().parent("minecraft:item/generated").textures(JModel.textures().layer0(Main.MOD_ID + ":item/" + item.getKey())), Main.ID("item/" + item.getKey()));
        }
    }

    public static Item get(String itemId) {
        if (ITEMS != null) return ITEMS.get(itemId);
        else return Items.AIR;
    }
}
