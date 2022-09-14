package survivalessentials.data.client;

import java.util.Objects;
import java.util.Optional;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import survivalessentials.items.SurvivalEssentialsItems;
import survivalessentials.SurvivalEssentials;
import survivalessentials.world.SurvivalEssentialsWorld;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SurvivalEssentials.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "SurvivalEssentials - Item Models";
    }

    @Override
    protected void registerModels() {
        blockItem(SurvivalEssentialsWorld.ANDESITE_LOOSE_ROCK);
        blockItem(SurvivalEssentialsWorld.DIORITE_LOOSE_ROCK);
        blockItem(SurvivalEssentialsWorld.GRANITE_LOOSE_ROCK);
        blockItem(SurvivalEssentialsWorld.STONE_LOOSE_ROCK);
        blockItem(SurvivalEssentialsWorld.SANDSTONE_LOOSE_ROCK);
        blockItem(SurvivalEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK);

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));

        build(itemGenerated, SurvivalEssentialsWorld.ROCK_STONE);
        build(itemGenerated, SurvivalEssentialsItems.FLINT_SHARD);
        build(itemGenerated, SurvivalEssentialsItems.PLANT_FIBER);
        build(itemGenerated, SurvivalEssentialsItems.PLANT_STRING);
        build(itemGenerated, SurvivalEssentialsItems.OINTMENT);
        build(itemGenerated, SurvivalEssentialsItems.PLANT_PASTE);
        build(itemGenerated, SurvivalEssentialsItems.CLOTH);
        build(itemGenerated, SurvivalEssentialsItems.CRUDE_SAW_BLADE);
        build(itemGenerated, SurvivalEssentialsItems.BASIC_SAW_BLADE);
        build(itemGenerated, SurvivalEssentialsItems.SHARP_SAW_BLADE);
        build(itemHandheld, SurvivalEssentialsItems.CRUDE_KNIFE);
        build(itemHandheld, SurvivalEssentialsItems.BASIC_KNIFE);
        build(itemHandheld, SurvivalEssentialsItems.SHARP_KNIFE);
        build(itemHandheld, SurvivalEssentialsItems.CRUDE_HATCHET);
        build(itemHandheld, SurvivalEssentialsItems.SAW_HANDLE);
        build(itemHandheld, SurvivalEssentialsItems.CRUDE_SAW);
        build(itemHandheld, SurvivalEssentialsItems.BASIC_SAW);
        build(itemHandheld, SurvivalEssentialsItems.SHARP_SAW);
        build(itemGenerated, SurvivalEssentialsItems.MORTAR_AND_PESTLE);
        build(itemGenerated, SurvivalEssentialsItems.CRUDE_BANDAGE);
        build(itemGenerated, SurvivalEssentialsItems.BANDAGE);
        build(itemGenerated, SurvivalEssentialsItems.WOODEN_CUP);
    }

    private void build(ModelFile itemGenerated, Item item) {
        String name = Objects.requireNonNull(item.getRegistryName()).getPath();

        getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

    protected void blockItem(Block block) {
        String type = Objects.requireNonNull(block.getRegistryName()).getPath().replace("_loose_rock", "");

        ItemModelBuilder builder = Optional.ofNullable(block)
            .map(Block::getRegistryName)
            .map(ResourceLocation::getPath)
            .map(path -> {
                return withExistingParent(path, modLoc("block/" + path));
            })
            .orElseThrow(() -> new IllegalStateException("Failed to create model for Block Item"));

        builder.texture("all", modLoc("block/loose/9p_loose_" + type));
    }

}
