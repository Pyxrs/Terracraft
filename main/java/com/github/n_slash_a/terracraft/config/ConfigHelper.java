package com.github.n_slash_a.terracraft.config;

import net.minecraftforge.fml.config.ModConfig;

/**
 * This bakes the config values to normal fields
 *
 * @author N_slash_A
 * It can be merged into the main TerracraftiaConfig class, but is separate because of personal preference and to keep the code organised
 */
public final class ConfigHelper {

    // We store a reference to the ModConfigs here to be able to change the values in them from our code
    // (For example from a config GUI)
    private static ModConfig clientConfig;
    private static ModConfig serverConfig;

    public static void bakeClient(final ModConfig config) {
        clientConfig = config;

        TerracraftiaConfig.clientBoolean = ConfigHolder.CLIENT.clientBoolean.get();
        TerracraftiaConfig.clientStringList = ConfigHolder.CLIENT.clientStringList.get();
        TerracraftiaConfig.clientEnumDyeColor = ConfigHolder.CLIENT.clientEnumDyeColor.get();

        TerracraftiaConfig.modelTranslucency = ConfigHolder.CLIENT.modelTranslucency.get();
        TerracraftiaConfig.modelScale = ConfigHolder.CLIENT.modelScale.get();

    }

    public static void bakeServer(final ModConfig config) {
        serverConfig = config;

        TerracraftiaConfig.serverBoolean = ConfigHolder.SERVER.serverBoolean.get();
        TerracraftiaConfig.serverStringList = ConfigHolder.SERVER.serverStringList.get();
        TerracraftiaConfig.serverEnumDyeColor = ConfigHolder.SERVER.serverEnumDyeColor.get();

    }

    private static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
        modConfig.getConfigData().set(path, newValue);
        modConfig.save();
    }

}