package survivalistessentials.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import net.minecraftforge.registries.RegisterEvent;

import survivalistessentials.common.CreativeTabs;
import survivalistessentials.items.item.Bandage;
import survivalistessentials.items.item.CrudeBandage;
import survivalistessentials.items.item.Mortar;
import survivalistessentials.items.item.SurvivalistEssentialsBook;
import survivalistessentials.items.item.WoodenCup;
import survivalistessentials.items.tool.CrudeHatchet;
import survivalistessentials.items.tool.SurvivalKnife;
import survivalistessentials.items.tool.SurvivalSaw;

public final class SurvivalistEssentialsItems {

    private static RegisterEvent.RegisterHelper<Item> ITEM_REGISTRY;

    // Items
    public static Item FLINT_SHARD;
    public static Item PLANT_FIBER;
    public static Item PLANT_STRING;
    public static Item OINTMENT;
    public static Item PLANT_PASTE;
    public static Item CLOTH;

    // Books
    public static Item BOOK;
    public static Item MODPACK_BOOK;

    // Tools
    public static Item CRUDE_KNIFE;
    public static Item BASIC_KNIFE;
    public static Item SHARP_KNIFE;
    public static Item CRUDE_HATCHET;
    public static Item SAW_HANDLE;
    public static Item CRUDE_SAW_BLADE;
    public static Item BASIC_SAW_BLADE;
    public static Item SHARP_SAW_BLADE;
    public static Item CRUDE_SAW;
    public static Item BASIC_SAW;
    public static Item SHARP_SAW;
    public static Item MORTAR_AND_PESTLE;

    // Bandages
    public static Item CRUDE_BANDAGE;
    public static Item BANDAGE;

    // Zombie Jesus
    public static Item WOODEN_CUP;

    public static void init(RegisterEvent.RegisterHelper<Item> registry) {
        ITEM_REGISTRY = registry;

        FLINT_SHARD = registerItem("flint_shard");
        PLANT_FIBER = registerItem("plant_fiber");
        PLANT_STRING = registerItem("plant_string");
        OINTMENT = registerItem("ointment");
        PLANT_PASTE = registerItem("plant_paste");
        CLOTH = registerItem("cloth");
        BOOK = registerBook("book");
        MODPACK_BOOK = registerBook("modpack_book");

        // Tools
        CRUDE_KNIFE = registerKnifeTool("crude_knife", ItemTiers.FLINT_TIER);
        BASIC_KNIFE = registerKnifeTool("basic_knife", ItemTiers.IRON_TIER);
        SHARP_KNIFE = registerKnifeTool("sharp_knife", ItemTiers.DIAMOND_TIER);
        CRUDE_HATCHET = registerHatchetTool("crude_hatchet", ItemTiers.STONE_TIER);
        SAW_HANDLE = registerSawTool("saw_handle", ItemTiers.NO_TIER, -8.0F);
        CRUDE_SAW_BLADE = registerSawBlade("crude_saw_blade");
        BASIC_SAW_BLADE = registerSawBlade("basic_saw_blade");
        SHARP_SAW_BLADE = registerSawBlade("sharp_saw_blade");
        CRUDE_SAW = registerSawTool("crude_saw", ItemTiers.FLINT_TIER, -4.0F);
        BASIC_SAW = registerSawTool("basic_saw", ItemTiers.IRON_TIER, -4.0F);
        SHARP_SAW = registerSawTool("sharp_saw", ItemTiers.DIAMOND_TIER, -4.0F);
        MORTAR_AND_PESTLE = registerMortar("mortar_and_pestle");

        // Bandages
        CRUDE_BANDAGE = registerItem("crude_bandage", new CrudeBandage(
            (new Item.Properties()).stacksTo(8).tab(CreativeTabs.ITEM_TAB_GROUP)
        ));
        BANDAGE = registerItem("bandage", new Bandage(
            (new Item.Properties()).stacksTo(16).tab(CreativeTabs.ITEM_TAB_GROUP)
        ));

        // Zombie Jesus
        WOODEN_CUP = registerItem("wooden_cup", new WoodenCup(
            (new Item.Properties()).stacksTo(1).tab(CreativeTabs.ITEM_TAB_GROUP)
        ));
    }

    public static Item registerSawBlade(String name) {
        return registerItem(name, new Item(
            new Item.Properties().tab(CreativeTabs.TOOL_TAB_GROUP)
        ));
    }

    private static Item registerItem(String name) {
        Item item = new Item(new Item.Properties().tab(CreativeTabs.ITEM_TAB_GROUP));

        return registerItem(name, item);
    }

    private static Item registerItem(String name, Item item) {
        ITEM_REGISTRY.register(name, item);

        return item;
    }

    private static Item registerKnifeTool(String name, Tier tier) {
        Item knifeTool = new SurvivalKnife(tier, 1, -1.4F, new Item.Properties().tab(CreativeTabs.TOOL_TAB_GROUP).setNoRepair());

        return registerItem(name, knifeTool);
    }

    private static Item registerHatchetTool(String name, Tier tier) {
        Item hatchetTool = new CrudeHatchet(tier, 4, -3.0F, new Item.Properties().tab(CreativeTabs.TOOL_TAB_GROUP).setNoRepair());

        return registerItem(name, hatchetTool);
    }

    private static Item registerSawTool(String name, Tier tier, float speed) {
        Item sawTool = new SurvivalSaw(name, tier, speed, new Item.Properties().tab(CreativeTabs.TOOL_TAB_GROUP).setNoRepair());

        return registerItem(name, sawTool);
    }

    private static Item registerMortar(String name) {
        return registerItem(name, new Mortar(new Item.Properties().tab(CreativeTabs.TOOL_TAB_GROUP).setNoRepair()));
    }

    public static Item registerBook(String name) {
        return registerItem(name, new SurvivalistEssentialsBook(
                new Item.Properties().tab(CreativeTabs.ITEM_TAB_GROUP),
                name
        ));
    }

}
