package survivalistessentials.items.item;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Mortar extends Item {

    public Mortar(Item.Properties tabGroup) {
        super(tabGroup);
    }

    @Override
    public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
        return true;
    }

    @NotNull
    @Override
    public ItemStack getCraftingRemainingItem(@NotNull ItemStack stack) {
        return stack.copy();
    }

}
