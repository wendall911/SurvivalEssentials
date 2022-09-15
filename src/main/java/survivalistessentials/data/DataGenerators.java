package survivalessentials.data;

import net.minecraft.data.DataGenerator;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import survivalessentials.data.client.ModBlockStateProvider;
import survivalessentials.data.client.ModItemModelProvider;
import survivalessentials.data.loot.ModLootTables;
import survivalessentials.data.loot.GlobalLootModifier;
import survivalessentials.data.overrides.BlockTagsOverrideProvider;
import survivalessentials.data.recipes.ModRecipesProvider;
import survivalessentials.SurvivalEssentials;

@Mod.EventBusSubscriber(modid = SurvivalEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

        if (modpackOverrides != null && modpackOverrides.contains("all")) {
            gen.addProvider(new BlockTagsOverrideProvider(gen, event.getExistingFileHelper()));
        }
    }

}
