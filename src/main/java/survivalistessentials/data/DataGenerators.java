package survivalistessentials.data;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import survivalistessentials.data.client.ModBlockStateProvider;
import survivalistessentials.data.client.ModItemModelProvider;
import survivalistessentials.data.loot.ModLootTables;
import survivalistessentials.data.loot.GlobalLootModifier;
import survivalistessentials.data.overrides.BlockTagsOverrideProvider;
import survivalistessentials.data.recipes.ModRecipesProvider;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.feature.SurvivalistEssentialsFeatures;
import survivalistessentials.world.modifier.SurvivalistEssentialsBiomeModifiers;

@EventBusSubscriber(modid = SurvivalistEssentials.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
        .add(Registries.CONFIGURED_FEATURE, SurvivalistEssentialsFeatures::configuredBootstrap)
        .add(Registries.PLACED_FEATURE, SurvivalistEssentialsFeatures::placementBootstrap)
        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
            context.register(SurvivalistEssentialsBiomeModifiers.LOOSE_ROCKS_MODIFIER_KEY, new BiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(SurvivalistEssentialsFeatures.PLACED_LOOSE_ROCKS_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
            ));
        });

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) throws ExecutionException, InterruptedException {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper());
        String modpackOverrides = System.getenv("MOD_OVERRIDES");
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        gen.addProvider(event.includeServer(), new ModBlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput, provider, blockTags, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModRecipesProvider.Runner(packOutput, provider));
        gen.addProvider(event.includeServer(), ModLootTables.create(packOutput, provider));
        gen.addProvider(event.includeServer(), new GlobalLootModifier(packOutput, provider));
        gen.addProvider(event.includeServer(), new ModItemModelProvider(packOutput, existingFileHelper));

        if (modpackOverrides != null && modpackOverrides.contains("all")) {
            gen.addProvider(event.includeServer(), new BlockTagsOverrideProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper()));
        }

        gen.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
                packOutput,
                event.getLookupProvider(),
                BUILDER,
                Set.of(SurvivalistEssentials.MODID)
        ));

        //gen.addProvider(event.includeServer(), new ModBookProvider(packOutput));
    }

}
