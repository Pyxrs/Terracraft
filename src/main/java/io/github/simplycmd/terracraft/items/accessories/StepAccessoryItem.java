package io.github.simplycmd.terracraft.items.accessories;

import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import io.github.simplycmd.terracraft.registry.BlockRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class StepAccessoryItem extends AccessoryItem {
    private final Block stepBlock;

    public StepAccessoryItem(Block stepBlock, FabricItemSettings settings) {
        super(settings);
        this.stepBlock = stepBlock;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity.isInSneakingPose() && !entity.isOnGround()) {
            if (world.getBlockState(entity.getBlockPos().down(1)).getBlock() != Blocks.AIR) {
                world.setBlockState(entity.getBlockPos(), stepBlock.getDefaultState());
                entity.teleport(entity.getX(), entity.getY() + 1, entity.getZ());
            }
            else if (world.getBlockState(entity.getBlockPos().down(2)).getBlock() != Blocks.AIR) {
                world.setBlockState(entity.getBlockPos().down(1), stepBlock.getDefaultState());
            }
        }
    }
}
