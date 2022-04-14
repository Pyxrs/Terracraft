package io.github.simplycmd.terracraft.util;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ParticleUtils {
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
                            //System.out.println(a.getName()+" -> "+d);
                            particleEffects.add(particleEffect);
                            d++;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //System.exit(0);
        return particleEffects.toArray(new ParticleEffect[0]);
    }

    public static byte getParticleId(ParticleEffect effect) {
        return (byte) particleEffects.indexOf(effect);
    }
}
