package inkandsoul.undying.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

import inkandsoul.undying.ModConfig;

public class ModConfigImpl {
    /**
     * This is our actual method to {@link ModConfig#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
