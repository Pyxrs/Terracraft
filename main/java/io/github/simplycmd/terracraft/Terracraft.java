package io.github.simplycmd.terracraft;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Terracraft implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "terracraft";
    public static final String MOD_NAME = "Terracraft";

    private static int currentItem;
    private static int stackSize;

    //New item instances

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing Items");
        register(Registry.ITEM, Content.accessories, ItemGroup.COMBAT, false);
        register(Registry.ITEM, Content.ammunition, ItemGroup.COMBAT, true);
        register(Registry.ITEM, Content.armor, ItemGroup.COMBAT, false);
        register(Registry.ITEM, Content.bars, ItemGroup.MATERIALS, true);
        register(Registry.ITEM, Content.blocks, ItemGroup.BUILDING_BLOCKS, true);
        register(Registry.ITEM, Content.coins, ItemGroup.MISC, true);
        register(Registry.ITEM, Content.crafting_stations, ItemGroup.DECORATIONS, true);
        register(Registry.ITEM, Content.dyes, ItemGroup.MATERIALS, false);
        register(Registry.ITEM, Content.furniture, ItemGroup.DECORATIONS, true);
        register(Registry.ITEM, Content.gems, ItemGroup.MATERIALS, true);
        register(Registry.ITEM, Content.minions, ItemGroup.TOOLS, false);
        register(Registry.ITEM, Content.miscellaneous, ItemGroup.MISC, true);
        register(Registry.ITEM, Content.mounts, ItemGroup.TRANSPORTATION, false);
        register(Registry.ITEM, Content.ores, ItemGroup.MATERIALS, true);
        register(Registry.ITEM, Content.paint, ItemGroup.MISC, true);
        register(Registry.ITEM, Content.pets, ItemGroup.MISC, false);
        register(Registry.ITEM, Content.potions, ItemGroup.BREWING, true);
        register(Registry.ITEM, Content.statues, ItemGroup.DECORATIONS, true);
        register(Registry.ITEM, Content.tools, ItemGroup.TOOLS, false);
        register(Registry.ITEM, Content.vanity_items, ItemGroup.MISC, false);
        register(Registry.ITEM, Content.walls, ItemGroup.DECORATIONS, true);
        register(Registry.ITEM, Content.weapons, ItemGroup.COMBAT, false);
        register(Registry.ITEM, Content.wings, ItemGroup.MISC, false);
        register(Registry.ITEM, Content.wire, ItemGroup.REDSTONE, true);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    public static void register(Registry<Item> type, String[] category, ItemGroup tab, Boolean stack){
        currentItem = 0;
        if (stack == true) {stackSize = 64;} else {stackSize = 1;}
        while (currentItem < category.length) {
            Registry.register(type, new Identifier("terracraft", category[currentItem]), new Item(new Item.Settings().group(tab).maxCount(stackSize)));
            currentItem++;
        }
    }
}