package io.github.simplycmd.terracraft;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "terracraft";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public Main() {
        LOGGER.info("Hello! This is Terracraft. Just to let you know, I am setting up a few things right now. In a bit of time, you should be able to start playing Minecraft. But, for now, you're stuck with me. I have so many questions, just waiting to be answered! How are you today? Do you like cheese? If so, which kind? Ok ima go now BYEEEEEEEEEE!");

    }
}
