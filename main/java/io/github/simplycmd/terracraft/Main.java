package io.github.simplycmd.terracraft;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main {
	// The MODID of the mod
	public static final String MODID = "terracraft";

	// A logger to print messages into the console
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	// This runs on mod startup
	public Main() {
		LOGGER.info("Hello! This is Terracraft. Hold on, I'm setting up a few things right now." +
				" In just a sec, you will get to start playing Minecraft!");
	}
}