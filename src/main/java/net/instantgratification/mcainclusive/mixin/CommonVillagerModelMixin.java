// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.model.CommonVillagerModel;
import net.conczin.mca.client.model.VillagerEntityBaseModelMCA;
import net.conczin.mca.client.render.VillagerVisuals;
import net.minecraft.client.model.geom.ModelPart;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerEntityBaseModelMCA.class, remap = false)
public abstract class CommonVillagerModelMixin implements CommonVillagerModel<Object> {
    @Shadow
    float breastSize;

    @Inject(method = "getBreastSize", at = @At("HEAD"), cancellable = true)
    private void onGetBreastSize(CallbackInfoReturnable<Float> cir) {
        float multiplier = MCAInclusiveExpressionsAddon.getScaleMultiplier();
        float baseSize = this.breastSize;
        if (baseSize == 0 && MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
            baseSize = 0.5f;
        }
        cir.setReturnValue(baseSize * multiplier);
    }

    @Inject(method = "applyVillagerDimensions", at = @At("TAIL"))
    private void onApplyVillagerDimensions(VillagerVisuals visuals, boolean isSneaking, CallbackInfo ci) {
        int cleavageAngle = MCAInclusiveExpressionsAddon.getCleavageAngle();
        float radAngle = (float) Math.toRadians(cleavageAngle);

        boolean allowAll = MCAInclusiveExpressionsAddon.isAllowAllGenders();
        if (allowAll) {
            getBreastPart().visible = true;
        }

        for (ModelPart part : getBreastParts()) {
            part.yRot = radAngle;
        }
    }
}
