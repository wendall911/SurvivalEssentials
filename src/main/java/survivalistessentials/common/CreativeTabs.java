package survivalistessentials.common;

import java.util.Map;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.registries.RegistryObject;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class CreativeTabs extends SurvivalistEssentialsModule {

    public static RegistryObject<CreativeModeTab> ALL_ITEMS_TAB;

    public static void init() {
        ALL_ITEMS_TAB = registerTab("all_items_tab", SurvivalistEssentialsItems.FLINT_SHARD);
    }

    private static RegistryObject<CreativeModeTab> registerTab(String name, Item icon) {
        return CREATIVE_MODE_TAB_REGISTRY.register(name, () -> CreativeModeTab.builder().icon(() -> new ItemStack(icon))
                .title(Component.translatable(SurvivalistEssentials.MODID + ".items"))
                .displayItems((features, output) -> {
                    for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsItems.getAllIngredients().entrySet()) {
                        Item item = entry.getValue();

                        output.accept(new ItemStack(item));
                    }
                    for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsWorld.getAll().entrySet()) {
                        Item item = entry.getValue();

                        output.accept(new ItemStack(item));
                    }
                }).build());
    }

}
