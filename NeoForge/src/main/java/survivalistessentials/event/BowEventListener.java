package survivalistessentials.event;

import net.minecraft.world.entity.player.Player;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ArrowLooseEvent;

import survivalistessentials.event.BowEventHandler;

public class BowEventListener {

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        BowEventHandler.onArrowLoose(event.getEntity());
    }

}
