package io.github.simplycmd.terracraft.util;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import dev.emi.trinkets.api.TrinketsApi;
import io.github.simplycmd.terracraft.items.accessories.AccessoryItem;
import io.github.simplycmd.terracraft.items.accessories.Accessory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import io.github.simplycmd.terracraft.items.accessories.DoubleJumpAccessory;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class AccessoryUtil {
    /**
     * Checks if the given AccessoryItem is equipped on the given LivingEntity.
     * @param entity The LivingEntity to check.
     * @param accessory The AccessoryItem to check.
     * @return The outcome of the check.
     */
    public static boolean isItemEquipped(LivingEntity entity, AccessoryItem accessory) {
        AtomicBoolean z = new AtomicBoolean(false);
        TrinketsApi.getTrinketComponent(entity).ifPresent((a)-> {
            a.getAllEquipped().forEach((slotReferenceItemStackPair -> {
                var b = slotReferenceItemStackPair.getLeft();
                var c = slotReferenceItemStackPair.getRight();
                if (b.inventory().getSlotType().getName().equals("accessory") && c.getItem() instanceof AccessoryItem o) {
                    if (o == accessory) z.set(true);
                }
            }));
        });
//        return ifEquipped(entity, "accessory", (item) ->
//            Optional.of(item instanceof AccessoryItem && item == accessory)
//        ).map(bool -> (Boolean) bool).orElse(false); // Trust me, I'm just as confused as you are.
        return z.get();
    }

    /**
     * Checks if the given AccessoryItem/ is equipped on the given LivingEntity.
     * @param entity The LivingEntity to check.
     * @param power The Class to check against.
     * @return The outcome of the check.
     */
    public static boolean isPowerEquipped(LivingEntity entity, Class<? extends Accessory> power) {
        AtomicBoolean z = new AtomicBoolean(false);
        TrinketsApi.getTrinketComponent(entity).ifPresent((a)-> {
            a.getAllEquipped().forEach((slotReferenceItemStackPair -> {
                var b = slotReferenceItemStackPair.getLeft();
                var c = slotReferenceItemStackPair.getRight();
                if (b.inventory().getSlotType().getName().equals("accessory") && c.getItem() instanceof AccessoryItem o) {
                    for (Accessory accessory : o.getAccessories()) {
                        if (power.isAssignableFrom(accessory.getClass())) z.set(true);
                    }
                }
            }));
        });
        return z.get();
    }

    /**
     * Executes the given Function on each item in the given slot type group.
     * @param entity The LivingEntity to check.
     * @param slotType String name of the SlotType.
     * @param function The Function to execute on each Item.
     * @return The first Function returned if there was an Item, otherwise Optional.empty().
     */
    public static Optional<?> ifEquipped(LivingEntity entity, String slotType, Function<Item, Optional<?>> function) {
        var component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        Optional<?> e = Optional.empty();
        var d = true;
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (equipped.getLeft().inventory().getSlotType().getName().equals(slotType)) {
                    var f = function.apply(equipped.getRight().getItem());
                    if (d && f.isPresent()) {
                        e = f; d = false;
                    }
                }
            }
        }
        return e;
    }

    /**
     * Tests if the given accessory isof/has the given class.
     * @param accessoryItem The AccessoryItem to test.
     * @param testAgainstClass The Class to check against.
     * @return The outcome of the test.
     */
    public static boolean testHasPowers(AccessoryItem accessoryItem, Class<? extends Accessory> testAgainstClass) {
        // Check if it's a CombinationAccessoryItem
        if (testCombinationContains(accessoryItem, testAgainstClass)) {
            return true;
        }
        // Check if it's an instance of the given AccessoryItem
        if (accessoryItem.getClass() == testAgainstClass) {
            return true;
        }
        return false;
    }

    private static boolean testCombinationContains(AccessoryItem combinationItem, Class<? extends Accessory> testAgainstClass) {
        for (var ingredient : combinationItem.getAccessories()) {
            if (ingredient.getClass() == testAgainstClass) {
                return true;
            }
        }
        return false;
    }

    public static DoubleJumpAccessory getDJ(LivingEntity entity) {
        var l = -1;
        var component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (equipped.getLeft().inventory().getSlotType().getName().equals("accessory")
                        && equipped.getRight().getItem() instanceof AccessoryItem o) {
                    for (Accessory accessory : o.getAccessories()) {
                        if (!(accessory instanceof DoubleJumpAccessory)) continue;
                        l = Math.max(DoubleJumpAccessory.power().get(accessory), l);
                    }
                }
            }
        }
        if (!DoubleJumpAccessory.power().containsValue(l)) return null;
        return DoubleJumpAccessory.getFromPower(l);
    }

    public static DoubleJumpAccessory[] getDJList(LivingEntity entity) {
        var l = -1;
        var d = new ArrayList<DoubleJumpAccessory>();
        var component = TrinketsApi.getTrinketComponent((LivingEntity) entity);
        if (component.isPresent()) {
            for (var equipped : component.get().getAllEquipped()) {
                if (equipped.getLeft().inventory().getSlotType().getName().equals("accessory")
                        && equipped.getRight().getItem() instanceof AccessoryItem o) {
                    for (Accessory accessory : o.getAccessories()) {
                        if (!(accessory instanceof DoubleJumpAccessory q)) continue;
                        d.add(q);
                    }
                }
            }
        }
        return d.toArray(new DoubleJumpAccessory[0]);
    }


    public static DoubleJumpAccessory getBestActiveDJ(LivingEntity entity) {
        @Nonnull
        var a = getDJList(entity);
        var d = new ArrayList<DoubleJumpAccessory>();
        for (DoubleJumpAccessory doubleJumpAccessoryItem : a) {
            if (((LivingEntityExtension)entity).terracraft$getJumpCounter().get(doubleJumpAccessoryItem) > 0) {
                d.add(doubleJumpAccessoryItem);
            }
        }

        DoubleJumpAccessory l = null;
        for (DoubleJumpAccessory doubleJumpAccessoryItem : d) {
            if (l == null || doubleJumpAccessoryItem.getPower() > l.getPower()) {
                l = doubleJumpAccessoryItem;
            }
        }
        return l;
    }

    public static boolean isEquipped(LivingEntity entity, Class<? extends Accessory> itemClazz) {
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
}
