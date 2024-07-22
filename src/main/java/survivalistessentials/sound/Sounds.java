package survivalistessentials.sound;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static survivalistessentials.SurvivalistEssentials.loc;

public class Sounds {

    private static final Map<ResourceLocation, SoundEvent> ALL = new LinkedHashMap<>();
    public static SoundEvent ARMOR_FAIL;
    public static SoundEvent BOW_FAIL;
    public static SoundEvent FLINT_KNAPPING;
    public static SoundEvent HOE_FAIL;
    public static SoundEvent SWORD_FAIL;
    public static SoundEvent TOOL_FAIL;

    public static void init(BiConsumer<SoundEvent, ResourceLocation> consumer) {
        ARMOR_FAIL = makeSoundEvent("armor_fail");
        BOW_FAIL = makeSoundEvent("bow_fail");
        FLINT_KNAPPING = makeSoundEvent("knapping");
        HOE_FAIL = makeSoundEvent("hoe_fail");
        SWORD_FAIL = makeSoundEvent("sword_fail");
        TOOL_FAIL = makeSoundEvent("tool_fail");

        for (Map.Entry<ResourceLocation, SoundEvent> entry : ALL.entrySet()) {
            consumer.accept(entry.getValue(), entry.getKey());
        }
    }

    private static SoundEvent makeSoundEvent(String name) {
        ResourceLocation loc = loc(name);
        SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(loc);

        ALL.put(loc, soundEvent);

        return soundEvent;
    }

}
