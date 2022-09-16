package survivalistessentials.items.tool;

import javax.annotation.Nonnull;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class CrudeHatchet extends AxeItem {

    public CrudeHatchet(Tier tier, int damage, float speed, Item.Properties tabGroup) {
        super(tier, damage, speed, tabGroup);
    }

    @Nonnull
    @Override
    public ItemStack getCraftingRemainingItem(@Nonnull ItemStack stack) {
        ItemStack container = stack.copy();
        
        if (!container.hurt(1, RandomSource.create(), null)) {
            return container;
        }
        else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public boolean hasCraftingRemainingItem(@Nonnull ItemStack stack) {
        return true;
    }

}
