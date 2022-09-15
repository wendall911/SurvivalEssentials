package survivalessentials.world.effect;

import net.minecraft.world.effect.MobEffect;

import net.minecraftforge.registries.RegistryObject;

import survivalessentials.common.SurvivalEssentialsModule;
import survivalessentials.world.effect.StopBleeding;
import survivalessentials.world.effect.ZombieEssence;

public final class SurvivalEssentialsEffects extends SurvivalEssentialsModule {

    public static final RegistryObject<MobEffect> STOP_BLEEDING = MOBEFFECT_REGISTRY.register(
        "stop_bleeding", () -> new StopBleeding()
    );

    public static final RegistryObject<MobEffect> ZOMBIE_ESSENCE = MOBEFFECT_REGISTRY.register(
        "zombie_essence", () -> new ZombieEssence()
    );

}
