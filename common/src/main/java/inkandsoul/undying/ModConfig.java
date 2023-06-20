package inkandsoul.undying;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;

public class ModConfig {
    // public static final boolean ENABLE;

    /**
     * We can use {@link Platform#getConfigFolder()} but this is just an example of
     * {@link ExpectPlatform}.
     * <p>
     * This must be a <b>public static</b> method. The platform-implemented solution
     * must be placed under a
     * platform sub-package, with its class suffixed with {@code Impl}.
     * <p>
     * Example:
     * Expect: net.examplemod.ExampleExpectPlatform#getConfigDirectory()
     * Actual Fabric:
     * net.examplemod.fabric.ExampleExpectPlatformImpl#getConfigDirectory()
     * Actual Forge:
     * net.examplemod.forge.ExampleExpectPlatformImpl#getConfigDirectory()
     * <p>
     * <a href="https://plugins.jetbrains.com/plugin/16210-architectury">You should
     * also get the IntelliJ plugin to help with @ExpectPlatform.</a>
     */
    @ExpectPlatform
    public static Path getConfigDirectory() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(ModConfig.class);

    public static boolean WHITE_OR_BLACK = true;
    public static boolean IMPLEMENT_WAY = false;
    public static List<String> LIST = new ArrayList<>();
    public static Map<String, JsonElement> json;

    public static void tryWriteDefaultContext(File file){
        try {
            Files.writeString(file.toPath(), defaultContext(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            LOGGER.error("[" + ModInit.MOD_ID + "] Failed create config file!", e);
        }
    }

    public static void init() {
        json = null;
        var dir = ModConfig.getConfigDirectory().toString() + "/" + ModInit.MOD_ID + ".json";
        File file = new File(dir);

        if (!file.exists()) {
            tryWriteDefaultContext(file);
        }

        if (file.isFile() && file.canRead()) {
            try {
                json = JsonParser
                        .parseString(new String(Files.readAllBytes(file.toPath())).replaceAll("( )+//.+\\n", ""))
                        .getAsJsonObject().asMap();
            } catch (Exception e) {
                tryWriteDefaultContext(file);
            }
        }

        // if (json == null) {
        //     json = JsonParser.parseString(defaultContext().replaceAll("( )+//.+\\n", "")).getAsJsonObject().asMap();
        //     tryWriteDefaultContext(file);
        // }
        
        IMPLEMENT_WAY = check(json.get("implement_way").getAsBoolean(), IMPLEMENT_WAY, "implement_way");
        WHITE_OR_BLACK = check(json.get("white_or_black").getAsBoolean(), WHITE_OR_BLACK, "white_or_black");
        check(json.get("list").getAsJsonArray(), Collections.<JsonElement>emptyList(), "list").forEach(i->LIST.add(i.getAsString()));
        
        LOGGER.info("[" + ModInit.MOD_ID + "] Config is loaded!");
    }

    private static <T> T check(T t, T defaultV, String option){
        if(Objects.nonNull(t)){
            return t;
        }else{
            LOGGER.info("[" + ModInit.MOD_ID + "] Missing options "+option+". Will use default value to replace: "+defaultV.toString());
            return defaultV;
        }
    }

    private static String defaultContext() {
        return """
                {
                    // 决定修改模式
                    //   兼容模式下兼容性更好，相比强制模式而言更加安全，但可能无法对所有Mod生效。
                    //   强制模式下兼容性较差，生效范围更广，但可能会导致某些问题。
                    // 決定修改模式
                    //   兼容模式下兼容性更好，相比强制模式而言更加安全，但可能存在適用範圍
                    //   强制模式下兼容性較差，有效範圍更廣，但可能會導致某些問題。
                    // true : 强制模式、false : 兼容模式
                    "implement_way": false
                    // 决定是黑名单还是白名单
                    // 決定是黑名單是許白名單
                    // true : 黑、false : 白
                    "white_or_black":true,
                    // 名单，格式："<物品id>"
                    // 名單，格式："<物品id>"
                    // <物品id>的格式为“<modid>:<item>”，可以按下F3+H在游戏内查看
                    // <物品id>的格式爲“<modid>:<item>”，可以按下F3+H在游戲内查看
                    // 例：\"minecraft:diamond_axe\"
                    "list":[]
                }""";
    }

    // @ExpectPlatform
    // public static Boolean whiteOrBlack(){
    // throw new AssertionError();
    // }
}
