// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.render.VillagerVisuals;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerVisuals.class, remap = false)
public abstract class VillagerVisualsMixin {
    @Inject(method = "female", at = @At("HEAD"), cancellable = true)
    private void onFemale(CallbackInfoReturnable<Boolean> cir) {
        if (MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
            cir.setReturnValue(true);
        }
    }
}
