package survivalistessentials.world.feature;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.WorldGenLevel;

import net.minecraftforge.common.util.Lazy;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.TagManager;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class LooseRocks extends Feature<NoneFeatureConfiguration> {

    private static final Lazy<Map<Block, Block>> LOOSE_ROCK_SUPPLIER = Lazy.of(() -> new ImmutableMap.Builder<Block, Block>()
        .put(Blocks.STONE, SurvivalistEssentialsWorld.STONE_LOOSE_ROCK)
        .put(Blocks.ANDESITE, SurvivalistEssentialsWorld.ANDESITE_LOOSE_ROCK)
        .put(Blocks.DIORITE, SurvivalistEssentialsWorld.DIORITE_LOOSE_ROCK)
        .put(Blocks.GRANITE, SurvivalistEssentialsWorld.GRANITE_LOOSE_ROCK)
        .put(Blocks.SANDSTONE, SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK)
        .put(Blocks.RED_SANDSTONE, SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK)
        .put(Blocks.TERRACOTTA, SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK)
        .put(Blocks.SAND, SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK)
        .put(Blocks.RED_SAND, SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK)
        .build()
    );

    public LooseRocks() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        SurvivalistEssentials.LOGGER.warn("place");
        final WorldGenLevel level = context.level();
        final BlockPos pos = context.origin();

        final BlockState stateAt = level.getBlockState(pos);
        final BlockState stateDown = level.getBlockState(pos.below());

        if (stateAt.isAir() && stateDown.is(TagManager.Blocks.LOOSE_ROCK_PLACEABLE_ON)) {
            for (int y = 1; y <= 8; y++) {
                final BlockPos stonePos = pos.below(y);
                final BlockState stoneState = level.getBlockState(stonePos);
                if (LOOSE_ROCK_SUPPLIER.get().containsKey(stoneState.getBlock())) {
                    final Block looseRockBlock = LOOSE_ROCK_SUPPLIER.get().get(stoneState.getBlock());

                    level.setBlock(pos, looseRockBlock.defaultBlockState(), 3);
                    break;
                }
            }
        }

        return true;
    }

}