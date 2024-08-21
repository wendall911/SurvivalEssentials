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

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import survivalistessentials.common.CreativeTabs;
import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.SurvivalistEssentialsModule;
import survivalistessentials.common.loot.LootItemBlockIsTagCondition;
import survivalistessentials.common.loot.SurvivalistEssentialsLootConditionTypes;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.event.AttackEventHandler;
import survivalistessentials.event.HarvestEventHandler;
import survivalistessentials.event.HoeEventHandler;
import survivalistessentials.event.LivingEquipmentChangeEventHandler;
import survivalistessentials.event.PlayerEventHandler;
import survivalistessentials.event.TooltipEventHandler;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.loot.SurvivalistEssentialsLootTables;
import survivalistessentials.sound.Sounds;
import survivalistessentials.world.SurvivalistEssentialsWorld;
import survivalistessentials.world.effect.SurvivalistEssentialsEffects;
import survivalistessentials.world.feature.SurvivalistEssentialsFeatures;

@Mod(SurvivalistEssentials.MODID)
public class SurvivalistEssentials {

    public static final String MODID = "survivalistessentials";
    public static final Logger LOGGER = LogManager.getFormatterLogger(SurvivalistEssentials.MODID);

    public SurvivalistEssentials(IEventBus bus, Dist dist, ModContainer container) {
        registryInit(bus);
        ConfigHandler.init(container);
        registerListeners(bus);
        SurvivalistEssentialsModule.initRegistries(bus);
        bus.addListener(ConfigHandler::onFileChange);
        bus.addListener(ConfigHandler::loadConfigs);
    }

    public void registerListeners(IEventBus bus) {
        bus.register(RegistryListener.class);
        SurvivalistEssentialsLootTables.init();
        SurvivalistEssentialsFeatures.setup();
    }

    public static final class RegistryListener {
        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerEvent(RegisterEvent event) {
            event.register(Registries.LOOT_CONDITION_TYPE, loc("is_tag"), () -> LootItemBlockIsTagCondition.LOOT_ITEM_BLOCK_IS_TAG);

            if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
                CreativeTabs.init();
            }
        }

        @SubscribeEvent
        public static void setup(FMLCommonSetupEvent event) {
            HarvestBlock.init();
            NeoForge.EVENT_BUS.register(AttackEventHandler.class);
            NeoForge.EVENT_BUS.register(HarvestEventHandler.class);
            NeoForge.EVENT_BUS.register(HoeEventHandler.class);
            NeoForge.EVENT_BUS.register(LivingEquipmentChangeEventHandler.class);
            NeoForge.EVENT_BUS.register(PlayerEventHandler.class);
            if (FMLEnvironment.dist == Dist.CLIENT) {
                NeoForge.EVENT_BUS.register(TooltipEventHandler.class);
            }
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
                for (Map.Entry<ResourceLocation, Item> entry : SurvivalistEssentialsWorld.getAll().entrySet()) {
                    Item item = entry.getValue();

                    event.accept(new ItemStack(item));
                }
            }
        }

    }

    @SubscribeEvent
    public static void serverStart(ServerStartedEvent event) {
        if (ConfigHandler.Common.logModpackData()) {
            BuiltInRegistries.BLOCK.forEach(block -> {
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_WOOD_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_wood_tool - level 0: %s", block);
                }
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_GOLD_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_gold_tool - level 0.1: %s", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_STONE_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_stone_tool - level 1: %s", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_IRON_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_iron_tool - level 2: %s", block);
                }
                if (block.defaultBlockState().is(BlockTags.NEEDS_DIAMOND_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_diamond_tool - level 3: %s", block);
                }
                if (block.defaultBlockState().is(Tags.Blocks.NEEDS_NETHERITE_TOOL)) {
                    SurvivalistEssentials.LOGGER.warn("needs_netherite_tool - level 4: %s", block);
                }
            });
        }
    }

    private void registryInit(IEventBus bus) {
        bind(bus, Registries.FEATURE, SurvivalistEssentialsFeatures::init);
        bind(bus, Registries.MOB_EFFECT, SurvivalistEssentialsEffects::init);
        bind(bus, Registries.ITEM, ModIntegration::init);
        bind(bus, Registries.ITEM, SurvivalistEssentialsItems::init);
        bind(bus, Registries.ITEM, SurvivalistEssentialsWorld::initItems);
        bind(bus, Registries.BLOCK, SurvivalistEssentialsWorld::initBlocks);
        bind(bus, Registries.LOOT_CONDITION_TYPE, SurvivalistEssentialsLootConditionTypes::init);
        bind(bus, Registries.SOUND_EVENT, Sounds::init);
    }

    private static <T> void bind(IEventBus bus, ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        bus.addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }

    public static ResourceLocation prefix(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public static ResourceLocation loc(String path) {
        return prefix(SurvivalistEssentials.MODID, path);
    }

}
