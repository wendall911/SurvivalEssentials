package survivalessentials.world.feature;

import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;

import net.minecraftforge.registries.RegistryObject;

import survivalessentials.common.SurvivalEssentialsModule;
import survivalessentials.world.feature.LooseRocks;

public final class SurvivalEssentialsFeatures extends SurvivalEssentialsModule {

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LOOSE_ROCKS_FEATURE = FEATURE_REGISTRY.register(
        "loose_rocks",
        LooseRocks::new
    );

}
