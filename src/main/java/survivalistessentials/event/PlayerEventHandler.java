package survivalessentials.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import survivalessentials.config.ConfigHandler;
import survivalessentials.SurvivalEssentials;

@Mod.EventBusSubscriber(modid = SurvivalEssentials.MODID)
public class PlayerEventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Player player = event.getPlayer();

        if ((player != null)
                && !player.isCreative()
                && !player.isSpectator()
                && !player.level.isClientSide
                && event.isWasDeath()) {
            ServerPlayer sp = (ServerPlayer) player;

            if (ConfigHandler.Common.enableHungerPenalty()) {
                sp.getFoodData().setFoodLevel(ConfigHandler.Common.hunger());
                sp.getFoodData().setSaturation(ConfigHandler.Common.saturation());
            }
            if (ConfigHandler.Common.enableHealthPenalty()) {
                sp.setHealth(ConfigHandler.Common.health());
            }
        }
    }

}
