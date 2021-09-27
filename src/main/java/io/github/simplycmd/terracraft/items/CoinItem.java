package io.github.simplycmd.terracraft.items;


import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class CoinItem extends Item implements IItem {
    public enum Coin {
        COPPER(new Value(0, 0, 0, 1), ItemRegistry.get("silver_coin")),
        SILVER(new Value(0, 0, 1, 0), ItemRegistry.get("gold_coin")),
        GOLD(new Value(0, 1, 0, 0), ItemRegistry.get("platinum_coin")),
        PLATINUM(new Value(1, 0, 0, 0), null);

        Value value;
        Item next;

        Coin(Value value, Item next) {
            this.value = value;
            this.next = next;
        }
    }
    Coin coin;

    public CoinItem(Coin coin) {
        super(new FabricItemSettings().group(ItemGroup.MISC));
        this.coin = coin;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }

    @Override
    public Value getSellValue() {
        return coin.value;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.getCount() == getMaxCount() && coin.next != null) {
            stack.decrement(getMaxCount());
            ((PlayerEntity) entity).getInventory().insertStack(coin.next.getDefaultStack());
        }
    }
}
