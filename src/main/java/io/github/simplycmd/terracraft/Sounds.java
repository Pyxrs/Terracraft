package io.github.simplycmd.terracraft;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sounds {
    public static SoundEvent ITEM_MAGIC_MIRROR_USE;

    public static void RegisterSounds() {
        ITEM_MAGIC_MIRROR_USE = RegisterSound("item_magic_mirror_use");
    }

    private static SoundEvent RegisterSound(String id) {
        Identifier SOUND_ID = new Identifier(Main.MOD_ID + ":" + id);
        SoundEvent SOUND = new SoundEvent(SOUND_ID);
        Registry.register(Registry.SOUND_EVENT, SOUND_ID, SOUND);
        return SOUND;
    }
}
