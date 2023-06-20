package inkandsoul.undying.fabric;

import inkandsoul.undying.ModInit;
import net.fabricmc.api.ModInitializer;

public class ModFabricInit implements ModInitializer {
    @Override
    public void onInitialize() {
        ModInit.init();
    }
}
