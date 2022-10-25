package survivalistessentials.data;

import net.minecraft.data.DataGenerator;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import survivalistessentials.data.client.ModBlockStateProvider;
import survivalistessentials.data.client.ModItemModelProvider;
import survivalistessentials.data.client.patchouli.ModBookProvider;
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

        gen.addProvider(new ModItemModelProvider(gen, existingFileHelper));
        gen.addProvider(new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(blockTags);
        gen.addProvider(new ModItemTagsProvider(gen, blockTags, existingFileHelper));
        gen.addProvider(new ModRecipesProvider(gen));
        gen.addProvider(new ModLootTables(gen));
        gen.addProvider(new GlobalLootModifier(gen));
        gen.addProvider(new ModBookProvider(gen));

        if (modpackOverrides != null && modpackOverrides.contains("all")) {
            gen.addProvider(new BlockTagsOverrideProvider(gen, event.getExistingFileHelper()));
        }
    }

}
