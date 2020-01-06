package com.github.simplycmd.terracraft;

import com.github.simplycmd.terracraft.init.ModItemGroups;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD) //EventBusSubscriber tells Forge that this class contains methods that should be subscribed to handle events. It contains the modid, bus and value parameters.
public class ModEventSubscriber {
    private static final Logger LOGGER = LogManager.getLogger(Main.MODID + " Mod Event Subscriber");

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)), "testing_block") //The rock material means you need a pic to break
        );
        LOGGER.debug("Registered Blocks");
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
                // This is a very simple Item. It has no special properties except for being on our creative tab.
                setup(new Item(new Item.Properties().group(ModItemGroups.TERRACRAFT)), "testing_dust")
        );

        //Automatically registers blockitems for blocks
        ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block.getRegistryName().getNamespace().equals(Main.MODID))
                .forEach(block -> {
                    final Item.Properties properties = new Item.Properties().group(ModItemGroups.TERRACRAFT);
                    final BlockItem blockItem = new BlockItem(block, properties);
                    registry.register(setup(blockItem, block.getRegistryName()));
                });
        LOGGER.debug("Registered Items");
    }


    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(Main.MODID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }
}