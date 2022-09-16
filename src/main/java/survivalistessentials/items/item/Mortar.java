package survivalistessentials.items.item;

import javax.annotation.Nonnull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Mortar extends Item {

    public Mortar(Item.Properties tabGroup) {
        super(tabGroup);
    }


    @Override
    public boolean hasCraftingRemainingItem(@Nonnull ItemStack stack) {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getCraftingRemainingItem(@Nonnull ItemStack stack) {
        return stack.copy();
    }

}
