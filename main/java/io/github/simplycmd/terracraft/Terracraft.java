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

    private static int currentItem = 0;

    //New item instances

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing Items");
        while (currentItem < Content.allitems.length) {
            Registry.register(Registry.ITEM, new Identifier("terracraft", Content.allitems[currentItem]), new Item(new Item.Settings().group(ItemGroup.MISC)));
            currentItem++;
        }
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}