package io.github.simplycmd.terracraft.items;

import com.simplycmd.featherlib.registry.SimpleItem;
import io.github.simplycmd.terracraft.mixin.SimpleItemAccessor;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.BiConsumer;

public class ExtendedSimpleItem extends SimpleItem {
    public ExtendedSimpleItem(Identifier id, Item item) {
        super(id, item);
    }

    public ExtendedSimpleItem(Identifier id, Item item, BiConsumer<Item, ItemModelGenerator> resources) {
        super(id, item, resources);
    }

    public ExtendedSimpleItem defaultItemModel() {
        System.out.println(this.resources);
        BiConsumer<Item, ItemModelGenerator> d = ((item, itemModelGenerator) -> {
            //System.out.println("this is called");
            itemModelGenerator.register(item, Models.GENERATED);
        });
        ((SimpleItemAccessor) this).setResources(Optional.of(d));
        //this.resources;
        System.out.println(this.resources);
        return this;
    }

    @Override
    protected void register() {
        super.register();
    }
}
