package survivalistessentials.proxy;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.GenerationStep;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.SurvivalistEssentialsModule;
import survivalistessentials.common.loot.SurvivalistEssentialsLootItemConditions;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.loot.SurvivalistEssentialsLootTables;
import survivalistessentials.sound.Sounds;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.effect.SurvivalistEssentialsEffects;
import survivalistessentials.world.feature.SurvivalistEssentialsFeatures;
import survivalistessentials.world.feature.LooseRockFeatureHolders;
import survivalistessentials.world.SurvivalistEssentialsWorld;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class CommonProxy {

    public CommonProxy() {}

    public void start() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ConfigHandler.init();
        Sounds.init(bus);
        registerListeners(bus);
    }

    public void registerListeners(IEventBus bus) {
        bus.register(RegistryListener.class);
    }

    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generator = event.getGeneration();

        if (ConfigHandler.Common.enableRockGen()) {
            generator.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                LooseRockFeatureHolders.LOOSE_ROCKS_PLACEMENT
            );
        }
    }

    public static final class RegistryListener {

        private static boolean setupDone = false;

        public static IForgeRegistry<Block> BLOCK_REGISTRY;

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

            SurvivalistEssentialsItems.init(event.getRegistry());
            ModIntegration.init(event.getRegistry());
            SurvivalistEssentialsWorld.initItems(event.getRegistry());
            SurvivalistEssentialsLootItemConditions.init(bus);
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerLootModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

            bus.register(new SurvivalistEssentialsLootTables());
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            BLOCK_REGISTRY = event.getRegistry();

            SurvivalistEssentialsWorld.initBlocks(event.getRegistry());
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

            bus.register(new SurvivalistEssentialsFeatures());
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerMobEffects(RegistryEvent.Register<MobEffect> event) {
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

            bus.register(new SurvivalistEssentialsEffects());
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void setupRegistries(FMLConstructModEvent event) {
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

            if (setupDone) {
                return;
            }
            setupDone = true;

            SurvivalistEssentialsModule.initRegistries(bus);
        }

        @SubscribeEvent
        public static void setup(FMLCommonSetupEvent event) {
            HarvestBlock.setup();
        }

    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if (ConfigHandler.Common.logModpackData()) {
            RegistryListener.BLOCK_REGISTRY.getValues().forEach((block) -> {
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

}
