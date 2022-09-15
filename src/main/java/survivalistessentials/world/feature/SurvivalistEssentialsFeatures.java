package survivalistessentials.world.feature;

import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;

import net.minecraftforge.registries.RegistryObject;

import survivalistessentials.common.SurvivalistEssentialsModule;
import survivalistessentials.world.feature.LooseRocks;

public final class SurvivalistEssentialsFeatures extends SurvivalistEssentialsModule {

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LOOSE_ROCKS_FEATURE = FEATURE_REGISTRY.register(
        "loose_rocks",
        LooseRocks::new
    );

}
