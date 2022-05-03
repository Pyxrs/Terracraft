package io.github.simplycmd.terracraft.mixin;

import com.simplycmd.featherlib.registry.SimpleItem;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;
import java.util.function.BiConsumer;

@Mixin(SimpleItem.class)
public interface SimpleItemAccessor {
    @Mutable
    @Accessor
    void setResources(Optional<BiConsumer<Item, ItemModelGenerator>> resources);
}
