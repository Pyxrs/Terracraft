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

    //New item instances
    public static final Item Gel = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing Items");
        Registry.register(Registry.ITEM, new Identifier("terracraft", "gel"), Gel);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}