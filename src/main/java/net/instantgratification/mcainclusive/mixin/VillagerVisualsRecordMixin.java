// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.render.VillagerVisuals;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerVisuals.class, remap = false)
public abstract class VillagerVisualsRecordMixin {
    @Shadow public abstract float breastSize();

    @Inject(method = "female", at = @At("HEAD"), cancellable = true)
    private void onFemale(CallbackInfoReturnable<Boolean> cir) {
        if (MCAInclusiveExpressionsAddon.isForceAllBreasted() || this.breastSize() > 0.0f) {
            cir.setReturnValue(true);
        }
    }
}
