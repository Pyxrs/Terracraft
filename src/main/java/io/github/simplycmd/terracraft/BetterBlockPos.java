package io.github.simplycmd.terracraft;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class BetterBlockPos extends BlockPos {

    @Getter
    @Setter
    BlockState blockState;

    public BetterBlockPos(int i, int j, int k, BlockState blockState) {
        super(i, j, k);
        this.blockState = blockState;
    }

    public BetterBlockPos(double d, double e, double f, BlockState blockState) {
        super(d, e, f);
        this.blockState = blockState;
    }

    public BetterBlockPos(Vec3d pos, BlockState blockState) {
        this(pos.x, pos.y, pos.z, blockState);
    }

    public BetterBlockPos(Position pos, BlockState blockState) {
        this(pos.getX(), pos.getY(), pos.getZ(), blockState);
    }

    public BetterBlockPos(Vec3i pos, BlockState blockState) {
        this(pos.getX(), pos.getY(), pos.getZ(), blockState);
    }
}
