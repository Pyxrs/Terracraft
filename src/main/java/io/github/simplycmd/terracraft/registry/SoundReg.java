package io.github.simplycmd.terracraft.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundReg {
    public static final Identifier ITEM_MAGIC_MIRROR_USE_ID = new Identifier("terracraft:item_magic_mirror_use");
    public static SoundEvent ITEM_MAGIC_MIRROR_USE_EVENT = new SoundEvent(ITEM_MAGIC_MIRROR_USE_ID);

    public static final Identifier BLOCK_POT_SMASH_ID = new Identifier("terracraft:block_pot_smash");
    public static SoundEvent BLOCK_POT_SMASH_EVENT = new SoundEvent(BLOCK_POT_SMASH_ID);

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, ITEM_MAGIC_MIRROR_USE_ID, ITEM_MAGIC_MIRROR_USE_EVENT);
        Registry.register(Registry.SOUND_EVENT, BLOCK_POT_SMASH_ID, BLOCK_POT_SMASH_EVENT);
    }
}
