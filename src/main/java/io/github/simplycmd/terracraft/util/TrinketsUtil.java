package io.github.simplycmd.terracraft.util;

import dev.emi.trinkets.api.TrinketsApi;
import io.github.simplycmd.terracraft.items.accessories.AccessoryItem;
import io.github.simplycmd.terracraft.items.accessories.DoubleJumpAccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemConvertible;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class TrinketsUtil {
    public static boolean isEquipped(LivingEntity entity, ItemConvertible item) {
        var component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (equipped.getLeft().inventory().getSlotType().getName().equals("accessory")
                        && equipped.getRight().getItem() == item.asItem()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isEquipped(LivingEntity entity, Class<? extends AccessoryItem> itemClazz) {
        var component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (equipped.getLeft().inventory().getSlotType().getName().equals("accessory")
                        && itemClazz.isAssignableFrom(equipped.getRight().getItem().getClass())) {
                    return true;
                }
            }
        }
        return false;
    }
    public static DoubleJumpAccessoryItem getDJ(LivingEntity entity) {
        var l = -1;
        var component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (equipped.getLeft().inventory().getSlotType().getName().equals("accessory")
                        && equipped.getRight().getItem() instanceof DoubleJumpAccessoryItem o) {
                    l = Math.max(DoubleJumpAccessoryItem.power().get(o), l);
                }
            }
        }
        if (!DoubleJumpAccessoryItem.power().containsValue(l)) return null;
        return DoubleJumpAccessoryItem.getFromPower(l);
    }

    public static DoubleJumpAccessoryItem[] getDJList(LivingEntity entity) {
        var l = -1;
        var d = new ArrayList<DoubleJumpAccessoryItem>();
        var component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (equipped.getLeft().inventory().getSlotType().getName().equals("accessory")
                        && equipped.getRight().getItem() instanceof DoubleJumpAccessoryItem o) {
                    d.add(o);
                }
            }
        }
        return d.toArray(new DoubleJumpAccessoryItem[0]);
    }


    public static DoubleJumpAccessoryItem getBestActiveDJ(LivingEntity entity) {
        @Nonnull
        var a = getDJList(entity);
        var d = new ArrayList<DoubleJumpAccessoryItem>();
        for (DoubleJumpAccessoryItem doubleJumpAccessoryItem : a) {
            if (((LivingEntityExtension)entity).terracraft$getJumpCounter().get(doubleJumpAccessoryItem) > 0) {
                d.add(doubleJumpAccessoryItem);
            }
        }

        DoubleJumpAccessoryItem l = null;
        for (DoubleJumpAccessoryItem doubleJumpAccessoryItem : d) {
            if (l == null || doubleJumpAccessoryItem.getPower() > l.getPower()) {
                l = doubleJumpAccessoryItem;
            }
        }
        return l;
    }
}
