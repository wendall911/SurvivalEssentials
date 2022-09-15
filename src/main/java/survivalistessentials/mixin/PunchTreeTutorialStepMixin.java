package survivalessentials.mixin;

import net.minecraft.client.tutorial.PunchTreeTutorialStepInstance;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PunchTreeTutorialStepInstance.class)
public abstract class PunchTreeTutorialStepMixin {

    @Inject(method = "tick",
        at = @At("HEAD"), cancellable = true)
    private void injectTick(CallbackInfo info) {
        info.cancel();
    }

}
