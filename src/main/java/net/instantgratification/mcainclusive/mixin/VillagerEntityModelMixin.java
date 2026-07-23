// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.model.VillagerEntityModelMCA;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VillagerEntityModelMCA.class, remap = false)
public abstract class VillagerEntityModelMixin {

    @Inject(method = "bodyData", at = @At("RETURN"), remap = false)
    private static void onBodyData(CubeDeformation dilation, CallbackInfoReturnable<MeshDefinition> cir) {
        MeshDefinition modelData = cir.getReturnValue();
        if (modelData != null) {
            PartDefinition root = modelData.getRoot();
            PartDefinition breasts = root.addOrReplaceChild("breasts", CubeListBuilder.create(), PartPose.ZERO);
            // Left Breast Box (width 3, height 3, depth 3) inside breasts
            breasts.addOrReplaceChild("left", CubeListBuilder.create().texOffs(18, 21).addBox(-3.25F, -1.25F, -1.5F, 3, 3, 3, dilation), PartPose.ZERO);
            // Right Breast Box (width 3, height 3, depth 3) inside breasts
            breasts.addOrReplaceChild("right", CubeListBuilder.create().texOffs(21, 21).addBox(0.25F, -1.25F, -1.5F, 3, 3, 3, dilation), PartPose.ZERO);
        }
    }
}
