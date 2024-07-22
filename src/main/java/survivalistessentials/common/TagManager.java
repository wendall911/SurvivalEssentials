package survivalistessentials.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static survivalistessentials.SurvivalistEssentials.loc;
import static survivalistessentials.SurvivalistEssentials.prefix;

public final class TagManager {

    public static final class Items {
        public static final TagKey<Item> FLINT_KNAPPABLE = create("flint_knappable");
        public static final TagKey<Item> PICKAXE_TOOLS = create("pickaxe_tools");
        public static final TagKey<Item> AXE_TOOLS = create("axe_tools");
        public static final TagKey<Item> SAW_TOOLS = create("saw_tools");
        public static final TagKey<Item> ADVANCED_SAW_TOOLS = create("advanced_saw_tools");
        public static final TagKey<Item> SHOVEL_TOOLS = create("shovel_tools");
        public static final TagKey<Item> HOE_TOOLS = create("hoe_tools");
        public static final TagKey<Item> KNIFE_TOOLS = create("knife_tools");
        public static final TagKey<Item> ADVANCED_KNIFE_TOOLS = create("advanced_knife_tools");
        public static final TagKey<Item> SHARP_TOOLS = create("sharp_tools");
        public static final TagKey<Item> SHEAR_TOOLS = create("shear_tools");
        public static final TagKey<Item> ROCK = create("rock");
        public static final TagKey<Item> SAW_PARTS = create("saw_parts");
        public static final TagKey<Item> BANDAGES = create("bandages");
        public static final TagKey<Item> COOKED_MEAT = create("cooked_meat");

        // Mod Integration
        // Fruit Trees
        public static final TagKey<Item> CHERRY_LOGS = create("cherry_logs");
        public static final TagKey<Item> CITRUS_LOGS = create("citrus_logs");

        // Biome Makeover
        public static final TagKey<Item> BMO_ANCIENT_OAK_LOG = create("ancient_oak_log");
        public static final TagKey<Item> BMO_STRIPPED_ANCIENT_OAK_LOG = create("stripped_ancient_oak_log");
        public static final TagKey<Item> BMO_ANCIENT_OAK_WOOD = create("ancient_oak_wood");
        public static final TagKey<Item> BMO_STRIPPED_ANCIENT_OAK_WOOD = create("stripped_ancient_oak_wood");
        public static final TagKey<Item> BMO_BLIGHTED_BALSA_LOG = create("blighted_balsa_log");
        public static final TagKey<Item> BMO_STRIPPED_BLIGHTED_BALSA_LOG = create("stripped_blighted_balsa_log");
        public static final TagKey<Item> BMO_BLIGHTED_BALSA_WOOD = create("blighted_balsa_wood");
        public static final TagKey<Item> BMO_STRIPPED_BLIGHTED_BALSA_WOOD = create("stripped_blighted_balsa_wood");
        public static final TagKey<Item> BMO_SWAMP_CYPRESS_LOG = create("swamp_cypress_log");
        public static final TagKey<Item> BMO_STRIPPED_SWAMP_CYPRESS_LOG = create("stripped_swamp_cypress_log");
        public static final TagKey<Item> BMO_SWAMP_CYPRESS_WOOD = create("swamp_cypress_wood");
        public static final TagKey<Item> BMO_STRIPPED_SWAMP_CYPRESS_WOOD = create("stripped_swamp_cypress_wood");
        public static final TagKey<Item> BMO_WILLOW_LOG = create("willow_log");
        public static final TagKey<Item> BMO_STRIPPED_WILLOW_LOG = create("stripped_willow_log");
        public static final TagKey<Item> BMO_WILLOW_WOOD = create("willow_wood");
        public static final TagKey<Item> BMO_STRIPPED_WILLOW_WOOD = create("stripped_willow_wood");

        // Biomes O' Plenty
        public static final TagKey<Item> BOP_DEAD_LOGS = create("dead_logs");
        public static final TagKey<Item> BOP_FIR_LOGS = create("fir_logs");
        public static final TagKey<Item> BOP_HELLBARK_LOGS = create("hellbark_logs");
        public static final TagKey<Item> BOP_JACARANDA_LOGS = create("jacaranda_logs");
        public static final TagKey<Item> BOP_MAGIC_LOGS = create("magic_logs");
        public static final TagKey<Item> BOP_MAHOGANY_LOGS = create("mahogany_logs");
        public static final TagKey<Item> BOP_PALM_LOGS = create("palm_logs");
        public static final TagKey<Item> BOP_REDWOOD_LOGS = create("redwood_logs");
        public static final TagKey<Item> BOP_UMBRAN_LOGS = create("umbran_logs");
        public static final TagKey<Item> BOP_WILLOW_LOGS = create("willow_logs");

        // Quark
        public static final TagKey<Item> QUARK_AZALEA_LOGS = create("azalea_logs");
        public static final TagKey<Item> QUARK_BLOSSOM_LOGS = create("blossom_logs");

