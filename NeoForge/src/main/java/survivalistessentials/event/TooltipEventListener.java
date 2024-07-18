package survivalistessentials.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import survivalistessentials.event.TooltipEventHandler;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class TooltipEventListener {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemToolTip(ItemTooltipEvent event) {
        TooltipEventHandler.onItemToolTip(event.getItemStack(), event.getToolTip());
    }

}
