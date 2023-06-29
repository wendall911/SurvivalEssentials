package survivalistessentials.world.feature;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.SurvivalistEssentialsModule;

public final class SurvivalistEssentialsFeatures extends SurvivalistEssentialsModule {

    public static final ResourceKey<ConfiguredFeature<?, ?>> LOOSE_ROCKS = FeatureUtils.createKey(SurvivalistEssentials.MODID + ":loose_rocks");
    public static final Feature<NoneFeatureConfiguration> LOOSE_ROCKS_FEATURE = register("loose_rocks", new LooseRocks());
    public static final ResourceKey<PlacedFeature> PLACED_LOOSE_ROCKS = PlacementUtils.createKey(SurvivalistEssentials.MODID + ":loose_rocks");

    public static void configuredBootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, SurvivalistEssentialsFeatures.LOOSE_ROCKS, SurvivalistEssentialsFeatures.LOOSE_ROCKS_FEATURE, NoneFeatureConfiguration.INSTANCE);
    }

    public static void placementBootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        final Holder<ConfiguredFeature<?, ?>> LOOSE_ROCKS_HOLDER = configuredFeatureGetter.getOrThrow(SurvivalistEssentialsFeatures.LOOSE_ROCKS);

        register(
                context,
                SurvivalistEssentialsFeatures.PLACED_LOOSE_ROCKS,
                LOOSE_ROCKS_HOLDER,
                // TODO: ConfigHandler.Common.rockGenFrequency()
                List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
        );
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value) {
        FEATURE_REGISTRY.register(key, () -> value);

        return value;
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration) {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }

}