        // All You Can Eat
        public static final TagKey<Item> AYCE_HAZEL_LOGS = create("hazel_logs");

        // Tinkers' Construct
        public static final TagKey<Item> TCON_BLOODSHROOM_LOGS = create("bloodshroom_logs");
        public static final TagKey<Item> TCON_GREENHEART_LOGS = create("greenheart_logs");
        public static final TagKey<Item> TCON_SKYROOT_LOGS = create("skyroot_logs");

        // Water Source
        public static final TagKey<Item> WS_PALM_TREE_LOGS = create("palm_tree_logs");

        // Botania
        public static final TagKey<Item> BOTANIA_DREAMWOOD_LOGS = create("dreamwood_logs");
        public static final TagKey<Item> BOTANIA_LIVINGWOOD_LOGS = create("livingwood_logs");

        // IE
        public static final TagKey<Item> IE_TREATED_WOOD = commonTag("treated_wood");

        // Undergarden
        public static final TagKey<Item> UNDERGARDEN_GRONGLE_LOGS = create("grongle_logs");
        public static final TagKey<Item> UNDERGARDEN_SMOGSTEM_LOGS = create("smogstem_logs");
        public static final TagKey<Item> UNDERGARDEN_WIGGLEWOOD_LOGS = create("wigglewood_logs");

        // BYG
        public static final TagKey<Item> BYG_ETHER_LOGS = create("ether_logs");
        public static final TagKey<Item> BYG_WHITE_MANGROVE_LOGS = create("white_mangrove_logs");
        public static final TagKey<Item> BYG_REDWOOD_LOGS = create("redwood_logs");
        public static final TagKey<Item> BYG_BLUE_ENCHANTED_LOGS = create("blue_enchanted_logs");
        public static final TagKey<Item> BYG_GREEN_ENCHANTED_LOGS = create("green_enchanted_logs");
        public static final TagKey<Item> BYG_LAMENT_LOGS = create("lament_logs");
        public static final TagKey<Item> BYG_MAHOGANY_LOGS = create("mahogany_logs");
        public static final TagKey<Item> BYG_CHERRY_LOGS = create("cherry_logs");
        public static final TagKey<Item> BYG_BAOBAB_LOGS = create("baobab_logs");
        public static final TagKey<Item> BYG_JACARANDA_LOGS = create("jacaranda_logs");
        public static final TagKey<Item> BYG_CYPRESS_LOGS = create("cypress_logs");
        public static final TagKey<Item> BYG_PALM_LOGS = create("palm_logs");
        public static final TagKey<Item> BYG_EBONY_LOGS = create("ebony_logs");
        public static final TagKey<Item> BYG_NIGHTSHADE_LOGS = create("nightshade_logs");
        public static final TagKey<Item> BYG_RAINBOW_EUCALYPTUS_LOGS = create("rainbow_eucalyptus_logs");
        public static final TagKey<Item> BYG_ASPEN_LOGS = create("aspen_logs");
        public static final TagKey<Item> BYG_FIR_LOGS = create("fir_logs");
        public static final TagKey<Item> BYG_SKYRIS_LOGS = create("skyris_logs");
        public static final TagKey<Item> BYG_CIKA_LOGS = create("cika_logs");
        public static final TagKey<Item> BYG_HOLLY_LOGS = create("holly_logs");
        public static final TagKey<Item> BYG_MAPLE_LOGS = create("maple_logs");
        public static final TagKey<Item> BYG_PINE_LOGS = create("pine_logs");
        public static final TagKey<Item> BYG_WILLOW_LOGS = create("willow_logs");
        public static final TagKey<Item> BYG_WITCH_HAZEL_LOGS = create("witch_hazel_logs");
        public static final TagKey<Item> BYG_ZELKOVA_LOGS = create("zelkova_logs");
        public static final TagKey<Item> BYG_EMBUR_LOGS = create("embur_logs");
        public static final TagKey<Item> BYG_SYTHIAN_LOGS = create("sythian_logs");
        public static final TagKey<Item> BYG_IMPARIUS_LOGS = create("imparius_logs");
        public static final TagKey<Item> BYG_BULBIS_LOGS = create("bulbis_logs");

