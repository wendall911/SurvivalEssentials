package survivalistessentials;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.illusivesoulworks.spectrelib.config.SpectreConfig;
import com.illusivesoulworks.spectrelib.config.SpectreConfigLoader;

import net.minecraft.resources.ResourceLocation;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.platform.Services;

public class SurvivalistEssentials {

    public static final String MODID = "survivalistessentials";
    public static final String MOD_NAME = "SurvivalistEssentials";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final Random RANDOM = new Random();
    public static boolean DATA_GEN = System.getenv("DATA_GEN") != null && System.getenv("DATA_GEN").contains("all");

    public static void init() {
    }
   
    public static void initConfig() {
        SpectreConfig commonConfig = SpectreConfigLoader.add(SpectreConfig.Type.COMMON, ConfigHandler.COMMON_SPEC, MODID);

        if (Services.PLATFORM.isPhysicalClient()) {
            SpectreConfigLoader.add(SpectreConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC, MODID);
            commonConfig.addLoadListener((config, flag) -> ConfigHandler.Client.init());
        }
    }

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MODID, path);
    }

}
