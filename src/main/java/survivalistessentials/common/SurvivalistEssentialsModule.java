package survivalessentials.common;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.levelgen.feature.Feature;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import survivalessentials.config.ConfigHandler;
import survivalessentials.SurvivalEssentials;

public abstract class SurvivalEssentialsModule {

    protected static final DeferredRegister<Feature<?>> FEATURE_REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, SurvivalEssentials.MODID);
    protected static final DeferredRegister<MobEffect> MOBEFFECT_REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SurvivalEssentials.MODID);
    protected static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, SurvivalEssentials.MODID);

    protected SurvivalEssentialsModule() {}

    public static void initRegistries(IEventBus bus) {
        MOBEFFECT_REGISTRY.register(bus);
        LOOT_MODIFIER_REGISTRY.register(bus);

        if (ConfigHandler.Common.enableRockGen()) {
            FEATURE_REGISTRY.register(bus);
        }
    }

}
