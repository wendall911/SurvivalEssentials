package survivalistessentials.data.client;

import java.util.Objects;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper) {
        super(packOutput, SurvivalistEssentials.MODID, exFileHelper);
    }

    @Override
    public String getName() {
        return "SurvivalistEssentials - Block State and Models";
    }

    @Override
    protected void registerStatesAndModels() {
        generateLooseRockBaseModel();

        generateLooseRockVariants(SurvivalistEssentialsWorld.ANDESITE_LOOSE_ROCK);
        generateLooseRockVariants(SurvivalistEssentialsWorld.DIORITE_LOOSE_ROCK);
        generateLooseRockVariants(SurvivalistEssentialsWorld.GRANITE_LOOSE_ROCK);
        generateLooseRockVariants(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK);
        generateLooseRockVariants(SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK);
        generateLooseRockVariants(SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK);
        generateLooseRockVariants(SurvivalistEssentialsWorld.ROCK_STONE_BLOCK);
    }

    private void generateLooseRockBaseModel() {
        BlockModelBuilder base = models()
            .withExistingParent("block/loose_rock", mcLoc("block/block"))
            .texture("particle", "#all");

        addFaces(base.element().from(4, 0, 4).to(5, 1, 5));
        addFaces(base.element().from(7, 0, 3).to(8, 1, 4));
        addFaces(base.element().from(5, 0, 7).to(6, 1, 8));
        addFaces(base.element().from(5, 0, 10).to(6, 1, 11));
        addFaces(base.element().from(11, 0, 6).to(12, 1, 7));
        addFaces(base.element().from(10, 0, 10).to(11, 1, 11));
        addFaces(base.element().from(9, 0, 8).to(10, 1, 9));
        addFaces(base.element().from(7, 0, 9).to(8, 1, 10));
        addFaces(base.element().from(7, 1, 6).to(8, 2, 7));
        addFaces(base.element().from(6, 1, 7).to(7, 2, 8));
        addFaces(base.element().from(8, 1, 8).to(9, 2, 9));
        addFaces(base.element().from(6, 0, 6).to(9, 1, 9));
    }

    private void addFaces(ModelBuilder<BlockModelBuilder>.ElementBuilder elementBuilder) {
        elementBuilder.allFaces((direction, faceBuilder) -> {
            ModelBuilder<BlockModelBuilder>.ElementBuilder.FaceBuilder f = faceBuilder
                .uvs(0, 0, 12, 12)
                .texture("#all");
        }).end();
    }

    private void generateLooseRockVariants(Block block) {
        ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);
        String type = Objects.requireNonNull(name).getPath().replace("_loose_rock", "");

        if (type.contains("rock_stone")) {
            type = "stone";
        }

        ModelFile modelFile = models()
            .withExistingParent(name.toString(), modLoc("block/loose_rock"))
            .texture("all", modLoc("block/loose/9p_loose_" + type));

        getVariantBuilder(block)
            .forAllStates(state -> ConfiguredModel.builder().modelFile(modelFile).build());
    }

}
