package survivalistessentials.util;

import net.minecraft.world.entity.player.Player;

import tschipp.carryon.common.carry.CarryOnDataManager;

public class CarryOnHelper {

    public static boolean isKeyPressed(Player player) {
        return CarryOnDataManager.getCarryData(player).isKeyPressed();
    }

}
