// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.model.CommonVillagerModel;
import net.conczin.mca.client.model.VillagerEntityBaseModelMCA;
import net.conczin.mca.client.render.VillagerRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerEntityBaseModelMCA.class, remap = false)
public abstract class CommonVillagerModelMixin extends HumanoidModel<VillagerRenderState> implements CommonVillagerModel<VillagerRenderState> {
    @Shadow
    public ModelPart breasts;
    @Shadow
    float breastSize;

    protected CommonVillagerModelMixin(ModelPart root) {
        super(root);
    }

    @Inject(method = "newBreasts", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onNewBreasts(CubeDeformation dilation, int oy, CallbackInfoReturnable<CubeListBuilder> cir) {
        CubeListBuilder builder = CubeListBuilder.create();
        if (net.conczin.mca.Config.getInstance().enableBoobs) {
            // Left Breast Box (width 3, height 3, depth 3)
            builder.texOffs(18, 21 + oy).addBox(-3.25F, -1.25F, -1.5F, 3, 3, 3, dilation);
            // Right Breast Box (width 3, height 3, depth 3)
            builder.texOffs(21, 21 + oy).addBox(0.25F, -1.25F, -1.5F, 3, 3, 3, dilation);
        }
        cir.setReturnValue(builder);
    }

    @Inject(method = "getBreastSize", at = @At("HEAD"), cancellable = true)
    private void onGetBreastSize(CallbackInfoReturnable<Float> cir) {
        float multiplier = MCAInclusiveExpressionsAddon.getAverageScaleMultiplier();
        float baseSize = this.breastSize;
        if (baseSize == 0 && MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
            baseSize = 0.5f;
        }
        cir.setReturnValue(baseSize * multiplier);
    }
}
