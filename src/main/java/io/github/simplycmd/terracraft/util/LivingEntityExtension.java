package io.github.simplycmd.terracraft.util;

import io.github.simplycmd.terracraft.items.accessories.DoubleJumpAccessoryItem;

import java.util.HashMap;

public interface LivingEntityExtension {
    HashMap<DoubleJumpAccessoryItem, Integer> terracraft$getJumpCounter();
    void terracraft$resetJumpCounter();
}
