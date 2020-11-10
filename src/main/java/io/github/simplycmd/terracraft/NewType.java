package io.github.simplycmd.terracraft;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class NewType {
    private Item item;
    private BlockItem blockitem;
    private Block block;

    public void setItem(Item item1) {
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
