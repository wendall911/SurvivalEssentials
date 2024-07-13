package survivalistessentials.data.client;

import java.util.Optional;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, SurvivalistEssentials.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "SurvivalistEssentials - Item Models";
    }

    @Override
    protected void registerModels() {
        blockItem(SurvivalistEssentialsWorld.ANDESITE_LOOSE_ROCK);
        blockItem(SurvivalistEssentialsWorld.DIORITE_LOOSE_ROCK);
        blockItem(SurvivalistEssentialsWorld.GRANITE_LOOSE_ROCK);
        blockItem(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK);
        blockItem(SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK);
        blockItem(SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK);

        ModelFile itemGenerated = getExistingFile(mcLoc(ITEM_FOLDER + "/generated"));
        ModelFile itemHandheld = getExistingFile(mcLoc(ITEM_FOLDER + "/handheld"));

        build(itemGenerated, SurvivalistEssentialsWorld.ROCK_STONE);
        build(itemGenerated, SurvivalistEssentialsItems.FLINT_SHARD);
        build(itemGenerated, SurvivalistEssentialsItems.PLANT_FIBER);
        build(itemGenerated, SurvivalistEssentialsItems.PLANT_STRING);
        build(itemGenerated, SurvivalistEssentialsItems.OINTMENT);
        build(itemGenerated, SurvivalistEssentialsItems.PLANT_PASTE);
        build(itemGenerated, SurvivalistEssentialsItems.CLOTH);
        build(itemGenerated, SurvivalistEssentialsItems.CRUDE_SAW_BLADE);
        build(itemGenerated, SurvivalistEssentialsItems.BASIC_SAW_BLADE);
        build(itemGenerated, SurvivalistEssentialsItems.SHARP_SAW_BLADE);
        build(itemHandheld, SurvivalistEssentialsItems.CRUDE_KNIFE);
        build(itemHandheld, SurvivalistEssentialsItems.BASIC_KNIFE);
        build(itemHandheld, SurvivalistEssentialsItems.SHARP_KNIFE);
        build(itemHandheld, SurvivalistEssentialsItems.CRUDE_HATCHET);
        build(itemHandheld, SurvivalistEssentialsItems.SAW_HANDLE);
        build(itemHandheld, SurvivalistEssentialsItems.CRUDE_SAW);
        build(itemHandheld, SurvivalistEssentialsItems.BASIC_SAW);
        build(itemHandheld, SurvivalistEssentialsItems.SHARP_SAW);
        build(itemGenerated, SurvivalistEssentialsItems.MORTAR_AND_PESTLE);
        build(itemGenerated, SurvivalistEssentialsItems.CRUDE_BANDAGE);
        build(itemGenerated, SurvivalistEssentialsItems.BANDAGE);
        build(itemGenerated, SurvivalistEssentialsItems.WOODEN_CUP);
        build(itemGenerated, SurvivalistEssentialsItems.BOOK);
        build(itemGenerated, SurvivalistEssentialsItems.MODPACK_BOOK);
    }

    private void build(ModelFile itemGenerated, Item item) {
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();

        getBuilder(name).parent(itemGenerated).texture("layer0", ITEM_FOLDER + "/" + name);
    }

    protected void blockItem(Block block) {
        String type = BuiltInRegistries.BLOCK.getKey(block).getPath().replace("_loose_rock", "");

        ItemModelBuilder builder = Optional.of(block)
            .map(BuiltInRegistries.BLOCK::getKey)
            .map(ResourceLocation::getPath)
            .map(path -> {
                return withExistingParent(path, modLoc(BLOCK_FOLDER + "/" + path));
            })
            .orElseThrow(() -> new IllegalStateException("Failed to create model for Block Item"));

        builder.texture("all", modLoc("block/loose/9p_loose_" + type));
    }

}
