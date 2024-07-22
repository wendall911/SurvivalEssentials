package survivalistessentials.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.TagManager;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class ModBlockTagsProvider extends IntrinsicHolderTagsProvider<Block> {

    public ModBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        super(packOutput, Registries.BLOCK, lookupProvider, (block) -> block.builtInRegistryHolder().key(), SurvivalistEssentials.MODID, helper);
    }

    @Override
    public String getName() {
        return "SurvivalistEssentials - Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(TagManager.Blocks.ALWAYS_BREAKABLE)
            .addTag(TagManager.Blocks.LOOSE_ROCKS)
            .addTag(BlockTags.BEDS)
            .add(Blocks.GRAVEL)
            .add(Blocks.BAMBOO)
            .add(Blocks.SNOW)
            .addTag(Tags.Blocks.GRAVELS)
            .addTag(BlockTags.DIRT)
            .addTag(Tags.Blocks.SANDS)
            .addTag(BlockTags.SAND)
            .addTag(TagManager.Blocks.FIBER_PLANTS);

        this.tag(TagManager.Blocks.ALWAYS_DROPS)
            .addTag(TagManager.Blocks.LOOSE_ROCKS)
            .addTag(BlockTags.BEDS)
            .add(Blocks.GRAVEL)
            .add(Blocks.SNOW)
            .addTag(Tags.Blocks.GRAVELS)
            .addTag(BlockTags.DIRT)
            .addTag(Tags.Blocks.SANDS)
            .addTag(BlockTags.SAND)
            .addTag(TagManager.Blocks.FIBER_PLANTS);

        this.tag(TagManager.Blocks.LOOSE_ROCK_PLACEABLE_ON)
            .add(Blocks.GRAVEL)
            .addTag(Tags.Blocks.GRAVELS)
            .add(Blocks.STONE)
            .add(Blocks.CALCITE)
            .add(Blocks.GRANITE)
            .add(Blocks.DIORITE)
            .add(Blocks.ANDESITE)
            .add(Blocks.COAL_ORE)
            .addTag(Tags.Blocks.ORES_COAL)
            .add(Blocks.SANDSTONE)
            .add(Blocks.IRON_BLOCK)
            .addTag(Tags.Blocks.ORES_IRON)
            .add(Blocks.COPPER_ORE)
            .addTag(Tags.Blocks.ORES_COPPER)
            .add(Blocks.MOSSY_COBBLESTONE)
            .add(Blocks.RED_SANDSTONE)
            .addTag(BlockTags.DIRT)
            .addTag(BlockTags.SAND)
            .addTag(Tags.Blocks.SANDS)
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
            .addOptionalTag(TagManager.commonLoc("grass"))
            .addOptionalTag(TagManager.commonLoc("bushes"));

        this.tag(TagManager.Blocks.BRANCHES)
            .addOptionalTag(ModIntegration.dynamictreesLoc("branches"));

        this.tag(TagManager.Blocks.MINEABLE_WITH_SHARP)
            .addTag(BlockTags.WOOL_CARPETS)
            .add(Blocks.COBWEB)
            .addTag(BlockTags.WOOL)
            .addTag(BlockTags.CANDLE_CAKES);
    }

}
