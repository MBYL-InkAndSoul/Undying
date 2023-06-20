package inkandsoul.iteminfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import inkandsoul.crafttech.registry.ModItems;

public class ModInit {
    public static final String MOD_ID = "iteminfo";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        ModConfig.init();
        LOGGER.info("ItemInfo is loaded!");
        // LOGGER.info(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
