package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.util.ParticleUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

import java.util.Random;

public class JumpingEffect {
    private final static Random random = new Random();

    public static void play(PlayerEntity player, byte effect, int particles) {
        play(player, player, effect, particles);
    }

    public static void play(PlayerEntity localPlayer, PlayerEntity effectPlayer, byte effect, int particles) {
        ParticleEffect particle = ParticleUtils.particleTypes()[effect];
        World world = localPlayer.getEntityWorld();
        world.playSound(localPlayer, effectPlayer.getBlockPos(), SoundEvents.ENTITY_TURTLE_SHAMBLE, SoundCategory.PLAYERS, 0.4f, 1);
        var d = particles/2;
        var l = particles-d;
        for(int i = 0; i < l; ++i) {
            var angle = Math.toRadians(((float)i)/l*360);
            double movementX = Math.sin(angle) / 3;
            double movementY = 0.5D;
            double movementZ = Math.cos(angle) / 3;
            world.addParticle(particle, effectPlayer.getParticleX(1.0D), effectPlayer.getY(), effectPlayer.getParticleZ(1.0D), movementX, movementY, movementZ);
        }

        for(int i = 0; i < d; ++i) {
            var angle = Math.toRadians(((float)i)/d*360);
            double movementX = Math.sin(angle) / 200;
            double movementY = -0.3D;
            double movementZ = Math.cos(angle) / 200;
            world.addParticle(particle, effectPlayer.getParticleX(1.0D), effectPlayer.getY(), effectPlayer.getParticleZ(1.0D), movementX, movementY, movementZ);
        }
    }
}