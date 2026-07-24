// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.entity.ai.Genetics;
import net.conczin.mca.entity.ai.relationship.Gender;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Genetics.class, remap = false)
public abstract class GeneticsMixin implements GeneticsDuck {
    @Shadow public abstract Gender getGender();
    @Shadow public abstract float getGene(Genetics.GeneType type);

    @Unique private float leftBreastSize = -1.0f;
    @Unique private float rightBreastSize = -1.0f;

    @Unique private float leftBreastX = 0.0f;
    @Unique private float leftBreastY = 0.0f;
    @Unique private float leftBreastZ = 0.0f;

    @Unique private float rightBreastX = 0.0f;
    @Unique private float rightBreastY = 0.0f;
    @Unique private float rightBreastZ = 0.0f;

    @Unique private float leftBreastPitch = 0.0f;
    @Unique private float leftBreastYaw = 0.0f;
    @Unique private float leftBreastRoll = 0.0f;

    @Unique private float rightBreastPitch = 0.0f;
    @Unique private float rightBreastYaw = 0.0f;
    @Unique private float rightBreastRoll = 0.0f;

    @Override
    public float getLeftBreastSize() {
        if (this.leftBreastSize < 0.0f) {
            ensureSampledBreastSize();
        }
        return this.leftBreastSize;
    }
    @Override public void setLeftBreastSize(float val) { this.leftBreastSize = val; }

    @Override
    public float getRightBreastSize() {
        if (this.rightBreastSize < 0.0f) {
            ensureSampledBreastSize();
        }
        return this.rightBreastSize;
    }
    @Override public void setRightBreastSize(float val) { this.rightBreastSize = val; }

    @Override public float getLeftBreastX() { return this.leftBreastX; }
    @Override public void setLeftBreastX(float val) { this.leftBreastX = val; }

    @Override public float getLeftBreastY() { return this.leftBreastY; }
    @Override public void setLeftBreastY(float val) { this.leftBreastY = val; }

    @Override public float getLeftBreastZ() { return this.leftBreastZ; }
    @Override public void setLeftBreastZ(float val) { this.leftBreastZ = val; }

    @Override public float getRightBreastX() { return this.rightBreastX; }
    @Override public void setRightBreastX(float val) { this.rightBreastX = val; }

    @Override public float getRightBreastY() { return this.rightBreastY; }
    @Override public void setRightBreastY(float val) { this.rightBreastY = val; }

    @Override public float getRightBreastZ() { return this.rightBreastZ; }
    @Override public void setRightBreastZ(float val) { this.rightBreastZ = val; }

    @Override public float getLeftBreastPitch() { return this.leftBreastPitch; }
    @Override public void setLeftBreastPitch(float val) { this.leftBreastPitch = val; }

    @Override public float getLeftBreastYaw() { return this.leftBreastYaw; }
    @Override public void setLeftBreastYaw(float val) { this.leftBreastYaw = val; }

    @Override public float getLeftBreastRoll() { return this.leftBreastRoll; }
    @Override public void setLeftBreastRoll(float val) { this.leftBreastRoll = val; }

    @Override public float getRightBreastPitch() { return this.rightBreastPitch; }
    @Override public void setRightBreastPitch(float val) { this.rightBreastPitch = val; }

    @Override public float getRightBreastYaw() { return this.rightBreastYaw; }
    @Override public void setRightBreastYaw(float val) { this.rightBreastYaw = val; }

    @Override public float getRightBreastRoll() { return this.rightBreastRoll; }
    @Override public void setRightBreastRoll(float val) { this.rightBreastRoll = val; }

    @Unique
    private void ensureSampledBreastSize() {
        net.minecraft.util.RandomSource random = net.minecraft.util.RandomSource.create();
        if (getGender() == Gender.FEMALE) {
            float scale = MCAInclusiveExpressionsAddon.sampleGraphBreastSize(random);
            this.leftBreastSize = scale;
            this.rightBreastSize = scale;
        } else {
            int chance = MCAInclusiveExpressionsAddon.getFullChestedTraitChance();
            if (chance > 0 && random.nextFloat() * 100.0f < chance) {
                float scale = MCAInclusiveExpressionsAddon.sampleGraphBreastSize(random);
                this.leftBreastSize = scale;
                this.rightBreastSize = scale;
            } else {
                this.leftBreastSize = 0.0f;
                this.rightBreastSize = 0.0f;
            }
        }
    }

    @Inject(method = "randomize", at = @At("TAIL"))
    private void onRandomize(CallbackInfo ci) {
        this.leftBreastSize = -1.0f;
        this.rightBreastSize = -1.0f;
        ensureSampledBreastSize();
    }

