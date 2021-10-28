package io.github.simplycmd.terracraft.registry;

import io.github.simplycmd.simplylib.registry.BlockRegistry;
import io.github.simplycmd.simplylib.registry.ID;
import io.github.simplycmd.simplylib.registry.ItemRegistry;
import io.github.simplycmd.simplylib.registry.RegisterModItemCallback;
import io.github.simplycmd.terracraft.Main;
import io.github.simplycmd.terracraft.items.*;
import io.github.simplycmd.terracraft.items.util.ArmorMaterials;
import io.github.simplycmd.terracraft.items.util.tools.AxeItem;
import io.github.simplycmd.terracraft.items.util.tools.HoeItem;
import io.github.simplycmd.terracraft.items.util.tools.PickaxeItem;
import io.github.simplycmd.terracraft.items.util.tools.ToolMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class ItemReg {

    public static void register() {
        RegisterModItemCallback.EVENT.register((items) -> {
            items.put(ID("magic_mirror"), new MirrorItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
            items.put(ID("grenade"), new GrenadeThrowableItem(new FabricItemSettings().group(ItemGroup.COMBAT)));
            items.put(ID("flaming_arrow"), new FlamingArrowItem());
            items.put(ID("soul_fire_arrow"), new FlamingArrowItem());
            items.put(ID("slap_hand"), new SlapHandItem());
            items.put(ID("wand_of_sparking"), new WandOfSparkingItem(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(250)));
            items.put(ID("heart"), new HeartItem());
            items.put(ID("umbrella"), new UmbrellaItem());
            items.put(ID("sickle"), new SickleItem());

            items.put(ID("daybloom"), new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));

            items.put(ID("copper_coin"), new CoinItem(CoinItem.Coin.COPPER));
            items.put(ID("silver_coin"), new CoinItem(CoinItem.Coin.SILVER));
            items.put(ID("gold_coin"), new CoinItem(CoinItem.Coin.GOLD));
            items.put(ID("platinum_coin"), new CoinItem(CoinItem.Coin.PLATINUM));

            items.put(ID("cactus_helmet"), new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT)));
            items.put(ID("cactus_chestplate"), new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT)));
            items.put(ID("cactus_leggings"), new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT)));
            items.put(ID("cactus_boots"), new ArmorItem(ArmorMaterials.CACTUS, EquipmentSlot.FEET, new FabricItemSettings().group(ItemGroup.COMBAT)));
            items.put(ID("cactus_sword"), new SwordItem(ToolMaterials.CACTUS, 3, -2.4F, new FabricItemSettings().group(ItemGroup.COMBAT)));
            items.put(ID("cactus_shovel"), new ShovelItem(ToolMaterials.CACTUS, 1.5F, -3.0F, new FabricItemSettings().group(ItemGroup.TOOLS)));
            items.put(ID("cactus_pickaxe"), new PickaxeItem(ToolMaterials.CACTUS, 1, -2.8F, new FabricItemSettings().group(ItemGroup.TOOLS)));
            items.put(ID("cactus_axe"), new AxeItem(ToolMaterials.CACTUS, 6.0F, -3.2F, new FabricItemSettings().group(ItemGroup.TOOLS)));
            items.put(ID("cactus_hoe"), new HoeItem(ToolMaterials.CACTUS, 0, -3.0F, new FabricItemSettings().group(ItemGroup.TOOLS)));
        });

        ItemRegistry.register();
    }

    public static Item get(String itemId) {
        Item item = ItemRegistry.get(ID(itemId));
        if (item != null) {
            return item;
        } else {
            return Items.AIR;
        }
    }

    private static ID ID(String id) {
        return new ID(Main.MOD_ID, id);
    }
}
