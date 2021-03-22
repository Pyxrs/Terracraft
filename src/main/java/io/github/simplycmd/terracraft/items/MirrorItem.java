package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.Main;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
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
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

public class MirrorItem extends Item {
    public MirrorItem(Settings settings) {
        super(settings);
    }

    private boolean tick = false;
    private int tickCounter = 0;

    private ServerPlayerEntity player;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (playerEntity instanceof ServerPlayerEntity) player = (ServerPlayerEntity) playerEntity;
        doFancyStuff(this.getDefaultStack(), playerEntity, world);
        tick = true;
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Environment(EnvType.CLIENT)
    public static void doFancyStuff(ItemStack stack, Entity entity, World world) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.particleManager.addEmitter(entity, ParticleTypes.END_ROD, 30);
        if (!world.isClient) {
            world.playSound(null, entity.getBlockPos(), Main.ITEM_MAGIC_MIRROR_USE, SoundCategory.PLAYERS, 1f, 1f);
        }
        if (entity == client.player) {
            client.gameRenderer.showFloatingItem(stack);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (tick == true) {
            if (tickCounter >= 30) {
                tick = false;
                tickCounter = 0;
                BlockPos position;
                RegistryKey<World> dimension;

                if (player.getSpawnPointPosition() == null) { // Determine if set position to bed or world spawn
                    position = ((ServerWorld) world).getSpawnPos();
                } else {
                    position = player.getSpawnPointPosition();
                }
                if (player.getSpawnPointDimension() == null) { // Determine if set dimension to overworld or other dimension
                    dimension = World.OVERWORLD;
                } else {
                    dimension = player.getSpawnPointDimension();
                }

                player.setVelocity(0, 0, 0);
                //player.moveToWorld(world.getServer().getWorld(dimension)); // Teleport to correct dimension
                player.teleport((ServerWorld) world, position.getX(), position.getY(), position.getZ(), 0, 0); // Teleport to correct position
            } else {
                tickCounter++;
            }
        }
    }
}