    @Inject(method = "getBreastSize", at = @At("HEAD"), cancellable = true)
    private void onGetBreastSize(CallbackInfoReturnable<Float> cir) {
        if (MCAInclusiveExpressionsAddon.isForceAllBreasted()) {
            cir.setReturnValue(1.0f);
            return;
        }

        net.conczin.mca.entity.ai.Genetics self = (net.conczin.mca.entity.ai.Genetics) (Object) this;
        boolean isMale = (self.getGender() == net.conczin.mca.entity.ai.relationship.Gender.MALE);

        if (this.leftBreastSize > 0.0f || this.rightBreastSize > 0.0f) {
            float base = Math.max(this.leftBreastSize, this.rightBreastSize);
            cir.setReturnValue(base);
        } else if (isMale) {
            cir.setReturnValue(0.0f);
        }
    }

    @Inject(method = "writeNbt", at = @At("TAIL"), require = 0)
    private void onWriteNbt(CompoundTag tag, CallbackInfo ci) {
        tag.putFloat("mca_inclusive_expressions:left_breast_size", this.leftBreastSize);
        tag.putFloat("mca_inclusive_expressions:right_breast_size", this.rightBreastSize);

        tag.putFloat("mca_inclusive_expressions:left_breast_x", this.leftBreastX);
        tag.putFloat("mca_inclusive_expressions:left_breast_y", this.leftBreastY);
        tag.putFloat("mca_inclusive_expressions:left_breast_z", this.leftBreastZ);

        tag.putFloat("mca_inclusive_expressions:right_breast_x", this.rightBreastX);
        tag.putFloat("mca_inclusive_expressions:right_breast_y", this.rightBreastY);
        tag.putFloat("mca_inclusive_expressions:right_breast_z", this.rightBreastZ);

        tag.putFloat("mca_inclusive_expressions:left_breast_pitch", this.leftBreastPitch);
        tag.putFloat("mca_inclusive_expressions:left_breast_yaw", this.leftBreastYaw);
        tag.putFloat("mca_inclusive_expressions:left_breast_roll", this.leftBreastRoll);

        tag.putFloat("mca_inclusive_expressions:right_breast_pitch", this.rightBreastPitch);
        tag.putFloat("mca_inclusive_expressions:right_breast_yaw", this.rightBreastYaw);
        tag.putFloat("mca_inclusive_expressions:right_breast_roll", this.rightBreastRoll);
    }

    @Inject(method = "readNbt", at = @At("TAIL"), require = 0)
    private void onReadNbt(CompoundTag tag, CallbackInfo ci) {
        tag.getFloat("mca_inclusive_expressions:left_breast_size").ifPresent(val -> this.leftBreastSize = val);
        tag.getFloat("mca_inclusive_expressions:right_breast_size").ifPresent(val -> this.rightBreastSize = val);

        tag.getFloat("mca_inclusive_expressions:left_breast_x").ifPresent(val -> this.leftBreastX = val);
        tag.getFloat("mca_inclusive_expressions:left_breast_y").ifPresent(val -> this.leftBreastY = val);
        tag.getFloat("mca_inclusive_expressions:left_breast_z").ifPresent(val -> this.leftBreastZ = val);

        tag.getFloat("mca_inclusive_expressions:right_breast_x").ifPresent(val -> this.rightBreastX = val);
        tag.getFloat("mca_inclusive_expressions:right_breast_y").ifPresent(val -> this.rightBreastY = val);
        tag.getFloat("mca_inclusive_expressions:right_breast_z").ifPresent(val -> this.rightBreastZ = val);

        tag.getFloat("mca_inclusive_expressions:left_breast_pitch").ifPresent(val -> this.leftBreastPitch = val);
        tag.getFloat("mca_inclusive_expressions:left_breast_yaw").ifPresent(val -> this.leftBreastYaw = val);
        tag.getFloat("mca_inclusive_expressions:left_breast_roll").ifPresent(val -> this.leftBreastRoll = val);

        tag.getFloat("mca_inclusive_expressions:right_breast_pitch").ifPresent(val -> this.rightBreastPitch = val);
        tag.getFloat("mca_inclusive_expressions:right_breast_yaw").ifPresent(val -> this.rightBreastYaw = val);
        tag.getFloat("mca_inclusive_expressions:right_breast_roll").ifPresent(val -> this.rightBreastRoll = val);
    }

    @Inject(method = "writeToNbt", at = @At("TAIL"), require = 0)
    private void onWriteToNbt(CompoundTag tag, CallbackInfo ci) {
        onWriteNbt(tag, ci);
    }

    @Inject(method = "readFromNbt", at = @At("TAIL"), require = 0)
    private void onReadFromNbt(CompoundTag tag, CallbackInfo ci) {
        onReadNbt(tag, ci);
    }
}
