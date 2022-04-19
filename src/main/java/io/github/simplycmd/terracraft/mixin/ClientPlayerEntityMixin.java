package io.github.simplycmd.terracraft.mixin;

import io.github.simplycmd.terracraft.items.accessories.AutoswingAccessoryItem;
import io.github.simplycmd.terracraft.items.accessories.DoubleJumpAccessoryItem;
import io.github.simplycmd.terracraft.util.LivingEntityExtension;
import io.github.simplycmd.terracraft.util.ParticleUtil;
import io.github.simplycmd.terracraft.packets.PacketHandler;
import io.github.simplycmd.terracraft.util.TrinketsUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.UUID;

import static io.github.simplycmd.terracraft.util.TrinketsUtil.getDJList;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends LivingEntity implements LivingEntityExtension {
    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow public abstract void sendSystemMessage(Text message, UUID sender);

    //private int amountOfJumps = 0;
    private boolean jumpedRecently = false;

    protected ClientPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
        throw new UnsupportedOperationException();
    }

    @Unique
    protected double getSquaredMaxAttackDistance(LivingEntity entity) {
        return (double)(this.getWidth() * 2.0F * this.getWidth() * 2.0F + entity.getWidth());
    }
    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void tickMovement(CallbackInfo info) {
        var player = (ClientPlayerEntity)(Object)this;
        if (player.getAttackCooldownProgress(0.5F) >= 1.0F && TrinketsUtil.isEquipped(player, AutoswingAccessoryItem.class)) {
            ((MinecraftClientAccessor)MinecraftClient.getInstance()).callDoAttack();
        }
        //this.sendSystemMessage(new LiteralText("" + player.getAttackCooldownProgress(0.5F)), null);
//        var e = this.world.getOtherEntities(this, new Box(new Vec3d(7,7,7), new Vec3d(-7, -7, -7)), (livingEntity) -> {
//            if (livingEntity instanceof LivingEntity)
//            return this.getSquaredMaxAttackDistance((LivingEntity) livingEntity) >= this.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
//            return false;
//        });
//        player.tryAttack()
//        if (player.input.jumping) {
//            player.sendSystemMessage(new LiteralText(String.format("Amount of jumps: %s, Jumping(Input): %s, Jumped Recently: %s, Amount Of Jumps: %s, Cooldown: %s", amountOfJumps, player.input.jumping, jumpedRecently, amountOfJumps, player.getItemCooldownManager().getCooldownProgress(TrinketsUtil.getDJ(player),0))), null);
//        }
        //System.out.println("ITEM COOLING: " + player.getItemCooldownManager().isCoolingDown(TrinketsUtil.getDJ(player).getItem()));
        //System.out.println(player.getItemCooldownManager().getCooldownProgress(TrinketsUtil.getDJ(player).getItem(),0));
        if(player.isOnGround() || player.isClimbing()) {
            terracraft$resetJumpCounter();
            //amountOfJumps = TrinketsUtil.getDJ(player) == null ? 0 : TrinketsUtil.getDJ(player).doubleJumps();
        } else if (getBestActiveDJ() != null && !jumpedRecently &&
                terracraft$getJumpCounter().get(getBestActiveDJ()) > 0 &&
                !player.getItemCooldownManager().isCoolingDown(getBestActiveDJ())){
            if (player.input.jumping && !player.getAbilities().flying) {
                if (canJump(player)) {
                    //--amountOfJumps;
                    doJump();
                    //player.sendSystemMessage(new LiteralText("<Console> JUMPING"), null);
                    //System.out.println("JUMP!");
                    ParticleUtil.jumpEffect(player, getBestActiveDJ().particleId(), getBestActiveDJ().particleAmount());
                    PacketHandler.sendToServer(getBestActiveDJ().getPower());
                    terracraft$getJumpCounter().put(getBestActiveDJ(), terracraft$getJumpCounter().get(getBestActiveDJ())-1);
                }
            }
        }
        jumpedRecently = player.input.jumping;
        if(player.isOnGround() || player.isClimbing()) {
            jumpedRecently = true;
        }
    }

    private boolean canJump(ClientPlayerEntity player) {
        return !(player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA && ElytraItem.isUsable(player.getEquippedStack(EquipmentSlot.CHEST))) && !player.isFallFlying() && !player.hasVehicle()
                && !player.isTouchingWater() && !player.hasStatusEffect(StatusEffects.LEVITATION) && getBestActiveDJ() != null;
    }

    @Unique
    public double doGetJumpBoostVelocityModifier() {
        return this.hasStatusEffect(StatusEffects.JUMP_BOOST) ? (double)(0.1F * (float)(this.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1)) : 0.0D + (getBestActiveDJ() == null ? 0.0D : getBestActiveDJ().jumpPower());
    }

    @Unique
    private void doJump2() {
        ClientPlayerEntity player = (ClientPlayerEntity)(Object)this;
        double d = (double)this.getJumpVelocity() + doGetJumpBoostVelocityModifier();
        Vec3d vec3d = player.getVelocity();
        player.setVelocity(vec3d.x, d, vec3d.z);
        if (player.isSprinting()) {
            float f = this.getYaw() * 0.017453292F;
            this.setVelocity(this.getVelocity().add((double)(-MathHelper.sin(f) * 0.2F), 0.0D, (double)(MathHelper.cos(f) * 0.2F)));
        }

        this.velocityDirty = true;
    }

    @Unique
    private void doJump() {
        ClientPlayerEntity player = (ClientPlayerEntity)(Object)this;
        doJump2();
        player.incrementStat(Stats.JUMP);
        if (player.isSprinting()) {
            player.addExhaustion(0.2F);
        } else {
            player.addExhaustion(0.05F);
        }

    }

    private DoubleJumpAccessoryItem  getBestActiveDJ() {
        @Nonnull
        var a = getDJList(this);
        var d = new ArrayList<DoubleJumpAccessoryItem>();
        for (DoubleJumpAccessoryItem doubleJumpAccessoryItem : a) {
            if (this.terracraft$getJumpCounter().get(doubleJumpAccessoryItem) > 0) {
                d.add(doubleJumpAccessoryItem);
            }
        }

        DoubleJumpAccessoryItem l = null;
        for (DoubleJumpAccessoryItem doubleJumpAccessoryItem : d) {
            if (l == null || doubleJumpAccessoryItem.getPower() > l.getPower()) {
                l = doubleJumpAccessoryItem;
            }
        }
        return l;
    }
}
