package survivalistessentials.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.data.client.SurvivalistEssentialsModelProvider;
import survivalistessentials.data.overrides.BlockTagsOverrideProvider;
import survivalistessentials.data.recipes.FabricModRecipeProvider;

public class FabicDatagenInitializer implements DataGeneratorEntrypoint {

    private static FabricTagProvider.BlockTagProvider fabricBlockTagProvider;

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        FabricDataGenerator.Pack pack = gen.createPack();
        fabricBlockTagProvider = pack.addProvider(FabricBlockTagProvider::new);

        if (System.getProperty(SurvivalistEssentials.MODID + ".common_datagen") != null) {
            configureCommonDatagen(pack);
        }
        else {
            configureFabricDatagen(pack);
        }
    }

    public static void configureCommonDatagen(FabricDataGenerator.Pack pack) {
        String modpackOverrides = System.getenv("MOD_OVERRIDES");

        pack.addProvider((dataOutput, registryFuture) -> new ModItemTagsProvider(dataOutput, registryFuture, fabricBlockTagProvider.contentsGetter()));
        pack.addProvider((dataOutput, registryFuture) -> new SurvivalistEssentialsModelProvider(dataOutput));
        pack.addProvider(ModBlockTagsProvider::new);

        if (modpackOverrides != null && modpackOverrides.contains("all")) {
           pack.addProvider(BlockTagsOverrideProvider::new);
        }
    }

    public static void configureFabricDatagen(FabricDataGenerator.Pack pack) {
        pack.addProvider((dataOutput, registryFuture) -> new FabricModRecipeProvider(dataOutput));
    }

}
