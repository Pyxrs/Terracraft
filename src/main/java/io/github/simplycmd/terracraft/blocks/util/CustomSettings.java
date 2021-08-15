package io.github.simplycmd.terracraft.blocks.util;

import lombok.Getter;

public class CustomSettings {
    public enum BlockstateType {
        NORMAL,
        RANDOM_X,
        RANDOM_Y,
        RANDOM,
    }
    public enum ItemModelType {
        NORMAL,
        TEXTURE
    }
    public enum LootType {
        NORMAL,
        NONE
    }

    @Getter
    String id;

    @Getter
    BlockstateType blockstateType;

    @Getter
    ItemModelType itemModelType;

    @Getter
    LootType lootType;

    public CustomSettings(String id, BlockstateType blockstateType, ItemModelType itemModelType, LootType lootType) {
        this.id = id;
        this.blockstateType = blockstateType;
        this.itemModelType = itemModelType;
        this.lootType = lootType;
    }

    public CustomSettings(String id, BlockstateType blockstateType, ItemModelType itemModelType) {
        this.id = id;
        this.blockstateType = blockstateType;
        this.itemModelType = itemModelType;
        this.lootType = LootType.NORMAL;
    }

    public CustomSettings(String id, BlockstateType blockstateType) {
        this.id = id;
        this.blockstateType = blockstateType;
        this.itemModelType = ItemModelType.NORMAL;
        this.lootType = LootType.NORMAL;
    }

    public CustomSettings(String id, LootType lootType) {
        this.id = id;
        this.blockstateType = BlockstateType.NORMAL;
        this.itemModelType = ItemModelType.NORMAL;
        this.lootType = lootType;
    }

    public CustomSettings(String id) {
        this.id = id;
        this.blockstateType = BlockstateType.NORMAL;
        this.itemModelType = ItemModelType.NORMAL;
        this.lootType = LootType.NORMAL;
    }
}
