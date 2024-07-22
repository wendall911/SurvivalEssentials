package survivalistessentials.items.tool;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import survivalistessentials.common.TagManager;
import survivalistessentials.items.SurvivalistEssentialsItems;

public class SurvivalSaw extends TieredItem {

    public String name;
    private final float speed;

    public SurvivalSaw(String name, Tier tier, float speed, Item.Properties tabGroup) {
        super(tier, tabGroup);

        this.speed = speed;
        this.name = name;
    }

    @NotNull
    @Override
    public ItemStack getCraftingRemainingItem(@NotNull ItemStack stack) {
        ItemStack container = stack.copy();

        if (Objects.equals(this.name, "saw_handle")) {
            return ItemStack.EMPTY;
        }

        container.setDamageValue(container.getDamageValue() + 1);

        if (container.getDamageValue() < container.getMaxDamage()) {
            return container;
        }
        else {
            stack.shrink(1);

            return new ItemStack(SurvivalistEssentialsItems.SAW_HANDLE);
        }
    }

    @Override
    public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pToRepair, @NotNull ItemStack pRepair) {
        return false;
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack pStack, BlockState pState) {
        return !pState.is(TagManager.Blocks.ALWAYS_BREAKABLE) ? this.speed : 1.0F;
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack pStack, Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos, @NotNull LivingEntity pEntityLiving) {
          if (!pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
              Tool tool = (Tool)pStack.get(DataComponents.TOOL);
              if (tool == null) {
                  return false;
              } else if (tool.damagePerBlock() > 0) {
                  pStack.hurtAndBreak(tool.damagePerBlock(), pEntityLiving, EquipmentSlot.MAINHAND);

                  return true;
              }
          }

          return true;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext pContext) {
        return InteractionResult.FAIL;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        return false;
    }

}
