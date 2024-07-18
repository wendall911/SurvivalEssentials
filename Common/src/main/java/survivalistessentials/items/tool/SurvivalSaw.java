package survivalistessentials.items.tool;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import net.neoforged.neoforge.common.ToolAction;

import survivalistessentials.common.TagManager;
import survivalistessentials.items.SurvivalistEssentialsItems;

public class SurvivalSaw extends TieredItem {

    public String name;
    private final float speed;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public SurvivalSaw(String name, Tier tier, float speed, Item.Properties tabGroup) {
        super(tier, tabGroup);

        this.speed = speed;
        this.name = name;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Speed modifier", speed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @NotNull
    @Override
    public ItemStack getCraftingRemainingItem(@NotNull ItemStack stack) {
        ItemStack container = stack.copy();
        
        if (Objects.equals(this.name, "saw_handle")) {
            return ItemStack.EMPTY;
        }
        else if (!container.hurt(1, RandomSource.create(), null)) {
            return container;
        }
        else {
            return new ItemStack(SurvivalistEssentialsItems.SAW_HANDLE);
        }
    }

    @Override
    public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
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
             pStack.hurtAndBreak(1, pEntityLiving, (entity) -> {
                entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
             });
          }

          return true;
    }

    @Override
    public InteractionResult useOn(@NotNull UseOnContext pContext) {
        return InteractionResult.FAIL;
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ToolAction toolAction) {
        return false;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        return false;
    }

}
