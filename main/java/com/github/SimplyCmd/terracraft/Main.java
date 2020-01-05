package com.github.simplycmd.terracraft;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public final class Main {

    public static final String MODID = "terracraft";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public Main() {
        LOGGER.debug("Hello from Main!");

        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        //modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        //modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
    }

}