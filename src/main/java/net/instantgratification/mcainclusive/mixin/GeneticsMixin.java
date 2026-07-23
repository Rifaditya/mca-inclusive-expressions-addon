// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.entity.ai.Genetics;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Genetics.class, remap = false)
public abstract class GeneticsMixin {
    @Shadow
    public abstract float getGene(Genetics.GeneType type);

    @Inject(method = "getBreastSize", at = @At("HEAD"), cancellable = true)
    private void onGetBreastSize(CallbackInfoReturnable<Float> cir) {
        // If allow_all_genders is enabled via GameRule, allow male/neutral characters to evaluate breast gene
        boolean allowAllGenders = false;
        try {
            var serverOpt = net.conczin.mca.MCA.getServer();
            if (serverOpt.isPresent() && MCAInclusiveExpressionsAddon.ALLOW_ALL_GENDERS_RULE != null) {
                allowAllGenders = serverOpt.get().getGameRules().get(MCAInclusiveExpressionsAddon.ALLOW_ALL_GENDERS_RULE);
            }
        } catch (Throwable ignored) {
        }

        if (allowAllGenders) {
            cir.setReturnValue(this.getGene(Genetics.BREAST));
        }
    }
}
