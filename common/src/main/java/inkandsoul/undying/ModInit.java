package inkandsoul.undying;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

// import inkandsoul.crafttech.registry.ModItems;

public class ModInit {
    public static final String MOD_ID = "undying";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        ModConfig.init();
        LOGGER.info("Undying is loaded!");
    }

    public static boolean is(String regName){
        if (ModConfig.LIST.contains(regName)) {
            if (ModConfig.WHITE_OR_BLACK) {
                return false;
            }
        } else {
            if (!ModConfig.WHITE_OR_BLACK) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copy from Unbreakabler/1.19 at 23/6/1
     */
    public static void giveUnbreakableTag(ItemStack stack, Level world) {
        if (!world.isClientSide || !stack.isDamageableItem()) {
            String regName = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
            if(is(regName)){
                return;
            }
        }
        
        stack.getOrCreateTag().putBoolean("Unbreakable", true);
    }
}
