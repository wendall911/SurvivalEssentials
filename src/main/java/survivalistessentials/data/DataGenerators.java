package survivalistessentials.data;

import net.minecraft.data.DataGenerator;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import survivalistessentials.data.client.ModBlockStateProvider;
import survivalistessentials.data.client.ModItemModelProvider;
import survivalistessentials.data.loot.ModLootTables;
import survivalistessentials.data.loot.GlobalLootModifier;
import survivalistessentials.data.overrides.BlockTagsOverrideProvider;
import survivalistessentials.data.recipes.ModRecipesProvider;
import survivalistessentials.SurvivalistEssentials;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, existingFileHelper);
        String modpackOverrides = System.getenv("MOD_OVERRIDES");

        gen.addProvider(true, new ModItemModelProvider(gen, existingFileHelper));
        gen.addProvider(true, new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(true, blockTags);
        gen.addProvider(true, new ModItemTagsProvider(gen, blockTags, existingFileHelper));
        gen.addProvider(true, new ModRecipesProvider(gen));
        gen.addProvider(true, new ModLootTables(gen));
        gen.addProvider(true, new GlobalLootModifier(gen));

        if (modpackOverrides != null && modpackOverrides.contains("all")) {
            gen.addProvider(true, new BlockTagsOverrideProvider(gen, event.getExistingFileHelper()));
        }
    }

}
