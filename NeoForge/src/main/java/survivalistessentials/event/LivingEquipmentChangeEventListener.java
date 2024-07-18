package survivalistessentials.event;

import net.minecraft.world.entity.player.Player;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

import survivalistessentials.event.LivingEquipmentChangeEventHandler;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class LivingEquipmentChangeEventListener {

    @SubscribeEvent
    public static void onChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            LivingEquipmentChangeEventHandler.onChange(player);
        }
    }

}
