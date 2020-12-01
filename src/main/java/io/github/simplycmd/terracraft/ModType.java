package io.github.simplycmd.terracraft;

import io.github.simplycmd.terracraft.items.ModItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ModType {
    private ModItem item;
    private BlockItem blockitem;
    private Block block;

    public void setItem(ModItem item1) {
        item = item1;
    }

    public void setBlockItem(BlockItem blockitem1) {
        blockitem = blockitem1;
    }

    public void setBlock(Block block1) {
        block = block1;
    }

    public Item getItem() {
        return item;
    }

    public BlockItem getBlockItem() {
        return blockitem;
    }

    public Block getBlock() {
        return block;
    }
}
