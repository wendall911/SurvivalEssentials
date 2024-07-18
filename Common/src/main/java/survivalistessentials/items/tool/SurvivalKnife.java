package survivalistessentials.items.tool;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;

import survivalistessentials.common.TagManager;

public class SurvivalKnife extends SwordItem {

    public SurvivalKnife(Tier tier, int damage, float speed, Item.Properties tabGroup) {
        super(tier, damage, speed, tabGroup);
    }

    @NotNull
    @Override
    public ItemStack getCraftingRemainingItem(@NotNull ItemStack stack) {
        ItemStack container = stack.copy();
        
        if (!container.hurt(1, RandomSource.create(), null)) {
            return container;
        }
        else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
        return true;
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
        knife.hurtAndBreak(1, player, (item) -> {
            item.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
    }

}

