package survivalistessentials;

import java.util.Random;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.DistExecutor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import survivalistessentials.proxy.ClientProxy;
import survivalistessentials.proxy.CommonProxy;

@Mod(SurvivalistEssentials.MODID)
public class SurvivalistEssentials {

    public static final String MODID = "survivalistessentials";
    public static final Logger LOGGER = LogManager.getFormatterLogger(SurvivalistEssentials.MODID);
    public static final Random RANDOM = new Random();

    public static CommonProxy PROXY;

    public SurvivalistEssentials() {
        PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        PROXY.start();
    }

}
