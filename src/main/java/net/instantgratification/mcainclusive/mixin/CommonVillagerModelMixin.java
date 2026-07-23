// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import com.google.common.collect.ImmutableList;
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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerEntityBaseModelMCA.class, remap = false)
public abstract class CommonVillagerModelMixin extends HumanoidModel<VillagerRenderState> implements CommonVillagerModel<VillagerRenderState> {
    @Shadow
    public ModelPart breasts;
    @Shadow
    float breastSize;

    @Unique
    private ModelPart leftBreastPart;
    @Unique
    private ModelPart rightBreastPart;

    protected CommonVillagerModelMixin(ModelPart root) {
        super(root);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInitTail(ModelPart root, CallbackInfo ci) {
        if (root.hasChild("left_breast")) {
            this.leftBreastPart = root.getChild("left_breast");
        }
        if (root.hasChild("right_breast")) {
            this.rightBreastPart = root.getChild("right_breast");
        }
    }

    @Inject(method = "getModelData", at = @At("RETURN"), cancellable = true, remap = false)
    private static void onGetModelData(CubeDeformation dilation, CallbackInfoReturnable<MeshDefinition> cir) {
        MeshDefinition modelData = cir.getReturnValue();
        if (modelData != null) {
            PartDefinition root = modelData.getRoot();
            // Container breasts part with 0 cubes so renderCommon visibility check passes
            root.addOrReplaceChild("breasts", CubeListBuilder.create(), PartPose.ZERO);
            // Left Breast Box (width 3, height 3, depth 3)
            root.addOrReplaceChild("left_breast", CubeListBuilder.create().texOffs(18, 21).addBox(-3.25F, -1.25F, -1.5F, 3, 3, 3, dilation), PartPose.ZERO);
            // Right Breast Box (width 3, height 3, depth 3)
            root.addOrReplaceChild("right_breast", CubeListBuilder.create().texOffs(21, 21).addBox(0.25F, -1.25F, -1.5F, 3, 3, 3, dilation), PartPose.ZERO);
        }
    }

    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void onSetupAnim(VillagerRenderState state, CallbackInfo ci) {
        // Keep container breasts part visible so CommonVillagerModel.renderCommon enters getBreastParts() loop
        if (this.breasts != null) {
            this.breasts.visible = true;
        }

        if (this.leftBreastPart != null && this.rightBreastPart != null) {
            // Copy body transforms using MCA's copyPartState helper
            CommonVillagerModel.copyPartState(this.leftBreastPart, this.body);
            CommonVillagerModel.copyPartState(this.rightBreastPart, this.body);

            float baseSize = this.breastSize;
            if (baseSize == 0 && MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
                baseSize = 0.5f;
            }

            float leftScale = baseSize * MCAInclusiveExpressionsAddon.getLeftScaleMultiplier();
            float rightScale = baseSize * MCAInclusiveExpressionsAddon.getRightScaleMultiplier();

            this.leftBreastPart.xScale = leftScale;
            this.leftBreastPart.yScale = leftScale;
            this.leftBreastPart.zScale = leftScale;
            this.leftBreastPart.visible = leftScale > 0.001f;

            this.rightBreastPart.xScale = rightScale;
            this.rightBreastPart.yScale = rightScale;
            this.rightBreastPart.zScale = rightScale;
            this.rightBreastPart.visible = rightScale > 0.001f;
        }
    }

    @Inject(method = "getBreastParts", at = @At("HEAD"), cancellable = true)
    private void onGetBreastParts(CallbackInfoReturnable<Iterable<ModelPart>> cir) {
        if (this.leftBreastPart != null && this.rightBreastPart != null) {
            cir.setReturnValue(ImmutableList.of(this.leftBreastPart, this.rightBreastPart));
        }
    }
}
