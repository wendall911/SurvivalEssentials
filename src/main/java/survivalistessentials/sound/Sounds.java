package survivalistessentials.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.SurvivalistEssentialsModule;

public class Sounds extends SurvivalistEssentialsModule {

    public static DeferredHolder<SoundEvent, SoundEvent> ARMOR_FAIL;
    public static DeferredHolder<SoundEvent, SoundEvent> BOW_FAIL;
    public static DeferredHolder<SoundEvent, SoundEvent> FLINT_KNAPPING;
    public static DeferredHolder<SoundEvent, SoundEvent> HOE_FAIL;
    public static DeferredHolder<SoundEvent, SoundEvent> SWORD_FAIL;
    public static DeferredHolder<SoundEvent, SoundEvent> TOOL_FAIL;

    public static void init(IEventBus bus) {
        ARMOR_FAIL = registerSound("armor_fail");
        BOW_FAIL = registerSound("bow_fail");
        FLINT_KNAPPING = registerSound("knapping");
        HOE_FAIL = registerSound("hoe_fail");
        SWORD_FAIL = registerSound("sword_fail");
        TOOL_FAIL = registerSound("tool_fail");
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        return SOUND_REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SurvivalistEssentials.MODID, name)));
    }

}
