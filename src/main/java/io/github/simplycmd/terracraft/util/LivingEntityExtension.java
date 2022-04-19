package io.github.simplycmd.terracraft.util;


import io.github.simplycmd.terracraft.items.accessories.v2.DoubleJumpAccessory;

import java.util.HashMap;

public interface LivingEntityExtension {
    HashMap<DoubleJumpAccessory, Integer> terracraft$getJumpCounter();
    void terracraft$resetJumpCounter();
}
