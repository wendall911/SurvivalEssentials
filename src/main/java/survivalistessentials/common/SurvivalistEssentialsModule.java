package survivalistessentials.common;

import com.mojang.serialization.Codec;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import survivalistessentials.SurvivalistEssentials;

public abstract class SurvivalistEssentialsModule {

    protected static final DeferredRegister<Feature<?>> FEATURE_REGISTRY = DeferredRegister.create(BuiltInRegistries.FEATURE, SurvivalistEssentials.MODID);
    protected static final DeferredRegister<MobEffect> MOBEFFECT_REGISTRY = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, SurvivalistEssentials.MODID);
    protected static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SurvivalistEssentials.MODID);
    protected static DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, SurvivalistEssentials.MODID);
    protected static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SurvivalistEssentials.MODID);
    protected static final DeferredRegister<LootItemConditionType> LOOT_ITEM_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, SurvivalistEssentials.MODID);


    protected SurvivalistEssentialsModule() {}

    public static void initRegistries(IEventBus bus) {
        MOBEFFECT_REGISTRY.register(bus);
        LOOT_MODIFIER_REGISTRY.register(bus);
        FEATURE_REGISTRY.register(bus);
        SOUND_REGISTRY.register(bus);
        CREATIVE_MODE_TAB_REGISTRY.register(bus);
        LOOT_ITEM_CONDITION_TYPES.register(bus);
    }

}
