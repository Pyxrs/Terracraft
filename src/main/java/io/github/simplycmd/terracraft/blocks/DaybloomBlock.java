package io.github.simplycmd.terracraft.blocks;

import io.github.simplycmd.terracraft.registry.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

import static io.github.simplycmd.terracraft.registry.BlockRegistry.ID;

public class DaybloomBlock extends PlantBlock implements Fertilizable {
    public static final int MAX_AGE = 2;
    public static final IntProperty AGE = Properties.AGE_2;

    public DaybloomBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.get(AGE);
        if (i < MAX_AGE && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9 && world.isDay()) {
            world.setBlockState(pos, state.with(AGE, i + 1), 2);
        }
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(BlockRegistry.getBlockItem(ID("daybloom_seeds")));
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = Math.min(MAX_AGE, state.get(AGE) + 1);
        world.setBlockState(pos, state.with(AGE, i), 2);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        return Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D).offset(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
