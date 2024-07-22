package survivalistessentials.world.modifier;

import net.minecraft.resources.ResourceKey;

import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static survivalistessentials.SurvivalistEssentials.loc;

public class SurvivalistEssentialsBiomeModifiers {

    public static final ResourceKey<BiomeModifier> LOOSE_ROCKS_MODIFIER_KEY =
        ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            loc("loose_rock")
        );;

}
