package survivalistessentials.common;

import com.mojang.serialization.Codec;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.feature.Feature;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import survivalistessentials.SurvivalistEssentials;

public abstract class SurvivalistEssentialsModule {

    protected static final DeferredRegister<Feature<?>> FEATURE_REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, SurvivalistEssentials.MODID);
    protected static final DeferredRegister<MobEffect> MOBEFFECT_REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SurvivalistEssentials.MODID);
    protected static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SurvivalistEssentials.MODID);
    protected static DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SurvivalistEssentials.MODID);
    protected static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SurvivalistEssentials.MODID);

    protected SurvivalistEssentialsModule() {}

    public static void initRegistries(IEventBus bus) {
        MOBEFFECT_REGISTRY.register(bus);
        LOOT_MODIFIER_REGISTRY.register(bus);
        FEATURE_REGISTRY.register(bus);
        SOUND_REGISTRY.register(bus);
        CREATIVE_MODE_TAB_REGISTRY.register(bus);
    }

}
