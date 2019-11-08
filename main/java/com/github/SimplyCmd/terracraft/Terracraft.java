package com.github.SimplyCmd.terracraft;

import static com.github.SimplyCmd.terracraft.Terracraft.MOD_ID;

import com.github.SimplyCmd.terracraft.lists.BlockList;
import com.github.SimplyCmd.terracraft.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
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

    public static final ItemGroup MELEE = new MeleeItemGroup();

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
            event.getRegistry().registerAll(
                    ItemList.testing_dust = new Item(new Item.Properties().group(MELEE)).setRegistryName(location("testing_dust")), //Add a comma at the end of this line and Copy-Paste it below to add another and then go to ItemList
                    ItemList.angel_statue = new BlockItem(BlockList.angel_statue, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(BlockList.angel_statue.getRegistryName())
            );
            logger.info("Items registered");
        }

        @SubscribeEvent //run on launch
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(
                    BlockList.angel_statue = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 30.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(location("angel_statue")) //lightValue = how much it glows, Sound = breaking sound
            );
            logger.info("Blocks registered");
        }

        private static ResourceLocation location(String name) {
            return new ResourceLocation(MOD_ID, "testing_dust");
        }
    }
}
