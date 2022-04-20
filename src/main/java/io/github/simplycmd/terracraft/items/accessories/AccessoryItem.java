package io.github.simplycmd.terracraft.items.accessories;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class AccessoryItem extends TrinketItem {
    private final Accessory[] accessories;
    private AccessoryItem(Accessory[] accessories, NeedsItem[] needsItems, Settings settings) {
        super(settings);
        var d = new ArrayList<Accessory>();
        d.addAll(Arrays.stream(accessories).toList());
        for (NeedsItem needsItem : needsItems) {
            d.add(needsItem.createAccessory(this));
        }
        this.accessories = d.toArray(new Accessory[0]);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var a = super.getModifiers(stack, slot, entity, uuid);
        for (Accessory accessory : this.accessories) {
            a.putAll(accessory.getModifiers(stack, slot, entity, uuid));
        }
        return a;
    }

    public Accessory[] getAccessories() {
        return Arrays.asList(accessories).toArray(new Accessory[0]);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        for (Accessory accessory : accessories) {
            accessory.inventoryTick(stack, world, entity, slot, selected);
        }
    }

    public static class Builder {
        private final ArrayList<Accessory> accessories = new ArrayList<>();
        private final ArrayList<NeedsItem> accessoriesItem = new ArrayList<>();
        private FabricItemSettings settings = null;
        private Builder() {}
        public static Builder create() {
            return new Builder();
        }

        public Builder settings(FabricItemSettings settings) {
            this.settings = settings;
            return this;
        }

        public Builder addAccessory(Accessory accessory) {
            accessories.add(accessory);
            return this;
        }

        public Builder addAccessory(NeedsItem item) {
            accessoriesItem.add(item);
            return this;
        }

        public AccessoryItem build() {
            if (this.settings == null) throw new RuntimeException("Settings should be non-null!");
            return new AccessoryItem(accessories.toArray(new Accessory[0]), accessoriesItem.toArray(new NeedsItem[0]), settings);
        }
    }

    public static interface NeedsItem {
        Accessory createAccessory(AccessoryItem item);
    }
}
