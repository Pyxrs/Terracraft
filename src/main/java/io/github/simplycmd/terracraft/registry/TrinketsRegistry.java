package io.github.simplycmd.terracraft.registry;

import dev.emi.trinkets.api.TrinketsApi;
import io.github.simplycmd.terracraft.Main;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class TrinketsRegistry {
    public static void register() {
        registerPredicate("hat", EquipmentSlot.HEAD);
		registerPredicate("shirt", EquipmentSlot.CHEST);
		registerPredicate("pants", EquipmentSlot.LEGS);
		registerPredicate("shoes", EquipmentSlot.FEET);
    }

    // The following code is based on VanitySlot's predicate system. https://github.com/NyakoFox/VanitySlots
    public static void registerPredicate(String identifier, EquipmentSlot slot) {
        TrinketsApi.registerTrinketPredicate(Main.ID(identifier), (stack, ref, entity) -> {
            if (stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getSlotType() == slot) {
                return TriState.TRUE;
            }
            return TriState.DEFAULT;
        });
    
        TrinketsApi.registerTrinketPredicate(Main.ID("quick_" + identifier), (stack, ref, entity) -> {
            if (entity.getEquippedStack(slot).isEmpty()) {
                return TriState.FALSE;
            }
            return TriState.TRUE;
        });
    }
}