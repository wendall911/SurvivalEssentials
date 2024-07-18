package survivalistessentials.event;

import net.minecraft.world.entity.player.Player;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

import survivalistessentials.event.AttackEventHandler;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class AttackEventListener {

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            AttackEventHandler.onHurt(player);
        }
    }

}
