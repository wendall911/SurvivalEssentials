package survivalistessentials.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import survivalistessentials.data.client.SurvivalistEssentialsBlockStateProvider;
import survivalistessentials.data.client.patchouli.ModBookProvider;
import survivalistessentials.data.recipes.NeoForgeRecipeProvider;
import survivalistessentials.SurvivalistEssentials;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeoForgeDatagenInitializer {

    @SubscribeEvent
    public static void configureNeoForgeDatagen(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeServer(), new NeoForgeRecipeProvider(gen.getPackOutput()));
        gen.addProvider(event.includeServer(), new SurvivalistEssentialsBlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModBookProvider(packOutput));
    }

}
