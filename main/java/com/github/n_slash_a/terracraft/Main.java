package com.github.n_slash_a.terracraft;

import com.github.n_slash_a.terracraft.config.ConfigHolder;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author N_slash_A
 */
@Mod(Main.MODID)
public class Main {

    public static final String MODID = "terracraft";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public Main() {
        LOGGER.debug("Hello from Main!");

        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        // Register Configs
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
    }
}