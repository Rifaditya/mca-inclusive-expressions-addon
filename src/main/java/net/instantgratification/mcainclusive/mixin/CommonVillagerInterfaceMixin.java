// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.conczin.mca.client.model.CommonVillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.instantgratification.mcainclusive.ducks.CommonVillagerModelDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

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

        // Breasts (Direct 1:1 Linear Matrix Scaling with MCA's native -35° angle!)
        if (self.getBreastPart().visible && self.getBodyPart().visible) {
            float baseSize = self.getBreastSize();
            if (baseSize == 0 && MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
                baseSize = 0.5f;
            }

            float leftMult = MCAInclusiveExpressionsAddon.getLeftScaleMultiplier();
            float rightMult = MCAInclusiveExpressionsAddon.getRightScaleMultiplier();
            float leftX = 0.0f, leftY = 0.0f, leftZ = 0.0f;
            float rightX = 0.0f, rightY = 0.0f, rightZ = 0.0f;

            if (self instanceof CommonVillagerModelDuck duck) {
                leftMult = duck.getRenderLeftScale();
                rightMult = duck.getRenderRightScale();
                leftX = duck.getRenderLeftX();
                leftY = duck.getRenderLeftY();
                leftZ = duck.getRenderLeftZ();
                rightX = duck.getRenderRightX();
                rightY = duck.getRenderRightY();
                rightZ = duck.getRenderRightZ();
            }

            // Direct linear 1:1 scaling (100% = 1.0x, 500% = 5.0x, 1000% = 10.0x)
            float leftBreastSize = baseSize * leftMult * self.getDimensions().getBreasts();
            float rightBreastSize = baseSize * rightMult * self.getDimensions().getBreasts();

            for (ModelPart part : self.getBreastParts()) {
                if (part == null || !part.visible || part.skipDraw) continue;

                // Push pose and apply part's native position & rotation (-35 degrees!)
                matrices.pushPose();
                part.translateAndRotate(matrices);

                ModelPartAccessor partAccess = (ModelPartAccessor) (Object) part;
                List<ModelPart.Cube> cubes = partAccess.getCubes();
                if (cubes != null && cubes.size() >= 2) {
                    // Render Left Breast Cube (Index 0) with direct 1:1 linear scale and 3D translation
                    if (leftBreastSize > 0) {
                        matrices.pushPose();
                        matrices.translate(leftX, leftY, leftZ);
                        matrices.scale(leftBreastSize, leftBreastSize, leftBreastSize);
                        cubes.get(0).compile(matrices.last(), vertices, light, overlay, color);
                        matrices.popPose();
                    }

                    // Render Right Breast Cube (Index 1) with direct 1:1 linear scale and 3D translation
                    if (rightBreastSize > 0) {
                        matrices.pushPose();
                        matrices.translate(rightX, rightY, rightZ);
                        matrices.scale(rightBreastSize, rightBreastSize, rightBreastSize);
                        cubes.get(1).compile(matrices.last(), vertices, light, overlay, color);
                        matrices.popPose();
                    }
                } else if (cubes != null && !cubes.isEmpty()) {
                    // Single cube fallback
                    float avgSize = (leftBreastSize + rightBreastSize) / 2.0f;
                    if (avgSize > 0) {
                        matrices.pushPose();
                        matrices.scale(avgSize, avgSize, avgSize);
                        for (ModelPart.Cube cube : cubes) {
                            cube.compile(matrices.last(), vertices, light, overlay, color);
                        }
                        matrices.popPose();
                    }
                }

                // Render any sub-children of this part (e.g. breast wear layers)
                Map<String, ModelPart> children = partAccess.getChildren();
                if (children != null) {
                    for (ModelPart child : children.values()) {
                        child.render(matrices, vertices, light, overlay, color);
                    }
                }

                matrices.popPose();
            }
        }
        ci.cancel();
    }
}
