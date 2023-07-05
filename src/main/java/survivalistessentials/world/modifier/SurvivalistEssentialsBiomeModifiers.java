package survivalistessentials.world.modifier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import survivalistessentials.SurvivalistEssentials;

public class SurvivalistEssentialsBiomeModifiers {

    public static final ResourceKey<BiomeModifier> LOOSE_ROCKS_MODIFIER_KEY = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(SurvivalistEssentials.MODID, "loose_rock"));;

}