// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.conczin.mca.client.model.CommonVillagerModel;
import net.conczin.mca.client.model.VillagerEntityBaseModelMCA;
import net.minecraft.client.model.geom.ModelPart;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import org.joml.Quaternionf;
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
        cir.setReturnValue(this.breastSize * multiplier);
    }

    @Inject(method = "renderCommon", at = @At("HEAD"), cancellable = true)
    private void onRenderCommon(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color, CallbackInfo ci) {
        int cleavageAngle = MCAInclusiveExpressionsAddon.getCleavageAngle();
        if (cleavageAngle <= 0) {
            return; // Use default un-rotated single box rendering if angle is zero
        }

        // Custom dual-mesh separate left/right breast rendering with cleavage outward angle
        float headSize = getDimensions().getHead();

        matrices.pushPose();
        matrices.scale(headSize, headSize, headSize);
        getCommonHeadParts().forEach(a -> a.render(matrices, vertices, light, overlay, color));
        matrices.popPose();

        getCommonBodyParts().forEach(a -> a.render(matrices, vertices, light, overlay, color));

        if (getBreastPart().visible && getBodyPart().visible) {
            float scaledSize = getBreastSize() * getDimensions().getBreasts();

            if (scaledSize > 0) {
                float radAngle = (float) Math.toRadians(cleavageAngle);

                matrices.pushPose();
                matrices.scale(
                        scaledSize * 0.2f + 1.05f,
                        scaledSize * 0.75f + 0.75f,
                        scaledSize * 0.75f + 0.75f
                );

                // Render Left Breast with +radAngle Y-rotation
                matrices.pushPose();
                matrices.mulPose(new Quaternionf().rotationY(radAngle));
                for (ModelPart part : getBreastParts()) {
                    part.render(matrices, vertices, light, overlay, color);
                }
                matrices.popPose();

                // Render Right Breast with -radAngle Y-rotation
                matrices.pushPose();
                matrices.mulPose(new Quaternionf().rotationY(-radAngle));
                for (ModelPart part : getBreastParts()) {
                    part.render(matrices, vertices, light, overlay, color);
                }
                matrices.popPose();

                matrices.popPose();
            }
        }
        ci.cancel();
    }
}
