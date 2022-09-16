package survivalistessentials.world.modifier;

import com.mojang.serialization.Codec;

import net.minecraftforge.registries.RegistryObject;

import survivalistessentials.common.SurvivalistEssentialsModule;

public class SurvivalistEssentialsBiomeModifiers extends SurvivalistEssentialsModule {

    public static final RegistryObject<Codec<LooseRockBiomeModifier>> LOOSE_ROCK_MODIFIER = BIOME_MODIFIER_REGISTRY.register("loose_rock", LooseRockBiomeModifier::makeCodec);

}