package survivalistessentials.common;

import java.util.Map;
import java.util.function.BiConsumer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.items.SurvivalistEssentialsItems;

import static survivalistessentials.SurvivalistEssentials.loc;

public class FabricCreativeTabs {

    public static final CreativeModeTab SURVIVALISTESSENTIALS_ITEM_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(SurvivalistEssentialsItems.FLINT_SHARD))
        .title(Component.translatable(SurvivalistEssentials.MODID + ".items"))
        .displayItems((features, output) -> {
            for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsItems.getAllIngredients().entrySet()) {
                Item item = entry.getValue();

                output.accept(new ItemStack(item));
            }
            for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsItems.getAll().entrySet()) {
                Item item = entry.getValue();

                output.accept(new ItemStack(item));
            }
        }).build();

    public static void init(BiConsumer<CreativeModeTab, ResourceLocation> consumer) {
        consumer.accept(SURVIVALISTESSENTIALS_ITEM_GROUP, loc("items"));
    }

}
