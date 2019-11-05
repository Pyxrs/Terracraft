package com.github.SimplyCmd.terracraft;

import static com.github.SimplyCmd.terracraft.Terracraft.MOD_ID;

import com.github.SimplyCmd.terracraft.lists.ItemList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The main class of the mod, this is the class that looks like a mod to forge.
 */
@Mod(MOD_ID)
public class Terracraft {
    /**
     * The modid of this mod, this has to match the modid in the mods.toml and has to be in the format defined in {@link net.minecraftforge.fml.loading.moddiscovery.ModInfo}
     */
    public static final String MOD_ID = "terracraft";
    public static Terracraft instance;
    private static final Logger logger = LogManager.getLogger(MOD_ID);

    public Terracraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Setup method registered");
    }
    private void clientRegistries(final FMLClientSetupEvent event) {
        logger.info("clientRegistries method registered");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent //run on launch
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            ItemList.testing_dust = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("testing_dust"));

            logger.info("Items registered")
        }

        private static ResourceLocation location(String name) {
            return new ResourceLocation(MOD_ID, name);
        }
    }
}
//at 10:30