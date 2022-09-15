package survivalistessentials.jei;

import java.util.Objects;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;

import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.world.SurvivalistEssentialsWorld;

@SuppressWarnings("unused")
@JeiPlugin
public class SurvivalistEssentialsJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SurvivalistEssentials.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        addIngredientInfo(registry, SurvivalistEssentialsWorld.ROCK_STONE);
        addIngredientInfo(registry, SurvivalistEssentialsItems.PLANT_FIBER);
        addIngredientInfo(registry, SurvivalistEssentialsItems.FLINT_SHARD);
        addIngredientInfo(registry, Items.STICK);

    }

    private void addIngredientInfo(IRecipeRegistration registry, Item item) {
        String name = Objects.requireNonNull(item.getRegistryName()).getPath();

        registry.addIngredientInfo(
            new ItemStack(item),
            VanillaTypes.ITEM_STACK,
            new TranslatableComponent("jei." + SurvivalistEssentials.MODID + ".description." + name)
        );
    }

}
