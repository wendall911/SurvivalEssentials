package survivalistessentials.event;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.sound.Sounds;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.util.ItemUse;

public class HoeEventHandler {

    public static void onHoeBlock(Player player, Function<Boolean> setCanceled) {
        if (!player.isCreative()) {
            final ItemStack handStack = player.getMainHandItem();
            final Level level = player.level();

            if (!ItemUse.isAllowedTool(handStack)) {
                if (!level.isClientSide && ConfigHandler.Client.enableFailSound()) {
                    level.playSound(null, player.getOnPos(), Sounds.HOE_FAIL.get(), SoundSource.PLAYERS, 0.2F, 1.0F);
                }
                setCanceled.apply(true);
            }
        }
    }

}
