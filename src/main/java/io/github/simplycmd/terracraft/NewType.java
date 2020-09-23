package io.github.simplycmd.terracraft;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class NewType {
    private Item item;
    private BlockItem blockitem;
    private Block block;

    public void setItem(Item item) {
        item = this.item;
    }
    public void setBlockItem(BlockItem blockitem) {
        blockitem = this.blockitem;
    }
    public void setBlock(Block block) {
        block = this.block;
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
