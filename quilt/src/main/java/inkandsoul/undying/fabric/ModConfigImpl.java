package inkandsoul.undying.fabric;

import org.quiltmc.loader.api.QuiltLoader;

import inkandsoul.undying.ModConfig;

import java.nio.file.Path;

public class ModConfigImpl {
    /**
     * This is our actual method to {@link ModConfig#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return QuiltLoader.getConfigDir();
    }
}
