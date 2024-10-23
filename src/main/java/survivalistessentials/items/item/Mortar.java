package survivalistessentials.items.item;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Mortar extends Item {

    public Mortar(Item.Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public ItemStack getCraftingRemainder(@NotNull ItemStack stack) {
        return stack.copy();
    }

}
