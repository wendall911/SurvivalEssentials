package survivalistessentials.world.modifier;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import survivalistessentials.world.feature.LooseRockFeatureHolders;

public class LooseRockBiomeModifier implements BiomeModifier {

    public static LooseRockBiomeModifier INSTANCE = new LooseRockBiomeModifier();

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase.equals(Phase.AFTER_EVERYTHING)) {
            if (biome.is(BiomeTags.IS_OVERWORLD)) {
                builder.getGenerationSettings().addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    LooseRockFeatureHolders.LOOSE_ROCKS_PLACEMENT
                );
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SurvivalistEssentialsBiomeModifiers.LOOSE_ROCK_MODIFIER.get();
    }

    public static Codec<LooseRockBiomeModifier> makeCodec() {
        return Codec.unit(INSTANCE);
    }

}
