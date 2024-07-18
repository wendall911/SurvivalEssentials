package survivalistessentials.integrations.rei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCustomDisplay;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import survivalistessentials.common.item.SurvivalistEssentialsItems;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.integrations.ArmorEnhancementRecipeMaker;
import survivalistessentials.integrations.HelmetThermometerRecipeMaker;
import survivalistessentials.integrations.WaterFilterRecipeMaker;

public class REIPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry helper) {
        // TODO add infos
    }

    @Override
    public void registerEntries(EntryRegistry registry) {
        registry.removeEntryIf(this::shouldHideEntry);
    }

    private boolean shouldHideEntry(EntryStack<?> entryStack) {
        if (ModList.get().isLoaded(ModIntegration.TS_MODID)) {
            ItemStack stack = entryStack.castValue();

            // TODO refactor SurvivalistEssentialsItems to provide a list of the items to hide
            // Implement in jei plugin as well
        }

        return false;
    }
}
