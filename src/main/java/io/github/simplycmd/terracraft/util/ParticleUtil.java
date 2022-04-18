package io.github.simplycmd.terracraft.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ParticleUtil {
    private static ArrayList<ParticleEffect> particleEffects = null;

    public static ParticleEffect[] particleTypes() {
        if (particleEffects == null) {
            particleEffects = new ArrayList<>();
            var d = 0;
            for (int i = 0; i < ParticleTypes.class.getFields().length; i++) {
                Field a = ParticleTypes.class.getFields()[i];
                if (Modifier.isStatic(a.getModifiers())) {
                    try {
                        if (a.get(null) instanceof ParticleEffect particleEffect) {
                            particleEffects.add(particleEffect);
                            d++;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return particleEffects.toArray(new ParticleEffect[0]);
    }

    public static byte getParticleId(ParticleEffect effect) {
        return (byte) particleEffects.indexOf(effect);
    }

    public static void jumpEffect(PlayerEntity player, byte effect, int particles) {
        jumpEffect(player, player, effect, particles);
    }

    public static void jumpEffect(PlayerEntity localPlayer, PlayerEntity effectPlayer, byte effect, int particles) {
        ParticleEffect particle = particleTypes()[effect];
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
