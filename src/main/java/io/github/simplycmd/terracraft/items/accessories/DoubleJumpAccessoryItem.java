package io.github.simplycmd.terracraft.items.accessories;

import com.simplycmd.featherlib.registry.SimpleItem;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import java.util.HashMap;

public abstract class DoubleJumpAccessoryItem extends AccessoryItem {
    public DoubleJumpAccessoryItem(FabricItemSettings settings) {
        super(settings);
    }

    public abstract byte particleId();

    public int particleAmount() {
        return 6;
    }

    public double jumpPower() {
        return 0;
    }

    public abstract int doubleJumps();

    public static DoubleJumpAccessoryItem create(FabricItemSettings settings, byte particleId, int particleAmount, double jumpPower, int doubleJumps) {
        return new DoubleJumpAccessoryItem(settings) {
            @Override
            public byte particleId() {
                return particleId;
            }

            @Override
            public int particleAmount() {
                return particleAmount;
            }

            @Override
            public double jumpPower() {
                return jumpPower;
            }

            @Override
            public int doubleJumps() {
                return doubleJumps;
            }
        };
    }

    private static HashMap<DoubleJumpAccessoryItem, Integer> power = null;
    //
    public static HashMap<DoubleJumpAccessoryItem, Integer> power() {
        if (power == null) {
            power = new HashMap<>();
            power.put(as(ItemRegistry.cloud_in_a_bottle), 0);
            power.put(as(ItemRegistry.tsunami_in_a_bottle), 5);
            power.put(as(ItemRegistry.sandstorm_in_a_bottle), 10);
        }
        return power;
    }
    public static DoubleJumpAccessoryItem getFromPower(int i) {
        final DoubleJumpAccessoryItem[] d = {null};
        power.forEach(((doubleJumpAccessoryItem, integer) -> {
            if (i == integer) d[0] = doubleJumpAccessoryItem;
        }));
        return d[0];
    }

    private static DoubleJumpAccessoryItem as(SimpleItem item) {
        return (DoubleJumpAccessoryItem) item.getItem();
    }

    public int getPower() {
        return power().get(this);
    }
}
