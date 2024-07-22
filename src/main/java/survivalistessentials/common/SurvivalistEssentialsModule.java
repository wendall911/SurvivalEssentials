package survivalistessentials.common;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import survivalistessentials.SurvivalistEssentials;

public abstract class SurvivalistEssentialsModule {

    protected static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SurvivalistEssentials.MODID);
    protected static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SurvivalistEssentials.MODID);

    protected SurvivalistEssentialsModule() {}

    public static void initRegistries(IEventBus bus) {
        LOOT_MODIFIER_REGISTRY.register(bus);
        CREATIVE_MODE_TAB_REGISTRY.register(bus);
    }

}
