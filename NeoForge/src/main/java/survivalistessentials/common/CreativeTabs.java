package survivalistessentials.common;

import java.util.Map;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.neoforged.neoforge.registries.DeferredHolder;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class CreativeTabs extends SurvivalistEssentialsModule {

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> ALL_ITEMS_TAB;

    public static void init() {
        ALL_ITEMS_TAB = registerTab("all_items_tab", SurvivalistEssentialsItems.FLINT_SHARD);
    }

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> registerTab(String name, Item icon) {
         return CREATIVE_MODE_TAB_REGISTRY.register(name, () -> CreativeModeTab.builder().icon(() -> new ItemStack(icon))
                .title(Component.translatable(SurvivalistEssentials.MODID + ".items"))
                .displayItems((features, output) -> {
                    for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsItems.getAllIngredients().entrySet()) {
                        Item item = entry.getValue();

                        output.accept(new ItemStack(item));
                    }
                    for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsWorld.getAllItems().entrySet()) {
                        Item item = entry.getValue();

                        output.accept(new ItemStack(item));
                    }
                }).build());
    }

}
