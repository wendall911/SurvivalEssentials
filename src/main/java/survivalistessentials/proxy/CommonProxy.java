package survivalistessentials.proxy;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;

import net.minecraftforge.common.Tags;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraftforge.registries.RegisterEvent;
import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.SurvivalistEssentialsModule;
import survivalistessentials.common.loot.LootItemBlockIsTagCondition;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.SurvivalistEssentialsIntegration;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.loot.SurvivalistEssentialsLootTables;
import survivalistessentials.sound.Sounds;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.effect.SurvivalistEssentialsEffects;
import survivalistessentials.world.feature.SurvivalistEssentialsFeatures;
import survivalistessentials.world.SurvivalistEssentialsWorld;
import survivalistessentials.world.modifier.SurvivalistEssentialsBiomeModifiers;

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
        bus.register(new SurvivalistEssentialsLootTables());
        bus.register(new SurvivalistEssentialsFeatures());
        bus.register(new SurvivalistEssentialsBiomeModifiers());
        bus.register(new SurvivalistEssentialsEffects());
    }

    public static final class RegistryListener {

        private static boolean setupDone = false;

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerEvent(RegisterEvent event) {
            event.register(Registry.ITEM_REGISTRY, SurvivalistEssentialsItems::init);
            event.register(Registry.ITEM_REGISTRY, SurvivalistEssentialsIntegration::init);
            event.register(Registry.ITEM_REGISTRY, SurvivalistEssentialsWorld::initItems);
            event.register(Registry.BLOCK_REGISTRY, SurvivalistEssentialsWorld::initBlocks);
            event.register(Registry.LOOT_ITEM_REGISTRY, new ResourceLocation(SurvivalistEssentials.MODID, "is_tag"), () -> LootItemBlockIsTagCondition.LOOT_ITEM_BLOCK_IS_TAG);
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
    public static void serverStart(ServerStartedEvent event) {
        if (ConfigHandler.Common.logModpackData()) {
            ForgeRegistries.BLOCKS.getValues().forEach((block) -> {
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
