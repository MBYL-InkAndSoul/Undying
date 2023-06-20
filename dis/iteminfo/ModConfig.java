package inkandsoul.iteminfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class ModConfig {
	// public static final boolean ENABLE;
    
    /**
     * We can use {@link Platform#getConfigFolder()} but this is just an example of {@link ExpectPlatform}.
     * <p>
     * This must be a <b>public static</b> method. The platform-implemented solution must be placed under a
     * platform sub-package, with its class suffixed with {@code Impl}.
     * <p>
     * Example:
     * Expect: net.examplemod.ExampleExpectPlatform#getConfigDirectory()
     * Actual Fabric: net.examplemod.fabric.ExampleExpectPlatformImpl#getConfigDirectory()
     * Actual Forge: net.examplemod.forge.ExampleExpectPlatformImpl#getConfigDirectory()
     * <p>
     * <a href="https://plugins.jetbrains.com/plugin/16210-architectury">You should also get the IntelliJ plugin to help with @ExpectPlatform.</a>
     */
    @ExpectPlatform
    public static Path getConfigDirectory() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(ModConfig.class);

    public static boolean MORE_INFO;
    public static Map<String, List<String>> MORE_TOOLTIPS_LITERAL = new LinkedHashMap<>();
    public static Map<String, List<String>> MORE_TOOLTIPS_TRANSLATABLE = new LinkedHashMap<>();

    public static void init(){
        JsonObject json = null;
        // LOGGER.info("Config Path: " + ModConfig.getConfigDirectory().toString());
        var dir = ModConfig.getConfigDirectory().toString() + "/"+ModInit.MOD_ID+".json";
        File file = new File(dir);

        if (!file.exists()) {
            try {
                Files.writeString(file.toPath(), defaultContext(), StandardOpenOption.CREATE_NEW);
            } catch (IOException e1) {
                LOGGER.error("["+ModInit.MOD_ID+"] Failed create config file!", e1);
            }
        }

        if (file.isFile() && file.canRead()) {
            try {
                json = JsonParser
                        .parseString(new String(Files.readAllBytes(file.toPath())).replaceAll("( )+//.+\\n", ""))
                        .getAsJsonObject();
            } catch (Exception e) {
                try {
                    Files.writeString(file.toPath(), defaultContext(), StandardOpenOption.CREATE_NEW);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                LOGGER.error("["+ModInit.MOD_ID+"] Failed reading config file!", e);
            }
        }

        if (json == null) {
            json = JsonParser.parseString(defaultContext().replaceAll("( )+//.+\\n", "")).getAsJsonObject();
        }

        // ENABLE = json.get("enable").getAsBoolean();
        MORE_INFO = json.get("more_info").getAsBoolean();
        json.get("info_literal").getAsJsonObject().asMap().forEach(
            (k,v)->MORE_TOOLTIPS_LITERAL.put(k, v.getAsJsonArray().asList().stream().map(i->i.getAsString()).toList())
        );
        json.get("info_translatable").getAsJsonObject().asMap().forEach(
            (k,v)->MORE_TOOLTIPS_TRANSLATABLE.put(k, v.getAsJsonArray().asList().stream().map(i->i.getAsString()).toList())
        );
        LOGGER.info("["+ModInit.MOD_ID+"] Read config end!");
    }

    private static String defaultContext() {
        return """
            {
                // 是否显示额外的物品信息？（燃烧时间、使用效果、耐久度等）
                // 是否額外顯示物品資訊？（燃燒時間、使用效果、耐久度等）
                //
                // 默认为true
                // 默認爲true
                "more_info":true,

                // 以硬编码的形式添加额外显示的信息
                // 以硬编码的形式添加額外顯示的信息
                // 
                // 格式：\"<modid>:<物品注册名>\":[\"文本\"]（物品注册名可以在游戏内按下F3+H查看）
                // 格式：\"<modid>:<物品注冊名>\":[\"文本\"]（物品注冊名可以在游戲内按下F3+H查看）
                "info_literal":{},

                // 以翻译键的形式添加额外显示的信息
                // 以翻譯鍵的形式添加額外顯示的信息
                //
                // 格式同上
                "info_translatable":{}
}""";
    }

    // @ExpectPlatform
    // public static Boolean whiteOrBlack(){
    //     throw new AssertionError();
    // }
}
