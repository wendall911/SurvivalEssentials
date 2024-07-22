package survivalistessentials.items.tool;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class CrudeHatchet extends AxeItem {

    public CrudeHatchet(Tier tier, Item.Properties tabGroup) {
        super(tier, tabGroup);
    }

    @NotNull
    @Override
    public ItemStack getCraftingRemainingItem(@NotNull ItemStack stack) {
        ItemStack container = stack.copy();

        container.setDamageValue(container.getDamageValue() + 1);

        if (container.getDamageValue() < container.getMaxDamage()) {
            return container;
        }
        else {
            stack.shrink(1);

            return ItemStack.EMPTY;
        }
    }

    @Override
    public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
        return true;
    }

}
