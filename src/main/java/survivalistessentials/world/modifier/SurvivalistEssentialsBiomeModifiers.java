package survivalistessentials.world.modifier;

import com.mojang.serialization.Codec;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.SurvivalistEssentialsModule;

public class SurvivalistEssentialsBiomeModifiers extends SurvivalistEssentialsModule {

    public static final ResourceKey<BiomeModifier> LOOSE_ROCKS_MODIFIER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(SurvivalistEssentials.MODID, "loose_rock"));
    public static final RegistryObject<Codec<LooseRockBiomeModifier>> LOOSE_ROCK_MODIFIER_CODEC = BIOME_MODIFIER_REGISTRY.register("loose_rock", LooseRockBiomeModifier::makeCodec);

}