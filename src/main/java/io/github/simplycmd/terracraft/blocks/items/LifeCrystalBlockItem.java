package io.github.simplycmd.terracraft.blocks.items;

import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.UUID;

public class LifeCrystalBlockItem extends BlockItem implements IItem {
    private static final UUID uuid = UUID.fromString("1af5ca30-fc27-48e2-88bb-f930a22f0ffe");

    public LifeCrystalBlockItem(Block block, FabricItemSettings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) < 40) {
            final EntityAttributeInstance health = Objects.requireNonNull(user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH));
            final double value = user.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) - 10;
            health.removeModifier(uuid);
            health.addPersistentModifier(new EntityAttributeModifier(uuid, "life", value + 2, EntityAttributeModifier.Operation.ADDITION));
            user.heal(2);
            user.getStackInHand(hand).decrement(1);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 1, 50, 0);
    }
}
