package com.github.SimplyCmd.terracraft.config;

import net.minecraft.item.DyeColor;

import java.util.List;

/**
 * This holds the baked (runtime) values for our config.
 * These values should never be from changed outside this package.
 * This can be split into
 *
 * @author N_slash_A
 */
public final class TerracraftiaConfig {

    // Client
    public static boolean clientBoolean;
    public static List<String> clientStringList;
    public static DyeColor clientEnumDyeColor;

    public static boolean modelTranslucency;
    public static double modelScale;

    // Server
    public static boolean serverBoolean;
    public static List<String> serverStringList;
    public static DyeColor serverEnumDyeColor;

}