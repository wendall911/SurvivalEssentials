package survivalistessentials.event;

import java.util.Objects;
import java.util.UUID;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.SurvivalistEssentials;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class PlayerEventHandler {

    private static final UUID STARTING_HEALTH_PENALTY = UUID.fromString("e86dd51f-e5bd-4606-940a-6ffc961b612d");

    @SubscribeEvent
    public static void onPlayerLogin(Player player) {
        applyHealthPenalty(player);
    }

    public static void onPlayerClone(Player player) {
        applyHealthPenalty(player);

        if (!player.isCreative()
                && !player.isSpectator()
                && !player.level().isClientSide
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

    private static void applyHealthPenalty(Player player) {
        if (player != null && ConfigHandler.Common.startingHealthPenalty() < 0) {
            float maxHealth = player.getMaxHealth();
            float newHealth = maxHealth + ConfigHandler.Common.startingHealthPenalty();

            if (newHealth >= 1.0F) {
                AttributeInstance attributeInstance = maxHealthAttribute(player);
                AttributeModifier modifier = new AttributeModifier(
                        STARTING_HEALTH_PENALTY,
                        "Starting Health Penalty",
                        ConfigHandler.Common.startingHealthPenalty(),
                        AttributeModifier.Operation.ADDITION
                );

                attributeInstance.removeModifier(STARTING_HEALTH_PENALTY);
                attributeInstance.addPermanentModifier(modifier);
            }
        }
    }

    private static AttributeInstance maxHealthAttribute(Player player) {
        return Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH));
    }

}
