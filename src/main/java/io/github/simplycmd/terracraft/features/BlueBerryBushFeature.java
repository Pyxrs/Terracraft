package io.github.simplycmd.terracraft.features;

import com.mojang.serialization.Codec;
import io.github.simplycmd.terracraft.blocks.BlueBerryBushBlock;
import io.github.simplycmd.terracraft.registry.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class BlueBerryBushFeature extends Feature<DefaultFeatureConfig> {
    private static final Random random = new Random();

    public BlueBerryBushFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos topPos = context.getWorld().getTopPosition(Heightmap.Type.WORLD_SURFACE, context.getOrigin());
        if (context.getWorld().getBlockState(new BlockPos(topPos.getX(), topPos.getY() - 1, topPos.getZ())) == Blocks.GRASS_BLOCK.getDefaultState())
            context.getWorld().setBlockState(topPos, BlockRegistry.blue_berry_bush.getBlock().getDefaultState().with(BlueBerryBushBlock.AGE, random.nextInt(2) + 1), 2);

        return true;
    }
}