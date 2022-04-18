package io.github.simplycmd.terracraft.items.util;

import io.github.simplycmd.terracraft.registry.ItemRegistry;
import io.github.simplycmd.terracraft.util.PlayerEntityExtension;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Value {
    private final long value;
    public static final long COPPER_VALUE = 1;
    public static final long SILVER_VALUE = 64;
    public static final long GOLD_VALUE = 4096;
    public static final long PLATINUM_VALUE = 262144;

    public Value(long platinum, long gold, long silver, long copper) {
        this.value = convert(platinum, gold, silver, copper);
    }

    public Value(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    public Money getMoney() {
        return new Money(value);
    }

    private static long convert(long platinum, long gold, long silver, long copper) {
        long value = 0;
        value += copper*COPPER_VALUE;
        value += silver*SILVER_VALUE;
        value += gold*GOLD_VALUE;
        value += platinum*PLATINUM_VALUE;
        return value;
    }

    public static boolean trySpend2(PlayerInventory playerInventory, long value) {
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
        //long original[] = new long[]{platinum, gold, silver, copper};
        var d = trySpend(platinum, gold, silver, copper, ((PlayerEntityExtension)playerInventory.player).getTemporaryMoney(), value);
        if (!d.successful) return false;
        //removeItemFromInventory(playerInventory, ItemRegistry.platinum_coin.getItem(), platinum-d.platinum);
        //removeItemFromInventory(playerInventory, ItemRegistry.gold_coin.getItem(), gold-d.gold);
        //removeItemFromInventory(playerInventory, ItemRegistry.silver_coin.getItem(), silver-d.silver);
        //removeItemFromInventory(playerInventory, ItemRegistry.copper_coin.getItem(), copper-d.copper);
        return true;
    }

    public static boolean trySpend(PlayerInventory playerInventory, long value) {
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
        var d = trySpend(platinum, gold, silver, copper, ((PlayerEntityExtension)playerInventory.player).getTemporaryMoney(), value);
        if (!d.successful) return false;
        removeItemFromInventory(playerInventory, ItemRegistry.platinum_coin.getItem(), platinum-d.platinum);
        removeItemFromInventory(playerInventory, ItemRegistry.gold_coin.getItem(), gold-d.gold);
        removeItemFromInventory(playerInventory, ItemRegistry.silver_coin.getItem(), silver-d.silver);
        removeItemFromInventory(playerInventory, ItemRegistry.copper_coin.getItem(), copper-d.copper);
        ((PlayerEntityExtension)playerInventory.player).setTemporaryMoney(d.temporaryMoney);
        return true;
    }

    private static void removeItemFromInventory(PlayerInventory inventory, Item item, long amount) {
        for (ItemStack itemStack : inventory.main) {
            if (amount <= 0) return;
            if (itemStack.isOf(item)) {
                var d = Math.min(itemStack.getCount(), amount);
                itemStack.decrement((int) d);
                amount -= d;
            }
        }
    }

    public static SpentMoney trySpend(int platinum, int gold, int silver, int copper, long temporary_money, long value) {
        long[] originals = new long[]{platinum, gold, silver, copper, temporary_money, value};
        var l = Math.min(value/PLATINUM_VALUE, platinum);
        for (long i = 0; i < l; i++) {
            value -= PLATINUM_VALUE;
            platinum--;
        }

        var d = Math.min(value/GOLD_VALUE, gold);
        for (long i = 0; i < d; i++) {
            value -= GOLD_VALUE;
            gold--;
        }

        var q = Math.min(value/SILVER_VALUE, silver);
        for (long i = 0; i < q; i++) {
            value -= SILVER_VALUE;
            silver--;
        }

        var p = Math.min(value/COPPER_VALUE, copper);
        for (long i = 0; i < p; i++) {
            value -= COPPER_VALUE;
            copper--;
        }
        var o = Math.min(value, temporary_money);
        value -= o;
        temporary_money -= o;
        if (!(temporary_money >= 0)) {
            value += o;
            temporary_money += o;
        }
        if (value > 0) {
            SpentMoney money = new SpentMoney();
            money.platinum = originals[0];
            money.gold = originals[1];
            money.silver = originals[2];
            money.copper = originals[3];
            money.temporaryMoney = originals[4];
            money.successful = false;
            return money;
        }
        SpentMoney money = new SpentMoney();
        money.successful = true;
        money.copper = copper;
        money.silver = silver;
        money.gold = gold;
        money.platinum = platinum;
        money.temporaryMoney = temporary_money;
        return money;
    }

    public static final class SpentMoney extends Money { private long temporaryMoney; private boolean successful;public boolean isSuccessful() { return successful; } public long getTemporaryMoney(){return temporaryMoney;}}
    public sealed static class Money permits SpentMoney {
        protected long copper;
        protected long silver;
        protected long gold;
        protected long platinum;
        protected Money(long value){
            this.platinum = value/PLATINUM_VALUE;
            long a = value%PLATINUM_VALUE;
            this.gold = a/GOLD_VALUE;
            long b = a%GOLD_VALUE;
            this.silver = b/SILVER_VALUE;
            long c = b%SILVER_VALUE;
            this.copper = c/COPPER_VALUE;
        }
        private Money(){}
        public long getPlatinum() { return platinum; }
        public long getGold() { return gold; }
        public long getCopper() { return copper; }
        public long getSilver() { return silver; }
    }
}
