package survivalistessentials.data.integration;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class ModIntegration {

    private static final Map<ResourceLocation, Item> ALL = new LinkedHashMap<>();

    public static final String AYCE_MODID = "allyoucaneat";
    public static final String BMO_MODID = "biomemakeover";
    public static final String BOP_MODID = "biomesoplenty";
    public static final String BOTANIA_MODID = "botania";
    public static final String FT_MODID = "fruitfulfun";
    public static final String IE_MODID = "immersiveengineering";
    public static final String QUARK_MODID = "quark";
    public static final String SGC_MODID = "sushigocrafting";
    public static final String TCON_MODID = "tconstruct";
    public static final String WS_MODID = "watersource";
    public static final String AN_MODID = "ars_nouveau";
    public static final String EXNIHILO_MODID = "exnihilosequentia";
    public static final String UNDERGARDEN_MODID = "undergarden";
    public static final String DYNAMICTREES_MODID = "dynamictrees";
    public static final String BYG_MODID = "byg";
    public static final String TF_MODID = "twilightforest";
    public static final String ECO_MODID = "ecologics";
    public static final String AQUA_MODID = "aquaculture";
    public static final String BAP_MODID = "betteranimalsplus";
    public static final String ALEX_MODID = "alexsmobs";
    public static final String PATCHOULI_MODID = "patchouli";
    public static final String MALUM_MODID = "malum";
    public static final String IFD_MODID = "iceandfire";
    public static final String TS_MODID = "tinkersurvival";
    public static final String CARRYON_MODID = "carryon";

    public static Item AQUA_DRIFTWOOD;
    public static Item CHERRY_PLANKS;
    public static Item CITRUS_PLANKS;
    public static Item BMO_ANCIENT_OAK_PLANKS;
    public static Item BMO_BLIGHTED_BALSA_PLANKS;
    public static Item BMO_SWAMP_CYPRESS_PLANKS;
    public static Item BMO_WILLOW_PLANKS;
    public static Item BOP_DEAD_PLANKS;
    public static Item BOP_FIR_PLANKS;
    public static Item BOP_HELLBARK_PLANKS;
    public static Item BOP_JACARANDA_PLANKS;
    public static Item BOP_MAGIC_PLANKS;
    public static Item BOP_MAHOGANY_PLANKS;
    public static Item BOP_PALM_PLANKS;
    public static Item BOP_REDWOOD_PLANKS;
    public static Item BOP_UMBRAN_PLANKS;
    public static Item BOP_WILLOW_PLANKS;
    public static Item BOTANIA_DREAMWOOD_PLANKS;
    public static Item BOTANIA_LIVINGWOOD_PLANKS;
    public static Item IE_STICK_TREATED;
    public static Item QUARK_AZALEA_PLANKS;
    public static Item QUARK_BLOSSOM_PLANKS;
    public static Item AYCE_HAZEL_PLANKS;
    public static Item TCON_BLOODSHROOM_PLANKS;
    public static Item TCON_GREENHEART_PLANKS;
    public static Item TCON_SKYROOT_PLANKS;
    public static Item WS_PALM_TREE_PLANKS;
    public static Item AN_ARCHWOOD_PLANKS;
    public static Item UNDERGARDEN_SMOGSTEM_PLANKS;
    public static Item UNDERGARDEN_WIGGLEWOOD_PLANKS;
    public static Item UNDERGARDEN_GRONGLE_PLANKS;
    public static Item BYG_ETHER_PLANKS;
    public static Item BYG_WHITE_MANGROVE_PLANKS;
    public static Item BYG_REDWOOD_PLANKS;
    public static Item BYG_BLUE_ENCHANTED_PLANKS;
    public static Item BYG_GREEN_ENCHANTED_PLANKS;
    public static Item BYG_LAMENT_PLANKS;
    public static Item BYG_MAHOGANY_PLANKS;
    public static Item BYG_CHERRY_PLANKS;
    public static Item BYG_BAOBAB_PLANKS;
    public static Item BYG_JACARANDA_PLANKS;
    public static Item BYG_CYPRESS_PLANKS;
    public static Item BYG_PALM_PLANKS;
    public static Item BYG_EBONY_PLANKS;
    public static Item BYG_NIGHTSHADE_PLANKS;
    public static Item BYG_RAINBOW_EUCALYPTUS_PLANKS;
    public static Item BYG_ASPEN_PLANKS;
    public static Item BYG_FIR_PLANKS;
    public static Item BYG_SKYRIS_PLANKS;
    public static Item BYG_CIKA_PLANKS;
    public static Item BYG_HOLLY_PLANKS;
    public static Item BYG_MAPLE_PLANKS;
    public static Item BYG_PINE_PLANKS;
    public static Item BYG_WILLOW_PLANKS;
    public static Item BYG_WITCH_HAZEL_PLANKS;
    public static Item BYG_ZELKOVA_PLANKS;
    public static Item BYG_EMBUR_PLANKS;
    public static Item BYG_SYTHIAN_PLANKS;
    public static Item BYG_IMPARIUS_PLANKS;
    public static Item BYG_BULBIS_PLANKS;
    public static Item ECO_COCONUT_PLANKS;
    public static Item ECO_WALNUT_PLANKS;
    public static Item ECO_AZALEA_PLANKS;
    public static Item ECO_FLOWERING_AZALEA_PLANKS;
    public static Item TF_CANOPY_PLANKS;
    public static Item TF_DARK_PLANKS;
    public static Item TF_MANGROVE_PLANKS;
    public static Item TF_MINING_PLANKS;
    public static Item TF_SORTING_PLANKS;
    public static Item TF_TIME_PLANKS;
    public static Item TF_TRANSFORMATION_PLANKS;
    public static Item TF_TWILIGHT_OAK_PLANKS;
    public static Item MALUM_RUNEWOOD_PLANKS;
    public static Item MALUM_SOULWOOD_PLANKS;
    public static Item IFD_DREADWOOD_PLANKS;

    // TF Messed up log/wood crap
    public static ResourceLocation TF_CANOPY_LOG;
    public static ResourceLocation TF_CANOPY_STRIPPED_LOG;
    public static ResourceLocation TF_CANOPY_WOOD;
    public static ResourceLocation TF_CANOPY_STRIPPED_WOOD;
    public static ResourceLocation TF_DARK_LOG;
    public static ResourceLocation TF_DARK_STRIPPED_LOG;
    public static ResourceLocation TF_DARK_WOOD;
    public static ResourceLocation TF_DARK_STRIPPED_WOOD;
    public static ResourceLocation TF_MANGROVE_LOG;
    public static ResourceLocation TF_MANGROVE_STRIPPED_LOG;
    public static ResourceLocation TF_MANGROVE_WOOD;
    public static ResourceLocation TF_MANGROVE_STRIPPED_WOOD;
    public static ResourceLocation TF_MINING_LOG;
    public static ResourceLocation TF_MINING_STRIPPED_LOG;
    public static ResourceLocation TF_MINING_WOOD;
    public static ResourceLocation TF_MINING_STRIPPED_WOOD;
    public static ResourceLocation TF_SORTING_LOG;
    public static ResourceLocation TF_SORTING_STRIPPED_LOG;
    public static ResourceLocation TF_SORTING_WOOD;
    public static ResourceLocation TF_SORTING_STRIPPED_WOOD;
    public static ResourceLocation TF_TIME_LOG;
    public static ResourceLocation TF_TIME_STRIPPED_LOG;
    public static ResourceLocation TF_TIME_WOOD;
    public static ResourceLocation TF_TIME_STRIPPED_WOOD;
    public static ResourceLocation TF_TRANSFORMATION_LOG;
    public static ResourceLocation TF_TRANSFORMATION_STRIPPED_LOG;
    public static ResourceLocation TF_TRANSFORMATION_WOOD;
    public static ResourceLocation TF_TRANSFORMATION_STRIPPED_WOOD;
    public static ResourceLocation TF_TWILIGHT_OAK_LOG;
    public static ResourceLocation TF_TWILIGHT_OAK_STRIPPED_LOG;
    public static ResourceLocation TF_TWILIGHT_OAK_WOOD;
    public static ResourceLocation TF_TWILIGHT_OAK_STRIPPED_WOOD;

    // And now Biome Makeover messed up crap
    public static ResourceLocation BMO_ANCIENT_OAK_LOG;
    public static ResourceLocation BMO_STRIPPED_ANCIENT_OAK_LOG;
    public static ResourceLocation BMO_ANCIENT_OAK_WOOD;
    public static ResourceLocation BMO_STRIPPED_ANCIENT_OAK_WOOD;
    public static ResourceLocation BMO_BLIGHTED_BALSA_LOG;
    public static ResourceLocation BMO_STRIPPED_BLIGHTED_BALSA_LOG;
    public static ResourceLocation BMO_BLIGHTED_BALSA_WOOD;
    public static ResourceLocation BMO_STRIPPED_BLIGHTED_BALSA_WOOD;
    public static ResourceLocation BMO_SWAMP_CYPRESS_LOG;
    public static ResourceLocation BMO_STRIPPED_SWAMP_CYPRESS_LOG;
    public static ResourceLocation BMO_SWAMP_CYPRESS_WOOD;
    public static ResourceLocation BMO_STRIPPED_SWAMP_CYPRESS_WOOD;
    public static ResourceLocation BMO_WILLOW_LOG;
    public static ResourceLocation BMO_STRIPPED_WILLOW_LOG;
    public static ResourceLocation BMO_WILLOW_WOOD;
    public static ResourceLocation BMO_STRIPPED_WILLOW_WOOD;

    public static void init(BiConsumer<Item, ResourceLocation> consumer) {
        String dataGen = System.getenv("DATA_GEN");

        if (dataGen != null && dataGen.contains("all")) {
            AQUA_DRIFTWOOD = make(aquaLoc("driftwood"));
            CHERRY_PLANKS = make(ftLoc("cherry_planks"));
            CITRUS_PLANKS = make(ftLoc("citrus_planks"));
            BMO_ANCIENT_OAK_PLANKS = make(bmoLoc("ancient_oak_planks"));
            BMO_BLIGHTED_BALSA_PLANKS = make(bmoLoc("blighted_balsa_planks"));
            BMO_SWAMP_CYPRESS_PLANKS = make(bmoLoc("swamp_cypress_planks"));
            BMO_WILLOW_PLANKS = make(bmoLoc("willow_planks"));
            BOP_DEAD_PLANKS = make(bopLoc("dead_planks"));
            BOP_FIR_PLANKS = make(bopLoc("fir_planks"));
            BOP_HELLBARK_PLANKS = make(bopLoc("hellbark_planks"));
            BOP_JACARANDA_PLANKS = make(bopLoc("jacaranda_planks"));
            BOP_MAGIC_PLANKS = make(bopLoc("magic_planks"));
            BOP_MAHOGANY_PLANKS = make(bopLoc("mahogany_planks"));
            BOP_PALM_PLANKS = make(bopLoc("palm_planks"));
            BOP_REDWOOD_PLANKS = make(bopLoc("redwood_planks"));
            BOP_UMBRAN_PLANKS = make(bopLoc("umbran_planks"));
            BOP_WILLOW_PLANKS = make(bopLoc("willow_planks"));
            BOTANIA_DREAMWOOD_PLANKS = make(botaniaLoc("dreamwood_planks"));
            BOTANIA_LIVINGWOOD_PLANKS = make(botaniaLoc("livingwood_planks"));
            IE_STICK_TREATED = make(ieLoc("stick_treated"));
            QUARK_AZALEA_PLANKS = make(qLoc("azalea_planks"));
            QUARK_BLOSSOM_PLANKS = make(qLoc("blossom_planks"));
            AYCE_HAZEL_PLANKS = make(ayceLoc("hazel_planks"));
            TCON_BLOODSHROOM_PLANKS = make(tconLoc("bloodshroom_planks"));
            TCON_GREENHEART_PLANKS = make(tconLoc("greenheart_planks"));
            TCON_SKYROOT_PLANKS = make(tconLoc("skyroot_planks"));
            WS_PALM_TREE_PLANKS = make(wsLoc("palm_tree_planks"));
            AN_ARCHWOOD_PLANKS = make(anLoc("archwood_planks"));
            UNDERGARDEN_GRONGLE_PLANKS = make(undergardenLoc("grongle_planks"));
            UNDERGARDEN_SMOGSTEM_PLANKS = make(undergardenLoc("smogstem_planks"));
            UNDERGARDEN_WIGGLEWOOD_PLANKS = make(undergardenLoc("wigglewood_planks"));
            BYG_ETHER_PLANKS = make(bygLoc("ether_planks"));
            BYG_WHITE_MANGROVE_PLANKS = make(bygLoc("white_mangrove_planks"));
            BYG_REDWOOD_PLANKS = make(bygLoc("redwood_planks"));
            BYG_BLUE_ENCHANTED_PLANKS = make(bygLoc("blue_enchanted_planks"));
            BYG_GREEN_ENCHANTED_PLANKS = make(bygLoc("green_enchanted_planks"));
            BYG_LAMENT_PLANKS = make(bygLoc("lament_planks"));
            BYG_MAHOGANY_PLANKS = make(bygLoc("mahogany_planks"));
            BYG_CHERRY_PLANKS = make(bygLoc("cherry_planks"));
            BYG_BAOBAB_PLANKS = make(bygLoc("baobab_planks"));
            BYG_JACARANDA_PLANKS = make(bygLoc("jacaranda_planks"));
            BYG_CYPRESS_PLANKS = make(bygLoc("cypress_planks"));
            BYG_PALM_PLANKS = make(bygLoc("palm_planks"));
            BYG_EBONY_PLANKS = make(bygLoc("ebony_planks"));
            BYG_NIGHTSHADE_PLANKS = make(bygLoc("nightshade_planks"));
            BYG_RAINBOW_EUCALYPTUS_PLANKS = make(bygLoc("rainbow_eucalyptus_planks"));
            BYG_ASPEN_PLANKS = make(bygLoc("aspen_planks"));
            BYG_FIR_PLANKS = make(bygLoc("fir_planks"));
            BYG_SKYRIS_PLANKS = make(bygLoc("skyris_planks"));
            BYG_CIKA_PLANKS = make(bygLoc("cika_planks"));
            BYG_HOLLY_PLANKS = make(bygLoc("holly_planks"));
            BYG_MAPLE_PLANKS = make(bygLoc("maple_planks"));
            BYG_PINE_PLANKS = make(bygLoc("pine_planks"));
            BYG_WILLOW_PLANKS = make(bygLoc("willow_planks"));
            BYG_WITCH_HAZEL_PLANKS = make(bygLoc("witch_hazel_planks"));
            BYG_ZELKOVA_PLANKS = make(bygLoc("zelkova_planks"));
            BYG_EMBUR_PLANKS = make(bygLoc("embur_planks"));
            BYG_SYTHIAN_PLANKS = make(bygLoc("sythian_planks"));
            BYG_IMPARIUS_PLANKS = make(bygLoc("imparius_planks"));
            BYG_BULBIS_PLANKS = make(bygLoc("bulbis_planks"));
            TF_CANOPY_PLANKS = make(tfLoc("canopy_planks"));
            TF_DARK_PLANKS = make(tfLoc("dark_planks"));
            TF_MANGROVE_PLANKS = make(tfLoc("mangrove_planks"));
            TF_MINING_PLANKS = make(tfLoc("mining_planks"));
            TF_SORTING_PLANKS = make(tfLoc("sorting_planks"));
            TF_TIME_PLANKS = make(tfLoc("time_planks"));
            TF_TRANSFORMATION_PLANKS = make(tfLoc("transformation_planks"));
            TF_TWILIGHT_OAK_PLANKS = make(tfLoc("twilight_oak_planks"));
            ECO_COCONUT_PLANKS = make(ecoLoc("coconut_planks"));
            ECO_WALNUT_PLANKS = make(ecoLoc("walnut_planks"));
            ECO_AZALEA_PLANKS = make(ecoLoc("azalea_planks"));
            ECO_FLOWERING_AZALEA_PLANKS = make(ecoLoc("flowering_azalea_planks"));

            // TF Messed up log/wood crap
            TF_CANOPY_LOG = tfLoc("canopy_log");
            TF_CANOPY_STRIPPED_LOG = tfLoc("stripped_canopy_log");
            TF_CANOPY_WOOD = tfLoc("canopy_wood");
            TF_CANOPY_STRIPPED_WOOD = tfLoc("stripped_canopy_wood");
            TF_DARK_LOG = tfLoc("dark_log");
            TF_DARK_STRIPPED_LOG = tfLoc("stripped_dark_log");
            TF_DARK_WOOD = tfLoc("dark_wood");
            TF_DARK_STRIPPED_WOOD = tfLoc("stripped_dark_wood");
            TF_MANGROVE_LOG = tfLoc("mangrove_log");
            TF_MANGROVE_STRIPPED_LOG = tfLoc("stripped_mangrove_log");
            TF_MANGROVE_WOOD = tfLoc("mangrove_wood");
            TF_MANGROVE_STRIPPED_WOOD = tfLoc("stripped_mangrove_wood");
            TF_MINING_LOG = tfLoc("mining_log");
            TF_MINING_STRIPPED_LOG = tfLoc("stripped_mining_log");
            TF_MINING_WOOD = tfLoc("mining_wood");
            TF_MINING_STRIPPED_WOOD = tfLoc("stripped_mining_wood");
            TF_SORTING_LOG = tfLoc("sorting_log");
            TF_SORTING_STRIPPED_LOG = tfLoc("stripped_sorting_log");
            TF_SORTING_WOOD = tfLoc("sorting_wood");
            TF_SORTING_STRIPPED_WOOD = tfLoc("stripped_sorting_wood");
            TF_TIME_LOG = tfLoc("time_log");
            TF_TIME_STRIPPED_LOG = tfLoc("stripped_time_log");
            TF_TIME_WOOD = tfLoc("time_wood");
            TF_TIME_STRIPPED_WOOD = tfLoc("stripped_time_wood");
            TF_TRANSFORMATION_LOG = tfLoc("transformation_log");
            TF_TRANSFORMATION_STRIPPED_LOG = tfLoc("stripped_transformation_log");
            TF_TRANSFORMATION_WOOD = tfLoc("transformation_wood");
            TF_TRANSFORMATION_STRIPPED_WOOD = tfLoc("stripped_transformation_wood");
            TF_TWILIGHT_OAK_LOG = tfLoc("twilight_oak_log");
            TF_TWILIGHT_OAK_STRIPPED_LOG = tfLoc("stripped_twilight_oak_log");
            TF_TWILIGHT_OAK_WOOD = tfLoc("twilight_oak_wood");
            TF_TWILIGHT_OAK_STRIPPED_WOOD = tfLoc("stripped_twilight_oak_wood");

            /*
             * And now Biome Makeover is doing stupid things ... tags were
             * added for a reason, this is a pattern with multiloader mods that
             * is just getting stupid. Either quit supporting Fabric loader, or
             * figure this shit out, it is going back to 1.12 stupidity.
             */
            BMO_ANCIENT_OAK_LOG = bmoLoc("ancient_oak_log");
            BMO_STRIPPED_ANCIENT_OAK_LOG = bmoLoc("stripped_ancient_oak_log");
            BMO_ANCIENT_OAK_WOOD = bmoLoc("ancient_oak_wood");
            BMO_STRIPPED_ANCIENT_OAK_WOOD = bmoLoc("stripped_ancient_oak_wood");
            BMO_BLIGHTED_BALSA_LOG = bmoLoc("blighted_balsa_log");
            BMO_STRIPPED_BLIGHTED_BALSA_LOG = bmoLoc("stripped_blighted_balsa_log");
            BMO_BLIGHTED_BALSA_WOOD = bmoLoc("blighted_balsa_wood");
            BMO_STRIPPED_BLIGHTED_BALSA_WOOD = bmoLoc("stripped_blighted_balsa_wood");
            BMO_SWAMP_CYPRESS_LOG = bmoLoc("swamp_cypress_log");
            BMO_STRIPPED_SWAMP_CYPRESS_LOG = bmoLoc("stripped_swamp_cypress_log");
            BMO_SWAMP_CYPRESS_WOOD = bmoLoc("swamp_cypress_wood");
            BMO_STRIPPED_SWAMP_CYPRESS_WOOD = bmoLoc("stripped_swamp_cypress_wood");
            BMO_WILLOW_LOG = bmoLoc("willow_log");
            BMO_STRIPPED_WILLOW_LOG = bmoLoc("stripped_willow_log");
            BMO_WILLOW_WOOD = bmoLoc("willow_wood");
            BMO_STRIPPED_WILLOW_WOOD = bmoLoc("stripped_willow_wood");
            MALUM_RUNEWOOD_PLANKS = make(malumLoc("runewood_planks"));
            MALUM_SOULWOOD_PLANKS = make(malumLoc("soulwood_planks"));
            IFD_DREADWOOD_PLANKS = make(ifdLoc("dreadwood_planks"));
        }

        for (Map.Entry<ResourceLocation, Item> entry : ALL.entrySet()) {
            consumer.accept(entry.getValue(), entry.getKey());
        }
    }

    private static Item make(ResourceLocation loc) {
        Item item = new Item(new Item.Properties());

        ALL.put(loc, item);

        return item;
    }

    public static ResourceLocation bmoLoc(String name) {
        return getLoc(BMO_MODID, name);
    }

    public static ResourceLocation tconLoc(String name) {
        return getLoc(TCON_MODID, name);
    }

    public static ResourceLocation ayceLoc(String name) {
        return getLoc(AYCE_MODID, name);
    }

    public static ResourceLocation qLoc(String name) {
        return getLoc(QUARK_MODID, name);
    }

    public static ResourceLocation bopLoc(String name) {
        return getLoc(BOP_MODID, name);
    }

    public static ResourceLocation botaniaLoc(String name) {
        return getLoc(BOTANIA_MODID, name);
    }

    public static ResourceLocation ftLoc(String name) {
        return getLoc(FT_MODID, name);
    }

    public static ResourceLocation ieLoc(String name) {
        return getLoc(IE_MODID, name);
    }

    public static ResourceLocation sgcLoc(String name) {
        return getLoc(SGC_MODID, name);
    }

    public static ResourceLocation wsLoc(String name) {
        return getLoc(WS_MODID, name);
    }

    public static ResourceLocation anLoc(String name) {
        return getLoc(AN_MODID, name);
    }

    public static ResourceLocation exnihiloLoc(String name) {
        return getLoc(EXNIHILO_MODID, name);
    }

    public static ResourceLocation undergardenLoc(String name) {
        return getLoc(UNDERGARDEN_MODID, name);
    }

    public static ResourceLocation dynamictreesLoc(String name) {
        return getLoc(DYNAMICTREES_MODID, name);
    }

    public static ResourceLocation bygLoc(String name) {
        return getLoc(BYG_MODID, name);
    }

    public static ResourceLocation tfLoc(String name) {
        return getLoc(TF_MODID, name);
    }

    public static ResourceLocation ecoLoc(String name) {
        return getLoc(ECO_MODID, name);
    }

    public static ResourceLocation aquaLoc(String name) {
        return getLoc(AQUA_MODID, name);
    }

    public static ResourceLocation bapLoc(String name) {
        return getLoc(BAP_MODID, name);
    }

    public static ResourceLocation alexLoc(String name) {
        return getLoc(ALEX_MODID, name);
    }

    public static ResourceLocation malumLoc(String name) {
        return getLoc(MALUM_MODID, name);
    }

    public static ResourceLocation ifdLoc(String name) {
        return getLoc(IFD_MODID, name);
    }

    public static ResourceLocation tsLoc(String name) {
        return getLoc(TS_MODID, name);
    }

    private static ResourceLocation getLoc(String modid, String name) {
        return new ResourceLocation(modid, name);
    }

}
