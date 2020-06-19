package io.github.simplycmd.terracraft;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = Main.MODID, /*Tells forge that this class belongs to Terracraft*/ bus = Mod.EventBusSubscriber.Bus.MOD /*This parameter tells Forge that @SubscribeEvent methods in this class should receive events from the MOD event bus.*/)
public class ModEventSubscriber {
	private static int currentItem = 0;

	@SubscribeEvent // Tells Forge that this method wants to subscribe to/listen for an event
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		while (currentItem < ContentLists.allitems.length) {
			event.getRegistry().registerAll(
					setup(new Item(new Item.Properties()), ContentLists.allitems[currentItem])
			);
			currentItem++;
		}
	}

	//----------------------------------------------------------------------------------------------------------------
	//The following code is a very elegant solution to the problem of having very messy and fragile registration code.
	//----------------------------------------------------------------------------------------------------------------

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
