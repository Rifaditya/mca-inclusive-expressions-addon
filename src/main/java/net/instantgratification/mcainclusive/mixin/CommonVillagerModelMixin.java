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
import net.instantgratification.mcainclusive.ducks.VillagerRenderStateDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerEntityBaseModelMCA.class, remap = false)
public abstract class CommonVillagerModelMixin extends HumanoidModel<VillagerRenderState> implements CommonVillagerModel<VillagerRenderState> {
    @Shadow public ModelPart breasts;
    @Shadow float breastSize;

    @Unique public float currentLeftScale = 1.0f;
    @Unique public float currentRightScale = 1.0f;
    @Unique public float currentLeftX = 0.0f;
    @Unique public float currentLeftY = 0.0f;
    @Unique public float currentLeftZ = 0.0f;
    @Unique public float currentRightX = 0.0f;
    @Unique public float currentRightY = 0.0f;
    @Unique public float currentRightZ = 0.0f;

    protected CommonVillagerModelMixin(ModelPart root) {
        super(root);
    }

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
