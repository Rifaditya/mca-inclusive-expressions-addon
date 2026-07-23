// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.conczin.mca.client.model.CommonVillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.instantgratification.mcainclusive.ducks.CommonVillagerModelDuck;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
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

        // Breasts (Orthogonal World-Space Position Translations + 3D Euler Rotations + Local Pivot Scaling)
        if (self.getBreastPart().visible && self.getBodyPart().visible) {
            float leftMult = MCAInclusiveExpressionsAddon.getLeftScaleMultiplier();
            float rightMult = MCAInclusiveExpressionsAddon.getRightScaleMultiplier();

            float leftX = 0.0f, leftY = 0.0f, leftZ = 0.0f;
            float rightX = 0.0f, rightY = 0.0f, rightZ = 0.0f;

            float leftPitch = 0.0f, leftYaw = 0.0f, leftRoll = 0.0f;
            float rightPitch = 0.0f, rightYaw = 0.0f, rightRoll = 0.0f;

            // 1. Read from in-world model duck
            if (self instanceof CommonVillagerModelDuck duck) {
                leftMult = duck.getRenderLeftScale();
                rightMult = duck.getRenderRightScale();

                leftX = duck.getRenderLeftX();
                leftY = duck.getRenderLeftY();
                leftZ = duck.getRenderLeftZ();

                rightX = duck.getRenderRightX();
                rightY = duck.getRenderRightY();
                rightZ = duck.getRenderRightZ();

                leftPitch = duck.getRenderLeftPitch();
                leftYaw = duck.getRenderLeftYaw();
                leftRoll = duck.getRenderLeftRoll();

                rightPitch = duck.getRenderRightPitch();
                rightYaw = duck.getRenderRightYaw();
                rightRoll = duck.getRenderRightRoll();
            }

            // 2. Failsafe Direct GUI Screen Override (When VillagerEditorScreen is open)
            try {
                if (MCAInclusiveExpressionsAddon.activeEditorScreen instanceof VillagerEditorScreenAccess access) {
                    if (access.getVillagerVisualization() != null || access.getVillager() != null) {
                        GeneticsDuck guiDuck = MCAInclusiveExpressionsAddon.getActiveGuiGenetics();
                        if (guiDuck != null) {
                            leftMult = guiDuck.getLeftBreastSize();
                            rightMult = guiDuck.getRightBreastSize();

                            leftX = guiDuck.getLeftBreastX();
                            leftY = guiDuck.getLeftBreastY();
                            leftZ = guiDuck.getLeftBreastZ();

                            rightX = guiDuck.getRightBreastX();
                            rightY = guiDuck.getRightBreastY();
                            rightZ = guiDuck.getRightBreastZ();

                            leftPitch = guiDuck.getLeftBreastPitch();
                            leftYaw = guiDuck.getLeftBreastYaw();
                            leftRoll = guiDuck.getLeftBreastRoll();

                            rightPitch = guiDuck.getRightBreastPitch();
                            rightYaw = guiDuck.getRightBreastYaw();
                            rightRoll = guiDuck.getRightBreastRoll();
                        }
                    }
                }
            } catch (Throwable ignored) {
            }

            // Native MCA 1:1 Scale Alignment at 100% (1.0x native MCA default max volume)
            float leftBreastSize = leftMult;
            float rightBreastSize = rightMult;

            for (ModelPart part : self.getBreastParts()) {
                if (part == null || !part.visible || part.skipDraw) continue;

                // Push pose and apply part's native position & rotation (-35 degrees!)
                matrices.pushPose();
                part.translateAndRotate(matrices);

                ModelPartAccessor partAccess = (ModelPartAccessor) (Object) part;
                List<ModelPart.Cube> cubes = partAccess.getCubes();
                if (cubes != null && cubes.size() >= 2) {
                    // Left Breast Box Center Pivot: (-1.75f, 0.25f, 0.0f) in model coordinates
                    float leftPivotX = -1.75f / 16.0f;
                    float leftPivotY = 0.25f / 16.0f;
                    float leftPivotZ = 0.0f;

                    if (leftBreastSize > 0) {
                        matrices.pushPose();
                        // 1. Temporarily un-rotate MCA's native -35° pitch tilt to enter pure orthogonal world space
                        if (part.xRot != 0.0f) {
                            matrices.mulPose(Axis.XP.rotation(-part.xRot));
                        }

                        // 2. Position Translation in pure orthogonal space (Up is Up, Left is Left, Forward is Forward)
                        matrices.translate(leftX, leftY, leftZ);

                        // 3. Re-apply MCA's native -35° pitch tilt
                        if (part.xRot != 0.0f) {
                            matrices.mulPose(Axis.XP.rotation(part.xRot));
                        }

                        // 4. Move to local pivot center
                        matrices.translate(leftPivotX, leftPivotY, leftPivotZ);

                        // 5. 3D Euler Rotations (Pitch X, Yaw Y, Roll Z)
                        if (leftPitch != 0.0f) matrices.mulPose(Axis.XP.rotationDegrees(leftPitch));
                        if (leftYaw != 0.0f) matrices.mulPose(Axis.YP.rotationDegrees(leftYaw));
                        if (leftRoll != 0.0f) matrices.mulPose(Axis.ZP.rotationDegrees(leftRoll));

                        // 6. Scale volume locally around pivot (zero position drift!)
                        matrices.scale(leftBreastSize, leftBreastSize, leftBreastSize);

                        // 7. Translate back from pivot
                        matrices.translate(-leftPivotX, -leftPivotY, -leftPivotZ);

                        cubes.get(0).compile(matrices.last(), vertices, light, overlay, color);
                        matrices.popPose();
                    }

                    // Right Breast Box Center Pivot: (+1.75f, 0.25f, 0.0f) in model coordinates
                    float rightPivotX = 1.75f / 16.0f;
                    float rightPivotY = 0.25f / 16.0f;
                    float rightPivotZ = 0.0f;

                    if (rightBreastSize > 0) {
                        matrices.pushPose();
                        // 1. Temporarily un-rotate MCA's native -35° pitch tilt to enter pure orthogonal world space
                        if (part.xRot != 0.0f) {
                            matrices.mulPose(Axis.XP.rotation(-part.xRot));
                        }

                        // 2. Position Translation in pure orthogonal space (Up is Up, Left is Left, Forward is Forward)
                        matrices.translate(rightX, rightY, rightZ);

                        // 3. Re-apply MCA's native -35° pitch tilt
                        if (part.xRot != 0.0f) {
                            matrices.mulPose(Axis.XP.rotation(part.xRot));
                        }

                        // 4. Move to local pivot center
                        matrices.translate(rightPivotX, rightPivotY, rightPivotZ);

                        // 5. 3D Euler Rotations (Pitch X, Yaw Y, Roll Z)
                        if (rightPitch != 0.0f) matrices.mulPose(Axis.XP.rotationDegrees(rightPitch));
                        if (rightYaw != 0.0f) matrices.mulPose(Axis.YP.rotationDegrees(rightYaw));
                        if (rightRoll != 0.0f) matrices.mulPose(Axis.ZP.rotationDegrees(rightRoll));

                        // 6. Scale volume locally around pivot (zero position drift!)
                        matrices.scale(rightBreastSize, rightBreastSize, rightBreastSize);

                        // 7. Translate back from pivot
                        matrices.translate(-rightPivotX, -rightPivotY, -rightPivotZ);

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
