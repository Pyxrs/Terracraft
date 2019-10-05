package com.github.n_slash_a.terracraftia;

import com.github.n_slash_a.terracraftia.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LeavesDropHandler {
    @SubscribeEvent
    public void addDropForType(HarvestDropsEvent event) {
        Block block = event.getState().getBlock();
        Item item = event.getHarvester().getHeldItemMainhand().getItem();
        System.out.println(item);
        if (block == Blocks.OAK_LEAVES && item == Items.ARROW) {
            event.getDrops().add(new ItemStack(ModItems.ACORN));
        }
    }
}