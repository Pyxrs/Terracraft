package io.github.simplycmd.terracraft.blocks;

import io.github.simplycmd.terracraft.Sounds;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PotBlock extends Block implements Waterloggable {
    private final PotBlocks type;

    /*
    Pot procedure:
        - Check if coin portal (ends process)
        - Give gold key next to dungeon
        - Drop a potion (ends process)
        - In multiplayer drop wormhole potion (ends process)
        - Random number from 1-8 (7 outcomes)
            1: Drop hearts
            2: Drop torches
            3: Drop ammo
            4: Drop healing potions
            5: Drop explosives
            6: Drop rope
            7/8: Drop money
     */

    public static final IntProperty VARIANT = IntProperty.of("variant", 0, 3);

    public PotBlock(PotBlocks type) {
        super(FabricBlockSettings.of((new Material.Builder(MapColor.TERRACOTTA_BROWN)).build()).breakInstantly().noCollision().sounds(new BlockSoundGroup(1.0F, 1.0F, Sounds.BLOCK_POT_SMASH_EVENT, SoundEvents.BLOCK_STONE_STEP, SoundEvents.BLOCK_STONE_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL)));
        setDefaultState(getStateManager().getDefaultState().with(VARIANT, 0));
        this.type = type;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(VARIANT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(VARIANT, (int) Math.round((Math.random() * (type.getVariants() - 1))));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.625, 0.75);
    }

    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.get("heart").getDefaultStack());
        item.setVelocity((Math.random() * 2) - 1, Math.random(), (Math.random() * 2) - 1);
        world.spawnEntity(item);
    }
}
