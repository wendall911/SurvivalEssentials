package survivalistessentials.world.feature;

import net.minecraftforge.registries.RegistryObject;

import survivalistessentials.common.SurvivalistEssentialsModule;

public final class SurvivalistEssentialsFeatures extends SurvivalistEssentialsModule {

    public static final RegistryObject<LooseRocks> LOOSE_ROCKS_FEATURE = FEATURE_REGISTRY.register(
        "loose_rocks",
        LooseRocks::new
    );

}
