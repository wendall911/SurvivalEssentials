package survivalistessentials.items.tool;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;

import survivalistessentials.common.TagManager;

public class SurvivalKnife extends SwordItem {

    public SurvivalKnife(ToolMaterial toolMaterial, float speed, float damage, Item.Properties properties) {
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

    @Override
    public boolean mineBlock(@NotNull ItemStack knife, @NotNull Level level, BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity player) {
        float destroySpeed = state.getDestroySpeed(level, pos);

        if (destroySpeed != 0.0F) {
            doDamage(knife, player);
        }
        else if (state.is(TagManager.Blocks.FIBER_PLANTS)) {
            if (level.random.nextFloat() < 0.2) {
                doDamage(knife, player);
            }
        }

        return true;
    }

    private void doDamage(ItemStack knife, LivingEntity player) {
        knife.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {
        return false;
    }

}

