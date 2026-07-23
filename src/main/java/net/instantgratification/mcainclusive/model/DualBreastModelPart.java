// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * Clean-room implementation of a dual-mesh breast model part for MCA Reborn.
 * Supports customizable cleavage separation angles.
 */
public class DualBreastModelPart {
    public final ModelPart leftBreast;
    public final ModelPart rightBreast;

    private float cleavageAngle = 0.0f; // Default angle

    public DualBreastModelPart(ModelPart leftBreast, ModelPart rightBreast) {
        this.leftBreast = leftBreast;
        this.rightBreast = rightBreast;
    }

    public void setCleavageAngle(float angle) {
        this.cleavageAngle = angle;
        // Apply angle to left and right breast
        this.leftBreast.yRot = -angle;
        this.rightBreast.yRot = angle;
    }

    public float getCleavageAngle() {
        return this.cleavageAngle;
    }

    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        this.leftBreast.render(poseStack, vertexConsumer, light, overlay, color);
        this.rightBreast.render(poseStack, vertexConsumer, light, overlay, color);
    }

    public void setupAnim(float xRot, float yRot, float zRot) {
        this.leftBreast.xRot = xRot;
        this.leftBreast.yRot = yRot - cleavageAngle;
        this.leftBreast.zRot = zRot;

        this.rightBreast.xRot = xRot;
        this.rightBreast.yRot = yRot + cleavageAngle;
        this.rightBreast.zRot = zRot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("left_breast",
            CubeListBuilder.create().texOffs(18, 21).addBox(-3.0F, -1.25F, -1.5F, 3.0F, 3.0F, 3.0F),
            PartPose.offset(0.0F, 5.0F, -1.5F));

        partdefinition.addOrReplaceChild("right_breast",
            CubeListBuilder.create().texOffs(21, 21).addBox(0.0F, -1.25F, -1.5F, 3.0F, 3.0F, 3.0F),
            PartPose.offset(0.0F, 5.0F, -1.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
