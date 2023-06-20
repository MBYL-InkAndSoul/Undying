package inkandsoul.splash.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import inkandsoul.splash.SplashText;
import net.minecraft.client.resources.SplashManager;

@Mixin(SplashManager.class)
public abstract class SplashManagerMixin {

    // @Accessor("splashes")
    // public abstract List<String> getSplashes();

    @Inject(method = "prepare", at = @At("RETURN"))
    public void addSplash(CallbackInfoReturnable<List<String>> ci){
        ci.getReturnValue().addAll(SplashText.text);
    }

    @Inject(method = "getSplash", at = @At("HEAD"), cancellable = true)
    public void getString(CallbackInfoReturnable<String> ci){
        var text = SplashText.EVENT.invoker().splashText();
        if(text != null){
            ci.setReturnValue(SplashText.EVENT.invoker().splashText());
        }
    }
}
