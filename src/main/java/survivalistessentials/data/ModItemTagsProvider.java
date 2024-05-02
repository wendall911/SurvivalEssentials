package survivalistessentials.data;

import java.util.Arrays;
import java.util.function.Function;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import survivalistessentials.common.TagManager;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, SurvivalistEssentials.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "SurvivalistEssentials - Item Tags";
    }

    @Override
    protected void addTags() {
        builder(
            TagManager.Items.FLINT_KNAPPABLE,
            Items.FLINT,
            SurvivalistEssentialsWorld.ROCK_STONE
        );
        getBuilder(TagManager.Items.PICKAXE_TOOLS)
            .addOptional(ModIntegration.ieLoc("buzzsaw"))
            .addOptional(ModIntegration.ieLoc("drill"))
            .addOptional(ModIntegration.ieLoc("hammer"));
        getBuilder(TagManager.Items.AXE_TOOLS)
            .addOptional(ModIntegration.ieLoc("buzzsaw"))
            .add(SurvivalistEssentialsItems.CRUDE_HATCHET);
        getBuilder(TagManager.Items.SAW_TOOLS)
            .add(SurvivalistEssentialsItems.CRUDE_SAW)
            .add(SurvivalistEssentialsItems.BASIC_SAW)
            .add(SurvivalistEssentialsItems.SHARP_SAW)
            .addOptional(ModIntegration.tsLoc("saw"));
        getBuilder(TagManager.Items.ADVANCED_SAW_TOOLS)
            .add(SurvivalistEssentialsItems.BASIC_SAW)
            .add(SurvivalistEssentialsItems.SHARP_SAW)
            .addOptional(ModIntegration.tsLoc("saw"));
        getBuilder(TagManager.Items.SHOVEL_TOOLS)
            .addOptional(ModIntegration.ieLoc("drill"));
        builder(
            TagManager.Items.HOE_TOOLS
        );
        getBuilder(TagManager.Items.KNIFE_TOOLS)
            .add(SurvivalistEssentialsItems.CRUDE_KNIFE)
            .add(SurvivalistEssentialsItems.BASIC_KNIFE)
            .add(SurvivalistEssentialsItems.SHARP_KNIFE)
            .addOptional(ModIntegration.tsLoc("knife"));
        getBuilder(TagManager.Items.ADVANCED_KNIFE_TOOLS)
            .add(SurvivalistEssentialsItems.BASIC_KNIFE)
            .add(SurvivalistEssentialsItems.SHARP_KNIFE)
            .addOptional(ModIntegration.tsLoc("knife"));
        getBuilder(TagManager.Items.SHARP_TOOLS)
            .addOptional(ModIntegration.ieLoc("revolver"))
            .addTag(TagManager.Items.KNIFE_TOOLS)
            .addTag(TagManager.Items.AXE_TOOLS);
        getBuilder(TagManager.Items.SHEAR_TOOLS)
            .addTag(Tags.Items.SHEARS);
        builder(TagManager.Items.ROCK, SurvivalistEssentialsWorld.ROCK_STONE);
        builder(
            TagManager.Items.SAW_PARTS,
            SurvivalistEssentialsItems.SAW_HANDLE,
            SurvivalistEssentialsItems.CRUDE_SAW_BLADE
        );
        builder(
            TagManager.Items.BANDAGES,
            SurvivalistEssentialsItems.CRUDE_BANDAGE,
            SurvivalistEssentialsItems.BANDAGE
        );
        getBuilder(TagManager.Items.COOKED_MEAT)
            .add(Items.COOKED_BEEF)
            .add(Items.COOKED_CHICKEN)
            .add(Items.COOKED_COD)
            .add(Items.COOKED_MUTTON)
            .add(Items.COOKED_PORKCHOP)
            .add(Items.COOKED_RABBIT)
            .add(Items.COOKED_SALMON)
            .addOptional(ModIntegration.aquaLoc("fish_fillet_cooked"))
            .addOptional(ModIntegration.aquaLoc("frog_legs_cooked"))
            .addOptional(ModIntegration.bapLoc("turkey_cooked"))
            .addOptional(ModIntegration.bapLoc("venisoncooked"))
            .addOptional(ModIntegration.bapLoc("pheasantcooked"))
            .addOptional(ModIntegration.bapLoc("crab_meat_cooked"))
            .addOptional(ModIntegration.bapLoc("turkey_leg_cooked"))
            .addOptional(ModIntegration.bapLoc("eel_meat_cooked"))
            .addOptional(ModIntegration.bapLoc("calamari_cooked"))
            .addOptional(ModIntegration.alexLoc("cooked_lobster_tail"))
            .addOptional(ModIntegration.alexLoc("cooked_moose_ribs"))
            .addOptional(ModIntegration.alexLoc("cooked_kangaroo_meat"))
            .addOptional(ModIntegration.alexLoc("cooked_catfish"));

        // Fruit Trees
        addLogVariants(TagManager.Items.CHERRY_LOGS, "cherry", ModIntegration::ftLoc);
        addLogVariants(TagManager.Items.CITRUS_LOGS, "citrus", ModIntegration::ftLoc);

        // Biome Makeover
        getBuilder(TagManager.Items.BMO_ANCIENT_OAK_LOG)
            .addOptional(ModIntegration.BMO_ANCIENT_OAK_LOG);
        getBuilder(TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_LOG)
            .addOptional(ModIntegration.BMO_STRIPPED_ANCIENT_OAK_LOG);
        getBuilder(TagManager.Items.BMO_ANCIENT_OAK_WOOD)
            .addOptional(ModIntegration.BMO_ANCIENT_OAK_WOOD);
        getBuilder(TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_WOOD)
            .addOptional(ModIntegration.BMO_STRIPPED_ANCIENT_OAK_WOOD);
        getBuilder(TagManager.Items.BMO_BLIGHTED_BALSA_LOG)
            .addOptional(ModIntegration.BMO_BLIGHTED_BALSA_LOG);
        getBuilder(TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_LOG)
            .addOptional(ModIntegration.BMO_STRIPPED_BLIGHTED_BALSA_LOG);
        getBuilder(TagManager.Items.BMO_BLIGHTED_BALSA_WOOD)
            .addOptional(ModIntegration.BMO_BLIGHTED_BALSA_WOOD);
        getBuilder(TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_WOOD)
            .addOptional(ModIntegration.BMO_STRIPPED_BLIGHTED_BALSA_WOOD);
        getBuilder(TagManager.Items.BMO_SWAMP_CYPRESS_LOG)
            .addOptional(ModIntegration.BMO_SWAMP_CYPRESS_LOG);
        getBuilder(TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_LOG)
            .addOptional(ModIntegration.BMO_STRIPPED_SWAMP_CYPRESS_LOG);
        getBuilder(TagManager.Items.BMO_SWAMP_CYPRESS_WOOD)
            .addOptional(ModIntegration.BMO_SWAMP_CYPRESS_WOOD);
        getBuilder(TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_WOOD)
            .addOptional(ModIntegration.BMO_STRIPPED_SWAMP_CYPRESS_WOOD);
        getBuilder(TagManager.Items.BMO_WILLOW_LOG)
            .addOptional(ModIntegration.BMO_WILLOW_LOG);
        getBuilder(TagManager.Items.BMO_STRIPPED_WILLOW_LOG)
            .addOptional(ModIntegration.BMO_STRIPPED_WILLOW_LOG);
        getBuilder(TagManager.Items.BMO_WILLOW_WOOD)
            .addOptional(ModIntegration.BMO_WILLOW_WOOD);
        getBuilder(TagManager.Items.BMO_STRIPPED_WILLOW_WOOD)
            .addOptional(ModIntegration.BMO_STRIPPED_WILLOW_WOOD);

        // Biomes O' Plenty
        addLogVariants(TagManager.Items.BOP_CHERRY_LOGS, "cherry", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_DEAD_LOGS, "dead", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_FIR_LOGS, "fir", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_HELLBARK_LOGS, "hellbark", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_JACARANDA_LOGS, "jacaranda", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_MAGIC_LOGS, "magic", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_MAHOGANY_LOGS, "mahogany", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_PALM_LOGS, "palm", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_REDWOOD_LOGS, "redwood", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_UMBRAN_LOGS, "umbran", ModIntegration::bopLoc);
        addLogVariants(TagManager.Items.BOP_WILLOW_LOGS, "willow", ModIntegration::bopLoc);

        // Botania
        addLogVariants(TagManager.Items.BOTANIA_DREAMWOOD_LOGS, "dreamwood", ModIntegration::botaniaLoc);
        addLogVariants(TagManager.Items.BOTANIA_LIVINGWOOD_LOGS, "livingwood", ModIntegration::botaniaLoc);

        // Quark
        addLogVariants(TagManager.Items.QUARK_AZALEA_LOGS, "azalea", ModIntegration::qLoc);
        addLogVariants(TagManager.Items.QUARK_BLOSSOM_LOGS, "blossom", ModIntegration::qLoc);

        // All You Can Eat
        addLogVariants(TagManager.Items.AYCE_HAZEL_LOGS, "hazel", ModIntegration::ayceLoc);

        // Tinkers' Construct
        addLogVariants(TagManager.Items.TCON_BLOODSHROOM_LOGS, "bloodshroom", ModIntegration::tconLoc);
        addLogVariants(TagManager.Items.TCON_GREENHEART_LOGS, "greenheart", ModIntegration::tconLoc);
        addLogVariants(TagManager.Items.TCON_SKYROOT_LOGS, "skyroot", ModIntegration::tconLoc);

        // Water Source
        addWsLogVariants(TagManager.Items.WS_PALM_TREE_LOGS, "palm_tree");

        // Undergarden
        addLogVariants(TagManager.Items.UNDERGARDEN_GRONGLE_LOGS, "grongle", ModIntegration::undergardenLoc);
        addLogVariants(TagManager.Items.UNDERGARDEN_SMOGSTEM_LOGS, "smogstem", ModIntegration::undergardenLoc);
        addLogVariants(TagManager.Items.UNDERGARDEN_WIGGLEWOOD_LOGS, "wigglewood", ModIntegration::undergardenLoc);

        // BYG
        addLogVariants(TagManager.Items.BYG_ETHER_LOGS, "ether", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_WHITE_MANGROVE_LOGS, "white_mangrove", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_REDWOOD_LOGS, "redwood", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_BLUE_ENCHANTED_LOGS, "blue_enchanted", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_GREEN_ENCHANTED_LOGS, "green_enchanted", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_LAMENT_LOGS, "lament", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_MAHOGANY_LOGS, "mahogany", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_CHERRY_LOGS, "cherry", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_BAOBAB_LOGS, "baobab", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_JACARANDA_LOGS, "jacaranda", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_CYPRESS_LOGS, "cypress", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_PALM_LOGS, "palm", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_EBONY_LOGS, "ebony", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_NIGHTSHADE_LOGS, "nightshade", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_RAINBOW_EUCALYPTUS_LOGS, "rainbow_eucalyptus", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_ASPEN_LOGS, "aspen", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_FIR_LOGS, "fir", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_SKYRIS_LOGS, "skyris", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_CIKA_LOGS, "cika", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_HOLLY_LOGS, "holly", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_MAPLE_LOGS, "maple", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_PINE_LOGS, "pine", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_WILLOW_LOGS, "willow", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_WITCH_HAZEL_LOGS, "witch_hazel", ModIntegration::bygLoc);
        addLogVariants(TagManager.Items.BYG_ZELKOVA_LOGS, "zelkova", ModIntegration::bygLoc);
        getBuilder(TagManager.Items.BYG_EMBUR_LOGS)
            .addOptionalTag(ModIntegration.bygLoc("embur_logs"));
        getBuilder(TagManager.Items.BYG_SYTHIAN_LOGS)
            .addOptionalTag(ModIntegration.bygLoc("sythian_logs"));
        getBuilder(TagManager.Items.BYG_IMPARIUS_LOGS)
            .addOptionalTag(ModIntegration.bygLoc("imparius_logs"));
        getBuilder(TagManager.Items.BYG_BULBIS_LOGS)
            .addOptionalTag(ModIntegration.bygLoc("bulbis_logs"));

        // Twilight Forest
        getBuilder(TagManager.Items.TF_GIANT_LOGS)
            .addOptional(ModIntegration.tfLoc("giant_log"));
        getBuilder(TagManager.Items.TF_CANOPY_LOG)
            .addOptional(ModIntegration.TF_CANOPY_LOG);
        getBuilder(TagManager.Items.TF_CANOPY_STRIPPED_LOG)
            .addOptional(ModIntegration.TF_CANOPY_STRIPPED_LOG);
        getBuilder(TagManager.Items.TF_CANOPY_WOOD)
            .addOptional(ModIntegration.TF_CANOPY_WOOD);
        getBuilder(TagManager.Items.TF_CANOPY_STRIPPED_WOOD)
            .addOptional(ModIntegration.TF_CANOPY_STRIPPED_WOOD);
        getBuilder(TagManager.Items.TF_DARK_LOG)
            .addOptional(ModIntegration.TF_DARK_LOG);
        getBuilder(TagManager.Items.TF_DARK_STRIPPED_LOG)
            .addOptional(ModIntegration.TF_DARK_STRIPPED_LOG);
        getBuilder(TagManager.Items.TF_DARK_WOOD)
            .addOptional(ModIntegration.TF_DARK_WOOD);
        getBuilder(TagManager.Items.TF_DARK_STRIPPED_WOOD)
            .addOptional(ModIntegration.TF_DARK_STRIPPED_WOOD);
        getBuilder(TagManager.Items.TF_MANGROVE_LOG)
            .addOptional(ModIntegration.TF_MANGROVE_LOG);
        getBuilder(TagManager.Items.TF_MANGROVE_STRIPPED_LOG)
            .addOptional(ModIntegration.TF_MANGROVE_STRIPPED_LOG);
        getBuilder(TagManager.Items.TF_MANGROVE_WOOD)
            .addOptional(ModIntegration.TF_MANGROVE_WOOD);
        getBuilder(TagManager.Items.TF_MANGROVE_STRIPPED_WOOD)
            .addOptional(ModIntegration.TF_MANGROVE_STRIPPED_WOOD);
        getBuilder(TagManager.Items.TF_MINING_LOG)
            .addOptional(ModIntegration.TF_MINING_LOG);
        getBuilder(TagManager.Items.TF_MINING_STRIPPED_LOG)
            .addOptional(ModIntegration.TF_MINING_STRIPPED_LOG);
        getBuilder(TagManager.Items.TF_MINING_WOOD)
            .addOptional(ModIntegration.TF_MINING_WOOD);
        getBuilder(TagManager.Items.TF_MINING_STRIPPED_WOOD)
            .addOptional(ModIntegration.TF_MINING_STRIPPED_WOOD);
        getBuilder(TagManager.Items.TF_SORTING_LOG)
            .addOptional(ModIntegration.TF_SORTING_LOG);
        getBuilder(TagManager.Items.TF_SORTING_STRIPPED_LOG)
            .addOptional(ModIntegration.TF_SORTING_STRIPPED_LOG);
        getBuilder(TagManager.Items.TF_SORTING_WOOD)
            .addOptional(ModIntegration.TF_SORTING_WOOD);
        getBuilder(TagManager.Items.TF_SORTING_STRIPPED_WOOD)
            .addOptional(ModIntegration.TF_SORTING_STRIPPED_WOOD);
        getBuilder(TagManager.Items.TF_TIME_LOG)
            .addOptional(ModIntegration.TF_TIME_LOG);
        getBuilder(TagManager.Items.TF_TIME_STRIPPED_LOG)
            .addOptional(ModIntegration.TF_TIME_STRIPPED_LOG);
        getBuilder(TagManager.Items.TF_TIME_WOOD)
            .addOptional(ModIntegration.TF_TIME_WOOD);
        getBuilder(TagManager.Items.TF_TIME_STRIPPED_WOOD)
            .addOptional(ModIntegration.TF_TIME_STRIPPED_WOOD);
        getBuilder(TagManager.Items.TF_TRANSFORMATION_LOG)
            .addOptional(ModIntegration.TF_TRANSFORMATION_LOG);
        getBuilder(TagManager.Items.TF_TRANSFORMATION_STRIPPED_LOG)
            .addOptional(ModIntegration.TF_TRANSFORMATION_STRIPPED_LOG);
        getBuilder(TagManager.Items.TF_TRANSFORMATION_WOOD)
            .addOptional(ModIntegration.TF_TRANSFORMATION_WOOD);
        getBuilder(TagManager.Items.TF_TRANSFORMATION_STRIPPED_WOOD)
            .addOptional(ModIntegration.TF_TRANSFORMATION_STRIPPED_WOOD);
        getBuilder(TagManager.Items.TF_TWILIGHT_OAK_LOG)
            .addOptional(ModIntegration.TF_TWILIGHT_OAK_LOG);
        getBuilder(TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_LOG)
            .addOptional(ModIntegration.TF_TWILIGHT_OAK_STRIPPED_LOG);
        getBuilder(TagManager.Items.TF_TWILIGHT_OAK_WOOD)
            .addOptional(ModIntegration.TF_TWILIGHT_OAK_WOOD);
        getBuilder(TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_WOOD)
            .addOptional(ModIntegration.TF_TWILIGHT_OAK_STRIPPED_WOOD);

        // Ecologics
        addLogVariants(TagManager.Items.ECO_COCONUT_LOGS, "coconut", ModIntegration::ecoLoc);
        addLogVariants(TagManager.Items.ECO_WALNUT_LOGS, "walnut", ModIntegration::ecoLoc);
        addLogVariants(TagManager.Items.ECO_AZALEA_LOGS, "azalea", ModIntegration::ecoLoc);
        addLogVariants(TagManager.Items.ECO_FLOWERING_AZALEA_LOGS, "flowering_azalea", ModIntegration::ecoLoc);

        // Malum
        addSoulwoodVariants(TagManager.Items.MALUM_RUNEWOOD_LOGS, "runewood");
        addRunewoodVariants(TagManager.Items.MALUM_SOULWOOD_LOGS, "soulwood");

        // Ice and Fire; Dragons
        getBuilder(TagManager.Items.IFD_DREADWOOD_LOGS)
            .addOptional(ModIntegration.ifdLoc("dreadwood_log"));

    }

    private void addWsLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.wsLoc(type + "_log"))
            .addOptional(ModIntegration.wsLoc("stripped_" + type + "_log"));
    }

    private void addLogVariants(TagKey<Item> tag, String type, Function<String, ResourceLocation> modLoc) {
        getBuilder(tag)
            .addOptional(modLoc.apply(type + "_log"))
            .addOptional(modLoc.apply("stripped_" + type + "_log"))
            .addOptional(modLoc.apply(type + "_wood"))
            .addOptional(modLoc.apply("stripped_" + type + "_wood"));
    }

    private void addRunewoodVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.malumLoc(type))
            .addOptional(ModIntegration.malumLoc(type + "_log"))
            .addOptional(ModIntegration.malumLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.malumLoc("exposed_" + type + "_log"))
            .addOptional(ModIntegration.malumLoc("revealed_" + type + "_log"));
    }

    private void addSoulwoodVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.malumLoc(type))
            .addOptional(ModIntegration.malumLoc(type + "_log"))
            .addOptional(ModIntegration.malumLoc("stripped_" + type))
            .addOptional(ModIntegration.malumLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.malumLoc("blighted_" + type))
            .addOptional(ModIntegration.malumLoc("exposed_" + type + "_log"))
            .addOptional(ModIntegration.malumLoc("revealed_" + type + "_log"));
    }

    protected TagsProvider.TagAppender<Item> getBuilder(TagKey<Item> tag) {
        return tag(tag);
    }

    private void builder(TagKey<Item> tag, ItemLike... items) {
        getBuilder(tag).add(Arrays.stream(items).map(ItemLike::asItem).toArray(Item[]::new));
    }

}
