package survivalistessentials.event;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.sound.Sounds;
import survivalistessentials.util.ItemUse;

public class BowEventHandler {

    @SubscribeEvent
    public static void onArrowLoose(Player player) {
        if (!player.isCreative()) {
            final ItemStack handStack = player.getMainHandItem();
            final Level level = player.level();

            if (!ItemUse.isAllowedTool(handStack)) {
                if (!level.isClientSide && ConfigHandler.CLIENT.enableFailSound()) {
                    level.playSound(null, player.getOnPos(), Sounds.BOW_FAIL.get(), SoundSource.PLAYERS, 0.6F, 1.0F);
                }
                event.setCanceled(true);
            }
        }
    }

}
