// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.entity.ai.relationship.VillagerDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerDimensions.Mutable.class, remap = false)
public abstract class VillagerDimensionsMixin {
    @Shadow private float breasts;

    @Inject(method = "getBreasts", at = @At("HEAD"), cancellable = true)
    private void onGetBreasts(CallbackInfoReturnable<Float> cir) {
        if (this.breasts <= 0.0f) {
            cir.setReturnValue(1.0f);
        }
    }
}
