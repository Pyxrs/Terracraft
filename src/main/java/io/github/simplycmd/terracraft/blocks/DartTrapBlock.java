package io.github.simplycmd.terracraft.blocks;

import io.github.simplycmd.simplylib.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class DartTrapBlock extends HorizontalFacingBlock {

    public DartTrapBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient && world.isReceivingRedstonePower(pos))
            world.spawnEntity(Util.initArrow(Items.TIPPED_ARROW, Potions.POISON, arrowPosition(pos, state), 1, Vec3d.of(state.get(Properties.HORIZONTAL_FACING).getVector()), PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY, world));
    }

    private Vec3d arrowPosition(BlockPos pos, BlockState state) {
        Vec3i vec = state.get(Properties.HORIZONTAL_FACING).getVector();
        return new Vec3d(pos.getX() + vec.getX() + 0.5, pos.getY() + vec.getY() + 0.5, pos.getZ() + vec.getZ() + 0.5);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
}
