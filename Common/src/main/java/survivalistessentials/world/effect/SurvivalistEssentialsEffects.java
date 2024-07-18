package survivalistessentials.world.effect;

import net.minecraft.world.effect.MobEffect;

import net.neoforged.neoforge.registries.DeferredHolder;

import survivalistessentials.common.SurvivalistEssentialsModule;

public final class SurvivalistEssentialsEffects extends SurvivalistEssentialsModule {

    public static final DeferredHolder<MobEffect, StopBleeding> STOP_BLEEDING = MOBEFFECT_REGISTRY.register(
        "stop_bleeding", StopBleeding::new
    );

    public static final DeferredHolder<MobEffect, ZombieEssence> ZOMBIE_ESSENCE = MOBEFFECT_REGISTRY.register(
        "zombie_essence", ZombieEssence::new
    );

    public static void init() {}

}
