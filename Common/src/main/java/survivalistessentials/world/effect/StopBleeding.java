package survivalistessentials.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import survivalistessentials.config.ConfigHandler;

public class StopBleeding extends MobEffect {

    public StopBleeding() {
        super(MobEffectCategory.BENEFICIAL, 0xf7b7ad);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        float healRate = (float) ConfigHandler.Common.healRate();

        if (entity.getHealth() >= entity.getMaxHealth()) {
            entity.removeEffect(this);
        }

        entity.heal(healRate * (float)(amplifier + 1));
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int j = 50 >> amplifier;

        if (j > 0) {
            return duration % j == 0;
        }
        else {
            return true;
        }
    }

}
