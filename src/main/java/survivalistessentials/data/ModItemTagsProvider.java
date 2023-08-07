package survivalistessentials.data;

import java.util.Arrays;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import net.minecraftforge.fml.common.Mod;
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
        builder(
            TagManager.Items.SAW_TOOLS,
            SurvivalistEssentialsItems.CRUDE_SAW,
            SurvivalistEssentialsItems.BASIC_SAW,
            SurvivalistEssentialsItems.SHARP_SAW
        );
        builder(
            TagManager.Items.ADVANCED_SAW_TOOLS,
            SurvivalistEssentialsItems.BASIC_SAW,
            SurvivalistEssentialsItems.SHARP_SAW
        );
        getBuilder(TagManager.Items.SHOVEL_TOOLS)
            .addOptional(ModIntegration.ieLoc("drill"));
        builder(
            TagManager.Items.HOE_TOOLS
        );
        builder(
            TagManager.Items.KNIFE_TOOLS,
            SurvivalistEssentialsItems.CRUDE_KNIFE,
            SurvivalistEssentialsItems.BASIC_KNIFE,
            SurvivalistEssentialsItems.SHARP_KNIFE
        );
        builder(
            TagManager.Items.ADVANCED_KNIFE_TOOLS,
            SurvivalistEssentialsItems.BASIC_KNIFE,
            SurvivalistEssentialsItems.SHARP_KNIFE
        );
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
        addFTLogVariants(TagManager.Items.CHERRY_LOGS, "cherry");
        addFTLogVariants(TagManager.Items.CITRUS_LOGS, "citrus");

        // Biome Makeover
        addBMOLogVariants(TagManager.Items.BMO_ANCIENT_OAK_LOGS, "ancient_oak");
        addBMOLogVariants(TagManager.Items.BMO_BLIGHTED_BALSA_LOGS, "blighted_balsa");
        addBMOLogVariants(TagManager.Items.BMO_SWAMP_CYPRESS_LOGS, "swamp_cypress");
        addBMOLogVariants(TagManager.Items.BMO_WILLOW_LOGS, "willow");

        // Biomes O' Plenty
        addBOPLogVariants(TagManager.Items.BOP_CHERRY_LOGS, "cherry");
        addBOPLogVariants(TagManager.Items.BOP_DEAD_LOGS, "dead");
        addBOPLogVariants(TagManager.Items.BOP_FIR_LOGS, "fir");
        addBOPLogVariants(TagManager.Items.BOP_HELLBARK_LOGS, "hellbark");
        addBOPLogVariants(TagManager.Items.BOP_JACARANDA_LOGS, "jacaranda");
        addBOPLogVariants(TagManager.Items.BOP_MAGIC_LOGS, "magic");
        addBOPLogVariants(TagManager.Items.BOP_MAHOGANY_LOGS, "mahogany");
        addBOPLogVariants(TagManager.Items.BOP_PALM_LOGS, "palm");
        addBOPLogVariants(TagManager.Items.BOP_REDWOOD_LOGS, "redwood");
        addBOPLogVariants(TagManager.Items.BOP_UMBRAN_LOGS, "umbran");
        addBOPLogVariants(TagManager.Items.BOP_WILLOW_LOGS, "willow");

        // Botania
        addBotaniaLogVariants(TagManager.Items.BOTANIA_DREAMWOOD_LOGS, "dreamwood");
        addBotaniaLogVariants(TagManager.Items.BOTANIA_LIVINGWOOD_LOGS, "livingwood");

        // Quark
        addQuarkLogVariants(TagManager.Items.QUARK_AZALEA_LOGS, "azalea");
        addQuarkLogVariants(TagManager.Items.QUARK_BLOSSOM_LOGS, "blossom");

        // All You Can Eat
        addAyceLogVariants(TagManager.Items.AYCE_HAZEL_LOGS, "hazel");

        // Tinkers' Construct
        addTconLogVariants(TagManager.Items.TCON_BLOODSHROOM_LOGS, "bloodshroom");
        addTconLogVariants(TagManager.Items.TCON_GREENHEART_LOGS, "greenheart");
        addTconLogVariants(TagManager.Items.TCON_SKYROOT_LOGS, "skyroot");

        // Water Source
        addWsLogVariants(TagManager.Items.WS_PALM_TREE_LOGS, "palm_tree");

        // Undergarden
        addUndergardenLogVariants(TagManager.Items.UNDERGARDEN_GRONGLE_LOGS, "grongle");
        addUndergardenLogVariants(TagManager.Items.UNDERGARDEN_SMOGSTEM_LOGS, "smogstem");
        addUndergardenLogVariants(TagManager.Items.UNDERGARDEN_WIGGLEWOOD_LOGS, "wigglewood");

        // BYG
        addBygLogVariants(TagManager.Items.BYG_ETHER_LOGS, "ether");
        addBygLogVariants(TagManager.Items.BYG_MANGROVE_LOGS, "mangrove");
        addBygLogVariants(TagManager.Items.BYG_REDWOOD_LOGS, "redwood");
        addBygLogVariants(TagManager.Items.BYG_BLUE_ENCHANTED_LOGS, "blue_enchanted");
        addBygLogVariants(TagManager.Items.BYG_GREEN_ENCHANTED_LOGS, "green_enchanted");
        addBygLogVariants(TagManager.Items.BYG_LAMENT_LOGS, "lament");
        addBygLogVariants(TagManager.Items.BYG_WITHERING_OAK_LOGS, "withering_oak");
        addBygLogVariants(TagManager.Items.BYG_MAHOGANY_LOGS, "mahogany");
        addBygLogVariants(TagManager.Items.BYG_CHERRY_LOGS, "cherry");
        addBygLogVariants(TagManager.Items.BYG_PALO_VERDE_LOGS, "palo_verde");
        addBygLogVariants(TagManager.Items.BYG_BAOBAB_LOGS, "baobab");
        addBygLogVariants(TagManager.Items.BYG_JACARANDA_LOGS, "jacaranda");
        addBygLogVariants(TagManager.Items.BYG_CYPRESS_LOGS, "cypress");
        addBygLogVariants(TagManager.Items.BYG_PALM_LOGS, "palm");
        addBygLogVariants(TagManager.Items.BYG_EBONY_LOGS, "ebony");
        addBygLogVariants(TagManager.Items.BYG_IMBUED_NIGHTSHADE_LOGS, "imbued_nightshade");
        addBygLogVariants(TagManager.Items.BYG_NIGHTSHADE_LOGS, "nightshade");
        addBygLogVariants(TagManager.Items.BYG_RAINBOW_EUCALYPTUS_LOGS, "rainbow_eucalyptus");
        addBygLogVariants(TagManager.Items.BYG_ASPEN_LOGS, "aspen");
        addBygLogVariants(TagManager.Items.BYG_SKYROOT_LOGS, "skyroot");
        addBygLogVariants(TagManager.Items.BYG_FIR_LOGS, "fir");
        addBygLogVariants(TagManager.Items.BYG_SKYRIS_LOGS, "skyris");
        addBygLogVariants(TagManager.Items.BYG_CIKA_LOGS, "cika");
        addBygLogVariants(TagManager.Items.BYG_HOLLY_LOGS, "holly");
        addBygLogVariants(TagManager.Items.BYG_MAPLE_LOGS, "maple");
        addBygLogVariants(TagManager.Items.BYG_PINE_LOGS, "pine");
        addBygLogVariants(TagManager.Items.BYG_WILLOW_LOGS, "willow");
        addBygLogVariants(TagManager.Items.BYG_WITCH_HAZEL_LOGS, "witch_hazel");
        addBygLogVariants(TagManager.Items.BYG_ZELKOVA_LOGS, "zelkova");
        addBygBulbisVariants(TagManager.Items.BYG_BULBIS_STEMS, "bulbis");
        addBygImpariusVariants(TagManager.Items.BYG_IMPARIUS_STEMS, "imparius");
        addBygSythianVariants(TagManager.Items.BYG_SYTHIAN_STEMS, "sythian");
        addBygEmburVariants(TagManager.Items.BYG_EMBUR_PEDU, "embur");

        // Twilight Forest
        getBuilder(TagManager.Items.TF_GIANT_LOGS)
            .addOptional(ModIntegration.tfLoc("giant_log"));

        // Ecologics
        addEcoLogVariants(TagManager.Items.ECO_COCONUT_LOGS, "coconut");
        addEcoLogVariants(TagManager.Items.ECO_WALNUT_LOGS, "walnut");
        addEcoLogVariants(TagManager.Items.ECO_AZALEA_LOGS, "azalea");
        addEcoLogVariants(TagManager.Items.ECO_FLOWERING_AZALEA_LOGS, "flowering_azalea");
    }

    private void addWsLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.wsLoc(type + "_log"))
            .addOptional(ModIntegration.wsLoc("stripped_" + type + "_log"));
    }

    private void addTconLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.tconLoc(type + "_log"))
            .addOptional(ModIntegration.tconLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.tconLoc(type + "_wood"))
            .addOptional(ModIntegration.tconLoc("stripped_" + type + "_wood"));
    }

    private void addAyceLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.ayceLoc(type + "_log"))
            .addOptional(ModIntegration.ayceLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.ayceLoc(type + "_wood"))
            .addOptional(ModIntegration.ayceLoc("stripped_" + type + "_wood"));
    }

    private void addQuarkLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.qLoc(type + "_log"))
            .addOptional(ModIntegration.qLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.qLoc(type + "_wood"))
            .addOptional(ModIntegration.qLoc("stripped_" + type + "_wood"));
    }

    private void addBMOLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.bmoLoc(type + "_log"))
            .addOptional(ModIntegration.bmoLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.bmoLoc(type + "_wood"))
            .addOptional(ModIntegration.bmoLoc("stripped_" + type + "_wood"));
    }

    private void addBOPLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.bopLoc(type + "_log"))
            .addOptional(ModIntegration.bopLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.bopLoc(type + "_wood"))
            .addOptional(ModIntegration.bopLoc("stripped_" + type + "_wood"));
    }

    private void addBotaniaLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.botaniaLoc(type + "_log"))
            .addOptional(ModIntegration.botaniaLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.botaniaLoc(type))
            .addOptional(ModIntegration.botaniaLoc("stripped_" + type));
    }

    private void addFTLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.ftLoc(type + "_log"))
            .addOptional(ModIntegration.ftLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.ftLoc(type + "_wood"))
            .addOptional(ModIntegration.ftLoc("stripped_" + type + "_wood"));
    }

    private void addUndergardenLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.undergardenLoc(type + "_log"))
            .addOptional(ModIntegration.undergardenLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.undergardenLoc(type + "_wood"))
            .addOptional(ModIntegration.undergardenLoc("stripped_" + type + "_wood"));
    }

    private void addBygLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.bygLoc(type + "_log"))
            .addOptional(ModIntegration.bygLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.bygLoc(type + "_wood"))
            .addOptional(ModIntegration.bygLoc("stripped_" + type + "_wood"));
    }

    private void addBygBulbisVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.bygLoc(type + "_stem"))
            .addOptional(ModIntegration.bygLoc("stripped_" + type + "_stem"))
            .addOptional(ModIntegration.bygLoc(type + "_wood"))
            .addOptional(ModIntegration.bygLoc("stripped_" + type + "_wood"));
    }

    private void addBygImpariusVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.bygLoc(type + "_stem"))
            .addOptional(ModIntegration.bygLoc("fungal_" + type + "_stem"))
            .addOptional(ModIntegration.bygLoc(type + "_hyphae"))
            .addOptional(ModIntegration.bygLoc("fungal_" + type + "_hyphae"));
    }

    private void addBygSythianVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.bygLoc(type + "_stem"))
            .addOptional(ModIntegration.bygLoc("stripped_" + type + "_stem"))
            .addOptional(ModIntegration.bygLoc(type + "_hyphae"))
            .addOptional(ModIntegration.bygLoc("stripped_" + type + "_hyphae"));
    }

    private void addBygEmburVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.bygLoc(type + "_pedu"))
            .addOptional(ModIntegration.bygLoc("fungal_" + type + "_pedu"))
            .addOptional(ModIntegration.bygLoc(type + "_hyphae"))
            .addOptional(ModIntegration.bygLoc("fungal_" + type + "_hyphae"));
    }

    private void addEcoLogVariants(TagKey<Item> tag, String type) {
        getBuilder(tag)
            .addOptional(ModIntegration.ecoLoc(type + "_log"))
            .addOptional(ModIntegration.ecoLoc("stripped_" + type + "_log"))
            .addOptional(ModIntegration.ecoLoc(type + "_wood"))
            .addOptional(ModIntegration.ecoLoc("stripped_" + type + "_wood"));
    }

    protected TagsProvider.TagAppender<Item> getBuilder(TagKey<Item> tag) {
        return tag(tag);
    }

    private void builder(TagKey<Item> tag, ItemLike... items) {
        getBuilder(tag).add(Arrays.stream(items).map(ItemLike::asItem).toArray(Item[]::new));
    }

}
