package io.github.simplycmd.terracraft.items;

import io.github.simplycmd.terracraft.items.util.BaseItem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.registry.SoundRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MirrorItem extends Item implements BaseItem {
    // TODO: Store tick in nbt so this works on multiplayer

    public MirrorItem(Settings settings) {
        super(settings);
    }

    private boolean tick = false;
    private int tickCounter = 0;

    private ServerPlayerEntity player;
    private ClientPlayerEntity clientPlayer;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (playerEntity instanceof ServerPlayerEntity) player = (ServerPlayerEntity) playerEntity; // Get server player
        else if (playerEntity instanceof ClientPlayerEntity) clientPlayer = (ClientPlayerEntity) playerEntity; // Get client player

        if (!tick) {
            final MinecraftClient client = MinecraftClient.getInstance();
            client.particleManager.addEmitter(clientPlayer, ParticleTypes.END_ROD, 30);
            client.gameRenderer.showFloatingItem(this.getDefaultStack());
            if (player != null)
            player.getWorld().playSound(playerEntity, playerEntity.getBlockPos(), SoundRegistry.ITEM_MAGIC_MIRROR_USE_EVENT, SoundCategory.PLAYERS, 1f, 1f);
            tick = true;
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        } else {
            return TypedActionResult.fail(playerEntity.getStackInHand(hand));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (player != null && tick) {
            tickCounter++;
            if (tickCounter >= 30) {
                magicMirror(player);
                tick = false;
                tickCounter = 0;
            }
        }
    }

    private static void magicMirror(ServerPlayerEntity player) {
        BlockPos position = player.getSpawnPointPosition();
        if (player.getSpawnPointPosition() == null) { // Determine if set position to bed or world spawn
            position = player.getWorld().getSpawnPos();
        }

        player.teleport(player.getWorld(), position.getX(), position.getY(), position.getZ(), 0, 0); // Teleport to correct position
    }

    @Deprecated
    public int getTick(@Nullable NbtCompound nbt) {
        if (nbt != null && nbt.contains("Tick", NbtElement.INT_TYPE)) {
            int n = nbt.getInt("Tick");
            return n;
        }

        return 0;
    }

    @Override
    public Value getSellValue() {
        return new Value(0, 1, 0, 0);
    }
}