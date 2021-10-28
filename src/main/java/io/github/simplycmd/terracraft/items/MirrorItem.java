package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.registry.SoundReg;
import io.github.simplycmd.terracraft.items.util.IItem;
import io.github.simplycmd.terracraft.items.util.Value;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MirrorItem extends Item implements IItem {
    // TODO: Store tick in nbt so this works on multiplayer

    public MirrorItem(Settings settings) {
        super(settings);
    }

    private boolean tick = false;
    private int tickCounter = 0;

    private ServerPlayerEntity player;
    private ClientPlayerEntity clientPlayer;
    private ServerWorld serverWorld;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (playerEntity instanceof ServerPlayerEntity) player = (ServerPlayerEntity) playerEntity; // Get server player
        else if (playerEntity instanceof ClientPlayerEntity) clientPlayer = (ClientPlayerEntity) playerEntity; // Get client player
        if (world instanceof ServerWorld) serverWorld = (ServerWorld) world; // Get server world

        if (!tick) {
            MinecraftClient.getInstance().particleManager.addEmitter(clientPlayer, ParticleTypes.END_ROD, 30);
            MinecraftClient.getInstance().gameRenderer.showFloatingItem(this.getDefaultStack());
            serverWorld.playSound(playerEntity, playerEntity.getBlockPos(), SoundReg.ITEM_MAGIC_MIRROR_USE_EVENT, SoundCategory.PLAYERS, 1f, 1f);
            tick = true;
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        } else {
            return TypedActionResult.fail(playerEntity.getStackInHand(hand));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (player != null && tick) {
            if (tickCounter >= 30) {
                tick = false;
                tickCounter = 0;
                BlockPos position;

                if (player.getSpawnPointPosition() == null) { // Determine if set position to bed or world spawn
                    position = serverWorld.getSpawnPos();
                } else {
                    position = player.getSpawnPointPosition();
                }

                System.out.println();

                player.teleport(serverWorld, position.getX(), position.getY(), position.getZ(), 0, 0); // Teleport to correct position
            } else {
                tickCounter++;
            }
        }
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 1, 0, 0);
    }
}