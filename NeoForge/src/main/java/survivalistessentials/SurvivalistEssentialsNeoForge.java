package survivalistessentials;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import survivalistessentials.common.CreativeTabs;
import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.SurvivalistEssentialsModule;
import survivalistessentials.common.loot.SurvivalistEssentialsLootConditionTypes;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.loot.SurvivalistEssentialsLootTables;
import survivalistessentials.sound.Sounds;
import survivalistessentials.world.SurvivalistEssentialsWorld;
import survivalistessentials.world.effect.SurvivalistEssentialsEffects;
import survivalistessentials.world.feature.SurvivalistEssentialsFeatures;

@Mod(SurvivalistEssentials.MODID)
public class SurvivalistEssentialsNeoForge {

    public static final String MODID = "survivalistessentials";
    public static final Logger LOGGER = LogManager.getFormatterLogger(SurvivalistEssentials.MODID);

    public SurvivalistEssentialsNeoForge(IEventBus bus) {
        registryInit(bus);
        SurvivalistEssentials.init();
        SurvivalistEssentials.initConfig();
        Sounds.init(bus);
        registerListeners(bus);
        SurvivalistEssentialsModule.initRegistries(bus);
    }

    public void registerListeners(IEventBus bus) {
        bus.register(RegistryListener.class);
        SurvivalistEssentialsLootTables.init();
        SurvivalistEssentialsFeatures.init();
        SurvivalistEssentialsEffects.init();
    }

    public static final class RegistryListener {

        private static boolean setupDone = false;

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerEvent(RegisterEvent event) {
            if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
                CreativeTabs.init();
            }
        }

        @SubscribeEvent
        public static void setup(FMLCommonSetupEvent event) {
            HarvestBlock.init();
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerCreativeTab(BuildCreativeModeTabContentsEvent event) {
            if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsItems.getToolsAndUtilities().entrySet()) {
                    Item item = entry.getValue();

                    event.accept(new ItemStack(item));
                }
            }
            if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
                for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsItems.getAllIngredients().entrySet()) {
                    Item item = entry.getValue();

                    event.accept(new ItemStack(item));
                }
                for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsWorld.getAllItems().entrySet()) {
                    Item item = entry.getValue();

                    event.accept(new ItemStack(item));
                }
            }
        }

    }

    private void registryInit(IEventBus bus) {
        bind(bus, Registries.ITEM, ModIntegration::init);
        bind(bus, Registries.ITEM, SurvivalistEssentialsItems::init);
        bind(bus, Registries.ITEM, SurvivalistEssentialsWorld::initItems);
        bind(bus, Registries.BLOCK, SurvivalistEssentialsWorld::initBlocks);
        bind(bus, Registries.LOOT_CONDITION_TYPE, SurvivalistEssentialsLootConditionTypes::init);
    }

    @SubscribeEvent
    public static void serverStart(ServerStartedEvent event) {
        if (ConfigHandler.Common.logModpackData()) {
            BuiltInRegistries.BLOCK.forEach(block -> {
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_WOOD_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_wood_tool - level 0: {}", block);
                }
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_GOLD_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_gold_tool - level 0.1: {}", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_STONE_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_stone_tool - level 1: {}", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_IRON_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_iron_tool - level 2: {}", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_DIAMOND_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_diamond_tool - level 3: {}", block);
                }
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_NETHERITE_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_netherite_tool - level 4: {}", block);
                }
            });
        }
    }

    private static <T> void bind(IEventBus bus, ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        bus.addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }

}
