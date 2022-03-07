package io.github.simplycmd.terracraft.blocks;

import java.util.ArrayList;

import com.simplycmd.featherlib.registry.SimpleBlock;

import io.github.simplycmd.terracraft.registry.BlockRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class BlockRenderer {
    public static void addBlocks() {
        final ArrayList<SimpleBlock> CUTOUT = new ArrayList<>() {{
            add(BlockRegistry.blue_berry_bush);
            add(BlockRegistry.daybloom);
    
            add(BlockRegistry.forest_pot);
    
            add(BlockRegistry.ice_torch.getBlock1());
            add(BlockRegistry.bone_torch.getBlock1());
            add(BlockRegistry.ultrabright_torch.getBlock1());
            add(BlockRegistry.demon_torch.getBlock1());
            add(BlockRegistry.cursed_torch.getBlock1());
            add(BlockRegistry.ichor_torch.getBlock1());
            add(BlockRegistry.rainbow_torch.getBlock1());
            add(BlockRegistry.desert_torch.getBlock1());
            add(BlockRegistry.coral_torch.getBlock1());
            add(BlockRegistry.corrupt_torch.getBlock1());
            add(BlockRegistry.crimson_torch.getBlock1());
            add(BlockRegistry.hallowed_torch.getBlock1());
            add(BlockRegistry.jungle_torch.getBlock1());
            add(BlockRegistry.ice_torch.getBlock2());
            add(BlockRegistry.bone_torch.getBlock2());
            add(BlockRegistry.ultrabright_torch.getBlock2());
            add(BlockRegistry.demon_torch.getBlock2());
            add(BlockRegistry.cursed_torch.getBlock2());
            add(BlockRegistry.ichor_torch.getBlock2());
            add(BlockRegistry.rainbow_torch.getBlock2());
            add(BlockRegistry.desert_torch.getBlock2());
            add(BlockRegistry.coral_torch.getBlock2());
            add(BlockRegistry.corrupt_torch.getBlock2());
            add(BlockRegistry.crimson_torch.getBlock2());
            add(BlockRegistry.hallowed_torch.getBlock2());
            add(BlockRegistry.jungle_torch.getBlock2());
        }};
        
        for (SimpleBlock block : CUTOUT) {
            BlockRenderLayerMap.INSTANCE.putBlock(block.getBlock(), RenderLayer.getCutout());
        }
    }
}