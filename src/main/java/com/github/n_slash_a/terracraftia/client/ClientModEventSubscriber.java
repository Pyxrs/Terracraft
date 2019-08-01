package com.github.n_slash_a.terracraftia.client;

import com.github.n_slash_a.terracraftia.Main;
import com.github.n_slash_a.terracraftia.client.render.tileentity.MiniModelTileEntityRenderer;
import com.github.n_slash_a.terracraftia.tileentity.MiniModelTileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 * @author Cadiboo
 */
@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(Main.MODID + " Client Mod Event Subscriber");

    /**
     * This method will be called by Forge when it is time for the mod to do its client-side setup
     * This method will always be called after the Registry events.
     * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
     */
    @SubscribeEvent
    public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {

        // Register TileEntity Renderers
        ClientRegistry.bindTileEntitySpecialRenderer(MiniModelTileEntity.class, new MiniModelTileEntityRenderer());
        LOGGER.debug("Registered TileEntity Renderers");

        // Register Entity Renderers
//		RenderingRegistry.registerEntityRenderingHandler(YourEntity.class, YourEntityRenderer::new);
//		LOGGER.debug("Registered Entity Renderers");

    }

}