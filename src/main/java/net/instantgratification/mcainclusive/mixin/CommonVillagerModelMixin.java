// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
            // Empty container breasts part so getBreastPart().visible check passes
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

    @Inject(method = "renderCommon", at = @At("HEAD"), cancellable = true, remap = false)
    private void onRenderCommon(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color, CallbackInfo ci) {
        // Head
        float headSize = getDimensions().getHead();
        matrices.pushPose();
        matrices.scale(headSize, headSize, headSize);
        getCommonHeadParts().forEach(a -> a.render(matrices, vertices, light, overlay, color));
        matrices.popPose();

        // Body
        getCommonBodyParts().forEach(a -> a.render(matrices, vertices, light, overlay, color));

        // Breasts (Independent Left and Right Matrix Scaling!)
        if (getBreastPart().visible && getBodyPart().visible) {
            float baseSize = getBreastSize();
            if (baseSize == 0 && MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
                baseSize = 0.5f;
            }

            float leftMult = MCAInclusiveExpressionsAddon.getLeftScaleMultiplier();
            float rightMult = MCAInclusiveExpressionsAddon.getRightScaleMultiplier();

            float leftBreastSize = baseSize * leftMult * getDimensions().getBreasts();
            float rightBreastSize = baseSize * rightMult * getDimensions().getBreasts();

            // Render Left Breast with leftScale Matrix
            if (leftBreastSize > 0 && this.leftBreastPart != null) {
                matrices.pushPose();
                matrices.scale(
                    leftBreastSize * 0.2f + 1.05f,
                    leftBreastSize * 0.75f + 0.75f,
                    leftBreastSize * 0.75f + 0.75f
                );
                this.leftBreastPart.render(matrices, vertices, light, overlay, color);
                matrices.popPose();
            }

            // Render Right Breast with rightScale Matrix
            if (rightBreastSize > 0 && this.rightBreastPart != null) {
                matrices.pushPose();
                matrices.scale(
                    rightBreastSize * 0.2f + 1.05f,
                    rightBreastSize * 0.75f + 0.75f,
                    rightBreastSize * 0.75f + 0.75f
                );
                this.rightBreastPart.render(matrices, vertices, light, overlay, color);
                matrices.popPose();
            }
        }
        ci.cancel();
    }
}
