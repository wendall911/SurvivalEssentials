package survivalistessentials.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.SurvivalistEssentialsModule;

public class Sounds extends SurvivalistEssentialsModule {

    public static RegistryObject<SoundEvent> ARMOR_FAIL;
    public static RegistryObject<SoundEvent> BOW_FAIL;
    public static RegistryObject<SoundEvent> FLINT_KNAPPING;
    public static RegistryObject<SoundEvent> HOE_FAIL;
    public static RegistryObject<SoundEvent> SWORD_FAIL;
    public static RegistryObject<SoundEvent> TOOL_FAIL;

    public static void init(IEventBus bus) {
        ARMOR_FAIL = registerSound("armor_fail");
        BOW_FAIL = registerSound("bow_fail");
        FLINT_KNAPPING = registerSound("knapping");
        HOE_FAIL = registerSound("hoe_fail");
        SWORD_FAIL = registerSound("sword_fail");
        TOOL_FAIL = registerSound("tool_fail");
    }

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUND_REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SurvivalistEssentials.MODID, name)));
    }

}
