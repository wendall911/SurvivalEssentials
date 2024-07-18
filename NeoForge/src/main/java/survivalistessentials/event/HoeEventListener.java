package survivalistessentials.event;

import net.minecraft.world.entity.player.Player;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ToolActions;
import net.neoforged.neoforge.event.level.BlockEvent;

import survivalistessentials.event.HoeEventHandler;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class HoeEventListener {

    @SubscribeEvent
    public static void onHoeBlock(BlockEvent.BlockToolModificationEvent event) {
        final Player player = event.getPlayer() != null ? event.getPlayer() : null;

        if (player != null && event.getToolAction() == ToolActions.HOE_TILL) {
            HoeEventHandler.onHoeBlock(player, event.setCanceled);
        }
    }

}
