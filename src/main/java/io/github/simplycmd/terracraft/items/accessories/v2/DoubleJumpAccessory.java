package io.github.simplycmd.terracraft.items.accessories.v2;

import com.simplycmd.featherlib.registry.SimpleItem;
import io.github.simplycmd.terracraft.registry.ItemRegistry;

import java.util.HashMap;

public abstract class DoubleJumpAccessory implements Accessory {
    public abstract byte particleId();

    public int particleAmount() {
        return 6;
    }

    public double jumpPower() {
        return 0;
    }

    public abstract int doubleJumps();

    public static DoubleJumpAccessory create(byte particleId, int particleAmount, double jumpPower, int doubleJumps) {
        return new DoubleJumpAccessory() {
            @Override
            public byte particleId() {
                return particleId;
            }

            @Override
            public int doubleJumps() {
                return doubleJumps;
            }

            @Override
            public double jumpPower() {
                return jumpPower;
            }

            @Override
            public int particleAmount() {
                return particleAmount;
            }
        };
    }

    private static HashMap<DoubleJumpAccessory, Integer> power = null;
    //
    public static HashMap<DoubleJumpAccessory, Integer> power() {
        if (power == null) {
            power = new HashMap<>();
            power.put(as(ItemRegistry.cloud_in_a_bottle), 0);
            power.put(as(ItemRegistry.tsunami_in_a_bottle), 5);
            power.put(as(ItemRegistry.sandstorm_in_a_bottle), 10);
        }
        return power;
    }
    public static DoubleJumpAccessory getFromPower(int i) {
        final DoubleJumpAccessory[] d = {null};
        power.forEach(((doubleJumpAccessory, integer) -> {
            if (i == integer) d[0] = doubleJumpAccessory;
        }));
        return d[0];
    }

    private static DoubleJumpAccessory as(SimpleItem item) {
        for (Accessory accessory : ((AccessoryItem) item.getItem()).getAccessories()) {
            if (accessory instanceof DoubleJumpAccessory dj) return dj;
        }
        throw new RuntimeException();
    }

//    private static DoubleJumpAccessory as(SimpleItem item) {
//        return null;
//    }

    public int getPower() {
        return power().get(this);
    }
}
