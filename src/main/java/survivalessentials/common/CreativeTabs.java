package survivalessentials.common;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import survivalessentials.client.CreativeTabBase;
import survivalessentials.items.SurvivalEssentialsItems;
import survivalessentials.SurvivalEssentials;
import survivalessentials.world.SurvivalEssentialsWorld;

public class CreativeTabs {

    public static final CreativeTabBase WORLD_TAB_GROUP = new CreativeTabBase(SurvivalEssentials.MODID + ".world", () -> new ItemStack(SurvivalEssentialsWorld.ROCK_STONE));
    public static final CreativeTabBase ITEM_TAB_GROUP = new CreativeTabBase(SurvivalEssentials.MODID + ".items", () -> new ItemStack(SurvivalEssentialsItems.FLINT_SHARD));
    public static final CreativeTabBase TOOL_TAB_GROUP = new CreativeTabBase(SurvivalEssentials.MODID + ".tools", () -> new ItemStack(SurvivalEssentialsItems.CRUDE_HATCHET));
    public static final CreativeModeTab INTEGRATION_TAB_GROUP = new CreativeTabBase(
        SurvivalEssentials.MODID + ".items", () -> new ItemStack(SurvivalEssentialsItems.FLINT_SHARD)
    ).setRecipeFolderName("building_blocks");

}
