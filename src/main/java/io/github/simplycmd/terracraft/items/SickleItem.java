package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.registry.BlockRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.FernBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SickleItem extends SwordItem implements IItem {
    public SickleItem() {
        super(ToolMaterials.IRON, 1, -2.2F, new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (miner instanceof ServerPlayerEntity && !((ServerPlayerEntity) miner).isCreative() && state.getBlock() instanceof FernBlock) {
            ItemEntity entity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), BlockRegistry.grass_bale.getItem().get().getDefaultStack());
            entity.updatePosition(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(entity);
        }
        stack.damage(1, miner, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public Value getBuyValue() {
        return new Value(0, 0, 60, 0);
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 0, 12, 0);
    }
}
