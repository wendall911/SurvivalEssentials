package survivalistessentials.event;

import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.sound.Sounds;
import survivalistessentials.util.ItemUse;

public class AttackEventHandler {

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (!player.isCreative()) {
                final ItemStack handStack = player.getMainHandItem();
                final Level level = player.level();
                boolean checkAllowed = event.getSource().getMsgId().contains("player");
                boolean bypassArmor = event.getSource().is(DamageTypeTags.BYPASSES_ARMOR);

                if (bypassArmor) {
                    checkAllowed = false;
                }

                if (checkAllowed && (handStack.is(Items.AIR) || !ItemUse.isAllowedTool(handStack))) {
                    if (!level.isClientSide && ConfigHandler.Client.enableFailSound() && ConfigHandler.Common.genericDamage() == 0.0F) {
                        level.playSound(null, player.getOnPos(), Sounds.SWORD_FAIL, SoundSource.PLAYERS, 0.4F, 1.0F);
                    }

                    event.setNewDamage(ConfigHandler.Common.genericDamage());
                }
            }
        }
    }

}
