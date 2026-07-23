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

    public void renderDualBreasts(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        int cleavageAngle = MCAInclusiveExpressionsAddon.getCleavageAngle();
        float scaledSize = getBreastSize() * getDimensions().getBreasts();

        if (getBreastPart().visible && getBodyPart().visible && scaledSize > 0) {
            float radAngle = (float) Math.toRadians(cleavageAngle);

            matrices.pushPose();
            // Inherit torso pitch, crouching, and riding translations
            this.getBodyPart().translateAndRotate(matrices);

            matrices.scale(
                    scaledSize * 0.2f + 1.05f,
                    scaledSize * 0.75f + 0.75f,
                    scaledSize * 0.75f + 0.75f
            );

            // Left Breast (Offset left & rotates +radAngle)
            matrices.pushPose();
            matrices.translate(-0.0625f * 1.5f, 0, 0);
            matrices.mulPose(new Quaternionf().rotationY(radAngle));
            for (ModelPart part : getBreastParts()) {
                part.render(matrices, vertices, light, overlay, color);
            }
            matrices.popPose();

            // Right Breast (Offset right & rotates -radAngle)
            matrices.pushPose();
            matrices.translate(0.0625f * 1.5f, 0, 0);
            matrices.mulPose(new Quaternionf().rotationY(-radAngle));
            for (ModelPart part : getBreastParts()) {
                part.render(matrices, vertices, light, overlay, color);
            }
            matrices.popPose();

            matrices.popPose();
        }
    }
}
