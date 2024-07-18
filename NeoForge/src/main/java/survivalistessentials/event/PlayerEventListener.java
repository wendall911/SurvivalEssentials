package survivalistessentials.event;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import survivalistessentials.event.PlayerEventHandler;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class PlayerEventListener {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEventHandler.onPlayerLogin(event.getEntity());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerClone(PlayerEvent.Clone event) {
        PlayerEventHandler.onPlayerClone(event.getEntity());
    }

}
