package survivalessentials;

import java.util.Random;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.DistExecutor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import survivalessentials.proxy.ClientProxy;
import survivalessentials.proxy.CommonProxy;

@Mod(SurvivalEssentials.MODID)
public class SurvivalEssentials {

    public static final String MODID = "survivalessentials";
    public static final Logger LOGGER = LogManager.getFormatterLogger(SurvivalEssentials.MODID);
    public static final Random RANDOM = new Random();

    public static CommonProxy PROXY;

    public SurvivalEssentials() {
        PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        PROXY.start();
    }

}
