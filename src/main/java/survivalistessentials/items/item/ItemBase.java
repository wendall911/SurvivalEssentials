package survivalistessentials.items.item;

import org.jetbrains.annotations.NotNull;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import survivalistessentials.world.effect.SurvivalistEssentialsEffects;

public class ItemBase extends Item {

    private final UseAnim animation;

    public ItemBase(Item.Properties props, UseAnim animation) {
        super(props);

        this.animation = animation;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        Player player = entity instanceof Player ? (Player)entity : null;

        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
        }

        if (!level.isClientSide) {
            String name = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();

            if (name.contains("bandage")) {
                int amplifier = 0;

                if (!name.contains("crude")) {
                    amplifier = 1;
                }

                Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.STOP_BLEEDING);
                entity.addEffect(new MobEffectInstance(effect, 600, amplifier));
            }
            else if (name.contains("cup")) {
                Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.ZOMBIE_ESSENCE);
                entity.addEffect(new MobEffectInstance(effect, 3600, 1));
            }

        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        if (animation == UseAnim.DRINK) {
            level.gameEvent(entity, GameEvent.DRINK, entity.getEyePosition());
        }

        return stack;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        String name = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
        boolean stopBleeding = player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.STOP_BLEEDING));
        boolean zombieEssence = player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(SurvivalistEssentialsEffects.ZOMBIE_ESSENCE));

        if (name.contains("bandage")) {
            if (stopBleeding || player.getHealth() >= player.getMaxHealth()) {
                return InteractionResultHolder.fail(stack);
            }
        }
        else if (name.contains("cup") && zombieEssence) {
            return InteractionResultHolder.fail(stack);
        }

        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return animation;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack, @NotNull LivingEntity pEntity) {
        return 1;
    }

    @Override
    public @NotNull SoundEvent getDrinkingSound() {
        return SoundEvents.PLAYER_ATTACK_WEAK;
    }

}