        // Twilight Forest
        public static final TagKey<Item> TF_GIANT_LOGS = create("giant_logs");
        public static final TagKey<Item> TF_CANOPY_LOG = create("canopy_log");
        public static final TagKey<Item> TF_CANOPY_STRIPPED_LOG = create("canopy_stripped_log");
        public static final TagKey<Item> TF_CANOPY_WOOD = create("canopy_wood");
        public static final TagKey<Item> TF_CANOPY_STRIPPED_WOOD = create("canopy_stripped_wood");
        public static final TagKey<Item> TF_DARK_LOG = create("dark_log");
        public static final TagKey<Item> TF_DARK_STRIPPED_LOG = create("dark_stripped_log");
        public static final TagKey<Item> TF_DARK_WOOD = create("dark_wood");
        public static final TagKey<Item> TF_DARK_STRIPPED_WOOD = create("dark_stripped_wood");
        public static final TagKey<Item> TF_MANGROVE_LOG = create("mangrove_log");
        public static final TagKey<Item> TF_MANGROVE_STRIPPED_LOG = create("mangrove_stripped_log");
        public static final TagKey<Item> TF_MANGROVE_WOOD = create("mangrove_wood");
        public static final TagKey<Item> TF_MANGROVE_STRIPPED_WOOD = create("mangrove_stripped_wood");
        public static final TagKey<Item> TF_MINING_LOG = create("mining_log");
        public static final TagKey<Item> TF_MINING_STRIPPED_LOG = create("mining_stripped_log");
        public static final TagKey<Item> TF_MINING_WOOD = create("mining_wood");
        public static final TagKey<Item> TF_MINING_STRIPPED_WOOD = create("mining_stripped_wood");
        public static final TagKey<Item> TF_SORTING_LOG = create("sorting_log");
        public static final TagKey<Item> TF_SORTING_STRIPPED_LOG = create("sorting_stripped_log");
        public static final TagKey<Item> TF_SORTING_WOOD = create("sorting_wood");
        public static final TagKey<Item> TF_SORTING_STRIPPED_WOOD = create("sorting_stripped_wood");
        public static final TagKey<Item> TF_TIME_LOG = create("time_log");
        public static final TagKey<Item> TF_TIME_STRIPPED_LOG = create("time_stripped_log");
        public static final TagKey<Item> TF_TIME_WOOD = create("time_wood");
        public static final TagKey<Item> TF_TIME_STRIPPED_WOOD = create("time_stripped_wood");
        public static final TagKey<Item> TF_TRANSFORMATION_LOG = create("transformation_log");
        public static final TagKey<Item> TF_TRANSFORMATION_STRIPPED_LOG = create("transformation_stripped_log");
        public static final TagKey<Item> TF_TRANSFORMATION_WOOD = create("transformation_wood");
        public static final TagKey<Item> TF_TRANSFORMATION_STRIPPED_WOOD = create("transformation_stripped_wood");
        public static final TagKey<Item> TF_TWILIGHT_OAK_LOG = create("twilight_oak_log");
        public static final TagKey<Item> TF_TWILIGHT_OAK_STRIPPED_LOG = create("twilight_oak_stripped_log");
        public static final TagKey<Item> TF_TWILIGHT_OAK_WOOD = create("twilight_oak_wood");
        public static final TagKey<Item> TF_TWILIGHT_OAK_STRIPPED_WOOD = create("twilight_oak_stripped_wood");

        // Ecologics
        public static TagKey<Item> ECO_COCONUT_LOGS = create("coconut_logs");
        public static TagKey<Item> ECO_WALNUT_LOGS = create("walnut_logs");
        public static TagKey<Item> ECO_AZALEA_LOGS = create("azalea_logs");
        public static TagKey<Item> ECO_FLOWERING_AZALEA_LOGS = create("flowering_azalea_logs");

        // Malum
        public static TagKey<Item> MALUM_RUNEWOOD_LOGS = create("runewood_logs");
        public static TagKey<Item> MALUM_SOULWOOD_LOGS = create("soulwood_logs");

        // Ice and Fire; Dragons
        public static TagKey<Item> IFD_DREADWOOD_LOGS = create("dreadwood_logs");

        private static TagKey<Item> create(String id) {
            return TagKey.create(Registries.ITEM, identifier(id));
        }

        private static TagKey<Item> commonTag(String name) {
            return TagKey.create(Registries.ITEM, commonLoc(name));
        }

        private static TagKey<Item> getItemTag(String modid, String name) {
            return TagKey.create(Registries.ITEM, prefix(modid, name));
        }
    }

    public static final class Blocks {
        public static final TagKey<Block> ALWAYS_BREAKABLE = create("always_breakable");
        public static final TagKey<Block> ALWAYS_DROPS = create("always_drops");
        public static final TagKey<Block> LOOSE_ROCK_PLACEABLE_ON = create("loose_rock_placeable_on");
        public static final TagKey<Block> LOOSE_ROCKS = create("loose_rocks");
        public static final TagKey<Block> FIBER_PLANTS = create("fiber_plants");
        public static final TagKey<Block> MINEABLE_WITH_SHARP = create("mineable_with_sharp");

        // Dynamic Trees
        public static final TagKey<Block> BRANCHES = create("branches");

        private static TagKey<Block> create(String id) {
            return TagKey.create(Registries.BLOCK, identifier(id));
        }

        private static TagKey<Block> forgeTag(String name) {
            return TagKey.create(Registries.BLOCK, commonLoc(name));
        }
    }

    public static ResourceLocation identifier(String path) {
        return loc(path);
    }

    public static ResourceLocation commonLoc(String path) {
        return prefix("c", path);
    }

}
