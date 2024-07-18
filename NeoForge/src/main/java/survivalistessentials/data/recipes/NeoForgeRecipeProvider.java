package survivalistessentials.data.recipes;

import org.jetbrains.annotations.NotNull;

import com.mojang.datafixers.util.Pair;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.data.integration.ModIntegration;

public class NeoForgeRecipeProvider extends RecipeProvider {

    public NeoForgeRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        RecipeOutput wrapped;

        for (Pair<ShapelessRecipeBuilder, ResourceLocation> builderResourceLocationPair : RecipeProviderBase.getPlankRecipes()) {
            ShapelessRecipeBuilder plankOverrideRecipe = builderResourceLocationPair.getFirst();
            ResourceLocation outputLocation = builderResourceLocationPair.getSecond();

            if (outputLocation != null) {
                String modid = outputLocation.getNamespace();

                if (modid.equals(SurvivalistEssentials.MODID)) {
                    plankOverrideRecipe.save(recipeOutput, outputLocation);
                }
                else {
                    plankOverrideRecipe.save(recipeOutput.withConditions(new ModLoadedCondition(modid)), outputLocation);
                }
            }
            else {
                plankOverrideRecipe.save(recipeOutput);
            }
        }

        RecipeProviderBase.cobblestoneFromRocks().getFirst().save(recipeOutput, RecipeProviderBase.cobblestoneFromRocks().getSecond());
        RecipeProviderBase.rocksFromCobblestone().getFirst().save(recipeOutput, RecipeProviderBase.rocksFromCobblestone().getSecond());
        RecipeProviderBase.sticksFromPlanks().save(recipeOutput);
        RecipeProviderBase.flintFromShards().getFirst().save(recipeOutput, RecipeProviderBase.flintFromShards().getSecond());
        RecipeProviderBase.plantString().save(recipeOutput);
        RecipeProviderBase.plantPaste().save(recipeOutput);
        RecipeProviderBase.ointment().save(recipeOutput);
        RecipeProviderBase.cloth().save(recipeOutput);
        RecipeProviderBase.stringFromPlantString().getFirst().save(recipeOutput, RecipeProviderBase.stringFromPlantString().getSecond());
        RecipeProviderBase.crudeSawBlade().save(recipeOutput);

        wrapped = recipeOutput.withConditions(new NotCondition(new ModLoadedCondition(ModIntegration.TS_MODID)));
        RecipeProviderBase.basicSawBlade().save(wrapped);
        RecipeProviderBase.sharpSawBlade().save(wrapped);
        RecipeProviderBase.crudeKnife().save(recipeOutput);
        RecipeProviderBase.basicKnife().save(wrapped);
        RecipeProviderBase.sharpKnife().save(wrapped);
        RecipeProviderBase.crudeHatchet().save(recipeOutput);
        RecipeProviderBase.sawHandle().save(recipeOutput);
        RecipeProviderBase.crudeSaw().save(recipeOutput);
        RecipeProviderBase.basicSaw().save(wrapped);
        RecipeProviderBase.sharpSaw().save(wrapped);
        RecipeProviderBase.mortar().save(recipeOutput);

        RecipeProviderBase.stickFronSapling().getFirst().save(recipeOutput, RecipeProviderBase.stickFronSapling().getSecond());
        RecipeProviderBase.stringFromWool().getFirst().save(recipeOutput, RecipeProviderBase.stringFromWool().getSecond());
        RecipeProviderBase.stringFromWoolAdvanced().getFirst().save(recipeOutput, RecipeProviderBase.stringFromWoolAdvanced().getSecond());
        RecipeProviderBase.flintShards().save(recipeOutput);
        RecipeProviderBase.crudeBandage().save(recipeOutput);
        RecipeProviderBase.bandage().save(recipeOutput);
        RecipeProviderBase.leatherFromCookedMeatSmoking().getFirst().save(recipeOutput, RecipeProviderBase.leatherFromCookedMeatSmoking().getSecond());

        wrapped = recipeOutput.withConditions(new ModLoadedCondition(ModIntegration.PATCHOULI_MODID));
        RecipeProviderBase.bookFromDirt().getFirst().save(wrapped, RecipeProviderBase.bookFromDirt().getSecond());
        RecipeProviderBase.dirtFromBook().getFirst().save(wrapped, RecipeProviderBase.dirtFromBook().getSecond());

        wrapped = recipeOutput.withConditions(new ModLoadedCondition(ModIntegration.TF_MODID));
        RecipeProviderBase.giantLogToOakPlanks().getFirst().save(wrapped, RecipeProviderBase.giantLogToOakPlanks().getSecond());

        wrapped = recipeOutput.withConditions(new ModLoadedCondition(ModIntegration.AQUA_MODID));
        RecipeProviderBase.planksFromDriftwood().getFirst().save(wrapped, RecipeProviderBase.planksFromDriftwood().getSecond());
        RecipeProviderBase.planksFromDriftwoodAdvanced().getFirst().save(wrapped, RecipeProviderBase.planksFromDriftwoodAdvanced().getSecond());

        wrapped = recipeOutput.withConditions(new ModLoadedCondition(ModIntegration.IE_MODID));
        RecipeProviderBase.treatedSticks().getFirst().save(wrapped, RecipeProviderBase.treatedSticks().getSecond());
        RecipeProviderBase.treatedSticksAdvanced().getFirst().save(wrapped, RecipeProviderBase.treatedSticksAdvanced().getSecond());
    }

}
