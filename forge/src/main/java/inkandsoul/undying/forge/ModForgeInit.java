package inkandsoul.undying.forge;

import dev.architectury.platform.forge.EventBuses;
import inkandsoul.undying.ModInit;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModInit.MOD_ID)
public class ModForgeInit {
    public ModForgeInit() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ModInit.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ModInit.init();
    }
}
