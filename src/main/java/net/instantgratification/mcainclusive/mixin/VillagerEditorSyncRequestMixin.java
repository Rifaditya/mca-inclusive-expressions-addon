// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.network.c2s.VillagerEditorSyncRequest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerEditorSyncRequest.class, remap = false)
public abstract class VillagerEditorSyncRequestMixin {

    @Inject(method = "isAllowedTopLevelKey", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onIsAllowedTopLevelKey(String key, CallbackInfoReturnable<Boolean> cir) {
        if (key != null && key.startsWith("mca_inclusive_expressions:")) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isAllowedMcaKey", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onIsAllowedMcaKey(String key, CallbackInfoReturnable<Boolean> cir) {
        if (key != null && key.startsWith("mca_inclusive_expressions:")) {
            cir.setReturnValue(true);
        }
    }
}
