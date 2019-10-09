package com.github.n_slash_a.terracraft;

import javax.annotation.Nonnull;

/**
 * Assorted common utility code
 *
 * @author Cadiboo
 */
public final class ModUtil {

    /**
     * Returns null, while claiming to never return null.
     * Useful for constants with @ObjectHolder who's values are null at compile time, but not at runtime
     *
     * @return null
     */
    @Nonnull
    // Get rid of "Returning null from Nonnull method" warnings
    @SuppressWarnings("ConstantConditions")
    public static <T> T _null() {
        return null;
    }

}