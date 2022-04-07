package io.github.simplycmd.terracraft.items.util.accessories;

import java.util.UUID;

import dev.emi.trinkets.api.TrinketItem;
import lombok.Getter;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class AccessoryItem extends TrinketItem {
    @Getter
    private final UUID accessoryUuid;

    public AccessoryItem(FabricItemSettings settings) {
        super(settings.maxCount(1));
        this.accessoryUuid = UUID.randomUUID();
    }
}
