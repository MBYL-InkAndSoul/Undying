package inkandsoul.undying.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import inkandsoul.undying.ModConfig;
import inkandsoul.undying.ModInit;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(ItemStack.class)
public class MixinItemStack {
    /**
     * Copy from Unbreakabler/1.19 at 23/6/1
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void removeDurability(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected, CallbackInfo ci) {
        if(!ModConfig.IMPLEMENT_WAY){
            ModInit.giveUnbreakableTag(stack, world);
        }
    }

    @Inject(method = "getDamageValue", at = @At("RETURN"), cancellable = true)
    public void getIDamageValue(CallbackInfoReturnable<Integer> ci) {
        if(ModConfig.IMPLEMENT_WAY){
            var self = (ItemStack)(Object)this;
            String regName = BuiltInRegistries.ITEM.getKey(self.getItem()).toString();
            if(ModInit.is(regName)){
                ci.setReturnValue(self.getMaxDamage());
            }
        }
    }
  
    @Inject(method = "setDamageValue", at = @At("HEAD"), cancellable = true)
    public void setIDamageValue(int i, CallbackInfo ci) {
        if(ModConfig.IMPLEMENT_WAY){
            var self = (ItemStack)(Object)this;
            String regName = BuiltInRegistries.ITEM.getKey(self.getItem()).toString();
            if(!ModInit.is(regName)){
                self.getOrCreateTag().putInt("Damage", self.getMaxDamage());
            }
            ci.cancel();
        }
    }
}
