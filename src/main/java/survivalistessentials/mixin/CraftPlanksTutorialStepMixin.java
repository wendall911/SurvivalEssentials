package survivalistessentials.mixin;

import net.minecraft.client.tutorial.CraftPlanksTutorialStep;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftPlanksTutorialStep.class)
public abstract class CraftPlanksTutorialStepMixin {

    @Inject(method = "tick",
        at = @At("HEAD"), cancellable = true)
    private void injectTick(CallbackInfo info) {
        info.cancel();
    }

}
