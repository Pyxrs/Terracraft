package io.github.simplycmd.terracraft.blocks;

import io.github.simplycmd.terracraft.registry.BlockReg;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlueBerryBushBlock extends SweetBerryBushBlock {
    public BlueBerryBushBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(BlockReg.get("blue_berry_bush").asItem());
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.BEE) {
            entity.slowMovement(state, new Vec3d(0.800000011920929D, 0.75D, 0.800000011920929D));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i = state.get(AGE);
        boolean bl = i == 3;
        if (!bl && player.getStackInHand(hand).isOf(Items.BONE_MEAL)) {
            return ActionResult.PASS;
        } else if (i > 1) {
            int j = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(BlockReg.get("blue_berry_bush").asItem(), j + (bl ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlockState(pos, state.with(AGE, 1), 2);
            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }
}
