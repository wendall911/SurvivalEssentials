package survivalessentials.event;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import survivalessentials.config.ConfigHandler;
import survivalessentials.sound.Sounds;
import survivalessentials.util.ItemUse;

public class BowEventHandler {

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        final Player player = event.getPlayer() != null ? event.getPlayer() : null;

        if (player != null && !player.isCreative()) {
            final ItemStack handStack = player.getMainHandItem();
            final Level level = player.getLevel();

            if (!ItemUse.isAllowedTool(handStack)) {
                if (!level.isClientSide && ConfigHandler.Client.enableFailSound()) {
                    level.playSound(null, player.getOnPos(), Sounds.BOW_FAIL.get(), SoundSource.BLOCKS, 0.6F, 1.0F);
                }
                event.setCanceled(true);
            }
        }
    }

}
