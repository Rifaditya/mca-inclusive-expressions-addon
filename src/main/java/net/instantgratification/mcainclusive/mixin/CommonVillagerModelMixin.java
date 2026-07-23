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
import net.instantgratification.mcainclusive.model.CommonVillagerModelAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerEntityBaseModelMCA.class, remap = false)
public abstract class CommonVillagerModelMixin extends HumanoidModel<VillagerRenderState> implements CommonVillagerModel<VillagerRenderState>, CommonVillagerModelAccess {
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

    @Inject(method = "newBreasts", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onNewBreasts(CubeDeformation dilation, int oy, CallbackInfoReturnable<CubeListBuilder> cir) {
        // Return empty container for breasts so MCA doesn't bake single wide boxes
        cir.setReturnValue(CubeListBuilder.create());
    }

    @Inject(method = "getModelData", at = @At("RETURN"), cancellable = true, remap = false)
    private static void onGetModelData(CubeDeformation dilation, CallbackInfoReturnable<MeshDefinition> cir) {
        MeshDefinition modelData = cir.getReturnValue();
        if (modelData != null) {
            PartDefinition root = modelData.getRoot();
            root.addOrReplaceChild("breasts", CubeListBuilder.create(), PartPose.ZERO);
            // Left Breast Box (pivot X = -1.75F)
            root.addOrReplaceChild("left_breast", CubeListBuilder.create().texOffs(18, 21).addBox(-1.5F, -1.25F, -1.5F, 2.75F, 3, 3, dilation), PartPose.offset(-1.75F, 0.0F, 0.0F));
            // Right Breast Box (pivot X = +1.75F)
            root.addOrReplaceChild("right_breast", CubeListBuilder.create().texOffs(21, 21).addBox(-1.25F, -1.25F, -1.5F, 2.75F, 3, 3, dilation), PartPose.offset(1.75F, 0.0F, 0.0F));
        }
    }

    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void onSetupAnim(VillagerRenderState state, CallbackInfo ci) {
        if (this.breasts != null) {
            this.breasts.visible = true;
        }

        if (this.leftBreastPart != null && this.rightBreastPart != null) {
            CommonVillagerModel.copyPartState(this.leftBreastPart, this.body);
            CommonVillagerModel.copyPartState(this.rightBreastPart, this.body);

            this.leftBreastPart.x += -1.75F;
            this.rightBreastPart.x += 1.75F;
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

    @Override
    public ModelPart getLeftBreastPart() {
        return this.leftBreastPart;
    }

    @Override
    public ModelPart getRightBreastPart() {
        return this.rightBreastPart;
    }
}
