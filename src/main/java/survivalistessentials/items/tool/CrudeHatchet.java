package survivalistessentials.items.tool;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;

public class CrudeHatchet extends AxeItem {

    public CrudeHatchet(ToolMaterial toolMaterial, float speed, float damage, Item.Properties properties) {
        super(toolMaterial, speed, damage, properties);
    }

    @NotNull
    @Override
    public ItemStack getCraftingRemainder(@NotNull ItemStack stack) {
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

}
