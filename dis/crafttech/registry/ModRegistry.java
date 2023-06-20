package inkandsoul.crafttech.registry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.RegistrarManager;
import inkandsoul.splash.ModInit;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class ModRegistry {

    private static Map<ResourceLocation, Item> items = new LinkedHashMap<>();
    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(ModInit.MOD_ID));
    
    public static ItemLike put(ResourceLocation arg0, Item arg1) {
        return items.put(arg0, arg1);
    }

    private static ItemLike put(String arg0, Item arg1) {
        return put(new ResourceLocation(ModInit.MOD_ID, arg0), arg1);
    }

    // public static final Registrar<Item> reg = REGISTRIES.get().builder(
    //     new ResourceLocation(ModInit.MOD_ID, "item"), new Item[]{}).build();

    public static void init() {
        // reg.register(new ResourceLocation(ModInit.MOD_ID, "test"), ()->new Item(new Item.Properties()));
    }

    public static class ItemT extends Item{

        private MutableComponent name;

        public ItemT(MutableComponent name, Properties properties) {
            super(properties);
            this.name = name;
        }

        @Override
        public Component getDescription() {
            return name;
        }

        @Override
        public Component getName(ItemStack itemStack) {
            return name;
        }
    }

    public static final CreativeModeTab ITEMS = CreativeTabRegistry.create(new ResourceLocation(ModInit.MOD_ID, "items"), () ->
            new ItemStack(Items.APPLE)).get();

    static{
        put("test", new ItemT(Component.literal("測試物品1"), new Item.Properties()));
        items.forEach((k,v)->Registry.register(BuiltInRegistries.ITEM, k, v));
        CreativeTabRegistry.appendStack(ITEMS, 
            new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(ModInit.MOD_ID, "test"))));
    }
}
