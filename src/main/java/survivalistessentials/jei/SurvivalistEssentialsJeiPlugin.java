package survivalessentials.jei;

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

import survivalessentials.items.SurvivalEssentialsItems;
import survivalessentials.SurvivalEssentials;
import survivalessentials.world.SurvivalEssentialsWorld;

@SuppressWarnings("unused")
@JeiPlugin
public class SurvivalEssentialsJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SurvivalEssentials.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        addIngredientInfo(registry, SurvivalEssentialsWorld.ROCK_STONE);
        addIngredientInfo(registry, SurvivalEssentialsItems.PLANT_FIBER);
        addIngredientInfo(registry, SurvivalEssentialsItems.FLINT_SHARD);
        addIngredientInfo(registry, Items.STICK);

    }

    private void addIngredientInfo(IRecipeRegistration registry, Item item) {
        String name = Objects.requireNonNull(item.getRegistryName()).getPath();

        registry.addIngredientInfo(
            new ItemStack(item),
            VanillaTypes.ITEM_STACK,
            new TranslatableComponent("jei." + SurvivalEssentials.MODID + ".description." + name)
        );
    }

}
