package survivalistessentials.data;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;

import com.mojang.serialization.JsonOps;

import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import survivalistessentials.data.client.SurvivalistEssentialsBlockStateProvider;
import survivalistessentials.data.client.SurvivalistEssentialsItemModelProvider;
import survivalistessentials.data.client.patchouli.SurvivalistEssentialsBookProvider;
import survivalistessentials.data.loot.SurvivalistEssentialsLootTables;
import survivalistessentials.data.loot.SurvivalistEssentialsGlobalLootModifier;
import survivalistessentials.data.overrides.BlockTagsOverrideProvider;
import survivalistessentials.data.recipes.SurvivalistEssentialsRecipesProvider;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.modifier.LooseRockBiomeModifier;

import java.util.Map;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        SurvivalistEssentialsBlockTagsProvider blockTags = new SurvivalistEssentialsBlockTagsProvider(gen, existingFileHelper);
        String modpackOverrides = System.getenv("MOD_OVERRIDES");

        gen.addProvider(true, new SurvivalistEssentialsItemModelProvider(gen, existingFileHelper));
        gen.addProvider(true, new SurvivalistEssentialsBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(true, blockTags);
        gen.addProvider(true, new SurvivalistEssentialsItemTagsProvider(gen, blockTags, existingFileHelper));
        gen.addProvider(true, new SurvivalistEssentialsRecipesProvider(gen));
        gen.addProvider(true, new SurvivalistEssentialsLootTables(gen));
        gen.addProvider(true, new SurvivalistEssentialsGlobalLootModifier(gen));

        if (modpackOverrides != null && modpackOverrides.contains("all")) {
            gen.addProvider(true, new BlockTagsOverrideProvider(gen, event.getExistingFileHelper()));
        }

        gen.addProvider(true, JsonCodecProvider.forDatapackRegistry(
            gen,
            existingFileHelper,
            SurvivalistEssentials.MODID,
            ops,
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            getBiomeModifiers()
        ));

        gen.addProvider(true, new SurvivalistEssentialsBookProvider(gen));
    }

    public static Map<ResourceLocation, BiomeModifier> getBiomeModifiers() {
        Map<ResourceLocation, BiomeModifier> map = Maps.newHashMap();

        map.putAll(generateBiomeModifier(new ResourceLocation(SurvivalistEssentials.MODID, "loose_rocks")));

        return map;
    }

    private static Map<ResourceLocation, BiomeModifier> generateBiomeModifier(ResourceLocation location) {
        final BiomeModifier addFeature = new LooseRockBiomeModifier();
        return Map.of(location, addFeature);
    }

}