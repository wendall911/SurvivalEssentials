package survivalistessentials.data;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaBlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.common.TagManager;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class ModBlockTagsProvider extends VanillaBlockTagsProvider {

    public ModBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(packOutput, provider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(TagManager.Blocks.ALWAYS_BREAKABLE)
            .addTag(TagManager.Blocks.LOOSE_ROCKS)
            .addTag(BlockTags.BEDS)
            .add(Blocks.GRAVEL)
            .add(Blocks.BAMBOO)
            .add(Blocks.SNOW)
            .addTag(TagManager.Blocks.FORGE_GRAVEL)
            .addTag(TagManager.Blocks.CONVENTIONAL_GRAVEL)
            .addTag(BlockTags.DIRT)
            .addTag(TagManager.Blocks.FORGE_SAND)
            .addTag(TagManager.Blocks.CONVENTIONAL_SAND)
            .addTag(BlockTags.SAND)
            .addTag(TagManager.Blocks.FIBER_PLANTS);

        this.tag(TagManager.Blocks.ALWAYS_DROPS)
            .addTag(TagManager.Blocks.LOOSE_ROCKS)
            .addTag(BlockTags.BEDS)
            .add(Blocks.GRAVEL)
            .add(Blocks.SNOW)
            .addTag(TagManager.Blocks.FORGE_GRAVEL)
            .addTag(TagManager.Blocks.CONVENTIONAL_GRAVEL)
            .addTag(BlockTags.DIRT)
            .addTag(TagManager.Blocks.FORGE_SAND)
            .addTag(TagManager.Blocks.CONVENTIONAL_SAND)
            .addTag(BlockTags.SAND)
            .addTag(TagManager.Blocks.FIBER_PLANTS);

        this.tag(TagManager.Blocks.LOOSE_ROCK_PLACEABLE_ON)
            .add(Blocks.GRAVEL)
            .addTag(TagManager.Blocks.FORGE_GRAVEL)
            .addTag(TagManager.Blocks.CONVENTIONAL_GRAVEL)
            .add(Blocks.STONE)
            .add(Blocks.CALCITE)
            .add(Blocks.GRANITE)
            .add(Blocks.DIORITE)
            .add(Blocks.ANDESITE)
            .add(Blocks.COAL_ORE)
            .addTag(TagManager.Blocks.FORGE_ORES_COAL)
            .addTag(TagManager.Blocks.CONVENTIONAL_ORES_COAL)
            .add(Blocks.SANDSTONE)
            .add(Blocks.IRON_BLOCK)
            .addTag(TagManager.Blocks.FORGE_ORES_IRON)
            .addTag(TagManager.Blocks.CONVENTIONAL_ORES_IRON)
            .add(Blocks.COPPER_ORE)
            .addTag(TagManager.Blocks.FORGE_ORES_COPPER)
            .addTag(TagManager.Blocks.CONVENTIONAL_ORES_COPPER)
            .add(Blocks.MOSSY_COBBLESTONE)
            .add(Blocks.RED_SANDSTONE)
            .addTag(BlockTags.DIRT)
            .addTag(BlockTags.SAND)
            .addTag(TagManager.Blocks.FORGE_SAND)
            .addTag(TagManager.Blocks.CONVENTIONAL_SAND)
            .addTag(BlockTags.TERRACOTTA);

        this.tag(TagManager.Blocks.LOOSE_ROCKS)
            .add(SurvivalistEssentialsWorld.ANDESITE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.DIORITE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.GRANITE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK)
            .add(SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK);

        this.tag(TagManager.Blocks.FIBER_PLANTS)
            .addTag(BlockTags.LEAVES)
            .add(Blocks.VINE)
            .add(Blocks.FERN)
            .add(Blocks.LARGE_FERN)
            .add(Blocks.GRASS_BLOCK)
            .add(Blocks.TALL_GRASS)
            .addOptional(ModIntegration.sgcLoc("avocado_leaves"))
            .addOptional(ModIntegration.exnihiloLoc("infested_leaves"))
            .addOptional(ModIntegration.exnihiloLoc("infesting_leaves"))
            .addOptionalTag(TagManager.forgeLoc("grass"))
            .addOptionalTag(TagManager.forgeLoc("bushes"));

        this.tag(TagManager.Blocks.BRANCHES)
            .addOptionalTag(ModIntegration.dynamictreesLoc("branches"));

        this.tag(TagManager.Blocks.MINEABLE_WITH_SHARP)
            .addTag(BlockTags.WOOL_CARPETS)
            .add(Blocks.COBWEB)
            .addTag(BlockTags.WOOL)
            .addTag(BlockTags.CANDLE_CAKES);
    }

}
