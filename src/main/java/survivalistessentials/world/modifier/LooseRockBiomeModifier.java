package survivalistessentials.world.modifier;

import com.mojang.serialization.Codec;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record LooseRockBiomeModifier(HolderSet<PlacedFeature> features, GenerationStep.Decoration step) implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase.equals(Phase.AFTER_EVERYTHING)) {
            if (biome.is(BiomeTags.IS_OVERWORLD)) {
                BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();

                this.features.forEach(holder -> generationSettings.addFeature(this.step, holder));
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SurvivalistEssentialsBiomeModifiers.LOOSE_ROCK_MODIFIER_CODEC.get();
    }

    public static Codec<LooseRockBiomeModifier> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder.group(
                PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(LooseRockBiomeModifier::features),
                GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(LooseRockBiomeModifier::step)
        ).apply(builder, LooseRockBiomeModifier::new));
    }

}
