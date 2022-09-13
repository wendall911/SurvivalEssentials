package survivalessentials.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import survivalessentials.SurvivalEssentials;
import survivalessentials.util.ItemUse;

@Mod.EventBusSubscriber(modid = SurvivalEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigReloadHandler {

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading event) {
        ItemUse.init();
    }

}
