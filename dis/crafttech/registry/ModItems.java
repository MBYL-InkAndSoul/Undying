package inkandsoul.crafttech.registry;

import dev.architectury.registry.registries.DeferredRegister;
import inkandsoul.splash.ModInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final DeferredRegister<Item> REG = DeferredRegister.create(ModInit.MOD_ID, Registries.ITEM);
}
