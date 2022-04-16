package io.github.simplycmd.terracraft.items.util;

import com.google.common.annotations.Beta;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import io.netty.util.internal.UnstableApi;
import lombok.Getter;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.data.DataProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

public class Value {
    private final int value;
    public static final int COPPER_VALUE = 1;
    public static final int SILVER_VALUE = 64;
    public static final int GOLD_VALUE = 4096;
    public static final int PLATINUM_VALUE = 262144;

    public Value(int platinum, int gold, int silver, int copper) {
        this.value = convert(platinum, gold, silver, copper);
    }

    public Value(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public Money getMoney() {
        return new Money(value);
    }

    private static int convert(int platinum, int gold, int silver, int copper) {
        int value = 0;
        value += copper*COPPER_VALUE;
        value += silver*SILVER_VALUE;
        value += gold*GOLD_VALUE;
        value += platinum*PLATINUM_VALUE;
        return value;
    }

    public static boolean trySpend2(PlayerInventory playerInventory, int value) {
        int platinum = 0;
        int gold = 0;
        int silver = 0;
        int copper = 0;
        for (int i = 0; i < playerInventory.main.size(); i++) {
            var stack = playerInventory.main.get(i);
            if (stack.isOf(ItemRegistry.platinum_coin.getItem())) {
                platinum += stack.getCount();
            }
            if (stack.isOf(ItemRegistry.gold_coin.getItem())) {
                gold += stack.getCount();
            }
            if (stack.isOf(ItemRegistry.silver_coin.getItem())) {
                silver += stack.getCount();
            }
            if (stack.isOf(ItemRegistry.copper_coin.getItem())) {
                copper += stack.getCount();
            }
        }
        int original[] = new int[]{platinum, gold, silver, copper};
        var d = trySpend(platinum, gold, silver, copper, value);
        if (!d.successful) return false;
        //removeItemFromInventory(playerInventory, ItemRegistry.platinum_coin.getItem(), platinum-d.platinum);
        //removeItemFromInventory(playerInventory, ItemRegistry.gold_coin.getItem(), gold-d.gold);
        //removeItemFromInventory(playerInventory, ItemRegistry.silver_coin.getItem(), silver-d.silver);
        //removeItemFromInventory(playerInventory, ItemRegistry.copper_coin.getItem(), copper-d.copper);
        return true;
    }

    public static boolean trySpend(PlayerInventory playerInventory, int value) {
        int platinum = 0;
        int gold = 0;
        int silver = 0;
        int copper = 0;
        for (int i = 0; i < playerInventory.main.size(); i++) {
            var stack = playerInventory.main.get(i);
            if (stack.isOf(ItemRegistry.platinum_coin.getItem())) {
                platinum += stack.getCount();
            }
            if (stack.isOf(ItemRegistry.gold_coin.getItem())) {
                gold += stack.getCount();
            }
            if (stack.isOf(ItemRegistry.silver_coin.getItem())) {
                silver += stack.getCount();
            }
            if (stack.isOf(ItemRegistry.copper_coin.getItem())) {
                copper += stack.getCount();
            }
        }
        int original[] = new int[]{platinum, gold, silver, copper};
        var d = trySpend(platinum, gold, silver, copper, value);
        if (!d.successful) return false;
        removeItemFromInventory(playerInventory, ItemRegistry.platinum_coin.getItem(), platinum-d.platinum);
        removeItemFromInventory(playerInventory, ItemRegistry.gold_coin.getItem(), gold-d.gold);
        removeItemFromInventory(playerInventory, ItemRegistry.silver_coin.getItem(), silver-d.silver);
        removeItemFromInventory(playerInventory, ItemRegistry.copper_coin.getItem(), copper-d.copper);
        return true;
    }

    private static void removeItemFromInventory(PlayerInventory inventory,  Item item, int amount) {
        for (ItemStack itemStack : inventory.main) {
            if (amount <= 0) return;
            if (itemStack.isOf(item)) {
                var d = Math.min(itemStack.getCount(), amount);
                itemStack.decrement(d);
                amount -= d;
            }
        }
    }

    public static SpentMoney trySpend(int platinum, int gold, int silver, int copper, int value) {
        int[] originals = new int[]{platinum, gold, silver, copper, value};
        var l = Math.min(value/PLATINUM_VALUE, platinum);
        for (int i = 0; i < l; i++) {
            value -= PLATINUM_VALUE;
            platinum--;
        }

        var d = Math.min(value/GOLD_VALUE, gold);
        for (int i = 0; i < d; i++) {
            value -= GOLD_VALUE;
            gold--;
        }

        var q = Math.min(value/SILVER_VALUE, silver);
        for (int i = 0; i < q; i++) {
            value -= SILVER_VALUE;
            silver--;
        }

        var p = Math.min(value/COPPER_VALUE, copper);
        for (int i = 0; i < p; i++) {
            value -= COPPER_VALUE;
            copper--;
        }
        if (value > 0) {
            SpentMoney money = new SpentMoney();
            money.platinum = originals[0];
            money.gold = originals[1];
            money.silver = originals[2];
            money.copper = originals[3];
            money.successful = false;
            return money;
        }
        SpentMoney money = new SpentMoney();
        money.successful = true;
        money.copper = copper;
        money.silver = silver;
        money.gold = gold;
        money.platinum = platinum;
        return money;
    }

    @Deprecated(forRemoval = true)
    @ApiStatus.ScheduledForRemoval
    @ApiStatus.Internal
    @ApiStatus.Experimental
    @UnstableApi
    @Beta
    private void testFeature() {
        SpentMoney d;
        if (true) throw new UnsupportedOperationException();
        System.out.println("Copper: " + d.copper + ", Silver: " + d.silver + ", Gold: " + d.gold + ", Platinum: " + d.platinum + ", Successful: " + d.successful);
    }

    public static final class SpentMoney extends Money { private boolean successful;public boolean isSuccessful() { return successful; }}
    public sealed static class Money permits SpentMoney {
        protected int copper;
        protected int silver;
        protected int gold;
        protected int platinum;
        protected Money(int value){
            this.platinum = value/PLATINUM_VALUE;
            int a = value%PLATINUM_VALUE;
            this.gold = a/GOLD_VALUE;
            int b = a%GOLD_VALUE;
            this.silver = b/SILVER_VALUE;
            int c = b%SILVER_VALUE;
            this.copper = c/COPPER_VALUE;
        }
        private Money(){}
        public int getPlatinum() { return platinum; }
        public int getGold() { return gold; }
        public int getCopper() { return copper; }
        public int getSilver() { return silver; }
    }
}
