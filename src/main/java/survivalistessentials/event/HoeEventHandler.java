package survivalistessentials.event;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.level.BlockEvent;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.sound.Sounds;
import survivalistessentials.util.ItemUse;

public class HoeEventHandler {

    @SubscribeEvent
    public static void onHoeBlock(BlockEvent.BlockToolModificationEvent event) {
        final Player player = event.getPlayer() != null ? event.getPlayer() : null;


        if (player != null && !player.isCreative() && event.getItemAbility() == ItemAbilities.HOE_TILL) {
            final ItemStack handStack = player.getMainHandItem();
            final Level level = player.level();

            if (!ItemUse.isAllowedTool(handStack)) {
                if (!level.isClientSide && ConfigHandler.Client.enableFailSound()) {
                    level.playSound(null, player.getOnPos(), Sounds.HOE_FAIL, SoundSource.PLAYERS, 0.2F, 1.0F);
                }
                event.setCanceled(true);
            }
        }
    }

}
