package ru.femboypig.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.femboypig.modmenu.SAC;

@Mixin(LivingEntity.class)
public class MixinEntityLiving {

    @Inject(method = {"getHandSwingDuration"}, at = {@At("HEAD")}, cancellable = true)
    private void getArmSwingAnimationEnd(final CallbackInfoReturnable<Integer> info) {
        if (SAC.enabledAnim) {
            if (SAC.slowAnim) {
                info.setReturnValue((int) SAC.slowAnimSpeed);
            }
        }
    }
}