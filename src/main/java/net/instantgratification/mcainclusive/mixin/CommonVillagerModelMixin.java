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
import net.instantgratification.mcainclusive.ducks.CommonVillagerModelDuck;
import net.instantgratification.mcainclusive.ducks.VillagerRenderStateDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerEntityBaseModelMCA.class, remap = false)
public abstract class CommonVillagerModelMixin extends HumanoidModel<VillagerRenderState> implements CommonVillagerModel<VillagerRenderState>, CommonVillagerModelDuck {
    @Shadow public ModelPart breasts;
    @Shadow float breastSize;

    @Unique private float currentLeftScale = 1.0f;
    @Unique private float currentRightScale = 1.0f;

    @Unique private float currentLeftX = 0.0f;
    @Unique private float currentLeftY = 0.0f;
    @Unique private float currentLeftZ = 0.0f;

    @Unique private float currentRightX = 0.0f;
    @Unique private float currentRightY = 0.0f;
    @Unique private float currentRightZ = 0.0f;

    @Unique private float currentLeftPitch = 0.0f;
    @Unique private float currentLeftYaw = 0.0f;
    @Unique private float currentLeftRoll = 0.0f;

    @Unique private float currentRightPitch = 0.0f;
    @Unique private float currentRightYaw = 0.0f;
    @Unique private float currentRightRoll = 0.0f;

    protected CommonVillagerModelMixin(ModelPart root) {
        super(root);
    }

    @Override public float getRenderLeftScale() { return this.currentLeftScale; }
    @Override public void setRenderLeftScale(float val) { this.currentLeftScale = val; }

    @Override public float getRenderRightScale() { return this.currentRightScale; }
    @Override public void setRenderRightScale(float val) { this.currentRightScale = val; }

    @Override public float getRenderLeftX() { return this.currentLeftX; }
    @Override public void setRenderLeftX(float val) { this.currentLeftX = val; }

    @Override public float getRenderLeftY() { return this.currentLeftY; }
    @Override public void setRenderLeftY(float val) { this.currentLeftY = val; }

    @Override public float getRenderLeftZ() { return this.currentLeftZ; }
    @Override public void setRenderLeftZ(float val) { this.currentLeftZ = val; }

    @Override public float getRenderRightX() { return this.currentRightX; }
    @Override public void setRenderRightX(float val) { this.currentRightX = val; }

    @Override public float getRenderRightY() { return this.currentRightY; }
    @Override public void setRenderRightY(float val) { this.currentRightY = val; }

    @Override public float getRenderRightZ() { return this.currentRightZ; }
    @Override public void setRenderRightZ(float val) { this.currentRightZ = val; }

    @Override public float getRenderLeftPitch() { return this.currentLeftPitch; }
    @Override public void setRenderLeftPitch(float val) { this.currentLeftPitch = val; }

    @Override public float getRenderLeftYaw() { return this.currentLeftYaw; }
    @Override public void setRenderLeftYaw(float val) { this.currentLeftYaw = val; }

    @Override public float getRenderLeftRoll() { return this.currentLeftRoll; }
    @Override public void setRenderLeftRoll(float val) { this.currentLeftRoll = val; }

    @Override public float getRenderRightPitch() { return this.currentRightPitch; }
    @Override public void setRenderRightPitch(float val) { this.currentRightPitch = val; }

    @Override public float getRenderRightYaw() { return this.currentRightYaw; }
    @Override public void setRenderRightYaw(float val) { this.currentRightYaw = val; }

    @Override public float getRenderRightRoll() { return this.currentRightRoll; }
    @Override public void setRenderRightRoll(float val) { this.currentRightRoll = val; }

    @Inject(method = "setupAnim", at = @At("HEAD"))
    private void onSetupAnim(VillagerRenderState state, CallbackInfo ci) {
        if (state instanceof VillagerRenderStateDuck stateDuck) {
            this.currentLeftScale = stateDuck.getLeftBreastScale();
            this.currentRightScale = stateDuck.getRightBreastScale();

            this.currentLeftX = stateDuck.getLeftBreastX();
            this.currentLeftY = stateDuck.getLeftBreastY();
            this.currentLeftZ = stateDuck.getLeftBreastZ();

            this.currentRightX = stateDuck.getRightBreastX();
            this.currentRightY = stateDuck.getRightBreastY();
            this.currentRightZ = stateDuck.getRightBreastZ();

            this.currentLeftPitch = stateDuck.getLeftBreastPitch();
            this.currentLeftYaw = stateDuck.getLeftBreastYaw();
            this.currentLeftRoll = stateDuck.getLeftBreastRoll();

            this.currentRightPitch = stateDuck.getRightBreastPitch();
            this.currentRightYaw = stateDuck.getRightBreastYaw();
            this.currentRightRoll = stateDuck.getRightBreastRoll();

            if (this.currentLeftScale > 0 || this.currentRightScale > 0 || MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
                if (this.breasts != null) {
                    this.breasts.visible = true;
                }
                if (this.breastSize <= 0) {
                    this.breastSize = 0.5f;
                }
            }
        }
    }

    @Inject(method = "newBreasts", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onNewBreasts(CubeDeformation dilation, int oy, CallbackInfoReturnable<CubeListBuilder> cir) {
        CubeListBuilder builder = CubeListBuilder.create();
        if (net.conczin.mca.Config.getInstance().enableBoobs) {
            // Left Breast Box (width 3, height 3, depth 3) -> Index 0 in cubes list
            builder.texOffs(18, 21 + oy).addBox(-3.25F, -1.25F, -1.5F, 3, 3, 3, dilation);
            // Right Breast Box (width 3, height 3, depth 3) -> Index 1 in cubes list
            builder.texOffs(21, 21 + oy).addBox(0.25F, -1.25F, -1.5F, 3, 3, 3, dilation);
        }
        cir.setReturnValue(builder);
    }

    @Inject(method = "getBreastSize", at = @At("HEAD"), cancellable = true)
    private void onGetBreastSize(CallbackInfoReturnable<Float> cir) {
        float baseSize = this.breastSize;
        if (baseSize == 0 && MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
            baseSize = 0.5f;
        }
        cir.setReturnValue(baseSize);
    }
}
