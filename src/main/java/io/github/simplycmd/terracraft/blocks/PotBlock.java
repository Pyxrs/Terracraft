package io.github.simplycmd.terracraft.blocks;

import io.github.simplycmd.terracraft.Sounds;
import io.github.simplycmd.terracraft.blocks.util.PotBlocks;
import io.github.simplycmd.terracraft.entities.coin_portal.CoinPortalEntity;
import io.github.simplycmd.terracraft.registry.EntityRegistry;
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

import java.util.Random;

public class PotBlock extends Block implements Waterloggable {
    private static final Random RANDOM = new Random();
    private final PotBlocks type;

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

        // Check if coin portal (ends process)
        if (RANDOM.nextFloat() < type.getCoin_portal_chance()) spawnCoinPortal(world, pos);
        else {
            // TODO: Give gold key next to dungeon
            // TODO: Drop a potion (ends process)
            // TODO: In multiplayer drop wormhole potion (ends process)

            // Random number from 1-8 (7 outcomes)
            switch(RANDOM.nextInt(7) + 1) {
                // 1: Drop hearts
                case 1: dropHearts(world, pos);
                // 2: Drop torches
                case 2: dropTorches(world, pos);
                // 3: Drop ammo
                case 3: ;
                // 4: Drop healing potions
                case 4: ;
                // 5: Drop explosives
                case 5: ;
                // 6: Drop rope
                case 6: ;
                // 7/8: Drop money
                case 7: ;
                case 8: ;
            }
        }
    }

    private void spawnCoinPortal(World world, BlockPos potPos) {
        CoinPortalEntity portal = new CoinPortalEntity(EntityRegistry.COIN_PORTAL, world);
        portal.updatePosition(potPos.getX(), potPos.getY() + 2, potPos.getZ());
        world.spawnEntity(portal);
    }

    private void dropHearts(World world, BlockPos pos) {
        if (world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10000, true).getHealth() < 20) {
            // Spawn first heart
            ItemEntity heart = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.get("heart").getDefaultStack());
            world.spawnEntity(heart);

            // Spawn second heart
            if (RANDOM.nextFloat() < 0.5) {
                ItemEntity heart2 = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.get("heart").getDefaultStack());
                world.spawnEntity(heart2);
            }
        }
    }

    private void dropTorches(World world, BlockPos pos) {
        if (world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10000, true).getHealth() < 20) {
            // Determines an amount from 4-12
            int amount = RANDOM.nextInt(8) + 4;

            // Tundra drops half the amount of torches
            if (type.equals(PotBlocks.TUNDRA)) {
                for (int i = 0; i < (amount / 2); i++) {
                    ItemEntity torch = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), type.getTorch_type().getDefaultStack());
                    world.spawnEntity(torch);
                }
            } else {
                for (int i = 0; i < amount; i++) {
                    ItemEntity torch = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), type.getTorch_type().getDefaultStack());
                    world.spawnEntity(torch);
                }
            }
        }
    }
}
