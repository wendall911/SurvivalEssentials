package survivalistessentials;

import java.util.function.BiConsumer;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import survivalistessentials.common.FabricCreativeTabs;
import survivalistessentials.common.loot.SurvivalistEssentialsLootConditionTypes;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.world.SurvivalistEssentialsWorld;

public class SurvivalistEssentialsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        registryInit();
        SurvivalistEssentials.init();
    }

    private void registryInit() {
        SurvivalistEssentialsItems.init(bind(BuiltInRegistries.ITEM));
        SurvivalistEssentialsWorld.initItems(bind(BuiltInRegistries.ITEM));
        SurvivalistEssentialsWorld.initBlocks(bind(BuiltInRegistries.BLOCK));
        FabricCreativeTabs.init(bind(BuiltInRegistries.CREATIVE_MODE_TAB));
        ModIntegration.init(bind(BuiltInRegistries.ITEM));
        SurvivalistEssentialsLootConditionTypes.init(bind(BuiltInRegistries.LOOT_CONDITION_TYPE));
    }

    private static <T> BiConsumer<T, ResourceLocation> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }

}
