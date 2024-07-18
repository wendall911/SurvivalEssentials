package survivalistessentials.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import survivalistessentials.event.HarvestEventHandler;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class HarvestEventListener {

    @SubscribeEvent
    public static void tagUpdate(TagsUpdatedEvent event) {
        HarvestEventHandler.tagUpdate();
    }

    @SubscribeEvent
    public static void breakBlock(BlockEvent.BreakEvent event) {
        HarvestEventHandler.breakBlock(event.getLevel(), event.getPos(), event.getPlayer(), event.setCanceled);
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        HarvestEventHandler.onProjectileImpact(event.getEntity());
    }

    @SubscribeEvent
    public static void harvestCheckEvent(PlayerEvent.HarvestCheck event) {
        HarvestEventHandler.harvestCheckEvent(event.getEntity(), event.getTargetBlock(), event.canHarvest(), event.setCanHarvest);
    }

    @SubscribeEvent
    public static void slowMining(PlayerEvent.BreakSpeed event) {
        HarvestEventHandler.slowMining(event.getEntity(), event.getPosition(), event.setNewSpeed);
    }

}
