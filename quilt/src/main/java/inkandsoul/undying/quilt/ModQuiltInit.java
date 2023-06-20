package inkandsoul.undying.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import inkandsoul.undying.ModInit;

public class ModQuiltInit implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        ModInit.init();
    }
}
