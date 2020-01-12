package com.github.simplycmd.terracraft;

import net.minecraft.util.Direction;
import net.minecraftforge.energy.EnergyStorage;

import javax.annotation.Nonnull;

public final class ModUtil {

    public static final Direction[] DIRECTIONS = Direction.values();
    @Nonnull
    @SuppressWarnings("ConstantConditions")
    public static <T> T _null() {
        return null;
    }
}