package survivalistessentials.data.recipes;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.mojang.datafixers.util.Pair;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.ItemLike;

import survivalistessentials.common.TagManager;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class RecipeProviderBase {

    private static final ItemLike rockStone = SurvivalistEssentialsWorld.ROCK_STONE;
    private static final ItemLike flintShard = SurvivalistEssentialsItems.FLINT_SHARD;
    private static final ItemLike plantFiber = SurvivalistEssentialsItems.PLANT_FIBER;
    private static final ItemLike plantString = SurvivalistEssentialsItems.PLANT_STRING;
    private static final ItemLike mortar = SurvivalistEssentialsItems.MORTAR_AND_PESTLE;
    private static final ItemLike plantPaste = SurvivalistEssentialsItems.PLANT_PASTE;
    private static final ItemLike ointment = SurvivalistEssentialsItems.OINTMENT;
    private static final ItemLike cloth = SurvivalistEssentialsItems.CLOTH;
    private static final ItemLike crudeKnife = SurvivalistEssentialsItems.CRUDE_KNIFE;
    private static List<Pair<ShapelessRecipeBuilder, ResourceLocation>> plankRecipes;

    static {
        // Saw Recipes
        // Minecraft
        plankRecipeBuilder(Blocks.OAK_PLANKS, ItemTags.OAK_LOGS, "has_logs");
        plankRecipeBuilder(Blocks.ACACIA_PLANKS, ItemTags.ACACIA_LOGS, "has_log");
        plankRecipeBuilder(Blocks.BIRCH_PLANKS, ItemTags.BIRCH_LOGS, "has_logs");
        plankRecipeBuilder(Blocks.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS, "has_log");
        plankRecipeBuilder(Blocks.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS, "has_logs");
        plankRecipeBuilder(Blocks.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS, "has_logs");
        plankRecipeBuilder(Blocks.WARPED_PLANKS, ItemTags.WARPED_STEMS, "has_logs");
        plankRecipeBuilder(Blocks.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS, "has_logs");
        plankRecipeBuilder(Blocks.MANGROVE_PLANKS, ItemTags.MANGROVE_LOGS, "has_logs");
        plankRecipeBuilder(Blocks.CHERRY_PLANKS, ItemTags.CHERRY_LOGS, "has_logs");
        bambooRecipeBuilder(Blocks.BAMBOO_PLANKS, ItemTags.BAMBOO_BLOCKS, "has_bamboo_block");

        // Fruit Trees
        plankRecipeBuilder(ModIntegration.CHERRY_PLANKS, TagManager.Items.CHERRY_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.CITRUS_PLANKS, TagManager.Items.CITRUS_LOGS, "has_logs");

        // Biome Makeover
        itemPlankRecipeBuilder(ModIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_ANCIENT_OAK_LOG, "has_logs", "wood/ancient_oak/", "ancient_oak_planks", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_LOG, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_ANCIENT_OAK_WOOD, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_wood", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_ANCIENT_OAK_PLANKS, TagManager.Items.BMO_STRIPPED_ANCIENT_OAK_WOOD, "has_logs", "wood/ancient_oak/", "ancient_oak_planks_wood_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_BLIGHTED_BALSA_LOG, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_LOG, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_BLIGHTED_BALSA_WOOD, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_wood", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_BLIGHTED_BALSA_PLANKS, TagManager.Items.BMO_STRIPPED_BLIGHTED_BALSA_WOOD, "has_logs", "wood/blighted_balsa/", "blighted_balsa_planks_wood_stipped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_SWAMP_CYPRESS_LOG, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_LOG, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_SWAMP_CYPRESS_WOOD, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_wood", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_SWAMP_CYPRESS_PLANKS, TagManager.Items.BMO_STRIPPED_SWAMP_CYPRESS_WOOD, "has_logs", "wood/swamp_cypress/", "swamp_cypress_planks_wood_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_WILLOW_LOG, "has_logs", "wood/willow/", "willow_planks", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_STRIPPED_WILLOW_LOG, "has_logs", "wood/willow/", "willow_planks_stripped", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_WILLOW_WOOD, "has_logs", "wood/willow/", "willow_planks_wood", ModIntegration.BMO_MODID);
        itemPlankRecipeBuilder(ModIntegration.BMO_WILLOW_PLANKS, TagManager.Items.BMO_STRIPPED_WILLOW_WOOD, "has_logs", "wood/willow/", "willow_planks_wood_stripped", ModIntegration.BMO_MODID);

        //Biomes O' Plenty
        plankRecipeBuilder(ModIntegration.BOP_DEAD_PLANKS, TagManager.Items.BOP_DEAD_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_FIR_PLANKS, TagManager.Items.BOP_FIR_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_HELLBARK_PLANKS, TagManager.Items.BOP_HELLBARK_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_JACARANDA_PLANKS, TagManager.Items.BOP_JACARANDA_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_MAGIC_PLANKS, TagManager.Items.BOP_MAGIC_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_MAHOGANY_PLANKS, TagManager.Items.BOP_MAHOGANY_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_PALM_PLANKS, TagManager.Items.BOP_PALM_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_REDWOOD_PLANKS, TagManager.Items.BOP_REDWOOD_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_UMBRAN_PLANKS, TagManager.Items.BOP_UMBRAN_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOP_WILLOW_PLANKS, TagManager.Items.BOP_WILLOW_LOGS, "has_logs");

        //Quark
        plankRecipeBuilder(ModIntegration.QUARK_AZALEA_PLANKS, TagManager.Items.QUARK_AZALEA_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.QUARK_BLOSSOM_PLANKS, TagManager.Items.QUARK_BLOSSOM_LOGS, "has_logs");

        //All You Can Eat
        plankRecipeBuilder(ModIntegration.AYCE_HAZEL_PLANKS, TagManager.Items.AYCE_HAZEL_LOGS, "has_logs");

        // Tinkers' Construct
        plankRecipeBuilder(ModIntegration.TCON_BLOODSHROOM_PLANKS, TagManager.Items.TCON_BLOODSHROOM_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.TCON_GREENHEART_PLANKS, TagManager.Items.TCON_GREENHEART_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.TCON_SKYROOT_PLANKS, TagManager.Items.TCON_SKYROOT_LOGS, "has_logs");

        // Water Source
        plankRecipeBuilder(ModIntegration.WS_PALM_TREE_PLANKS, TagManager.Items.WS_PALM_TREE_LOGS, "has_logs");

        // Botania
        plankRecipeBuilder(ModIntegration.BOTANIA_DREAMWOOD_PLANKS, TagManager.Items.BOTANIA_DREAMWOOD_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BOTANIA_LIVINGWOOD_PLANKS, TagManager.Items.BOTANIA_LIVINGWOOD_LOGS, "has_logs");

        // Ars Nouveau
        plankRecipeBuilder(ModIntegration.AN_ARCHWOOD_PLANKS, TagManager.Items.ARS_ARCHWOOD, "has_logs");

        // Undergarden
        plankRecipeBuilder(ModIntegration.UNDERGARDEN_GRONGLE_PLANKS, TagManager.Items.UNDERGARDEN_GRONGLE_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.UNDERGARDEN_SMOGSTEM_PLANKS, TagManager.Items.UNDERGARDEN_SMOGSTEM_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.UNDERGARDEN_WIGGLEWOOD_PLANKS, TagManager.Items.UNDERGARDEN_WIGGLEWOOD_LOGS, "has_logs");

        // BYG
        plankRecipeBuilder(ModIntegration.BYG_ETHER_PLANKS, TagManager.Items.BYG_ETHER_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_WHITE_MANGROVE_PLANKS, TagManager.Items.BYG_WHITE_MANGROVE_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_REDWOOD_PLANKS, TagManager.Items.BYG_REDWOOD_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_BLUE_ENCHANTED_PLANKS, TagManager.Items.BYG_BLUE_ENCHANTED_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_GREEN_ENCHANTED_PLANKS, TagManager.Items.BYG_GREEN_ENCHANTED_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_LAMENT_PLANKS, TagManager.Items.BYG_LAMENT_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_MAHOGANY_PLANKS, TagManager.Items.BYG_MAHOGANY_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_CHERRY_PLANKS, TagManager.Items.BYG_CHERRY_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_BAOBAB_PLANKS, TagManager.Items.BYG_BAOBAB_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_JACARANDA_PLANKS, TagManager.Items.BYG_JACARANDA_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_CYPRESS_PLANKS, TagManager.Items.BYG_CYPRESS_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_PALM_PLANKS, TagManager.Items.BYG_PALM_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_EBONY_PLANKS, TagManager.Items.BYG_EBONY_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_NIGHTSHADE_PLANKS, TagManager.Items.BYG_NIGHTSHADE_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_RAINBOW_EUCALYPTUS_PLANKS, TagManager.Items.BYG_RAINBOW_EUCALYPTUS_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_ASPEN_PLANKS, TagManager.Items.BYG_ASPEN_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_FIR_PLANKS, TagManager.Items.BYG_FIR_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_SKYRIS_PLANKS, TagManager.Items.BYG_SKYRIS_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_CIKA_PLANKS, TagManager.Items.BYG_CIKA_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_HOLLY_PLANKS, TagManager.Items.BYG_HOLLY_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_MAPLE_PLANKS, TagManager.Items.BYG_MAPLE_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_PINE_PLANKS, TagManager.Items.BYG_PINE_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_WILLOW_PLANKS, TagManager.Items.BYG_WILLOW_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_WITCH_HAZEL_PLANKS, TagManager.Items.BYG_WITCH_HAZEL_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_ZELKOVA_PLANKS, TagManager.Items.BYG_ZELKOVA_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_EMBUR_PLANKS, TagManager.Items.BYG_EMBUR_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_SYTHIAN_PLANKS, TagManager.Items.BYG_SYTHIAN_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_IMPARIUS_PLANKS, TagManager.Items.BYG_IMPARIUS_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.BYG_BULBIS_PLANKS, TagManager.Items.BYG_BULBIS_LOGS, "has_logs");

        // Twilight Forest
        itemPlankRecipeBuilder(ModIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_LOG, "has_logs", "wood/", "canopy_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_STRIPPED_LOG, "has_logs", "wood/", "canopy_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_WOOD, "has_logs", "wood/", "canopy_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_CANOPY_PLANKS, TagManager.Items.TF_CANOPY_STRIPPED_WOOD, "has_logs", "wood/", "canopy_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_LOG, "has_logs", "wood/", "darkwood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_STRIPPED_LOG, "has_logs", "wood/", "darkwood_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_WOOD, "has_logs", "wood/", "darkwood_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_DARK_PLANKS, TagManager.Items.TF_DARK_STRIPPED_WOOD, "has_logs", "wood/", "darkwood_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_LOG, "has_logs", "wood/", "mangrove_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_STRIPPED_LOG, "has_logs", "wood/", "mangrove_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_WOOD, "has_logs", "wood/", "mangrove_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_MANGROVE_PLANKS, TagManager.Items.TF_MANGROVE_STRIPPED_WOOD, "has_logs", "wood/", "mangrove_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_LOG, "has_logs", "wood/", "mining_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_STRIPPED_LOG, "has_logs", "wood/", "mining_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_WOOD, "has_logs", "wood/", "mining_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_MINING_PLANKS, TagManager.Items.TF_MINING_STRIPPED_WOOD, "has_logs", "wood/", "mining_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_LOG, "has_logs", "wood/", "sorting_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_STRIPPED_LOG, "has_logs", "wood/", "sorting_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_WOOD, "has_logs", "wood/", "sorting_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_SORTING_PLANKS, TagManager.Items.TF_SORTING_STRIPPED_WOOD, "has_logs", "wood/", "sorting_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_LOG, "has_logs", "wood/", "time_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_STRIPPED_LOG, "has_logs", "wood/", "time_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_WOOD, "has_logs", "wood/", "time_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TIME_PLANKS, TagManager.Items.TF_TIME_STRIPPED_WOOD, "has_logs", "wood/", "time_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_LOG, "has_logs", "wood/", "transformation_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_STRIPPED_LOG, "has_logs", "wood/", "transformation_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_WOOD, "has_logs", "wood/", "transformation_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TRANSFORMATION_PLANKS, TagManager.Items.TF_TRANSFORMATION_STRIPPED_WOOD, "has_logs", "wood/", "transformation_from_stripped_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_LOG, "has_logs", "wood/", "twilight_oak_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_LOG, "has_logs", "wood/", "twilight_oak_from_stripped_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_WOOD, "has_logs", "wood/", "twilight_oak_from_wood_planks", ModIntegration.TF_MODID);
        itemPlankRecipeBuilder(ModIntegration.TF_TWILIGHT_OAK_PLANKS, TagManager.Items.TF_TWILIGHT_OAK_STRIPPED_WOOD, "has_logs", "wood/", "twilight_oak_from_stripped_wood_planks", ModIntegration.TF_MODID);

        // Ecologics
        plankRecipeBuilder(ModIntegration.ECO_COCONUT_PLANKS, TagManager.Items.ECO_COCONUT_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.ECO_WALNUT_PLANKS, TagManager.Items.ECO_WALNUT_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.ECO_AZALEA_PLANKS, TagManager.Items.ECO_AZALEA_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.ECO_FLOWERING_AZALEA_PLANKS, TagManager.Items.ECO_FLOWERING_AZALEA_LOGS, "has_logs");

        // Malum
        plankRecipeBuilder(ModIntegration.MALUM_RUNEWOOD_PLANKS, TagManager.Items.MALUM_RUNEWOOD_LOGS, "has_logs");
        plankRecipeBuilder(ModIntegration.MALUM_SOULWOOD_PLANKS, TagManager.Items.MALUM_SOULWOOD_LOGS, "has_logs");

        // Ice and Fire; Dragons
        plankRecipeBuilder(ModIntegration.IFD_DREADWOOD_PLANKS, TagManager.Items.IFD_DREADWOOD_LOGS, "has_logs");
    }

    // Material Recipes
    protected static Pair<ShapedRecipeBuilder, ResourceLocation> cobblestoneFromRocks() {
        return Pair.of(
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLESTONE)
                .define('R', rockStone)
                .pattern("RR")
                .pattern("RR")
                .unlockedBy("has_loose_rock", has(rockStone)),
            new ResourceLocation(SurvivalistEssentials.MODID, "cobblestone_from_rocks")
        );
    }

    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> rocksFromCobblestone() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, rockStone, 4)
                .requires(Blocks.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE)),
            new ResourceLocation(SurvivalistEssentials.MODID, "rocks_from_cobblestone")
        );
    }

    protected static ShapelessRecipeBuilder sticksFromPlanks() {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STICK, 2)
                .requires(ItemTags.PLANKS)
                .requires(TagManager.Items.SAW_TOOLS)
                .group("sticks")
                .unlockedBy("has_planks", has(ItemTags.PLANKS));
    }

    protected static Pair<ShapedRecipeBuilder, ResourceLocation> flintFromShards() {
        return Pair.of(
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.FLINT)
                .define('S', flintShard)
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_flint_shard", has(flintShard)),
            new ResourceLocation(SurvivalistEssentials.MODID, "flint_from_shards")
        );
    }

    protected static ShapedRecipeBuilder plantString() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, plantString)
            .define('F', plantFiber)
            .pattern("FF")
            .pattern("F ")
            .unlockedBy("has_plant_fiber", has(plantFiber));
    }

    protected static ShapedRecipeBuilder plantPaste() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, plantPaste)
            .define('F', plantFiber)
            .define('U', mortar)
            .pattern("F")
            .pattern("U")
            .unlockedBy("has_plant_fiber", has(plantFiber));
    }

    protected static ShapedRecipeBuilder ointment() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ointment)
            .define('P', plantPaste)
            .pattern("PP")
            .pattern("PP")
            .unlockedBy("has_plant_paste", has(plantPaste));
    }

    protected static ShapedRecipeBuilder cloth() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, cloth)
            .define('S', TagManager.Items.STRING)
            .pattern("SSS")
            .unlockedBy("has_string", has(TagManager.Items.STRING));
    }

    protected static Pair<SimpleCookingRecipeBuilder, ResourceLocation> stringFromPlantString() {
        return Pair.of(
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(plantString), RecipeCategory.MISC, Items.STRING, 0.1F, 50)
                .unlockedBy("has_plant_string", has(plantString)),
            new ResourceLocation(SurvivalistEssentials.MODID, "string_from_plant_string")
        );
    }

    // Saw Blades
    protected static ShapedRecipeBuilder crudeSawBlade() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_SAW_BLADE)
            .define('D', flintShard)
            .define('S', plantString)
            .define('I', Items.STICK)
            .pattern("ID")
            .pattern("SD")
            .unlockedBy("has_plant_string", has(plantString));
    }

    protected static ShapedRecipeBuilder basicSawBlade() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_SAW_BLADE)
            .define('D', Items.IRON_INGOT)
            .define('S', TagManager.Items.STRING)
            .define('I', Items.STICK)
            .pattern("ID")
            .pattern("SD")
            .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT));
    }

    protected static ShapedRecipeBuilder sharpSawBlade() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_SAW_BLADE)
            .define('D', Items.DIAMOND)
            .define('S', TagManager.Items.STRING)
            .define('I', Items.STICK)
            .pattern("ID")
            .pattern("SD")
            .unlockedBy("has_diamond", has(Items.DIAMOND));
    }

    // Tool Recipes
    protected static ShapedRecipeBuilder crudeKnife() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, crudeKnife)
            .define('S', flintShard)
            .define('T', Items.STICK)
            .pattern("S")
            .pattern("T")
            .unlockedBy("has_flint_shard", has(flintShard));
    }

    protected static ShapedRecipeBuilder basicKnife() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_KNIFE)
            .define('I', Items.IRON_INGOT)
            .define('S', Items.STICK)
            .define('X', TagManager.Items.STRING)
            .pattern("IX")
            .pattern(" S")
            .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT));
    }

    protected static ShapedRecipeBuilder sharpKnife() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_KNIFE)
            .define('D', Items.DIAMOND)
            .define('S', Items.STICK)
            .define('X', TagManager.Items.STRING)
            .pattern("DX")
            .pattern(" S")
            .unlockedBy("has_diamond", has(Items.DIAMOND));
    }

    protected static ShapedRecipeBuilder crudeHatchet() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_HATCHET)
            .define('R', rockStone)
            .define('S', plantString)
            .define('I', Items.STICK)
            .pattern("SR")
            .pattern("I ")
            .unlockedBy("has_loose_rock", has(rockStone));
    }

    protected static ShapedRecipeBuilder sawHandle() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.SAW_HANDLE)
            .define('S', plantString)
            .define('I', Items.STICK)
            .pattern("IS")
            .pattern(" I")
            .unlockedBy("has_plant_string", has(plantString));
    }

    protected static ShapedRecipeBuilder crudeSaw() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.CRUDE_SAW)
            .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
            .define('B', SurvivalistEssentialsItems.CRUDE_SAW_BLADE)
            .define('S', plantString)
            .pattern("BS")
            .pattern(" H")
            .unlockedBy("has_crude_saw_handle", has(SurvivalistEssentialsItems.SAW_HANDLE));
    }

    protected static ShapedRecipeBuilder basicSaw() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.BASIC_SAW)
            .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
            .define('B', SurvivalistEssentialsItems.BASIC_SAW_BLADE)
            .define('S', TagManager.Items.STRING)
            .pattern("BS")
            .pattern(" H")
            .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT));
    }

    protected static ShapedRecipeBuilder sharpSaw() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SurvivalistEssentialsItems.SHARP_SAW)
            .define('H', SurvivalistEssentialsItems.SAW_HANDLE)
            .define('B', SurvivalistEssentialsItems.SHARP_SAW_BLADE)
            .define('S', TagManager.Items.STRING)
            .pattern("BS")
            .pattern(" H")
            .unlockedBy("has_diamond", has(Items.DIAMOND));
    }

    protected static ShapedRecipeBuilder mortar() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, mortar)
            .define('I', Items.STICK)
            .define('P', ItemTags.PLANKS)
            .define('R', rockStone)
            .pattern("  I")
            .pattern("PRP")
            .pattern(" P ")
            .unlockedBy("has_plant_fiber", has(plantFiber));
    }

    // Knife Recipes
    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> stickFronSapling() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STICK)
                .requires(ItemTags.SAPLINGS)
                .requires(TagManager.Items.KNIFE_TOOLS)
                .group("sticks")
                .unlockedBy("has_sapling", has(ItemTags.SAPLINGS)),
            new ResourceLocation(SurvivalistEssentials.MODID, "stick_from_sapling")
        );
    }

    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> stringFromWool() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STRING, 2)
                .requires(ItemTags.WOOL)
                .requires(crudeKnife)
                .group("string")
                .unlockedBy("has_wool", has(ItemTags.WOOL)),
            new ResourceLocation(SurvivalistEssentials.MODID, "string_from_wool")
        );
    }

    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> stringFromWoolAdvanced() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STRING, 4)
                .requires(ItemTags.WOOL)
                .requires(TagManager.Items.ADVANCED_KNIFE_TOOLS)
                .group("string")
                .unlockedBy("has_wool", has(ItemTags.WOOL)),
            new ResourceLocation(SurvivalistEssentials.MODID, "string_from_wool_advanced")
        );
    }

    protected static ShapelessRecipeBuilder flintShards() {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, flintShard, 2)
            .requires(TagManager.Items.FLINT_KNAPPABLE)
            .requires(TagManager.Items.KNIFE_TOOLS)
            .group("flint_shards")
            .unlockedBy("has_crude_knife", has(crudeKnife));
    }

    //Bandages
    protected static ShapedRecipeBuilder crudeBandage() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SurvivalistEssentialsItems.CRUDE_BANDAGE)
            .define('P', plantString)
            .define('S', Items.STICK)
            .define('F', plantFiber)
            .pattern("SF")
            .pattern("PF")
            .unlockedBy("has_plant_string", has(plantString));
    }

    protected static ShapedRecipeBuilder bandage() {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SurvivalistEssentialsItems.BANDAGE)
            .define('P', plantString)
            .define('S', Items.STICK)
            .define('C', cloth)
            .define('O', ointment)
            .pattern("SC")
            .pattern("PO")
            .unlockedBy("has_ointment", has(ointment));
    }

    // Leather from Smoking
    protected static Pair<SimpleCookingRecipeBuilder, ResourceLocation> leatherFromCookedMeatSmoking() {
        return Pair.of(
            SimpleCookingRecipeBuilder.smoking(Ingredient.of(TagManager.Items.COOKED_MEAT), RecipeCategory.FOOD, Items.LEATHER, 0.35F, 100)
                .unlockedBy("has_cooked_meat", has(TagManager.Items.COOKED_MEAT)),
            new ResourceLocation(SurvivalistEssentials.MODID, "leather_from_cooked_meat_smoking")
        );
    }

    // Book
    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> bookFromDirt() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SurvivalistEssentialsItems.BOOK)
                .requires(Items.DIRT)
                .requires(Items.DIRT)
                .group("books")
                .unlockedBy("has_loose_rock", has(rockStone)),
             new ResourceLocation(SurvivalistEssentials.MODID, "book_from_dirt")
        );
    }

    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> dirtFromBook() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.DIRT, 2)
                .requires(SurvivalistEssentialsItems.BOOK)
                .group("books")
                .unlockedBy("has_intro_book",has(SurvivalistEssentialsItems.BOOK)),
            new ResourceLocation(SurvivalistEssentials.MODID, "dirt_from_book")
        );
        //save(wrapped,
    }

    // Twighlight Forest
    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> giantLogToOakPlanks() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS,Items.OAK_PLANKS,64)
                .requires(TagManager.Items.TF_GIANT_LOGS)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy("has_advanced_saw",has(TagManager.Items.ADVANCED_SAW_TOOLS)),
            new ResourceLocation(ModIntegration.TF_MODID, "giant_log_to_oak_planks")
        );
    }

    // Aquaculture
    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> planksFromDriftwood() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 2)
                .requires(ModIntegration.AQUA_DRIFTWOOD)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("planks")
                .unlockedBy("has_driftwood", has(ModIntegration.AQUA_DRIFTWOOD)),
            new ResourceLocation(ModIntegration.AQUA_MODID, "planks_from_driftwood")
        );
    }

    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> planksFromDriftwoodAdvanced() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 4)
                .requires(ModIntegration.AQUA_DRIFTWOOD)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy("has_driftwood", has(ModIntegration.AQUA_DRIFTWOOD)),
            new ResourceLocation(SurvivalistEssentials.MODID, "planks_from_driftwood")
        );
    }

    // Immersive Engineering
    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> treatedSticks() {
        return Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModIntegration.IE_STICK_TREATED, 2)
                .requires(TagManager.Items.IE_TREATED_WOOD)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("treated_sticks")
                .unlockedBy("has_treated_planks", has(TagManager.Items.IE_TREATED_WOOD)),
            new ResourceLocation(SurvivalistEssentials.MODID, "stick_treated")
        );
    }

    protected static Pair<ShapelessRecipeBuilder, ResourceLocation> treatedSticksAdvanced() {
        return Pair.of(ShapelessRecipeBuilder.shapeless(
            RecipeCategory.MISC, ModIntegration.IE_STICK_TREATED, 4)
                .requires(TagManager.Items.IE_TREATED_WOOD)
                .requires(TagManager.Items.IE_TREATED_WOOD)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("treated_sticks")
                .unlockedBy("has_treated_planks", has(TagManager.Items.IE_TREATED_WOOD)),
            new ResourceLocation(ModIntegration.IE_MODID, "crafting/stick_treated")
        );
    }

    private static void plankRecipeBuilder(ItemLike item, TagKey<Item> itemTag, String label) {
        ResourceLocation itemLoc = BuiltInRegistries.ITEM.getKey(item.asItem());
        String name = itemLoc.getPath();
        String modid = itemLoc.getNamespace();
        ResourceLocation outputLocation = getResourceLocation(modid, name);

        plankRecipes.add(Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 2)
                .requires(itemTag)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("planks")
                .unlockedBy(label, has(itemTag)),
            outputLocation
        ));

        plankRecipes.add(Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 4)
                .requires(itemTag)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy(label, has(TagManager.Items.ADVANCED_SAW_TOOLS)),
            new ResourceLocation(SurvivalistEssentials.MODID, modid + "_" + name)
        ));
    }

    private static @Nullable ResourceLocation getResourceLocation(String modid, String name) {
        ResourceLocation outputLocation = null;

        if (modid.contains(ModIntegration.TCON_MODID)) {
            outputLocation = new ResourceLocation(ModIntegration.TCON_MODID, "world/wood/" + name.split("_")[0] + "/planks");
        } else if (modid.contains(ModIntegration.QUARK_MODID)) {
            outputLocation = new ResourceLocation(ModIntegration.QUARK_MODID, "world/crafting/woodsets/" + name.split("_")[0] + "/planks");
        } else if (modid.contains(ModIntegration.MALUM_MODID)) {
            outputLocation = new ResourceLocation(ModIntegration.MALUM_MODID, name);
        } else if (modid.contains(ModIntegration.IFD_MODID)) {
            outputLocation = new ResourceLocation(ModIntegration.IFD_MODID, "dread_wood_planks");
        }

        return outputLocation;
    }

    private static void itemPlankRecipeBuilder (ItemLike output, TagKey<Item> input, String label, String path, String name, String modid) {
        plankRecipes.add(Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 2)
                .requires(input)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("planks")
                .unlockedBy(label, has(input)),
            new ResourceLocation(modid, path + name)
        ));

        plankRecipes.add(Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .requires(input)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy(label, has(TagManager.Items.ADVANCED_SAW_TOOLS)),
            new ResourceLocation(SurvivalistEssentials.MODID, modid + "_" + name)
        ));
    }

    private static void bambooRecipeBuilder(ItemLike item, TagKey<Item> itemTag, String label) {
        plankRecipes.add(Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 1)
                .requires(itemTag)
                .requires(SurvivalistEssentialsItems.CRUDE_SAW)
                .group("planks")
                .unlockedBy(label, has(itemTag)),
            null
        ));

        plankRecipes.add(Pair.of(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 2)
                .requires(itemTag)
                .requires(TagManager.Items.ADVANCED_SAW_TOOLS)
                .group("planks")
                .unlockedBy(label, has(TagManager.Items.ADVANCED_SAW_TOOLS)),
            null
        ));
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> has (TagKey < Item > pTag) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pTag).build());
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> has (ItemLike pItemLike){
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).build());
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger (ItemPredicate...predicates){
        return CriteriaTriggers.INVENTORY_CHANGED.createCriterion(
                new InventoryChangeTrigger.TriggerInstance(
                        Optional.empty(),
                        InventoryChangeTrigger.TriggerInstance.Slots.ANY,
                        List.of(predicates)
                )
        );
    }

    protected static List<Pair<ShapelessRecipeBuilder, ResourceLocation>> getPlankRecipes() {
        return plankRecipes;
    }

}
