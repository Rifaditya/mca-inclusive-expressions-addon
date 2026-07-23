// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.model.CommonVillagerModel;
import net.conczin.mca.client.model.VillagerEntityBaseModelMCA;
import net.conczin.mca.client.render.VillagerRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
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
        // Return empty container for breasts so breasts has 0 direct boxes
        cir.setReturnValue(CubeListBuilder.create());
    }

    @Inject(method = "getModelData", at = @At("RETURN"), cancellable = true, remap = false)
    private static void onGetModelData(CubeDeformation dilation, CallbackInfoReturnable<MeshDefinition> cir) {
        MeshDefinition modelData = cir.getReturnValue();
        if (modelData != null) {
            PartDefinition root = modelData.getRoot();
            PartDefinition breasts = root.addOrReplaceChild("breasts", CubeListBuilder.create(), PartPose.ZERO);
            // Left Breast Box (width 3, height 3, depth 3) inside breasts
            breasts.addOrReplaceChild("left", CubeListBuilder.create().texOffs(18, 21).addBox(-3.25F, -1.25F, -1.5F, 3, 3, 3, dilation), PartPose.ZERO);
            // Right Breast Box (width 3, height 3, depth 3) inside breasts
            breasts.addOrReplaceChild("right", CubeListBuilder.create().texOffs(21, 21).addBox(0.25F, -1.25F, -1.5F, 3, 3, 3, dilation), PartPose.ZERO);
        }
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
