package survivalistessentials.data.client;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class SurvivalistEssentialsModelProvider extends FabricModelProvider {

    public SurvivalistEssentialsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public String getName() {
        return "SurvivalistEssentials - Item Models";
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        blockItem(SurvivalistEssentialsWorld.ANDESITE_LOOSE_ROCK, itemModelGenerator);
        blockItem(SurvivalistEssentialsWorld.DIORITE_LOOSE_ROCK, itemModelGenerator);
        blockItem(SurvivalistEssentialsWorld.GRANITE_LOOSE_ROCK, itemModelGenerator);
        blockItem(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK, itemModelGenerator);
        blockItem(SurvivalistEssentialsWorld.SANDSTONE_LOOSE_ROCK, itemModelGenerator);
        blockItem(SurvivalistEssentialsWorld.RED_SANDSTONE_LOOSE_ROCK, itemModelGenerator);

        itemModelGenerator.generateFlatItem(SurvivalistEssentialsWorld.ROCK_STONE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.FLINT_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.PLANT_FIBER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.PLANT_STRING, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.OINTMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.PLANT_PASTE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.CLOTH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_SAW_BLADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.BASIC_SAW_BLADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.SHARP_SAW_BLADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.BASIC_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.SHARP_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.SAW_HANDLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_SAW, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.BASIC_SAW, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.SHARP_SAW, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.MORTAR_AND_PESTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.CRUDE_BANDAGE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.BANDAGE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.WOODEN_CUP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.BOOK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SurvivalistEssentialsItems.MODPACK_BOOK, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        // Doing this in NeoForge, as I have no idea how to do very custom models with Fabric
    }

    protected void blockItem(Block block, ItemModelGenerators itemModelGenerator) {
        String type = BuiltInRegistries.BLOCK.getKey(block).getPath().replace("_loose_rock", "");

        ModelTemplates.FLAT_ITEM.create(new ResourceLocation("block/loose/9p_loose_" + type), TextureMapping.layer0(block.asItem()), itemModelGenerator.output);
    }

}
