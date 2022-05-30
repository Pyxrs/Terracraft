package io.github.simplycmd.terracraft.items.util.accessories;

import java.util.UUID;
import java.util.Optional;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.SlotType;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import io.github.simplycmd.terracraft.TrinketsUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import dev.emi.trinkets.api.TrinketItem;
import lombok.Getter;

public class AccessoryItem extends TrinketItem {
    @Getter
    private final UUID accessoryUuid;

    public AccessoryItem(FabricItemSettings settings) {
        super(settings.maxCount(1));
        this.accessoryUuid = UUID.randomUUID();
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return !TrinketsUtil.isEquipped(entity, this);
	}
}
