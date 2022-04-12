package io.github.simplycmd.terracraft;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;

public class TrinketsUtil {
    public static boolean isEquipped(LivingEntity entity, Item item) {
        var component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (equipped.getLeft().inventory().getSlotType().getName().equals("accessory")
                        && equipped.getRight().getItem() == item) {
                    return true;
                }
            }
        }
        return false;
    }
}
