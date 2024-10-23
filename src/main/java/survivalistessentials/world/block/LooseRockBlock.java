package survivalistessentials.world.block;

import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LooseRockBlock extends Block {

    // West, ??, North, fromWest, height, fromNorth
    public static final VoxelShape rockHitbox = box(4, 0, 3, 12, 2, 11);

    public LooseRockBlock(ResourceKey<Block> resourceKey) {
        super(Properties.of().mapColor(MapColor.CLAY).sound(SoundType.STONE).strength(0.0F).noCollission().noOcclusion().setId(resourceKey));
    }

    @Override
    protected void neighborChanged(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @Nullable Orientation orientation, boolean isMoving) {
        if (!state.canSurvive(level, pos) && !level.isClientSide) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockState stateUnder = level.getBlockState(pos.below());
        return stateUnder.isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return rockHitbox;
    }

}
