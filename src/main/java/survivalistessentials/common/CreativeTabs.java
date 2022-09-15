package survivalistessentials.common;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import survivalistessentials.client.CreativeTabBase;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class CreativeTabs {

    public static final CreativeTabBase WORLD_TAB_GROUP = new CreativeTabBase(SurvivalistEssentials.MODID + ".world", () -> new ItemStack(SurvivalistEssentialsWorld.ROCK_STONE));
    public static final CreativeTabBase ITEM_TAB_GROUP = new CreativeTabBase(SurvivalistEssentials.MODID + ".items", () -> new ItemStack(SurvivalistEssentialsItems.FLINT_SHARD));
    public static final CreativeTabBase TOOL_TAB_GROUP = new CreativeTabBase(SurvivalistEssentials.MODID + ".tools", () -> new ItemStack(SurvivalistEssentialsItems.CRUDE_HATCHET));
    public static final CreativeModeTab INTEGRATION_TAB_GROUP = new CreativeTabBase(
        SurvivalistEssentials.MODID + ".items", () -> new ItemStack(SurvivalistEssentialsItems.FLINT_SHARD)
    ).setRecipeFolderName("building_blocks");

}
