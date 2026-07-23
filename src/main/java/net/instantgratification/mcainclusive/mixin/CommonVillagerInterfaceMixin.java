// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.conczin.mca.client.model.CommonVillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.instantgratification.mcainclusive.model.CommonVillagerModelAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CommonVillagerModel.class, remap = false)
public interface CommonVillagerInterfaceMixin {

    @Inject(method = "renderCommon", at = @At("HEAD"), cancellable = true, remap = false)
    private void onRenderCommon(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color, CallbackInfo ci) {
        CommonVillagerModel<?> self = (CommonVillagerModel<?>) this;

        // Head
        float headSize = self.getDimensions().getHead();
        matrices.pushPose();
        matrices.scale(headSize, headSize, headSize);
        self.getCommonHeadParts().forEach(a -> a.render(matrices, vertices, light, overlay, color));
        matrices.popPose();

        // Body
        self.getCommonBodyParts().forEach(a -> a.render(matrices, vertices, light, overlay, color));

        // Breasts (Independent Left and Right Matrix Scaling!)
        if (self.getBreastPart().visible && self.getBodyPart().visible) {
            float baseSize = self.getBreastSize();
            if (baseSize == 0 && MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
                baseSize = 0.5f;
            }

            float leftMult = MCAInclusiveExpressionsAddon.getLeftScaleMultiplier();
            float rightMult = MCAInclusiveExpressionsAddon.getRightScaleMultiplier();

            float leftBreastSize = baseSize * leftMult * self.getDimensions().getBreasts();
            float rightBreastSize = baseSize * rightMult * self.getDimensions().getBreasts();

            ModelPart leftPart = null;
            ModelPart rightPart = null;

            if (self instanceof CommonVillagerModelAccess access) {
                leftPart = access.getLeftBreastPart();
                rightPart = access.getRightBreastPart();
            }

            // Render Left Breast with leftScale Matrix
            if (leftBreastSize > 0 && leftPart != null) {
                matrices.pushPose();
                matrices.scale(
                    leftBreastSize * 0.2f + 1.05f,
                    leftBreastSize * 0.75f + 0.75f,
                    leftBreastSize * 0.75f + 0.75f
                );
                leftPart.render(matrices, vertices, light, overlay, color);
                matrices.popPose();
            }

            // Render Right Breast with rightScale Matrix
            if (rightBreastSize > 0 && rightPart != null) {
                matrices.pushPose();
                matrices.scale(
                    rightBreastSize * 0.2f + 1.05f,
                    rightBreastSize * 0.75f + 0.75f,
                    rightBreastSize * 0.75f + 0.75f
                );
                rightPart.render(matrices, vertices, light, overlay, color);
                matrices.popPose();
            }
        }
        ci.cancel();
    }
}
