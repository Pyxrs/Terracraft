package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.Main;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static void RegisterItems() {
        RegisterItem("magic_mirror", new MirrorItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
    }

    public static void RegisterItem(String id, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, id), item);
    }
}
