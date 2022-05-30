package io.github.simplycmd.terracraft.items.util;

import lombok.Getter;

public class Value {
    @Getter
    int platinum;

    @Getter
    int gold;

    @Getter
    int silver;

    @Getter
    int copper;

    public Value(int platinum, int gold, int silver, int copper) {
        this.platinum = platinum;
        this.gold = gold;
        this.silver = silver;
        this.copper = copper;
    }
}
