package survivalistessentials.world.effect;

import net.minecraft.world.effect.MobEffect;

import net.minecraftforge.registries.RegistryObject;

import survivalistessentials.common.SurvivalistEssentialsModule;
import survivalistessentials.world.effect.StopBleeding;
import survivalistessentials.world.effect.ZombieEssence;

public final class SurvivalistEssentialsEffects extends SurvivalistEssentialsModule {

    public static final RegistryObject<MobEffect> STOP_BLEEDING = MOBEFFECT_REGISTRY.register(
        "stop_bleeding", StopBleeding::new
    );

    public static final RegistryObject<MobEffect> ZOMBIE_ESSENCE = MOBEFFECT_REGISTRY.register(
        "zombie_essence", ZombieEssence::new
    );

}
